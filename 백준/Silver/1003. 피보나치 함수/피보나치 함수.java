import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Integer> numbers;
    private static int zeroCount;
    private static int oneCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            numbers.add(num);
        }

        for (int num : numbers) {
            zeroCount = 1;
            oneCount = 0;
            fibonacci(num,0);

            bw.write(zeroCount + " " + oneCount + "\n");
            bw.flush();
        }

        bw.close();

    }

    private static void fibonacci(int num, int depth) {
        if (depth == num) {
            return;
        }
        oneCount += zeroCount;
        zeroCount = oneCount - zeroCount;
        fibonacci(num, depth+1);
    }
}