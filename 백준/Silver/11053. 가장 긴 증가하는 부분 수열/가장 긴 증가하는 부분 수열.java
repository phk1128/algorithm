import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[] sequence;
    private static Stack<Integer> lis;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        sequence = new int[n];
        lis = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(sequence[0]);

        for (int i = 1; i < n; i++) {
            if (lis.peek() < sequence[i]) {
                lis.add(sequence[i]);
            } else {
                binarySearch(0, lis.size() - 1, sequence[i]);
            }
        }

        bw.write(String.valueOf(lis.size()));
        bw.flush();
        bw.close();

    }

    private static void binarySearch(int start, int end, int target) {
        if (start == end) {
            lis.set(start, target);
            return;
        }

        int mid = (start + end) / 2;

        if (target > lis.get(mid)) {
            binarySearch(mid + 1, end, target);
        } else {
            binarySearch(start, mid, target);
        }
    }
}