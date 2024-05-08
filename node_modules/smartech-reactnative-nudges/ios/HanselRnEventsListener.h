//
//  HanselRnEventsListener.h
//  HanselRn
//
//  Created by Rajeev Rajeshuni on 23/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <SmartechNudges/Hansel-umbrella.h>
#import "HanselRnUtils.h"

NS_ASSUME_NONNULL_BEGIN

@interface HanselRnEventsListener : NSObject <HanselEventsListener>

+ (instancetype) sharedInstance;

- (void) registerListener;
- (void) deRegisterListener;

@end

NS_ASSUME_NONNULL_END
