package io.hansel.react;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by avin on 24/09/18.
 */

public class HanselLogsRn extends ReactContextBaseJavaModule {

    public HanselLogsRn(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "HanselLogsRn";
    }

    @ReactMethod
    public void e(String tag, String message) {
        Log.e(tag, message);
    }
}
