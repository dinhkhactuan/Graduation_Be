package com.Graduation_Be.api;

import com.Graduation_Be.shard.enums.MessageSys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ApiResponse<T> {
    private int code;

    private MessageSys message;

    private T Data;
}
