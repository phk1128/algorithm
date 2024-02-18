import java.util.*;
import java.io.*;

public class Main {


    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int n;
    private static int[][] cost;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        cost = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n; i++) {
            cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
            cost[i][1] += Math.min(cost[i - 1][0], cost[i - 1][2]);
            cost[i][2] += Math.min(cost[i - 1][0], cost[i - 1][1]);
        }

        int answer = Math.min(Math.min(cost[n - 1][0], cost[n - 1][1]), cost[n - 1][2]);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}