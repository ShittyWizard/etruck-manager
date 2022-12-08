package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TruckOverloadException extends Exception {
    public TruckOverloadException(String message) {
        super(message);
    }
}
