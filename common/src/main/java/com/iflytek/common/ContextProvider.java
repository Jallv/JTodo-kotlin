package com.iflytek.common;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * @author aljiang
 */
public class ContextProvider {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void init(Context context) {
        ContextProvider.context = context;
    }

    public static Context getContext() {
        return context;
    }
}
