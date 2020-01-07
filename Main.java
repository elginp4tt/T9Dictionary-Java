import java.io.IOException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        T9Saver t9Saver = new T9Saver();
        try {
            System.out.println("Initializing T9 Emulator");
            TrieNode root = t9Saver.initializeTrieNodes();
            int option = 0;
            Scanner sc = new Scanner(System.in);
            while (option != 3) {
                System.out.println("***Welcome to the T9 Emulator***");
                System.out.println("1: Search a word");
                System.out.println("2: Add a word");
                System.out.println("3: Save and Exit");
                System.out.println("Enter option");
                option = sc.nextInt();
                switch (option) {
                case 1:
                    System.out.println("Enter T9 Integer string to search");
                    String t9String = sc.next();
                    System.out.println("Enter number of word suggestions");
                    System.out.println("Suggestions will be printed in order of usage frequency");
                    int suggestCount = sc.nextInt();
                    String[] suggestions = root.suggest(t9String, suggestCount);

                    for (int counter = 0; counter < suggestions.length; counter++) {
                        if (suggestions[counter] != null) {
                            System.out.println(counter + 1 + ": " + suggestions[counter]);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter word to add to T9 dictionary");
                    String word = sc.next().toLowerCase();
                    System.out.println("Enter use frequency weightage for the word");
                    System.out.println("Higher frequency = larger number");
                    int useFrequency = sc.nextInt();
                    root.insert(word, useFrequency);
                    System.out.println(word + " has been sucessfully added into T9 dictonary");
                    break;
                case 3:
                    System.out.println("T9 dictionary will save and shut down");
                    t9Saver.save(root);
                    System.out.println("T9 dictionary has saved to mappedDictionary.txt and shut down");
                    sc.close();
                    break;
                }
            }
        } catch (IOException e) {
            // TODO
            e.getMessage();
        }
    }
}