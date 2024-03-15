import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static boolean[] visited;
    private static int[][] gears;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        gears = new int[4][8];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            String inputGear = st.nextToken();
            String[] splitInputGear = inputGear.split("");
            for (int j = 0; j < splitInputGear.length; j++) {
                gears[i][j] = Integer.parseInt(splitInputGear[j]);
            }
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            visited = new boolean[4];
            recursiveSolve(number - 1, direction);
        }

        bw.write(String.valueOf(getAnswer()));
        bw.flush();
        bw.close();

    }

    private static void recursiveSolve(int number, int direction) {

        if (visited[number]) {
            return;
        }

        visited[number] = true;
        int right = gears[number][2];
        int left = gears[number][6];
        int tmpNum = gears[number][0];

        // 시계방향회전
        if (direction == 1) {
            for (int i = 7; i > 0; i--) {
                int newIdx = (i + 1) % 8;
                gears[number][newIdx] = gears[number][i];
            }
            gears[number][1] = tmpNum;
        }

        // 반시계방향회전
        if (direction == -1) {
            for (int i = 1; i <= 7; i++) {
                int newIdx = (8 + (i - 1)) % 8;
                gears[number][newIdx] = gears[number][i];
            }
            gears[number][7] = tmpNum;
        }

        if ((number + 1) <= 3) {
            if (gears[number + 1][6] != right) {
                recursiveSolve(number + 1, -direction);
            }
        }

        if ((number - 1) >= 0) {
            if (gears[number - 1][2] != left) {
                recursiveSolve(number - 1, -direction);
            }
        }
    }

    private static int getAnswer() {
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == 1) {
                answer += Math.pow(2, i);
            }
        }
        return answer;
    }
}