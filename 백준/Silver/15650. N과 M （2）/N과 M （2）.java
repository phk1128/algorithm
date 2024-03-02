import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        recursiveSolve(1, 0, 0, "");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int start, int count, int depth, String numbers) {

        if (depth == M) {
            if (count == M) {
                sb.append(numbers, 1, numbers.length());
                sb.append("\n");
            }
            return;
        }
        for (int i = start; i <= N; i++) {
            String tmpNumbers = numbers + " " + i;
            recursiveSolve(i + 1, count + 1, depth + 1, tmpNumbers);
        }
    }
}