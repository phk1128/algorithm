import java.util.*;
import java.io.*;

class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[] dices;
    private static int[] route;
    private static int[] routeIdx;
    private static int[][] routes;
    private static boolean[] blueZone;
    private static int max;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dices = new int[10];
        route = new int[4];
        routeIdx = new int[4];
        blueZone = new boolean[4];
        max = 0;
        st = new StringTokenizer(br.readLine());
        String[] input = st.nextToken("").split(" ");
        for (int i = 0; i < 10; i++) {
            dices[i] = Integer.parseInt(input[i]);
        }
        routes = new int[][]{
                {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40}
                , {10, 13, 16, 19, 25, 30, 35, 40}
                , {20, 22, 24, 25, 30, 35, 40}
                , {30, 28, 27, 26, 25, 30, 35, 40}
        };

        recursiveSolve(0, 0, 10);
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();

    }

    private static void recursiveSolve(int total, int depth, int limit) {

        if (depth == limit) {
            max = Math.max(max, total);
            return;
        }

        for (int i = 0; i < 4; i++) {

            int cIdx = routeIdx[i];
            int cR = route[i];
            boolean cB = blueZone[i];

            if (cR == -1) {
                continue;
            }

            int nIdx = cIdx + dices[depth];
            int nR = getNextRoute(cR, nIdx);
            boolean nB = false;

            if (nR != cR) {
                nIdx = 0;
                nB = true;
            }

            if (!isMove(i, nR, nIdx, nB)) {
                continue;
            }

            int score = 0;
            if (nR != -1) {
                score = routes[nR][nIdx];
            }

            route[i] = nR;
            routeIdx[i] = nIdx;
            blueZone[i] = nB;
            recursiveSolve(total + score, depth + 1, limit);
            route[i] = cR;
            routeIdx[i] = cIdx;
            blueZone[i] = cB;
        }
    }


    private static int getNextRoute(int cR, int nIdx) {

        if (isGoal(cR, nIdx)) {
            return -1;
        }

        if (cR == 0) {
            if (routes[0][nIdx] == 10) {
                return 1;
            }
            if (routes[0][nIdx] == 20) {
                return 2;
            }
            if (routes[0][nIdx] == 30) {
                return 3;
            }
        }
        return cR;
    }

    private static boolean isGoal(int cR, int nIdx) {
        if (cR == 0 && nIdx > 20) {
            return true;
        }
        if (cR == 1 && nIdx > 7) {
            return true;
        }
        if (cR == 2 && nIdx > 6) {
            return true;
        }
        if (cR == 3 && nIdx > 7) {
            return true;
        }
        return false;
    }

    private static boolean isMove(int horseIdx, int nR, int nIdx, boolean nB) {

        if (nR == -1) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            if (i == horseIdx || route[i] == -1) {
                continue;
            }
            if (nR == route[i] && nIdx == routeIdx[i]) {
                return false;
            }
            if (nR != 0 && route[i] != 0 && !nB && !blueZone[i]) {
                if (routes[nR][nIdx] == routes[route[i]][routeIdx[i]]) {
                    return false;
                }
            }
            if (routes[nR][nIdx] == 40 && routes[route[i]][routeIdx[i]] == 40) {
                return false;
            }
        }
        return true;
    }
}
