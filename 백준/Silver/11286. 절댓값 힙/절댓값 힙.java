import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> queue = new PriorityQueue<>((n1, n2) -> {
            if (Math.abs(n1) == Math.abs(n2)) {
                return n1 - n2;
            }
            return Math.abs(n1) - Math.abs(n2);
        });
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            if (number == 0) {
                if (!queue.isEmpty()) {
                    number = queue.poll();
                }
                sb.append(number);
                sb.append("\n");
                continue;
            }
            queue.offer(number);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}