package stream_checker;

import java.util.ArrayList;
import java.util.List;

class StreamChecker {

    int ALPHABET_LEN = 26;
    TrieNode root;
    List<Character> history;

    class TrieNode {
        boolean isWord;
        TrieNode[] next;

        public TrieNode() {
            isWord = false;
            next = new TrieNode[ALPHABET_LEN];
        }
    }

    public StreamChecker(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            insert(word);
        }
        history = new ArrayList<>();
    }

    private void insert(String input) {
        TrieNode node = root;
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            int pos = c - 'a';
            if (node.next[pos] == null) {
                node.next[pos] = new TrieNode();
            }
            node = node.next[pos];
        }
        node.isWord = true;
    }

    public boolean query(char letter) {
        history.add(letter);
        TrieNode node = root;

        for (int i = history.size() - 1; i >= 0; i--) {
            char c = history.get(i);
            int index = c - 'a';
            if (node.next[index] == null) {
                return false;
            } else {
                if (node.next[index].isWord) {
                    return true;
                }
                node = node.next[index];
            }
        }

        return false;
    }
}

