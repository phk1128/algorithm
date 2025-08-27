import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main  {

    private static int N, M;
    private static int[] arr;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (exist(0, N - 1, numbers[i])) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    private static boolean exist(int start, int end, int target) {
        if (start >= end) {
            return arr[start] == target;
        }
        int mid = (start + end) / 2;

        if (arr[mid] < target) {
            return exist(mid + 1, end, target);
        }

        if (arr[mid] >= target) {
            return exist(start, mid, target);
        }
        return false;
    }
}
