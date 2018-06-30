package com.redridgeapps.movies.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {

    private static MainThreadExecutor mainThreadExecutor;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public static MainThreadExecutor getExecutor() {
        if (mainThreadExecutor != null)
            return mainThreadExecutor;

        mainThreadExecutor = new MainThreadExecutor();
        return mainThreadExecutor;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mHandler.post(command);
    }

}