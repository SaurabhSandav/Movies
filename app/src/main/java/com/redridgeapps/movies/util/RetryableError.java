package com.redridgeapps.movies.util;

import com.redridgeapps.movies.util.function.Action;

public class RetryableError extends Throwable {

    private Action retry;

    public RetryableError(Throwable throwable, Action retry) {
        super(throwable);
        this.retry = retry;
    }

    public void retry() {
        retry.execute();
    }
}
