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
   Node class
   ======================= */
    class Node {
        int key;
        String data;

        Node(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }

/* =======================
   Main class
   ======================= */
    class timeMethods {

        public static void main(String[] args) throws Exception {

            DecimalFormat fiveD = new DecimalFormat("0.00000");
            DecimalFormat fourD = new DecimalFormat("0.0000");

            // Load file into ONE array
            Node[] array = loadFile("ulysses.numbered");
            int n = array.length;

            // Binary search assumes sorted keys
            Arrays.sort(array, (a, b) -> a.key - b.key);

            int repetitions = 30;

            double linTime = 0, linTime2 = 0;
            double binTime = 0, binTime2 = 0;

            long start, finish;

            for (int r = 0; r < repetitions; r++) {

                int randomKey = (int)(Math.random() * 32654) + 1;

                // ---- Linear Search Timing ----
                start = System.currentTimeMillis();
                linearSearch(array, randomKey);
                finish = System.currentTimeMillis();

                double t = finish - start;
                linTime += t;
                linTime2 += t * t;

                // ---- Binary Search Timing ----
                start = System.currentTimeMillis();
                binarySearch(array, randomKey);
                finish = System.currentTimeMillis();

                t = finish - start;
                binTime += t;
                binTime2 += t * t;
            }

            double linAve = linTime / repetitions;
            double binAve = binTime / repetitions;

            double linStd = Math.sqrt(linTime2 - repetitions * linAve * linAve) / (repetitions - 1);
            double binStd = Math.sqrt(binTime2 - repetitions * binAve * binAve) / (repetitions - 1);

            System.out.println("\n\nStatistics");
            System.out.println("____________________________________________");
            System.out.println("Linear Search:");
            System.out.println("Average time        = " + fiveD.format(linAve / 1000) + " s");
            System.out.println("Standard deviation = " + fourD.format(linStd) + " ms");

            System.out.println("\nBinary Search:");
            System.out.println("Average time        = " + fiveD.format(binAve / 1000) + " s");
            System.out.println("Standard deviation = " + fourD.format(binStd) + " ms");

            System.out.println("\nn = " + n);
            System.out.println("Repetitions = " + repetitions);
            System.out.println("____________________________________________");
        }

        /* =======================
           Linear Search
           ======================= */
        static Node linearSearch(Node[] arr, int key) {
            for (Node n : arr) {
                if (n.key == key)
                    return n;
            }
            return null;
        }

        /* =======================
           Binary Search
           ======================= */
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

        /* =======================
           File Loading
           ======================= */
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
