package com.ljn.demo.exception;

import com.ljn.demo.response_bean.me.REnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoException extends RuntimeException {
    private REnum rEnum;
}
