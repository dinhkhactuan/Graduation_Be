package com.Graduation_Be.exception.exceptionOption;

import com.Graduation_Be.shard.enums.MessageSys;

public class BadGatewayException extends RuntimeException {
    public BadGatewayException(MessageSys message) {
        super(String.valueOf(message));
    }
}