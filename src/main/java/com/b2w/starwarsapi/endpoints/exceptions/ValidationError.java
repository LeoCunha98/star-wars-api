package com.b2w.starwarsapi.endpoints.exceptions;


import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(String title, int status, String detail, long timeStamp, String developerMsg) {
        super(title, status, detail, timeStamp, developerMsg);
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }

    public List<FieldMessage> getErrors(){
        return errors;
    }
}
