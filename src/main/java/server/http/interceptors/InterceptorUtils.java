package server.http.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import server.http.errors.ErrorResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InterceptorUtils {
    public static void sendError(HttpServletResponse response, ErrorResponse errorResponse, HttpStatus status){
        ObjectMapper mapper = new ObjectMapper();

        response.setStatus(status.value());
        response.setContentType("application/json");

        try {
            response.getWriter().write(mapper.writeValueAsString(errorResponse));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
