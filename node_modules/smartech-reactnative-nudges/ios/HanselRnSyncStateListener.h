//
//  HanselRnSyncStateListener.h
//  HanselRn
//
//  Created by Rajeev Rajeshuni on 23/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <SmartechNudges/Hansel-umbrella.h>
#import "HanselRnUtils.h"

NS_ASSUME_NONNULL_BEGIN

@interface HanselRnSyncStateListener : NSObject <HanselSyncStateListener>
+ (instancetype) sharedInstance;
- (void) stopSyncEvents;
- (void) startSyncEvents;
@end

NS_ASSUME_NONNULL_END
