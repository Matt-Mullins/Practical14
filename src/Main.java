//Matthew Mullins
//4397399
//02 March 2026
//Practical 14


import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

void main() {
    /* =======================
    File: KeyValue.java
   ======================= */

    class Node {
        int key;
        String data;
        Node next;

        Node(int key, String data) {
            this.key = key;
            this.data = data;
            this.next = null;
        }
    }

    class Openhash {
        private Node[] table;
        private int m;
        private int size;

        OpenHash(int m ){
            this.m =m;
            table = new Node[m+1];
            size = 0;
        }
        int hash(String key){
            int h = Math.abs(key.hashCode());
            return (h % m ) +1;
        }

        static Node linearSearch(Node[] arr, int key) {
            for (Node n : arr) {
                if (n.key == key)
                    return n;
            }
            return null;
        }

        static Node binarySearch(Node[] arr, int key) {
            int left = 0, right = arr.length - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (arr[mid].key == key)
                    return arr[mid];
                else if (arr[mid].key < key)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            return null;
        }

        static Node[] loadFile(String filename) throws Exception {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            ArrayList<Node> list = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+", 2);
                int key = Integer.parseInt(parts[0]);
                String data = parts.length > 1 ? parts[1] : "";
                list.add(new Node(key, data));
            }
            br.close();

            return list.toArray(new Node[0]);
        }
    }
}
