package com.Graduation_Be.exception.exceptionOption;

import com.Graduation_Be.shard.enums.MessageSys;

public class InternalServerException extends RuntimeException {
    public InternalServerException(MessageSys message) {
        super(String.valueOf(message));
    }
}