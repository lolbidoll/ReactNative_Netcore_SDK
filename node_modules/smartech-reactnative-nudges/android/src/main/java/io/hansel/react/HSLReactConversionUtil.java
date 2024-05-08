package io.hansel.react;


import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.hansel.core.logger.HSLLogger;
import io.hansel.core.logger.LogGroup;

/**
 * Created by avin on 20/09/18.
 */

public final class HSLReactConversionUtil {

    /**
     * toObject extracts a value from a {@link ReadableMap} by its key,
     * and returns a POJO representing that object.
     *
     * @param readableMap The Map to containing the value to be converted
     * @param key         The key for the value to be converted
     * @return The converted POJO
     */
    public static Object toObject(ReadableMap readableMap, String key) {
        if (readableMap == null) {
            return null;
        }

        Object result = null;

        ReadableType readableType = readableMap.getType(key);
        switch (readableType) {
            case Null:
                result = key;
                break;
            case Boolean:
                result = readableMap.getBoolean(key);
                break;
            case Number:
                // Can be int or double.
                double tmp = readableMap.getDouble(key);
                result = tmp == (int) tmp ? (int) tmp : tmp;
                break;
            case String:
                result = readableMap.getString(key);
                break;
            case Map:
                result = toMap(readableMap.getMap(key));
                break;
            case Array:
                result = toList(readableMap.getArray(key));
                break;
            default:
                HSLLogger.w("Could not convert react object with key: " + key, LogGroup.RC);
                break;
        }

        if (result == null) {
            HSLLogger.d("Key = " + key + " : " + result);
        } else {
            HSLLogger.d("Key = " + key + " : " + result);
        }

        return result;
    }

    /**
     * toMap converts a {@link ReadableMap} into a HashMap.
     *
     * @param readableMap The ReadableMap to be converted.
     * @return A HashMap containing the data that was in the ReadableMap.
     */
    public static Map<String, Object> toMap(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }

        com.facebook.react.bridge.ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        if (!iterator.hasNextKey()) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            result.put(key, toObject(readableMap, key));
        }

        return result;
    }

    public static HashMap<String, Object> toHashMap(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }

        com.facebook.react.bridge.ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        if (!iterator.hasNextKey()) {
            return null;
        }

        HashMap<String, Object> result = new HashMap<>();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            ReadableType readableType = readableMap.getType(key);
            switch (readableType) {
                case Null:
                    result.put(key, null);
                    break;
                case Boolean:
                    result.put(key, readableMap.getBoolean(key));
                    break;
                case Number:

                    // Can be int or double.
                    double tmp = readableMap.getDouble(key);
                    if (tmp == (int) tmp) {
                        result.put(key, (int) tmp);
                    } else {
                        result.put(key, tmp);
                    }
                    break;
                case String:
                    result.put(key, readableMap.getString(key));
                    break;
                default:
                    HSLLogger.w("Could not convert object with key: " + key, LogGroup.RC);
                    break;
            }
        }

        return result;
    }

    /**
     * toList converts a {@link ReadableArray} into an ArrayList.
     *
     * @param readableArray The ReadableArray to be conveted.
     * @return An ArrayList containing the data that was in the ReadableArray.
     */
    public static List<Object> toList(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }

        List<Object> result = new ArrayList<>(readableArray.size());
        int n = readableArray.size();
        for (int index = 0; index < n; index++) {
            ReadableType readableType = readableArray.getType(index);
            switch (readableType) {
                case Null:
                    if (result != null) result.add(null);
                    break;
                case Boolean:
                    if (result != null) result.add(readableArray.getBoolean(index));
                    break;
                case Number:
                    // Can be int or double.
                    double tmp = readableArray.getDouble(index);
                    if (result != null) {
                        if (tmp == (int) tmp) {
                            result.add((int) tmp);
                        } else {
                            result.add(tmp);
                        }
                    }
                    break;
                case String:
                    if (result != null) result.add(readableArray.getString(index));
                    break;
                case Map:
                    if (result != null) result.add(toMap(readableArray.getMap(index)));
                    break;
                case Array:
                    result = toList(readableArray.getArray(index));
                    break;
                default:
                    HSLLogger.w("Could not convert object with index: " + index, LogGroup.RC);
                    break;
            }
        }

        return result;
    }

    /**
     * toList converts a {@link ReadableArray} into an ArrayList.
     *
     * @param readableArray The ReadableArray to be conveted.
     * @return An ArrayList containing the data that was in the ReadableArray.
     */
    public static ArrayList<String> toArrayList(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }

        ArrayList<String> result = new ArrayList<>(readableArray.size());
        int n = readableArray.size();
        for (int index = 0; index < n; index++) {
            ReadableType readableType = readableArray.getType(index);
            switch (readableType) {
                case String:
                    result.add(readableArray.getString(index));
                    break;
            }
        }

        return result;
    }

    /**
     * toList converts a {@link ReadableArray} into an ArrayList.
     *
     * @param readableArray The ReadableArray to be conveted.
     * @return An ArrayList containing the data that was in the ReadableArray.
     */
    public static String[] toStringArray(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }

        String[] result = new String[readableArray.size()];
        int n = readableArray.size();
        for (int index = 0; index < n; index++) {
            ReadableType readableType = readableArray.getType(index);
            switch (readableType) {
                case String:
                    result[index] = readableArray.getString(index);
                    break;
            }
        }

        return result;
    }

    public static Object[] toArray(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }

        int n = readableArray.size();
        Object[] result = new Object[n];
        for (int index = 0; index < n; index++) {
            ReadableType readableType = readableArray.getType(index);
            switch (readableType) {
                case Null:
                    result[index] = null;
                    break;
                case Boolean:
                    result[index] = readableArray.getBoolean(index);
                    break;
                case Number:

                    double tmp = readableArray.getDouble(index);
                    if (tmp == (int) tmp) {
                        result[index] = (int) tmp;
                    } else {
                        result[index] = tmp;
                    }
                    break;
                case String:
                    result[index] = readableArray.getString(index);
                    break;
                case Map:
                    result[index] = readableArray.getMap(index);
                    break;
                case Array:
                    result[index] = readableArray.getArray(index);
                    break;
                default:
                    HSLLogger.w("Could not convert object with index: " + index, LogGroup.RC);
                    break;
            }
        }

        return result;
    }

    public static WritableArray toWritableArray(String[] array) {
        if (array == null) {
            return null;
        }

        WritableArray result = Arguments.createArray();
        int n = array.length;
        for (int index = 0; index < n; index++) {
            result.pushString(array[index]);
        }

        return result;
    }

    public static WritableArray toWritableArray(ArrayList<String> list) {
        if (list == null) {
            return null;
        }

        WritableArray result = Arguments.createArray();
        int n = list.size();
        for (int index = 0; index < n; index++) {
            result.pushString(list.get(index));
        }

        return result;
    }

    public static JSONObject toJsonObject(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }

        JSONObject jsonObject = new JSONObject();

        ReadableMapKeySetIterator dimensionsIterator = readableMap.keySetIterator();

        while (dimensionsIterator.hasNextKey()) {
            String key = dimensionsIterator.nextKey();

            if (key == null) {
                HSLLogger.d("Key is NULL");
                continue;
            }

            Object result = null;

            ReadableType readableType = readableMap.getType(key);
            try {
                switch (readableType) {
//                case Null:
//                    jsonObject.put(key, )
//                    break;
                    case Boolean:
                        result = readableMap.getBoolean(key);
                        break;
                    case Number:
                        // Can be int or double.
                        double tmp = readableMap.getDouble(key);
                        result = tmp == (int) tmp ? (int) tmp : tmp;
                        break;
                    case String:
                        result = readableMap.getString(key);
                        break;
                    case Map:
//                        result = toMap(readableMap.getMap(key));
                        result = new JSONObject(toMap(readableMap.getMap(key)));
                        break;
                    case Array:
                        result = toList(readableMap.getArray(key));
                        break;
                    default:
                        HSLLogger.w("Could not convert object with key: " + key, LogGroup.RC);
                        break;
                }

                if (result != null) {
                    jsonObject.put(key, result);
                }
                HSLLogger.d("Key = " + key + " : " + result);
            } catch (JSONException e) {
                HSLLogger.printStackTrace(e, "Key = " + key + " : JSONException", LogGroup.RC);
            }
        }

        return jsonObject;
    }

    public static WritableMap toWritableMap(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        WritableMap writableMap = Arguments.createMap();

        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();

            if (key == null) {
                HSLLogger.d("Key is NULL");
                continue;
            }

            Object value = jsonObject.opt(key);
            try {
                if (value instanceof Double || value instanceof Integer || value instanceof Float || value instanceof Long) {
                    writableMap.putDouble(key, Double.parseDouble(value + ""));
                } else if (value instanceof Boolean) {
                    writableMap.putBoolean(key, (Boolean) value);
                } else if (value instanceof String) {
                    writableMap.putString(key, (String) value);
                } else if (value instanceof JSONObject) {
                    writableMap.putMap(key, toWritableMap((JSONObject) value));
                } else if (value instanceof JSONArray) {
                    writableMap.putArray(key, toWritableArray((JSONArray) value));
                }

            } catch (Throwable e) {
                HSLLogger.printStackTrace(e, "Key = " + key + " : JSONException", LogGroup.RC);
            }
        }

        return writableMap;
    }

    public static WritableMap toWritableMap(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }

        WritableMap writableMap = Arguments.createMap();

        ReadableMapKeySetIterator dimensionsIterator = readableMap.keySetIterator();

        while (dimensionsIterator.hasNextKey()) {
            String key = dimensionsIterator.nextKey();

            if (key == null) {
                HSLLogger.d("Key is NULL");
                continue;
            }

            ReadableType readableType = readableMap.getType(key);
            switch (readableType) {
                case Null:
                    writableMap.putNull(key);
                    break;
                case Boolean:
                    writableMap.putBoolean(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    // Can be int or double.
                    double tmp = readableMap.getDouble(key);
                    if (tmp == (int) tmp) {
                        writableMap.putInt(key, (int) tmp);
                    } else {
                        writableMap.putDouble(key, tmp);
                    }
                    break;
                case String:
                    writableMap.putString(key, readableMap.getString(key));
                    break;
                case Map:
                    writableMap.putMap(key, toWritableMap(readableMap.getMap(key)));
                    break;
                case Array:
                    writableMap.putArray(key, toWritableArray(readableMap.getArray(key)));
                    break;
                default:
                    HSLLogger.w("Could not convert object with key: " + key, LogGroup.RC);
                    break;
            }

        }

        return writableMap;
    }

    public static WritableMap toWritableMap(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return null;
        }

        WritableMap writableMap = Arguments.createMap();

        Set<String> set = hashMap.keySet();
        ArrayList<String> list = new ArrayList<>(set);
        int n = list.size();
        for (int i = 0; i < n; i++) {
            String key = list.get(i);
            if (key == null) {
                continue;
            }

            writableMap.putString(key, hashMap.get(key));
        }

        return writableMap;
    }

    public static WritableArray toWritableArray(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }

        WritableArray writableArray = Arguments.createArray();
        int n = jsonArray.length();
        for (int i = 0; i < n; i++) {

            Object value = jsonArray.opt(i);
            try {
                if (value instanceof Double || value instanceof Integer || value instanceof Float || value instanceof Long) {
                    writableArray.pushDouble(Double.parseDouble(value + ""));
                } else if (value instanceof Boolean) {
                    writableArray.pushBoolean((Boolean) value);
                } else if (value instanceof String) {
                    writableArray.pushString((String) value);
                } else if (value instanceof JSONObject) {
                    writableArray.pushMap(toWritableMap((JSONObject) value));
                } else if (value instanceof JSONArray) {
                    writableArray.pushArray(toWritableArray((JSONArray) value));
                }

            } catch (Throwable e) {
                HSLLogger.printStackTrace(e);
            }
        }

        return writableArray;
    }

    public static WritableArray toWritableArray(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }

        WritableArray writableArray = Arguments.createArray();
        int n = readableArray.size();
        for (int i = 0; i < n; i++) {

            ReadableType readableType = readableArray.getType(i);

            switch (readableType) {
                case Null:
                    writableArray.pushNull();
                    break;
                case Boolean:
                    writableArray.pushBoolean(readableArray.getBoolean(i));
                    break;
                case Number:
                    // Can be int or double.
                    double tmp = readableArray.getDouble(i);
                    if (tmp == (int) tmp) {
                        writableArray.pushInt((int) tmp);
                    } else {
                        writableArray.pushDouble(tmp);
                    }
                    break;
                case String:
                    writableArray.pushString(readableArray.getString(i));
                    break;
                case Map:
                    writableArray.pushMap(toWritableMap(readableArray.getMap(i)));
                    break;
                case Array:
                    writableArray.pushArray(toWritableArray(readableArray.getArray(i)));
                    break;
                default:
                    HSLLogger.w("Could not convert object with index: " + i, LogGroup.RC);
                    break;
            }

        }

        return writableArray;
    }

    public static JSONArray toJSONArray(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }

        JSONArray jsonArray = new JSONArray();
        int n = readableArray.size();
        for (int index = 0; index < n; index++) {
            ReadableType readableType = readableArray.getType(index);

            try {
                switch (readableType) {
                    case Null:
//                    result.add(String.valueOf(index));
//                    jsonArray.put(String.valueOf())
                        break;
                    case Boolean:
//                    result.add(readableArray.getBoolean(index));
                        jsonArray.put(readableArray.getBoolean(index));
                        break;
                    case Number:
                        // Can be int or double.
                        double tmp = readableArray.getDouble(index);
                        if (tmp == (int) tmp) {
//                        result.add((int) tmp);
                            jsonArray.put((int) tmp);
                        } else {
//                        result.add(tmp);
                            jsonArray.put(tmp);
                        }
                        break;
                    case String:
//                        result.add(readableArray.getString(index));
                        jsonArray.put(readableArray.getString(index));
                        break;
                    case Map:
//                        result.add(toMap(readableArray.getMap(index)));
                        jsonArray.put(new JSONObject(toMap(readableArray.getMap(index))));
                        break;
                    case Array:
//                        result = toList(readableArray.getArray(index));
                        jsonArray.put(toJSONArray(readableArray.getArray(index)));
                        break;
                    default:
                        HSLLogger.w("Could not convert object with index: " + index, LogGroup.RC);
                        break;
                }
            } catch (JSONException e) {
                HSLLogger.printStackTrace(e, "JsonException toJSONArray", LogGroup.RC);
            }
        }

        return jsonArray;
    }


}