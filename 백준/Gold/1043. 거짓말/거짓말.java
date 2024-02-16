import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static boolean[] visited;
    private static boolean[] truth;
    private static List<Integer> truthMan;
    private static List<Integer>[] graph;
    private static List<Integer>[] party;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        truth = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        party = new ArrayList[m];
        truthMan = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            party[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());

        while (p-- > 0) {
            int num = Integer.parseInt(st.nextToken());
            truthMan.add(num);
        }

        for (int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int[] tmpArray = new int[t];

            for (int i = 0; i < t; i++) {
                int num = Integer.parseInt(st.nextToken());
                tmpArray[i] = num;
                party[k].add(num);
            }

            for (int j = 0; j < tmpArray.length - 1; j++) {
                graph[tmpArray[j]].add(tmpArray[j + 1]);
                graph[tmpArray[j + 1]].add(tmpArray[j]);
            }
        }
        int answer = 0;

        for (int num : truthMan) {
            if (!truth[num]) {
                recursiveTruthManFind(num);
            }

        }

        for (List<Integer> par : party) {
            boolean flag = false;
            for (int num : par) {
                if (recursiveSolve(num)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }

    private static boolean recursiveSolve(int start) {

        boolean flag = false;

        if (truth[start]) {
            return true;
        }

        visited[start] = true;

        for (int num : graph[start]) {
            if (!visited[start]) {
                flag = recursiveSolve(num);
            }
        }
        return flag;
    }

    private static void recursiveTruthManFind(int start) {

        truth[start] = true;

        for (int num : graph[start]) {
            if (!truth[num]) {
                recursiveTruthManFind(num);
            }
        }
    }
}