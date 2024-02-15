import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            TreeMap<Integer, Integer> numberMap = new TreeMap<>();
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int number = Integer.parseInt(st.nextToken());

                if (Objects.equals(command, "I")) {
                    numberMap.put(number, numberMap.getOrDefault(number, 0) + 1);
                }
                if (Objects.equals(command, "D")) {
                    if (numberMap.isEmpty()) {
                        continue;
                    }
                    int num = 0;
                    if (number == -1) {
                        num = numberMap.firstKey();
                    }
                    if (number == 1) {
                        num = numberMap.lastKey();
                    }
                    if (numberMap.put(num, numberMap.get(num) - 1) == 1) {
                        numberMap.remove(num);
                    }
                }
            }

            if (numberMap.isEmpty()) {
                sb.append("EMPTY");
            } else {
                sb.append(numberMap.lastKey());
                sb.append(" ");
                sb.append(numberMap.firstKey());
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}