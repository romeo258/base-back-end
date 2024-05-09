package tech.springcorps.secureapp.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) { super(message); }
}
