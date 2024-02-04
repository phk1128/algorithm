import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Integer> buttons;
    private static List<Integer> channel;
    private static List<Integer> errorButtons;
    private static long min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        min = Math.abs(100 - n);

        if (m == 0) {

            min = Math.min(min, String.valueOf(n).length());
            bw.write(String.valueOf(min));
            bw.flush();
            bw.close();
            return;
        }

        if (m == 10) {

            bw.write(String.valueOf(min));
            bw.flush();
            bw.close();
            return;
        }

        buttons = new ArrayList<>();
        errorButtons = new ArrayList<>();
        channel = Arrays.stream(String.valueOf(n).split("")).map(Integer::parseInt).collect(Collectors.toList());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            errorButtons.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < 10; i++) {
            if (!errorButtons.contains(i)) {
                buttons.add(i);
            }
        }

        for (int i = -1; i < 2; i++) {
            
            if (i == -1 && channel.size() == 1) {
                continue;
            }
            recursiveSolve("", 0, n, channel.size() + i);
        }

        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(String numStr, int depth, int n, int limitDepth) {

        if (depth == limitDepth) {
            int resultDiff = Math.abs(Integer.parseInt(numStr) - n);
            min = Math.min(min, limitDepth + resultDiff);
            return;
        }

        for (int button : buttons) {
            recursiveSolve(numStr + button, depth + 1, n, limitDepth);
        }
    }

}