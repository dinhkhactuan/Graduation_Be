package com.Graduation_Be.exception.exceptionOption;

import com.Graduation_Be.shard.enums.MessageSys;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(MessageSys message) {
        super(String.valueOf(message));
    }
}
