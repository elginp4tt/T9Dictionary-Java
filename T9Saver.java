import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

class T9Saver {

    public T9Saver() {
    }

    private boolean mappedDictionaryExists() {
        URL path = T9Saver.class.getResource("mappedDictionary.txt");
        try {
            File mappedDictionary = new File(path.getFile());
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public void save(TrieNode root) {
        try {
            new PrintWriter("mappedDictionary.txt").close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        saveToMappedDictionary(root);
    }

    private void saveToMappedDictionary(TrieNode root) {
        HashMap<Character, TrieNode> children = root.getChildren();
        PriorityQueue<Word> words = root.getWords();

        if (!children.isEmpty()) {
            Iterator childIterator = children.entrySet().iterator();
            while (childIterator.hasNext()) {
                Map.Entry childrenEntry = (Map.Entry) childIterator.next();
                saveToMappedDictionary((TrieNode) childrenEntry.getValue());
            }
        }

        if (!words.isEmpty()) {
            Iterator wordIterator = words.iterator();
            try {
                FileWriter fw = new FileWriter("mappedDictionary.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                while (wordIterator.hasNext()) {
                    Word wordPair = (Word) wordIterator.next();
                    String line = wordPair.getWord() + " " + wordPair.getUseFrequency();
                    pw.println(line);
                }
                pw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // takes in a list delimited by \n previously saved by the program to generate
    // TrieNodes
    private TrieNode convertMappedDictionaryToTrieNodes() throws FileNotFoundException, IOException {
        File dictionary = new File("C:\\Users\\Elgin Patt\\Desktop\\T9Dictionary\\mappedDictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(dictionary));
        String curLine = null;
        TrieNode root = new TrieNode();

        while ((curLine = br.readLine()) != null) {
            String[] pair = curLine.split(" ");
            root.insert(pair[0], Integer.valueOf(pair[1]));
        }
        br.close();
        return root;
    }

    // takes in a list delimited by \n with a word on each line to generate
    // TrieNodes
    // does not check for duplicates yet
    private TrieNode convertDictionaryToTrieNodes() throws FileNotFoundException, IOException {
        File dictionary = new File("C:\\Users\\Elgin Patt\\Desktop\\T9Dictionary\\dictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(dictionary));
        String curWord = null;
        TrieNode root = new TrieNode();

        while ((curWord = br.readLine()) != null) {
            root.insert(curWord, 0);
            System.out.println(curWord + " has been inserted into dictionary");
        }
        br.close();
        return root;
    }

    public TrieNode initializeTrieNodes() throws FileNotFoundException, IOException {
        TrieNode root = new TrieNode();
        if (mappedDictionaryExists()) {
            System.out.println("Mapped dictionary found, initializing");
            root = convertMappedDictionaryToTrieNodes();
        } else {
            try {
                System.out.println("Mapped dictionary not found, converting dictionary.txt");
                root = convertDictionaryToTrieNodes();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, terminating program");
            }
        }
        return root;
    }

}