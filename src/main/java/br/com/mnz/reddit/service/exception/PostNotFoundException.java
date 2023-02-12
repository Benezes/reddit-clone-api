package br.com.mnz.reddit.service.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException() { }

    public PostNotFoundException(String message) {
        super(message);
    }
}
