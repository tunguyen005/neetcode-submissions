public class Codec {

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }

        sb.append(root.val).append(",");

        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    private TreeNode deserialize(List<String> list) {

        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(list.remove(0)));

        root.left = deserialize(list);
        root.right = deserialize(list);

        return root;
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public TreeNode deserialize(String data) {

        List<String> list =
                new LinkedList<>(Arrays.asList(data.split(",")));

        return deserialize(list);
    }
}