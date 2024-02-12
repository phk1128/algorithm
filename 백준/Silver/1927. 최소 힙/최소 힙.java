import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static PriorityQueue<Integer> queue;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        queue = new PriorityQueue<>();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) {
                if (queue.isEmpty()) {
                    sb.append(0);

                } else {
                    sb.append(queue.poll());
                }
                sb.append("\n");
                continue;
            }
            queue.offer(num);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}