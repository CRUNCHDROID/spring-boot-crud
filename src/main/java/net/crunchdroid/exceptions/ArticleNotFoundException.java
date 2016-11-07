package net.crunchdroid.exceptions;

/**
 * @author CrunchDroid
 */
public class ArticleNotFoundException extends RuntimeException{

    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
