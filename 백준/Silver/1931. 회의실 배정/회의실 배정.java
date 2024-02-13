import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] times;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        times = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times[i][0] = start;
            times[i][1] = end;
        }
        Arrays.sort(times, (t1, t2) -> {
            if (t1[1] == t2[1]) {
                return t1[0] - t2[0];
            }
            return t1[1] - t2[1];
        });
        int answer = 0;
        int start = 0;
        for (int[] time : times) {
            if (start <= time[0]) {
                start = time[1];
                answer++;
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}