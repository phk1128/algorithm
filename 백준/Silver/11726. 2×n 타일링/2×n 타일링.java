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
        for (int i = 3; i < n + 1; i++) {
            result.add((result.get(i-1) + result.get(i-2)) % 10_007);
        }

        bw.write(String.valueOf(result.get(n)));
        bw.flush();
        bw.close();
    }
}