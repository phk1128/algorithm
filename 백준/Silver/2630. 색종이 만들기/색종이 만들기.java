import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] mapView;
    private static int white;
    private static int blue;
    private static StringBuilder sb;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        mapView = new int[n][n];
        white = 0;
        blue = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                mapView[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursiveSolve(0, 0, n);

        sb = new StringBuilder();
        sb.append(white);
        sb.append("\n");
        sb.append(blue);
        sb.append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }

    private static void recursiveSolve(int x, int y, int size) {

        if (isSameColor(x, y, size)) {
            if (mapView[y][x] == 1) {
                blue++;
            }
            if (mapView[y][x] == 0) {
                white++;
            }
            return;
        }
        int newSize = size / 2;

        recursiveSolve(x + newSize, y, newSize); // 제1사분면
        recursiveSolve(x, y, newSize); // 제2사분면
        recursiveSolve(x, y + newSize, newSize); // 제3사분면
        recursiveSolve(x + newSize, y + newSize, newSize); // 제4사분면
    }

    private static boolean isSameColor(int x, int y, int size) {

        int color = mapView[y][x];

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (color != mapView[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}