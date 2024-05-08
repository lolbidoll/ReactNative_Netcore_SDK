//
//  HanselRnEventsListener.m
//  HanselRn
//
//  Created by Rajeev Rajeshuni on 23/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "HanselRnEventsListener.h"

@implementation HanselRnEventsListener
{
    bool hasListener;
}

- (NSArray<NSString *> *)supportedEvents
{
    return @[@"HanselInternalEvent"];
}

+ (instancetype) sharedInstance {
    static HanselRnEventsListener* manager;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        manager = [[HanselRnEventsListener alloc] init];
    });
    return manager;
}


- (void)fireHanselEventwithName:(NSString * _Nonnull)eventName andProperties:(NSDictionary * _Nullable)properties {
    @try {
        if(hasListener) {
            [[NSNotificationCenter defaultCenter] postNotificationName:FIRE_EVENT_NOTIFICATION object:nil userInfo:@{NOTIFICATION_EVENT_NAME:@"HanselInternalEvent",NOTIFICATION_BODY:@{@"eventName": eventName,@"properties": properties != nil ? properties: [NSNull null]}}];
        }
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught while sending event for internal event from react native bridge.");
    }
}

- (void) registerListener {
    @try {
        hasListener = YES;
        [HanselTracker registerListener:self];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in registerListener method in HanselRnEventsListener class.");
    }
}

- (void) deRegisterListener {
    @try {
        hasListener = NO;
        [HanselTracker deRegisterListener];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in deRegisterListener method in HanselRnEventsListener class.");
    }
}

@end
