package io.hansel.react;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import org.json.JSONArray;
import org.json.JSONObject;

import io.hansel.actions.configs.HanselConfigs;
import io.hansel.react.HSLReactConversionUtil;

/**
 * Created by avin on 20/09/18.
 */

public class HanselConfigsRn extends ReactContextBaseJavaModule {

    public HanselConfigsRn(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "HanselConfigsRn";
    }

    @ReactMethod
    public void getString(String configName, String fallbackValue, Callback callback) {
        String value = HanselConfigs.getString(configName, fallbackValue);
        if (callback != null) {
            callback.invoke(value);
        }
    }

    @ReactMethod
    public void getBoolean(String configName, boolean fallbackValue, Callback callback) {
        boolean value = HanselConfigs.getBoolean(configName, fallbackValue);
        if (callback != null) {
            callback.invoke(value);
        }
    }

    @ReactMethod
    public void getDouble(String configName, double fallbackValue, Callback callback) {
        double value = HanselConfigs.getDouble(configName, fallbackValue);
        if (callback != null) {
            callback.invoke(value);
        }
    }

    @ReactMethod
    public void getJSONArray(String configName, ReadableArray defaultValue, Callback callback) {
        JSONArray value = HanselConfigs.getJSONArray(configName, HSLReactConversionUtil.toJSONArray(defaultValue));
        if (callback != null) {
            callback.invoke(HSLReactConversionUtil.toWritableArray(value));
        }
    }

    @ReactMethod
    public void getJSONObject(String configName, ReadableMap defaultValue, Callback callback) {
        JSONObject value = HanselConfigs.getJSONObject(configName, HSLReactConversionUtil.toJsonObject(defaultValue));
        if (callback != null) {
            callback.invoke(HSLReactConversionUtil.toWritableMap(value));
        }
    }

}
