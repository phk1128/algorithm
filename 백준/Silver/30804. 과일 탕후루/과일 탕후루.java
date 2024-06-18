import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        int[] count = new int[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = arr[0];
        int end = arr[1];
        int max = 0;
        int stack = 0;
        for (int i = 1; i <= N; i++) {
            int num = arr[i];
            if (num == start || num == end) {
                if (num != end) {
                    start = end;
                    end = num;
                    stack = 0;
                }
            } else {
                count[start] = 0;
                count[end] = stack;
                start = end;
                end = num;
                stack = 0;
            }
            stack++;
            count[num]++;
            int sum = count[start] + count[end];
            max = Math.max(sum, max);
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();

    }
}