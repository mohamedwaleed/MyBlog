package my.blog.common.exceptions;

import my.blog.common.exceptions.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;


@ControllerAdvice
public class RestErrorHandler {

    public static ErrorResponseDTO createErrorDTO(String status, String clientMsg, String developerMsg) {
        return new ErrorResponseDTO(status, clientMsg, developerMsg);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleIOException(IOException ex) {
        return createErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getCause().getMessage(), ex.getMessage());
    }

}
