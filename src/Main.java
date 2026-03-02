//Matthew Mullins
//4397399
//02 March 2026
//Practical 14

/* =======================
   Node class
   ======================= */
class Node {
    String key;
    String data;
    Node next;

    Node(String key, String data) {
        this.key = key;
        this.data = data;
        this.next = null;
    }
}

/* =======================
   Open Hash Table
   ======================= */
class openHash {

    private Node[] table;
    private int m;
    private int size;

    openHash(int m) {
        this.m = m;
        table = new Node[m + 1];   // indices 1..m
        size = 0;
    }

    int hash(String key) {
        int h = Math.abs(key.hashCode());
        return (h % m) + 1;
    }

    boolean isFull() {
        return size >= m;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isInTable(String key) {
        return lookup(key) != null;
    }

    void insert(String key, String data) {
        if (isFull()) return;

        int i = hash(key);
        int step = 1;

        while (table[i] != null) {
            if (table[i].key.equals(key)) {
                table[i].data = data;
                return;
            }
            i = ((i + step) % m) + 1;
            step++;
        }
        table[i] = new Node(key, data);
        size++;
    }

    String lookup(String key) {
        int i = hash(key);
        int step = 1;

        while (table[i] != null) {
            if (table[i].key.equals(key))
                return table[i].data;
            i = ((i + step) % m) + 1;
            step++;
        }
        return null;
    }

    String remove(String key) {
        int i = hash(key);
        int step = 1;

        while (table[i] != null) {
            if (table[i].key.equals(key)) {
                String val = table[i].data;
                table[i] = null;
                size--;
                return val;
            }
            i = ((i + step) % m) + 1;
            step++;
        }
        return null;
    }
}

/* =======================
   Chained Hash Table
   ======================= */
class chainedHash {

    private Node[] table;
    private int m;

    chainedHash(int m) {
        this.m = m;
        table = new Node[m + 1];   // indices 1..m
    }

    int hash(String key) {
        int h = Math.abs(key.hashCode());
        return (h % m) + 1;
    }

    boolean isEmpty() {
        for (int i = 1; i <= m; i++)
            if (table[i] != null) return false;
        return true;
    }

    boolean isInTable(String key) {
        return lookup(key) != null;
    }

    void insert(String key, String data) {
        int i = hash(key);

        if (table[i] == null) {
            table[i] = new Node(key, data);
            return;
        }

        Node curr = table[i];
        while (true) {
            if (curr.key.equals(key)) {
                curr.data = data;
                return;
            }
            if (curr.next == null) break;
            curr = curr.next;
        }
        curr.next = new Node(key, data);
    }

    String lookup(String key) {
        int i = hash(key);
        Node curr = table[i];

        while (curr != null) {
            if (curr.key.equals(key))
                return curr.data;
            curr = curr.next;
        }
        return null;
    }

    String remove(String key) {
        int i = hash(key);
        Node curr = table[i];
        Node prev = null;

        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null)
                    table[i] = curr.next;
                else
                    prev.next = curr.next;
                return curr.data;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }
}
