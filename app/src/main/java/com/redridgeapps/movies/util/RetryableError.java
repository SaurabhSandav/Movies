package com.redridgeapps.movies.util;

import com.redridgeapps.movies.util.function.Action;

public class RetryableError {

    private Throwable throwable;

    private Action retry;

    public RetryableError(Throwable throwable, Action retry) {
        this.throwable = throwable;
        this.retry = retry;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void retry() {
        retry.execute();
    }
}
