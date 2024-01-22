import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Integer> coins;

    private static int answer = 0;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 동전의 갯수
        int k = Integer.parseInt(st.nextToken()); // 목표 금액

        coins = new ArrayList<>();
        addCoin(0,n);

        for (int i = n-1; i >= 0; i--) {
            if (coins.get(i) <= k) {
                while (true) {
                    if (coins.get(i) > k) {
                        break;
                    }
                    k -= coins.get(i);
                    answer++;
                }
            }
            if (k == 0) {
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }

    private static void addCoin(int depth, int n) throws IOException {
        if (depth == n) {
            return;
        }
        st = new StringTokenizer(br.readLine());
        coins.add(Integer.parseInt(st.nextToken()));
        addCoin(depth + 1, n);
    }
}