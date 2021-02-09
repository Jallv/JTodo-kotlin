package com.iflytek.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.os.SystemClock;

import com.tencent.mmkv.MMKV;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * SpUtils, easy to get or put data
 * <ul>
 * <strong>Preference Name</strong>
 * <li>you can change preference name by </li>
 * </ul>
 * <ul>
 * <strong>Put Value</strong>
 * <li>put string {@link #putString(Context, String, String)}</li>
 * <li>put int {@link #putInt(Context, String, int)}</li>
 * <li>put long {@link #putLong(Context, String, long)}</li>
 * <li>put float {@link #putFloat(Context, String, float)}</li>
 * <li>put boolean {@link #putBoolean(Context, String, boolean)}</li>
 * </ul>
 * <ul>
 * <strong>Get Value</strong>
 * <li>get string {@link #getString(Context, String)}, {@link #getString(Context, String, String)}</li>
 * <li>get int {@link #getInt(Context, String)}, {@link #getInt(Context, String, int)}</li>
 * <li>get long {@link #getLong(Context, String)}, {@link #getLong(Context, String, long)}</li>
 * <li>get float {@link #getFloat(Context, String)}, {@link #getFloat(Context, String, float)}</li>
 * <li>get boolean {@link #getBoolean(Context, String)}, {@link #getBoolean(Context, String, boolean)}</li>
 * </ul>
 *
 * @author fengjun
 */
public class SpUtils {
    private static final String TAG = "SpUtils";

    private SpUtils() {
        throw new AssertionError();
    }

    private final static String SP_INIT = "init";
    private final static Map<String, MMKV> mMMKVCache = new HashMap<>();

    public static void init(Context context) {
        MMKV.initialize(context);
    }

    private static synchronized MMKV getMMKV(Context context, String name) {
        long start = SystemClock.elapsedRealtime();
        MMKV mmkv;
        String initKey = String.format("%s_%s", name, SP_INIT);
        mmkv = mMMKVCache.get(name);
        if (mmkv != null) {
            return mmkv;
        }
        mmkv = MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE);
        boolean initialize = mmkv.decodeBool(initKey);
        mMMKVCache.put(name, mmkv);
        if (initialize) {
            return mmkv;
        }
        // 迁移旧数据
        SharedPreferences old = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        if (old != null) {
            mmkv.importFromSharedPreferences(old);
            old.edit().clear().apply();
        }
        mmkv.encode(initKey, true);
        return mmkv;
    }

    public static boolean encode(Context context, String name, String key, Object object) {
        if (object == null) {
            return false;
        }
        MMKV mmkv = getMMKV(context, name);
        if (object instanceof String) {
            return mmkv.encode(key, (String) object);
        } else if (object instanceof Integer) {
            return mmkv.encode(key, (Integer) object);
        } else if (object instanceof Boolean) {
            return mmkv.encode(key, (Boolean) object);
        } else if (object instanceof Float) {
            return mmkv.encode(key, (Float) object);
        } else if (object instanceof Long) {
            return mmkv.encode(key, (Long) object);
        } else if (object instanceof Double) {
            return mmkv.encode(key, (Double) object);
        } else if (object instanceof byte[]) {
            return mmkv.encode(key, (byte[]) object);
        } else if (object instanceof Parcelable) {
            return mmkv.encode(key, (Parcelable) object);
        } else {
            return mmkv.encode(key, object.toString());
        }
    }

    public static void encodeSet(Context context, String name, String key, Set<String> sets) {
        MMKV mmkv = getMMKV(context, name);
        mmkv.encode(key, sets);
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Integer decodeInt(Context context, String name, String key, int defaultValue) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeInt(key, defaultValue);
    }

    public static Double decodeDouble(Context context, String name, String key, double defaultValue) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeDouble(key, defaultValue);
    }

    public static Long decodeLong(Context context, String name, String key, long defaultValue) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeLong(key, defaultValue);
    }

    public static Boolean decodeBoolean(Context context, String name, String key, boolean defaultValue) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeBool(key, defaultValue);
    }

    public static Float decodeFloat(Context context, String name, String key, float defaultValue) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeFloat(key, defaultValue);
    }

    public static byte[] decodeBytes(Context context, String name, String key) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeBytes(key);
    }

    public static String decodeString(Context context, String name, String key, String defaultValue) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeString(key, defaultValue);
    }

    public static Set<String> decodeStringSet(Context context, String name, String key) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeStringSet(key, Collections.<String>emptySet());
    }

    public static Parcelable decodeParcelable(Context context, String name, String key, Parcelable defaultValue) {
        MMKV mmkv = getMMKV(context, name);
        return mmkv.decodeParcelable(key, null);
    }

    /**
     * 移除某个key对
     *
     * @param key
     */
    public static void removeKey(Context context, String name, String key) {
        MMKV mmkv = getMMKV(context, name);
        mmkv.removeValueForKey(key);
    }

    /**
     * 清除所有key
     */
    public static void clearAll(Context context, String name) {
        MMKV mmkv = getMMKV(context, name);
        mmkv.clearAll();
    }

    /**
     * put string preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putString(Context context, String key, String value) {
        return putString(context, context.getPackageName(), key, value);
    }

    /**
     * put string preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putString(Context context, String name, String key, String value) {
        return encode(context, name, key, value);
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws ClassCastException if there is a preference with this
     * name that is not a string
     * @see #getString(Context, String, String)
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a string
     */
    public static String getString(Context context, String key, String defaultValue) {
        return getString(context, context.getPackageName(), key, defaultValue);
    }


    /**
     * get string preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a string
     */
    public static String getString(Context context, String name, String key, String defaultValue) {
        return decodeString(context, name, key, defaultValue);
    }

    /**
     * put int preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putInt(Context context, String key, int value) {
        return putInt(context, context.getPackageName(), key, value);
    }

    /**
     * put int preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putInt(Context context, String name, String key, int value) {
        return encode(context, name, key, value);
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a int
     * @see #getInt(Context, String, int)
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a int
     */
    public static int getInt(Context context, String key, int defaultValue) {
        return getInt(context, context.getPackageName(), key, defaultValue);
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a int
     */
    public static int getInt(Context context, String name, String key, int defaultValue) {
        return decodeInt(context, name, key, defaultValue);
    }

    /**
     * put long preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putLong(Context context, String key, long value) {
        return putLong(context, context.getPackageName(), key, value);
    }

    /**
     * put long preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putLong(Context context, String name, String key, long value) {
        return encode(context, name, key, value);
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a long
     * @see #getLong(Context, String, long)
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a long
     */
    public static long getLong(Context context, String key, long defaultValue) {
        return getLong(context, context.getPackageName(), key, -1);
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a long
     */
    public static long getLong(Context context, String name, String key, long defaultValue) {
        return decodeLong(context, name, key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putFloat(Context context, String key, float value) {
        return putFloat(context, context.getPackageName(), key, value);
    }

    /**
     * put float preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putFloat(Context context, String name, String key, float value) {
        return encode(context, name, key, value);
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a float
     * @see #getFloat(Context, String, float)
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a float
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        return getFloat(context, context.getPackageName(), key, defaultValue);
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a float
     */
    public static float getFloat(Context context, String name, String key, float defaultValue) {
        return decodeFloat(context, name, key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        return putBoolean(context, context.getPackageName(), key, value);
    }

    /**
     * put boolean preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putBoolean(Context context, String name, String key, boolean value) {
        return encode(context, name, key, value);
    }

    /**
     * get boolean preferences, default is false
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws ClassCastException if there is a preference with this
     * name that is not a boolean
     * @see #getBoolean(Context, String, boolean)
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * get boolean preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a boolean
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getBoolean(context, context.getPackageName(), key, defaultValue);
    }

    /**
     * get boolean preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a boolean
     */
    public static boolean getBoolean(Context context, String name, String key, boolean defaultValue) {
        return decodeBoolean(context, name, key, defaultValue);
    }
}
