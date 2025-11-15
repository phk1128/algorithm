import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dpL = new int[N];

        for (int i = 0; i < N; i++) {
            dpL[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dpL[i] = Math.max(dpL[i] , dpL[j] + 1);
                }
            }
        }

        int[] dpR = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            dpR[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    dpR[i] = Math.max(dpR[i], dpR[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dpL[i] + dpR[i] - 1);
        }

        System.out.println(max);

    }

}
