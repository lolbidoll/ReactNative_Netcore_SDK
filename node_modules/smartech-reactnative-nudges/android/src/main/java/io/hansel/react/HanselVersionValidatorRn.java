package io.hansel.react;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

//import io.hansel.react.BuildConfig;
import io.hansel.core.logger.HSLLogger;

//import io.hansel.BuildConfig;

public class HanselVersionValidatorRn extends ReactContextBaseJavaModule {

    private boolean versionWarningChecked;

    public HanselVersionValidatorRn(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "HanselVersionValidatorRn";
    }


    @ReactMethod
    public void validateVersion(String currentJSVersion) {
//         if (!versionWarningChecked) {
//             if (!io.hansel.BuildConfig.HANSEL_JS_VERSION.equals(currentJSVersion)) {
// //                HSLLogger.wMin(" Older version of Hansel.js found. Please upgrade to version "
// //                        + io.hansel.BuildConfig.HANSEL_JS_VERSION + " from "
// //                        + BuildConfig.HANSEl_JS_FILE_HELP_DOC_URL
// //                        + " to avoid Runtime Exceptions");
//             }
//             versionWarningChecked = true;
//         }
    }
}
