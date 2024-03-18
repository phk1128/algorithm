// 뱀은 (1,1)에 위치
// 뱀은 처음에 오른쪽을 향하고 있다.
// 1. 뱀은 몸길이를 늘려 "머리"를 "다음칸"에 위치 시킨다.
// 2. 벽이나 자기자신의 몸과 부딪히면 게임 끝
// 3. 이동한 칸에 사과가 있다면, 그 칸에 있던 "사과가 없어지고" "꼬리는 움직이지 않는다." => 몸길이가 늘어난다.
// 4. 이동한 칸에 사과가 없다면, "몸길이를 줄여서 꼬리가 위치한 칸을 비워준다" 즉, 몸길이는 변하지 않는다.
// 5. C가 L일 경우 반시계, D일 경우 시계로 90도 회전

import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int[][] mapView;
    private static int[][] directionLog;
    private static Queue<Command> commands;
    private static int[][] directions;
    private static int[] tail;
    private static int answer;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        mapView = new int[N + 1][N + 1];
        directionLog = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            mapView[r][c] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        commands = new LinkedList<>();
        directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우하좌상
        tail = new int[]{1, 1};

        while (L-- > 0) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            commands.offer(new Command(time, command));
        }

        mapView[1][1] = -1;
        directionLog[1][1] = 0;
        recursiveSolve(1, 1, 0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int r, int c, int d, int count) {

        directionLog[r][c] = d;
        mapView[r][c] = -1;

        int newR = r + directions[d][0];
        int newC = c + directions[d][1];

        if (!(newR >= 1 && newR <= N && newC >= 1 && newC <= N)) {
            answer = count + 1;
            return;
        }
        if (mapView[newR][newC] == -1) {
            answer = count + 1;
            return;
        }

        if (mapView[newR][newC] == 0) {
            int tailR = tail[0];
            int tailC = tail[1];
            mapView[tailR][tailC] = 0;
            int dL = directionLog[tailR][tailC];
            tail = new int[]{tailR + directions[dL][0], tailC + directions[dL][1]};
        }

        count++;

        if (!commands.isEmpty()) {
            Command command = commands.peek();
            int time = command.getTime();

            if (time == count) {
                String rotation = command.getRotation();
                // 반시계 방향 회전
                if (Objects.equals("L", rotation)) {
                    d = (4 + (d - 1)) % 4;
                }
                //시계 방향 회전
                if (Objects.equals("D", rotation)) {
                    d = (d + 1) % 4;
                }
                commands.poll();
            }
        }

        recursiveSolve(newR, newC, d, count);
    }

    static class Command {

        private int time;
        private String rotation;

        public Command(int time, String rotation) {
            this.time = time;
            this.rotation = rotation;
        }

        private int getTime() {
            return this.time;
        }

        private String getRotation() {
            return this.rotation;
        }
    }
}