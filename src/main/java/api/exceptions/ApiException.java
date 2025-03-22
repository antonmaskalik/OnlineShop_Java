package api.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final int statusCode;

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = -1;
    }

}