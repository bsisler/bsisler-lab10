import java.io.IOException;

public class EmptyFileException extends IOException {
    private String filename;

    public EmptyFileException (String filename) {
        super(filename + " was empty");
        this.filename = filename;
    }

    public String toString() {
        return "EmptyFileException: " + filename + " was empty";
    }
}
