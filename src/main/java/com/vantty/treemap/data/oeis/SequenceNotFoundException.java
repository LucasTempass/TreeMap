package com.vantty.treemap.data.oeis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SequenceNotFoundException extends RuntimeException {
    
    public SequenceNotFoundException(String sequenceId) {
        super("Sequence with id" + sequenceId + "was not found");
    }
    
}
