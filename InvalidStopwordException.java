public class InvalidStopwordException extends Exception {
    private String stopword;
    
    public InvalidStopwordException (String stopword) {
        super("Couldn't find stopword: " + stopword);
        this.stopword = stopword;
    }

    public String toString() {
        return "InvalidSoptwordException: Couldn't find stopword: " + stopword;
    }
}
