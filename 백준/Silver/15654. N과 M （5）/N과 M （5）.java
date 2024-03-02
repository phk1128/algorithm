import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] arr;
    private static boolean[] visited;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        sb = new StringBuilder();

        recursiveSolve(0, "");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int depth, String numbers) {

        if (depth == M) {
            sb.append(numbers, 1, numbers.length());
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            String tmpNumbers = numbers + " " + arr[i];
            recursiveSolve(depth + 1, tmpNumbers);
            visited[i] = false;
        }
    }
}