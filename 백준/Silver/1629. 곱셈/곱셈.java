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
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long answer = recursiveSolve(A, B, C);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static long recursiveSolve(int A, int B, int C) {

        if (B == 1 || B == 0) {
            return A % C;
        }

        long result = recursiveSolve(A, B / 2, C);

        if (B % 2 != 0) {
            return ((result * result) % C * (A % C)) % C;
        }
        return (result * result) % C;
    }
}