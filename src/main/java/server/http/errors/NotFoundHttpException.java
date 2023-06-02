package server.http.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotFoundHttpException extends ResponseEntity<ErrorResponse> {
    public NotFoundHttpException(String message) {
        super(new ErrorResponse(message), HttpStatus.NOT_FOUND);
    }
}
