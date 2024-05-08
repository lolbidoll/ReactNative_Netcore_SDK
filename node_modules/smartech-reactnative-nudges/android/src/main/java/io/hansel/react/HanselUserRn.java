package io.hansel.react;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import io.hansel.hanselsdk.Hansel;

/**
 * Created by avin on 21/09/18.
 */

public class HanselUserRn extends ReactContextBaseJavaModule {

    public HanselUserRn(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public static void putStringAttribute(String key, String value) {
        Hansel.getUser().putAttribute(key, value);
    }

    @ReactMethod
    public static void putDoubleAttribute(String key, double value) {
        Hansel.getUser().putAttribute(key, value);
    }

    @ReactMethod
    public static void putBooleanAttribute(String key, boolean value) {
        Hansel.getUser().putAttribute(key, value);
    }

    @ReactMethod
    public static void putPrivateStringAttribute(String key, String value) {
        Hansel.getUser().putPrivateAttribute(key, value);
    }

    @ReactMethod
    public static void putPrivateDoubleAttribute(String key, double value) {
        Hansel.getUser().putPrivateAttribute(key, value);
    }

    @ReactMethod
    public static void putPrivateBooleanAttribute(String key, boolean value) {
        Hansel.getUser().putPrivateAttribute(key, value);
    }

    @ReactMethod
    public static void setUserId(String userId) {
        Hansel.getUser().setUserId(userId);
    }

    @ReactMethod
    public static void clearAttribute(String key) {
        Hansel.getUser().clearAttribute(key);
    }

    @ReactMethod
    public static void clear() {
        Hansel.getUser().clear();
    }

    @Override
    public String getName() {
        return "HanselUserRn";
    }
}
