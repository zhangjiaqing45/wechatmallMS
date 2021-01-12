package com.fante.common.exception;

import java.text.MessageFormat;

/**
 * 业务异常
 *
 * @author fante
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer httpCode;

    private String message;

    public BusinessException(String message)
    {
        super(message);
        this.message = message;
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }

    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(Integer httpCode)
    {
        super();
        this.httpCode = httpCode;
    }

    public BusinessException(Integer httpCode, String message)
    {
        super(message);
        this.httpCode = httpCode;
        this.message = message;
    }

    public Integer getHttpCode()
    {
        return httpCode;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.httpCode, this.message);
    }
}
