package backtobackswe.replace_words_with_prefix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution {

  public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
      this.children = new HashMap<>();
      this.isWord = false;
    }

    public HashMap<Character, TrieNode> getChildren() {
      return children;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
      this.children = children;
    }

    public boolean isWord() {
      return isWord;
    }

    public void setIsWord(boolean isWord) {
      this.isWord = isWord;
    }
  }

  public class Trie {
    private TrieNode root;

    public Trie() {
      this.root = new TrieNode();
    }

    public void insert(String word) {
      TrieNode current = root;

      for (char l : word.toCharArray()) {
        current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
      }
      current.setIsWord(true);
    }

    public boolean find(String word) {
      TrieNode current = root;

      for (int ix = 0; ix < word.length(); ix++) {
        char ch = word.charAt(ix);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
          return false;
        }
        current = node;
      }
      return current.isWord();
    }

    public boolean hasPrefix(String word) {
      TrieNode current = root;

      for (int ix = 0; ix < word.length(); ix++) {
        char ch = word.charAt(ix);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
          return false;
        }
        if (node.isWord) {
          return true;
        }
        current = node;
      }
      return current.isWord();
    }
  }

  public String replaceWordsWithPrefix(String[] prefixes, String sentence) {
    if (prefixes.length == 0) {
      return "";
    }

    final Trie trie = new Trie();

    int shortestIx = 0;

    for (int ix = 0; ix < prefixes.length; ix++) {
      if (prefixes[ix].length() < prefixes[shortestIx].length()) {
        shortestIx = ix;
      }
      trie.insert(prefixes[ix]);
    }

    final String[] words = sentence.split(" ");

    for (int ix = 0; ix < words.length; ix++) {
      if (trie.hasPrefix(words[ix])) {
        words[ix] = prefixes[shortestIx];
      }
    }

    return String.join(" ", words);
  }
}
