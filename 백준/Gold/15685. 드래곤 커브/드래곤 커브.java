import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] curves;
    private static List<int[]> curveGroup;
    private static boolean[][] visitedPoint;
    private static boolean[][] visitedFace;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        curves = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        curveGroup = new ArrayList<>();
        visitedPoint = new boolean[101][101];
        visitedFace = new boolean[100][100];

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            curveGroup.add(curves[d]);
            generateCurveGroup(0,g);
            move(x, y);
            curveGroup.clear();
        }

        bw.write(String.valueOf(getAnswer()));
        bw.flush();
        bw.close();
    }

    private static int getAnswer() {

        int answer = 0;

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (visitedFace[x][y]) {
                    continue;
                }
                if (visitedPoint[x][y] && visitedPoint[x+1][y] && visitedPoint[x][y+1] && visitedPoint[x+1][y+1]) {
                    visitedFace[x][y] = true;
                    answer++;
                }
            }
        }
        return answer;
    }

    private static void move(int x, int y) {

        visitedPoint[x][y] = true;

        for (int[] curve : curveGroup) {
            int newX = x + curve[0];
            int newY = y + curve[1];
            visitedPoint[newX][newY] = true;
            x = newX;
            y = newY;
        }
    }


    private static void generateCurveGroup(int depth, int g) {

        if (depth == g) {
            return;
        }
        for (int i = (curveGroup.size()) - 1; i >= 0; i--) {
            int newX = -curveGroup.get(i)[0];
            int newY = curveGroup.get(i)[1];
            curveGroup.add(new int[]{newY, newX});
        }

        generateCurveGroup(depth + 1, g);

    }
}