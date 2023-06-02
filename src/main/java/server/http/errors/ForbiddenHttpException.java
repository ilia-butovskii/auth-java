package server.http.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ForbiddenHttpException extends ResponseEntity<ErrorResponse>{
    public ForbiddenHttpException(String message) {
        super(new ErrorResponse(message), HttpStatus.FORBIDDEN);
    }


}
