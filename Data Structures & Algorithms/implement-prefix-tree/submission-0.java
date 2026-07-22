class PrefixTreeNode {
    private PrefixTreeNode[] links;

    private final int R = 26;

    private boolean isEnd;

    public PrefixTreeNode() {
        links = new PrefixTreeNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public PrefixTreeNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, PrefixTreeNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

class PrefixTree {
    private PrefixTreeNode root;

    public PrefixTree() {
        root = new PrefixTreeNode();     
    }

    public void insert(String word) {
        PrefixTreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!node.containsKey(currChar)) {
                node.put(currChar, new PrefixTreeNode());
            }
            node = node.get(currChar);
        }
        node.setEnd();
    }

    public PrefixTreeNode searchPrefix(String word) {
        PrefixTreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (node.containsKey(currChar)) {
                node = node.get(currChar);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        PrefixTreeNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        PrefixTreeNode node = searchPrefix(prefix);
        return node != null;
    }
}
