import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static long[] tree;
    private static long answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        long n = Integer.parseInt(st.nextToken());
        long m = Integer.parseInt(st.nextToken());

        tree = new long[(int) n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tree);

        binarySearch(1, tree[(int) n - 1], m, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void binarySearch(long start, long end, long target, long sum) {
        if (start == end) {
            answer = start - 1;
            return;
        }

        long mid = (start + end) / 2;
        long tmpSum = 0;

        for (long t : tree) {
            long tmpNum = t - mid;
            if (tmpNum >= 0) {
                tmpSum += tmpNum;
            }
        }

        if (tmpSum == target) {
            answer = mid;
            return;
        }

        if (tmpSum > target) {
            binarySearch(mid + 1, end, target, tmpSum);
        }

        if (tmpSum <= target) {
            binarySearch(start, mid, target, tmpSum);
        }
    }
}