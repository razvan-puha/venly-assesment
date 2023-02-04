package io.venly.demo.exception;

public class NoPathAvailableException extends RuntimeException {

    public NoPathAvailableException(String source, String target) {
        super(String.format("No path between '%s' and '%s'", source, target));
    }
}