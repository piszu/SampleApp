package pl.mariuszpisz.sampleapp.commons.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private final HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();

    private final String message;

    private List<ExceptionResponseDetails> subErrors;

    public ExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ExceptionResponse(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = "Unexpected error " + ex.getLocalizedMessage();
    }

    public ExceptionResponse(HttpStatus status, String message, List<ExceptionResponseDetails> subErrors) {
        this.status = status;
        this.message = message;
        this.subErrors = subErrors;
    }
}