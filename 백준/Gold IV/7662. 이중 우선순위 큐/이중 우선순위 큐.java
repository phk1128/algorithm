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
            Queue<Integer> min = new PriorityQueue<>();
            Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> numberMap = new HashMap<>();
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int number = Integer.parseInt(st.nextToken());

                if (Objects.equals(command, "I")) {
                    min.offer(number);
                    max.offer(number);
                    numberMap.put(number, numberMap.getOrDefault(number, 0) + 1);
                }
                if (Objects.equals(command, "D")) {
                    if (numberMap.isEmpty()) {
                        continue;
                    }
                        if (number == -1) {
                            commandD(min, numberMap);
                        }
                        if (number == 1) {
                            commandD(max, numberMap);
                        }
                }
            }

            if (numberMap.isEmpty()) {
                sb.append("EMPTY");
            } else {
                int number = commandD(max, numberMap);
                sb.append(number);
                sb.append(" ");
                if (!numberMap.isEmpty()) {
                    number = commandD(min, numberMap);
                }
                sb.append(number);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int commandD(Queue<Integer> queue, Map<Integer, Integer> numberMap) {

        int number = queue.poll();
        int count = numberMap.getOrDefault(number, 0);

        if (count == 0) {
            return commandD(queue, numberMap);
        }

        if (count == 1) {
            numberMap.remove(number);
        } else {
            numberMap.put(number, count - 1);
        }

        return number;
    }
}