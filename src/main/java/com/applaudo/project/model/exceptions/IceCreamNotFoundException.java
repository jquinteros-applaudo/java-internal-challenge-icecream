package com.applaudo.project.model.exceptions;

import java.text.MessageFormat;

public class IceCreamNotFoundException extends RuntimeException {

    public IceCreamNotFoundException(Long id) {
        super(MessageFormat.format("IceCream {0} not found", id));
    }
}
