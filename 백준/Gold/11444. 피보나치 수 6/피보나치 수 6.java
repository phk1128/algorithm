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
        long n = Long.parseLong(st.nextToken());

        long[][] matrix = new long[][]{{1, 1}, {1, 0}};

        bw.write(String.valueOf(divideSolve(matrix, n - 1)[0][0]));
        bw.flush();
        bw.close();
    }

    private static long[][] divideSolve(long[][] matrix, long n) {

        if (n == 1L || n == 0L) {
            return matrix;
        }

        long[][] result = divideSolve(matrix, n / 2);

        result = power(result, result);

        if (n % 2 == 1) {
            result = power(result, matrix);
        }

        return result;
    }

    private static long[][] power(long[][] matrix1, long[][] matrix2) {

        long[][] result = new long[2][2];
        // 2x2 행렬곱
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                    result[i][j] %= 1_000_000_007;
                }
            }
        }

        return result;
    }
}