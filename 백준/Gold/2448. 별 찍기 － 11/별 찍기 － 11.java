import java.util.*;
import java.io.*;

public class Main {


    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int n;
    private static String[] stars;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        stars = new String[n];
        stars[2] = "*****";
        stars[1] = " * * ";
        stars[0] = "  *  ";

        for (int k = 6; k <= n; k = k * 2) {
            solve(k);
        }

        StringBuilder sb = new StringBuilder();

        for (String star : stars) {
            sb.append(star);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int k) {
        int end = k;
        int start = k / 2;

        int j = 0;
        for (int i = start; i < end; i++) {
            stars[i] = stars[j] + " " + stars[j];
            j++;
        }

        String space = "";

        for (int i = 0; i < start; i++) {
            space += " ";
        }

        for (int i = 0; i < start; i++) {
            stars[i] = space + stars[i] + space;
        }
    }
}