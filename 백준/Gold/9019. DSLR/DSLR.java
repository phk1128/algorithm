import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int number;
    private static int target;
    private static String[] command;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            number = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            command = new String[10000];
            Arrays.fill(command, "");
            visited[number] = true;
            bfsSolve();
            sb.append(command[target]);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void bfsSolve() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(number);
        visited[number] = true;
        while (!queue.isEmpty() && !visited[target]) {
            int now = queue.poll(); // 큐에서 값을 뺀다 = 현재 탐색 위치

            int D = (2 * now) % 10000; // n을 두배로 바꾸고 10000으로 나눈 나머지
            int S = now == 0 ? 9999 : now - 1; // 0일 때, 9999, 아니면 1을 빼준다
            int L = (now % 1000) * 10 + now / 1000; // 1234 -> 2341 : 1234를 1000으로 나눈 나머지(234)에 10을 곱함=2340, 1234를 1000으로 나누면 1, 2340+1=2341
            int R = (now % 10) * 1000 + now / 10; // 1234 -> 4123 : 1234를 10으로 나눈 나머지에 1000 곱합 = 4000, 1234를 10으로 나누면 123, 4000+123=4123

            if (!visited[D]) {
                queue.add(D); // 큐에 넣는다
                visited[D] = true; // 방문처리한다
                command[D] = command[now] + "D"; // 명령어를 추가한다
            }

            if (!visited[S]) {
                queue.add(S);
                visited[S] = true;
                command[S] = command[now] + "S";
            }

            if (!visited[L]) {
                queue.add(L);
                visited[L] = true;
                command[L] = command[now] + "L";
            }

            if (!visited[R]) {
                queue.add(R);
                visited[R] = true;
                command[R] = command[now] + "R";
            }
        }
    }
}