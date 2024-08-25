package com.Graduation_Be.Api;

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

    private  String message;

    private T Data;
}
