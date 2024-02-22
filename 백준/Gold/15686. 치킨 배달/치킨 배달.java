import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<int[]> chickens;
    private static List<int[]> homes;
    private static int min;
    private static int[] combination;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        chickens = new ArrayList<>();
        homes = new ArrayList<>();
        min = Integer.MAX_VALUE;
        combination = new int[m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                int number = Integer.parseInt(st.nextToken());
                if (number == 2) {
                    chickens.add(new int[]{r, c});
                }
                if (number == 1) {
                    homes.add(new int[]{r, c});
                }
            }
        }
        solve(0, 0, m);

        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }

    private static void solve(int start, int count, int m) {
        if (count == m) {
            int sum = 0;
            for (int[] home : homes) {
                int tmpMin = Integer.MAX_VALUE;
                for (int i : combination) {
                    int distR = Math.abs(home[0] - chickens.get(i)[0]);
                    int distC = Math.abs(home[1] - chickens.get(i)[1]);
                    tmpMin = Math.min(distR + distC, tmpMin);
                }
                sum += tmpMin;
            }
            min = Math.min(sum, min);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            combination[count] = i;
            solve(i + 1, count + 1, m);
        }
    }
}