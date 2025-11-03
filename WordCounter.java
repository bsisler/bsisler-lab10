import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.Scanner;

public class WordCounter {
    //Counts the number of words in the text up to a stopword
    //If stopword null count all words in the file
    //If stopword not found raise an InvalidStopwordException
    //If the count is less than five raise a TooSmallTextException
    public int processText (StringBuffer text, String stopword) {
        throws InvalidStopwordException, TooSmallText {
             // Specifies a regular expression / word
            Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
            Matcher regexMatcher = regex.matcher(text);

            int count = 0;
            boolean foundStopword = false;

            while (regexMatcher.find()) {
                String word = regexMatcher.group();
                count++;
                if (word.equalsIgnoreCase(stopword)) {
                    foundStopword = true;
                    break;
                }
            } 

            if (stopword == null) {
                throw new InvalidStopwordException("InvalidStopwordException: Couldn't find stopword: " + stopword);
            }
            if (count < 5) {
                throw new TooSmallText("TooSmallText: Only found " + count + " words.");
            }
            return count;
        }
        
    }
    //Converts the contents of a file to a StringBuffer
    //If the file cannot be opened prompt the user to re-enter the filename until it can be opened
    //If the file is empty raise an EmptyFileException with the file's path in the message
    public StringBuffer processFile (String path) {
        throws EmptyFileException 
            File myFile = new File(path);
            while (myFile.exists()) {
                Scanner reader = new Scanner(System.in);
                System.out.print("File not found. Re-enter filename: ");
                path = sc.nextLine();
                myFile = new File(path);
            }
            
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(myFile))) {
                String line;
                boolean isEmpty = true;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append(" ");
                    isEmpty = false;
                }
                if (isEmpty == true) {
                    throw new EmptyFileException("EmptyFileExcetion: " + path + " was empty");
                }
            } catch (IOException e) {
                System.out.println ("Error reading file: " + e.getMessage());
            }
            //s.append(" ");
            return new StringBuffer(sb.toString());
        
    }
    //Asks the user to choose a process file, option 1, or text, option 2
    //If invalid option is entered prompt user to enter an option until valid option entered
    //Check if there is an argument specifiying a stopword
    //If stopword not specified allow one chance to input a stopword
    //Calls the process methods depending on the option inputed and outputs the count
    public static void main (String[] args) {

    }
}
