import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.Scanner;

public class WordCounter {
    //Counts the number of words in the text up to a stopword
    //If stopword null count all words in the file
    //If stopword not found raise an InvalidStopwordException
    //If the count is less than five raise a TooSmallTextException
    public static int processText (StringBuffer text, String stopword) throws InvalidStopwordException, TooSmallText {
        // Specifies a regular expression / word
        Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
        Matcher regexMatcher = regex.matcher(text);
        int count = 0;
        boolean foundStopword = false;
        //Goes through each word in the text
        while (regexMatcher.find()) {
            String word = regexMatcher.group();
            count++;
            if (stopword != null && word.equals(stopword)) {
                foundStopword = true;
                break;
            }
        } 
        //Checks for exceptions
        if (count < 5) {
            throw new TooSmallText(count);
        }
        if (stopword != null && foundStopword == false) {
            throw new InvalidStopwordException(stopword);
        }
        return count;
    }
    //Converts the contents of a file to a StringBuffer
    //If the file cannot be opened prompt the user to re-enter the filename until it can be opened
    //If the file is empty raise an EmptyFileException with the file's path in the message
    public static StringBuffer processFile (String path) throws EmptyFileException{
        File file = new File(path);
        Scanner input = new Scanner(System.in);
        //Keep asking for file until valid file entered
        while (file.exists() == false) {
            System.out.print("File not found. Please enter a valid filename: ");
            String newPath = input.nextLine();
            file = new File(newPath);
        }
        //Converts the text in the file to a string buffer
        StringBuilder sb = new StringBuilder();
        boolean isEmpty = true;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isEmpty == false) {
                    sb.append(" ");
                }
                sb.append(line);
                isEmpty = false;
            }
        } catch (IOException e) {
        }
        //Checks for exception
        if (isEmpty == true) {
            throw new EmptyFileException(file.getName());
        }
        return new StringBuffer(sb.toString());
    }
    //Asks the user to choose a process file, option 1, or text, option 2
    //If invalid option is entered prompt user to enter an option until valid option entered
    //Check if there is an argument specifiying a stopword
    //If stopword not specified allow one chance to input a stopword
    //Calls the process methods depending on the option inputed and outputs the count
    public static void main (String[] args) {
        StringBuffer text = new StringBuffer();
        String stopword = null;
        try {
            text = processFile(args[0]);
        } catch (EmptyFileException e) {
            System.out.println(e);
            text = new StringBuffer("");
        }
        if (args.length > 1) {
            stopword = args[1];
        }
        try {
            int count = processText(text, stopword);
            System.out.println("Found " + count + " words.");
        } catch (InvalidStopwordException e) {
            System.out.println(e);
        } catch (TooSmallText e) {
            System.out.println(e);
        }
    }
}
