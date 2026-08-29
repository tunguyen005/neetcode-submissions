class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    String word = null;
}

class Solution {
    char[][] fullboard = null;
    ArrayList<String> answer = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {

        // Reset result
        this.answer = new ArrayList<>();

        TrieNode root = new TrieNode();

        // Build Trie
        for (String word : words) {

            TrieNode node = root;

            for (char letter : word.toCharArray()) {

                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {

                    TrieNode newNode = new TrieNode();

                    node.children.put(letter, newNode);

                    // Move to the newly created node
                    node = newNode;
                }
            }

            node.word = word;
        }

        this.fullboard = board;

        // Start DFS from every cell
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {

                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return answer;
    }

    public void backtracking(int row, int col, TrieNode parent) {

        Character letter = fullboard[row][col];

        TrieNode currNode = parent.children.get(letter);

        // Found a complete word
        if (currNode.word != null) {

            answer.add(currNode.word);

            // Prevent duplicate result
            currNode.word = null;
        }

        // Mark cell as visited
        fullboard[row][col] = '#';

        // Four directions
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {

            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];

            // Correct boundary check
            if (newRow < 0 ||
                newCol < 0 ||
                newRow >= fullboard.length ||
                newCol >= fullboard[0].length) {

                continue;
            }

            // Continue DFS only if Trie has this character
            if (currNode.children.containsKey(fullboard[newRow][newCol])) {

                backtracking(newRow, newCol, currNode);
            }
        }

        // Restore cell
        fullboard[row][col] = letter;

        // Trie pruning
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}