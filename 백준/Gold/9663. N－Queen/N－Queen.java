import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int[] queens;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        answer = 0;
        queens = new int[N];

        recursiveSolve(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int row) {

        if (row == N) {
            answer++;
            return;
        }

        for (int c = 0; c < N; c++) {
            queens[row] = c;
            if (isAvailable(row)) {
                recursiveSolve(row + 1);
            }
        }
    }

    private static boolean isAvailable(int row) {

        for (int r = 0; r < row; r++) {
            if (queens[r] == queens[row]) {
                return false;
            }

            if (Math.abs(r - row) == Math.abs(queens[row] - queens[r])) {
                return false;
            }
        }
        return true;

    }
}