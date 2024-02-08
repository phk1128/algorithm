import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static StringBuilder sb;
    private static List<String> students;
    private static String[] comb;
    private static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();
        comb = new String[3];

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            students = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            if (n > 32) {
                min = 0;
            } else {
                while (n-- > 0) {
                    String student = st.nextToken();
                    students.add(student);
                }
                min = Integer.MAX_VALUE;
                recursiveSolve(0, 0);
            }
            sb.append(min);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int depth, int start) {
        if (min == 0) {
            return;
        }
        if (depth == 3) {
            min = Math.min(min, getDist());
            return;
        }
        for (int i = start; i < students.size(); i++) {
            comb[depth] = students.get(i);
            recursiveSolve(depth + 1, i + 1);
        }
    }

    private static int getDist() {
        int dist = 0;
        for (int i = 0; i < 4; i++) {
            if (!Objects.equals(comb[0].charAt(i), comb[1].charAt(i))) {
                dist++;
            }
            if (!Objects.equals(comb[0].charAt(i), comb[2].charAt(i))) {
                dist++;
            }
            if (!Objects.equals(comb[1].charAt(i), comb[2].charAt(i))) {
                dist++;
            }
        }
        return dist;
    }
}