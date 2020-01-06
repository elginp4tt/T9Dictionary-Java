import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

class TrieNode {
    private static HashMap<Character, Character> t9HashMap = new HashMap<>(); // integer to character converter
    private HashMap<Character, TrieNode> children = new HashMap<>(); // references to children trienode instances
    private PriorityQueue<Word> words = new PriorityQueue<Word>(1,
            new WordComparator()); // references to t9 words on the node

    public TrieNode() {
        populatet9HashMap();
    }

    public PriorityQueue<Word> getWords() {
        return this.words;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return this.children;
    }

    // incoming string will be in integer form, should run automatically as word is
    // updated
    public String[] suggest(String t9String, int suggestCount) {
        TrieNode trieNode = this;
        for (int counter = 0; counter < t9String.length(); counter++) {
            char curInteger = t9String.charAt(counter);

            if (trieNode.children.containsKey(curInteger)) {
                trieNode = trieNode.children.get(curInteger);
            } else {
                break;
            }
        }

        Iterator iterator = trieNode.getWords().iterator();
        String[] suggestions = new String[suggestCount];
        int wordCount = 0;

        while (iterator.hasNext() && wordCount < suggestCount) {
            Word curPair = (Word) iterator.next();
            suggestions[wordCount] = curPair.getWord();
            wordCount++;
        }
        return suggestions;
    }

    public void insert(String word, Integer useFrequency) {
        TrieNode trieNodeLeaf = traverseAndAddNodes(word);
        trieNodeLeaf.words.add(new Word(word, useFrequency));
    }

    public TrieNode traverseAndAddNodes(String word) {
        TrieNode trieNode = this;

        for (int counter = 0; counter < word.length(); counter++) {
            char curChar = word.charAt(counter);
            char curInteger = t9HashMap.get(curChar);

            if (trieNode.children.containsKey(curInteger)) {
                trieNode = trieNode.children.get(curInteger);
            } else {
                trieNode = new TrieNode();
                trieNode.children.put(curInteger, trieNode);
            }
        }
        return trieNode;
    }

    public void populatet9HashMap() {
        // check if empty since static
        if (t9HashMap.isEmpty()) {
            t9HashMap.put('a', '2');
            t9HashMap.put('b', '2');
            t9HashMap.put('c', '2');

            t9HashMap.put('d', '3');
            t9HashMap.put('e', '3');
            t9HashMap.put('f', '3');

            t9HashMap.put('g', '4');
            t9HashMap.put('h', '4');
            t9HashMap.put('i', '4');

            t9HashMap.put('j', '5');
            t9HashMap.put('k', '5');
            t9HashMap.put('l', '5');

            t9HashMap.put('m', '6');
            t9HashMap.put('n', '6');
            t9HashMap.put('o', '6');

            t9HashMap.put('p', '7');
            t9HashMap.put('q', '7');
            t9HashMap.put('r', '7');
            t9HashMap.put('s', '7');

            t9HashMap.put('t', '8');
            t9HashMap.put('u', '8');
            t9HashMap.put('v', '8');

            t9HashMap.put('w', '9');
            t9HashMap.put('x', '9');
            t9HashMap.put('y', '9');
            t9HashMap.put('z', '9');
        }
    }
}