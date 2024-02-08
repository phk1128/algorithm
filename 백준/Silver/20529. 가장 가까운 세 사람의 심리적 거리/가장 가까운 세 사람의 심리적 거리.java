import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static StringBuilder sb;
    private static List<String> students;
    private static boolean[] visited;
    private static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();

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
                visited = new boolean[students.size()];
                recursiveSolve("", 0);
            }
            sb.append(min);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(String pairs, int start) {
        if (min == 0) {
            return;
        }
        if (pairs.split(",").length == 3) {
            List<String> pairsList = Arrays.stream(pairs.split(",")).collect(Collectors.toList());
            min = Math.min(combination("", 0, 0, pairsList, new boolean[3]), min);
            return;
        }
        for (int i = start; i < students.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                recursiveSolve(students.get(i) + "," + pairs, i + 1);
                visited[i] = false;
            }
        }
    }

    private static int combination(String pair, int dist, int start, List<String> pairsList, boolean[] tmpVisited) {
        if (pair.split(",").length == 2) {
            for (int i = 0; i < 4; i++) {
                if (!Objects.equals(pair.split(",")[0].charAt(i), pair.split(",")[1].charAt(i))) {
                    dist += 1;
                }
            }
            return dist;
        }
        for (int i = start; i < pairsList.size(); i++) {
            if (!tmpVisited[i]) {
                tmpVisited[i] = true;
                dist = combination(pairsList.get(i) + "," + pair, dist, i + 1, pairsList, tmpVisited);
                tmpVisited[i] = false;
            }
        }
        return dist;
    }
}