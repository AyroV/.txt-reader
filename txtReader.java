import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainClass {

    private static void listf(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if(fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    listf(file.getAbsolutePath(), files);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //txt reader
        System.out.println("Select Operation:");
        System.out.println("1 - Count Words");
        System.out.println("2 - Count Lines");
        System.out.println("3 - Count Specific Word");
        System.out.print("Select: ");
        int operation = -1;

        Scanner userInput = new Scanner(System.in);
        String operationChoice = userInput.nextLine();

        String target = "";
        switch (operationChoice) {
            case "1":
                operation = 0;
                break;
            case "2":
                operation = 1;
                break;
            case "3":
                operation = 2;
                System.out.print("Enter the word to count: ");
                target = userInput.nextLine();
                break;
            default:
                System.out.println("Wrong Input!");
                return;
        }

        System.out.print("Enter main folder location: ");
        String folderLocation = userInput.nextLine();

        int totalCount = 0;
        List<File> list = new ArrayList<File>();
        listf(folderLocation, list);

        for (final File fileEntry : list) {
            Scanner myReader = new Scanner(fileEntry);
            int wordCount = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                if(operation == 0) {
                    String[] words = data.split(" ");
                    wordCount = words.length;
                    totalCount += wordCount;
                }

                else if(operation == 1) {
                    if(data.length() > 0) {
                        totalCount++;
                    }
                }

                else if(operation == 2) {
                    wordCount = 0;
                    String[] words = data.split(" ");
                    for (String word : words) {
                        //Checks if the word have any punctuation at the end or start
                        StringBuilder sb = new StringBuilder(word);
                        if((word.charAt(0) > 32 && word.charAt(0) < 48)) {
                            sb.deleteCharAt(0);
                        }

                        if((word.charAt(word.length() - 1) > 32 && word.charAt(word.length() - 1) < 48)) {
                            sb.deleteCharAt(word.length() - 1);
                        }

                        if (sb.toString().equals(target)) {
                            wordCount++;
                        }
                    }
                    totalCount += wordCount;
                }

                else {
                    System.out.println("Wrong operation input");
                    return;
                }
            }
        }

        System.out.println(totalCount);
    }
}
