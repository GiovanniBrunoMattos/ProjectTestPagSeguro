package com.br.bank.exception;

import lombok.Generated;

import java.beans.ConstructorProperties;
import java.net.URI;
import java.util.Objects;

public class ExternalServiceException extends RuntimeException {

    private static final long serialVersionUID = 2213587894684779809L;
    private URI url;
    private String msgDetails;

    public ExternalServiceException() {

    }

    public ExternalServiceException(URI url) {
        this();
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalServiceException that = (ExternalServiceException) o;
        return url.equals(that.url) && msgDetails.equals(that.msgDetails);
    }


    @ConstructorProperties({"url", "msgDetails", "externalService"})
    @Generated
    public ExternalServiceException(final URI url, final String msgDetails) {
        this.url = url;
        this.msgDetails = msgDetails;
    }

    public String getMessage() {
        if (Objects.isNull(this.msgDetails)) {
            return "Failed to access an External Service";
        } else {
            return "Failed to access an External Service";
        }
    }
}

