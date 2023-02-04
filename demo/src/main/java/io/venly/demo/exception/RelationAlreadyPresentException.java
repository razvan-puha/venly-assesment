package io.venly.demo.exception;

public class RelationAlreadyPresentException extends RuntimeException {

    public RelationAlreadyPresentException(String wordOne, String wordTwo) {
        super(String.format("Relation already present betwwen '%s' and '%s'", wordOne, wordTwo));
    }
}