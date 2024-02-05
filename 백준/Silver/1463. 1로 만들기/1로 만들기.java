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
        int n = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(recursiveSolve(n, 0)));
        bw.flush();
        bw.close();
    }

    private static int recursiveSolve(int n, int count) {
        if (n < 2) {
            return count;
        }
        return Math.min(recursiveSolve(n / 2, count + 1 + (n % 2)), recursiveSolve(n / 3, count + 1 + (n % 3)));
    }

}