package jackie;

/**
 * Holds custom exceptions that can be thrown and handled by Jackie.
 */
public class JackieExceptions {

    /**
     * Custom exception that is thrown when input is in the wrong format.
     */
    static class InvalidInputException extends Exception {
        public InvalidInputException (String msg) {
            super(msg);
        }
    }
}
