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
            String commands = st.nextToken();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            String[] inputListSplit = st.nextToken().replace("[", "").replace("]", "").split(",");
            Deque<String> inputList = new LinkedList<>();
            for (String input : inputListSplit) {
                if (!Objects.equals(input, "")) {
                    inputList.offer(input);
                }
            }
            sb.append(solve(commands, inputList));
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static String solve(String commands, Deque<String> inputList) {
        boolean flag = true;
        for (String command : commands.split("")) {

            if (Objects.equals(command, "R")) {
                flag = !flag;
            }

            if (Objects.equals(command, "D")) {
                if (inputList.size() == 0) {
                    return "error";
                }
                if (flag) {
                    inputList.removeFirst();
                } else {
                    inputList.removeLast();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (!inputList.isEmpty()) {
            String num;
            if (flag) {
                num = inputList.removeFirst();
            } else {
                num = inputList.removeLast();
            }

            sb.append(num);
            if (!inputList.isEmpty()) {
                sb.append(",");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}