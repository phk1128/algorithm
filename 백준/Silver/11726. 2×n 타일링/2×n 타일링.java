import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static List<Long> result;

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        result = new ArrayList<>(List.of(0L,1L,2L));
        calculate(3, n);
        bw.close();
    }

    private static void calculate(int idx, int n) throws IOException{
        if (idx == n + 1 || n < 3) {
            bw.write(String.valueOf(result.get(n)));
            bw.flush();
            return;
        }
        long a = result.get(idx - 2);
        long b = result.get(idx - 1);
        result.add((a+b)  % 10_007);
        calculate(idx + 1, n);
    }
}