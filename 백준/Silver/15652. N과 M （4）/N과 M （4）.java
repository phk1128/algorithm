import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    private static final StringBuilder sb = new StringBuilder();
    private static int[] combi;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        combi = new int[M];
        solve(1,N, M, 0);
        System.out.println(sb);
    }

    private static void solve(int s, int N, int M, int depth) {
        if (depth == M) {
            for (int num : combi) {
                sb.append(num);
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = s; i <= N; i++) {
            combi[depth] = i;
            solve(i,N, M, depth + 1);
        }

    }


}
