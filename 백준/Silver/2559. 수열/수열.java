import java.io.IOException;
import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int max = 0;

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        List<Integer> numbers = new ArrayList<>();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Stream.iterate(0, i -> i + 1).limit(n).forEach(i -> {
            numbers.add(Integer.parseInt(st.nextToken()));
        });

        for (int i = 0; i < k; i++) {
            max += numbers.get(i);
        }

        int answer = max;

        for (int i = k; i < n; i++) {
            max += numbers.get(i);
            max -= numbers.get(i-k);

            answer = Math.max(max, answer);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
