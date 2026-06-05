class MyHashSet {

    private LinkedList<Integer>[] buckets;
    private int size = 1000; 

    public MyHashSet() {
        buckets = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void add(int key) {
        int idx = hash(key);

        if (!buckets[idx].contains(key)) {
            buckets[idx].add(key);
        }
    }

    public void remove(int key) {
        int idx = hash(key);

        buckets[idx].remove(Integer.valueOf(key));
    }

    public boolean contains(int key) {
        int idx = hash(key);

        return buckets[idx].contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */