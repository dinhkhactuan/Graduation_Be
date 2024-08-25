package com.Graduation_Be.exception.exceptionOption;

import com.Graduation_Be.shard.enums.MessageSys;

public class BadRequestException extends RuntimeException {
    public BadRequestException(MessageSys message) {
        super(String.valueOf(message));
    }
}