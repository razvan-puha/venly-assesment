package io.venly.demo.exception;

import java.net.URI;

public class ResourceCreatedException extends RuntimeException {

    public ResourceCreatedException(URI uri) {
        super(String.format("Resource created: %s", uri.toASCIIString()));
    }
}