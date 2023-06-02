package server.http.errors;

public class ErrorResponse {
    public boolean error = true;
    public String message;

    public ErrorResponse(String message){
        this.message = message;
    }
}
