//
//  HanselUserRn.m
//  AwesomeProject
//
//  Created by Rajeev Rajeshuni on 22/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "HanselUserRn.h"

@implementation HanselUserRn

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(putStringAttribute: (NSString*) key attribute: (NSString*) attribute) {
    @try {
        [[Hansel getUser] putStringAttribute:attribute forKey:key];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in putStringAttributeForKey method in HanselUserRn Bridge.");
    }
}

RCT_EXPORT_METHOD(putDoubleAttribute: (NSString*) key attribute: (double) attribute) {
    @try {
        [[Hansel getUser] putDoubleAttribute:attribute forKey:key];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in putDoubleAttributeForKey method in HanselUserRn Bridge.");
    }
}

RCT_EXPORT_METHOD(putBooleanAttribute: (NSString*) key attribute: (BOOL) attribute) {
    @try {
        [[Hansel getUser] putBoolAttribute:attribute forKey:key];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in putBoolAttributeForKey method in HanselUserRn Bridge.");
    }
}

RCT_EXPORT_METHOD(setUserId: (NSString*) userId) {
    @try {
        [[Hansel getUser] setUserId:userId];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in setUserId method in HanselUserRn Bridge.");
    }
}

RCT_EXPORT_METHOD(clear) {
    @try {
        [[Hansel getUser] clear];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in clear method in HanselUserRn Bridge.");
    }
}



@end


