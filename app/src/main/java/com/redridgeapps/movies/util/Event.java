package com.redridgeapps.movies.util;

public class Event<T> {

    private T payload;
    private boolean hasBeenHandled = false;

    public Event(T payload) {
        this.payload = payload;
    }

    public boolean hasBeenHandled() {
        return hasBeenHandled;
    }

    public T getPayloadIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return payload;
        }
    }

    public T getPayload() {
        hasBeenHandled = true;
        return payload;
    }
}
