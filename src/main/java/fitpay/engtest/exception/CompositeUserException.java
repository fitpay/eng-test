package fitpay.engtest.exception;

public class CompositeUserException extends Exception{

    private static final long serialVersionUID = 1L;


    public CompositeUserException(String message, Throwable cause){
        super(message, cause);
    }
    

}
