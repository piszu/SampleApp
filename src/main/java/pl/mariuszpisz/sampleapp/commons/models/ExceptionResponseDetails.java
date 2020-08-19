package pl.mariuszpisz.sampleapp.commons.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExceptionResponseDetails {

    private String object;

    private String message;

    public ExceptionResponseDetails(String object, String message) {
        this.object = object;
        this.message = message;
    }
}