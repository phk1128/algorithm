import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static long[][] cost;

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        cost = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
            cost[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            cost[start][end] = Math.min(cost[start][end], weight);
        }

        floyd(n);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (cost[i][j] == Integer.MAX_VALUE) {
                    cost[i][j] = 0;
                }
                sb.append(cost[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void floyd(int n) {
        //특정 경유지
        for (int i = 1; i <= n; i++) {
            //시작점
            for (int start = 1; start <= n; start++) {
                //끝점
                for (int end = 1; end <= n; end++) {

                    if (cost[start][end] > cost[start][i] + cost[i][end]){
                        cost[start][end] = cost[start][i] + cost[i][end];
                    }
                }
            }
        }
    }
}