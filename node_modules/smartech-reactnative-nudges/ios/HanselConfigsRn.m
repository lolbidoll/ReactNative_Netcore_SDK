//
//  HanselConfigsRn.m
//  AwesomeProject
//
//  Created by Rajeev Rajeshuni on 22/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "HanselConfigsRn.h"

@implementation HanselConfigsRn

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getString: (NSString* _Nonnull) key withDefaultValue: (NSString* _Nonnull) defaultValue callback:(RCTResponseSenderBlock)callback) {
    @try {
        [HanselRnUtils returnResult:[HanselConfigs getString:key withDefaultValue:defaultValue] withCallback:callback];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in getString method in HanselConfigsRn bridge.");
    }
}

RCT_EXPORT_METHOD(getDouble: (NSString* _Nonnull) key withDefaultValue: (NSNumber* _Nonnull) defaultValue callback:(RCTResponseSenderBlock)callback) {
    @try {
        [HanselRnUtils returnResult:[HanselConfigs getDouble:key withDefaultValue:defaultValue] withCallback:callback];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in getDouble method in HanselConfigsRn bridge.");
    }
}

RCT_EXPORT_METHOD(getBoolean: (NSString* _Nonnull) key withDefaultValue: (BOOL) defaultValue callback:(RCTResponseSenderBlock)callback) {
    @try {
        [HanselRnUtils returnResult:[NSNumber numberWithBool:[HanselConfigs getBool:key withDefaultValue:defaultValue]] withCallback:callback];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in getBool method in HanselConfigsRn bridge.");
    }
}

RCT_EXPORT_METHOD(getJSONObject: (NSString* _Nonnull) key withDefaultValue: (NSDictionary*) defaultValue callback:(RCTResponseSenderBlock)callback) {
    @try {
        [HanselRnUtils returnResult:[HanselConfigs getJSON:key withDefaultValue:defaultValue] withCallback:callback];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in getJSON method in HanselConfigsRn bridge.");
    }
}

RCT_EXPORT_METHOD(getList: (NSString* _Nonnull) key withDefaultValue: (NSArray*) defaultValue callback:(RCTResponseSenderBlock)callback) {
    @try {
        [HanselRnUtils returnResult:[HanselConfigs getList:key withDefaultValue:defaultValue] withCallback:callback];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in getList method in HanselConfigsRn bridge.");
    }
}

RCT_EXPORT_METHOD(getJSONArray: (NSString* _Nonnull) key withDefaultValue: (NSArray*) defaultValue callback:(RCTResponseSenderBlock)callback) {
    @try {
        [HanselRnUtils returnResult:[HanselConfigs getJSONArray:key withDefaultValue:defaultValue] withCallback:callback];
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in getJSONArray method in HanselConfigsRn bridge.");
    }
}

@end

