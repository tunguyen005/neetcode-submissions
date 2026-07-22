class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean word = false;

    public TrieNode() {

    }
}

class WordDictionary {

    TrieNode trie;

    public WordDictionary() {
        trie = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = trie;

        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }

        node.word = true;
    }

    private boolean searchInNode(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.word;
        }

        char ch = word.charAt(index);

        if (ch == '.') {

            for (TrieNode child : node.children.values()) {
                if (searchInNode(word, index + 1, child)) {
                    return true;
                }
            }

            return false;
        }

        if (!node.children.containsKey(ch)) {
            return false;
        }

        return searchInNode(word, index + 1, node.children.get(ch));
    }

    public boolean search(String word) {
        return searchInNode(word, 0, trie);
    }
}