package com.amatta.scheduler.amatta_back.app.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadRequestAlertException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final String errorKey;
    private final String entityName;
    private final Object errorData;
}
