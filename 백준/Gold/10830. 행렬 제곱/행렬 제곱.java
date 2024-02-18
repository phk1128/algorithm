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
        long b = Long.parseLong(st.nextToken());

        long[][] matrix = new long[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }
        StringBuilder sb = new StringBuilder();
        long[][] solvedMatrix = divideSolve(matrix, b, n);

        for (long[] m : solvedMatrix) {
            for (long factor : m) {
                sb.append(factor);
                sb.append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static long[][] divideSolve(long[][] matrix, long b, int n) {

        if (b == 0 || b == 1) {
            return matrix;
        }

        long[][] result = divideSolve(matrix, b / 2, n);

        result = power(result, result, n);

        if (b % 2 == 1L) {
            result = power(matrix, result, n);
        }

        return result;
    }

    private static long[][] power(long[][] matrix1, long[][] matrix2, int n) {

        long[][] result = new long[n][n];

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] += (matrix1[i][k] * matrix2[k][j]);
                    result[i][j] %= 1000;
                }
            }
        }
        return result;
    }
}