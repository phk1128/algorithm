import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] dists;
    private static int[] costs;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());

        dists = new int[N + 5];
        costs = new int[N + 5];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dists[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        long min = costs[0];
        long answer = 0;
        for (int i = 0; i < N - 1; i++) {
            if (costs[i] < min) {
                min = costs[i];
            }
            answer += (min * dists[i]);
        }
        System.out.println(answer);
    }
}
