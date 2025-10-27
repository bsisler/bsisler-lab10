import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    //Counts the number of words in the text up to a stopword
    //If stopword null count all words in the file
    //If stopword not found raise an InvalidStopwordException
    //If the count is less than five raise a TooSmallTextException
    public int processText (StringBuffer text, String stopword) {
        // Specifies a regular expression
        Pattern regex = Pattern.compile("your regular expression here");
        Matcher regexMatcher = regex.matcher(text);
        while (regexMatcher.find()) {
            System.out.println("I just found the word: " + regexMatcher.group());
        } 
        int count = 0;
        if (stopword == null) {

        }
        if (count < 5) {
            
        }
        return 0;
    }
    //Converts the contents of a file to a StringBuffer
    //If the file cannot be opened prompt the user to re-enter the filename until it can be opened
    //If the file is empty raise an EmptyFileException with the file's path in the message
    public StringBuffer processFile (String path) {
        StringBuffer s = new StringBuffer();
        //s.append(" ");
        return s;
    }
    //Asks the user to choose a process file, option 1, or text, option 2
    //If invalid option is entered prompt user to enter an option until valid option entered
    //Check if there is an argument specifiying a stopword
    //If stopword not specified allow one chance to input a stopword
    //Calls the process methods depending on the option inputed and outputs the count
    public static void main (String[] args) {

    }
}
