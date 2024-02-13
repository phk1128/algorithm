import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static Map<String, String> siteAndPassword;
    private static List<String> sites;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        siteAndPassword = new HashMap<>();
        sites = new ArrayList<>();

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            siteAndPassword.put(st.nextToken(), st.nextToken());
        }
        while (m--> 0) {
            st = new StringTokenizer(br.readLine());
            sites.add(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        for (String site : sites) {
            sb.append(siteAndPassword.get(site));
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}