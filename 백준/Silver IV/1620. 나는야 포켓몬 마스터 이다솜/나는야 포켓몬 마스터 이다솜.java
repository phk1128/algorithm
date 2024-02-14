import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String[] poketmonList;
    private static Map<String, Integer> poketmonMap;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        poketmonList = new String[n + 1];
        poketmonMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            poketmonList[i] = name;
            poketmonMap.put(name, i);
        }

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            try {
                int number = Integer.parseInt(input);
                sb.append(poketmonList[number]);
                sb.append("\n");
            } catch (Exception e) {
                sb.append(poketmonMap.get(input));
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}