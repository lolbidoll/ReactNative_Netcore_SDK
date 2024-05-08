//
//  HanselUtils.m
//  HanselRn
//
//  Created by Rajeev Rajeshuni on 23/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "HanselRnUtils.h"

@implementation HanselRnUtils

+ (void)returnResult:(id)result withCallback:(RCTResponseSenderBlock)callback {
    @try {
        if (callback == nil) {
            NSLog(@"Hansel error: Callback given is nil");
            return;
        }
        id r  = result != nil ? result : [NSNull null];
        callback(@[r]);
    } @catch (NSException *exception) {
        NSLog(@"Hansel error: Exception caught in returnResult method in HanselRnUtils.");
    }
}

@end
