import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int m;
    private static int n;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            boolean flag = false;

            for (int i = x; i < (m * n); i += m) {

                if (i % n == y) {
                    sb.append(i + 1);
                    sb.append("\n");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                sb.append(-1);
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}