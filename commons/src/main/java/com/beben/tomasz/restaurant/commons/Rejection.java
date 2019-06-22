package com.beben.tomasz.restaurant.commons;

import lombok.NonNull;
import lombok.Value;

@Value
public class Rejection {

    @Value
    static class Reason {
        @NonNull
        String reason;

        public String getReason() {
            return reason;
        }
    }

    @NonNull
    Reason reason;

    public String getReasonMessage() {
        return reason.reason;
    }

    public static Rejection withReason(String reason) {
        return new Rejection(new Reason(reason));
    }
}

