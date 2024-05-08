//
//  HanselRnSyncStateListener.m
//  HanselRn
//
//  Created by Rajeev Rajeshuni on 23/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "HanselRnSyncStateListener.h"



@implementation HanselRnSyncStateListener
{
    BOOL hasListener;
}

+ (instancetype) sharedInstance {
    static dispatch_once_t onceToken;
    static HanselRnSyncStateListener* manager;
    dispatch_once(&onceToken, ^{
        manager = [[HanselRnSyncStateListener alloc] init];
    });
    return manager;
}


- (void)onHanselSynced:(BOOL)state {
    @try {
        if(hasListener) {
            [[NSNotificationCenter defaultCenter] postNotificationName:FIRE_EVENT_NOTIFICATION object:nil userInfo:@{NOTIFICATION_EVENT_NAME:@"HanselRequestSynced",NOTIFICATION_BODY:@{@"request": @"configs",@"state":[NSNumber numberWithBool:state]}}];
        }
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught while sending event for request sync from react native bridge.");
    }
}

- (void) stopSyncEvents {
    @try {
        hasListener = NO;
        [Hansel removeHanselSyncStateListenerForRequest:Configs];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in stopSyncEvents method in HanselRnSyncStateListener.");
    }
}

- (void) startSyncEvents {
    @try {
        hasListener = YES;
        [Hansel setHanselSyncStateListener:self forRequest:Configs];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in startSyncEvents method in HanselRnSyncStateListener.");
    }
}


@end
