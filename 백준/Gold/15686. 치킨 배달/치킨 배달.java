import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<int[]> chList;
    private static List<int[]> hsList;
    private static int ans;
    private static int[] pick;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        chList = new ArrayList<>();
        hsList = new ArrayList<>();
        ans = Integer.MAX_VALUE;
        pick = new int[m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 2) chList.add(new int[]{r, c});
                else if (val == 1) hsList.add(new int[]{r, c});
            }
        }

        comb(0, 0, m);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void comb(int start, int cnt, int m) {
        if (cnt == m) {
            int sum = 0;
            for (int[] h : hsList) {
                int d = Integer.MAX_VALUE;
                for (int idx : pick) {
                    int[] ch = chList.get(idx);
                    d = Math.min(d, Math.abs(h[0] - ch[0]) + Math.abs(h[1] - ch[1]));
                }
                sum += d;
            }
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = start; i < chList.size(); i++) {
            pick[cnt] = i;
            comb(i + 1, cnt + 1, m);
        }
    }
}
