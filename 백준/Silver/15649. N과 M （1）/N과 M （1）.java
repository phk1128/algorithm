import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static boolean[] visited;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];

        dfs(n,m,new Stack<>());
        bw.close();

    }
    
    private static void dfs(int n, int m, Stack<String> result) throws IOException {
        if (result.size() == m) {
            bw.write(String.join(" ", result) + "\n");
            bw.flush();
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result.push(String.valueOf(i));
                dfs(n,m,result);
                result.pop();
                visited[i] = false;
            }
        }
    }
}
