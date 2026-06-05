class Node {

    int key;
    int value;

    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class MyHashMap {

    private Node[] buckets;
    private int SIZE = 1000;

    public MyHashMap() {
        buckets = new Node[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int idx = hash(key);

        Node curr = buckets[idx];

        while (curr != null) {
            if (curr.key == key) {
                curr.value = value;
                return;
            }

            curr = curr.next;
        }

        Node node = new Node(key, value);

        node.next = buckets[idx];
        buckets[idx] = node;
    }


    public int get(int key) {
        int idx = hash(key);

        Node curr = buckets[idx];

        while (curr != null) {
            if (curr.key == key) {
                return curr.value;
            }

            curr = curr.next;
        }

        return -1;
    }

    public void remove(int key) {
        int idx = hash(key);

        Node curr = buckets[idx];
        Node prev = null;
        
        while (curr != null) {
            if (curr.key == key) {
                if (prev == null) {
                    buckets[idx] = curr.next;
                } else {
                    prev.next = curr.next;
                }

                return;
            }

            prev = curr;
            curr = curr.next;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */