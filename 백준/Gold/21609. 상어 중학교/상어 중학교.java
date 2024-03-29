//일반 블록은 M가지 색을 가지고 있다 -> 검은색 -1 / 무지개 0
//블록 그룹에는 일반 블록이 적어도 하나 존재해야함, 이 때 일반 블록의 색은 모두 같아야한다.
//검은 블록은 포함 되어 있으면 안되고 , 무지개 블록은 얼마든지 들어있어도됨
//그룹에 속한 블록의 개수는 2보다 크거나 같아야함
//블록 기준은 무지개 블록이 아닌 블록 중 행의 번호가 가장 작은 블록 중 열의 번호가 가장 작은 블록

import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] mapView;
    private static int[][] rotatedMapView;
    private static boolean[][] colorVisited;
    private static boolean[][] rainbowVisited;
    private static int[][] directions;
    private static List<BlockGroup> blockGroups;
    private static int rainbowCount;
    private static int size;
    private static int score;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mapView = new int[N][N];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        score = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int number = Integer.parseInt(st.nextToken());
                mapView[r][c] = number;
            }
        }

        solve();

        bw.write(String.valueOf(score));
        bw.flush();
        bw.close();
    }

    private static void solve() {

        addBlockGroup();
        if (blockGroups.isEmpty()) {
            return;
        }
        Collections.sort(blockGroups);
        BlockGroup blockGroup = blockGroups.get(blockGroups.size() - 1);
        if (blockGroup.size < 2) {
            return;
        }

        score += (blockGroup.size * blockGroup.size);
        removeBlock(blockGroup.r, blockGroup.c, blockGroup.color);
        gravity();
        rotate();
        gravity();

        solve();

    }


    private static void gravity() {

        for (int r = N - 2; r >= 0; r--) {
            for (int c = 0; c < N; c++) {
                if (mapView[r][c] == -1) {
                    continue;
                }
                int tmpR = r;
                while (true) {
                    if (tmpR == N - 1) {
                        mapView[tmpR][c] = mapView[r][c];
                        mapView[r][c] = -3;
                        break;
                    }
                    if (mapView[tmpR + 1][c] == -3) {
                        tmpR++;
                    } else {
                        if (tmpR > r) {
                            mapView[tmpR][c] = mapView[r][c];
                            mapView[r][c] = -3;
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }

    private static void rotate() {

        rotatedMapView = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                rotatedMapView[N - 1 - c][r] = mapView[r][c];
            }
        }

        for (int i = 0; i < N; i++) {
            mapView[i] = Arrays.copyOf(rotatedMapView[i], N);
        }

    }

    private static void removeBlock(int r, int c, int color) {

        mapView[r][c] = -3;

        for (int[] direction : directions) {
            int newR = r + direction[0];
            int newC = c + direction[1];

            if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                continue;
            }

            if (mapView[newR][newC] != color && mapView[newR][newC] != 0) {
                continue;
            }
            removeBlock(newR, newC, color);
        }
    }

    private static void addBlockGroup() {

        colorVisited = new boolean[N][N];
        blockGroups = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!colorVisited[r][c] && mapView[r][c] != 0 && mapView[r][c] != -1 && mapView[r][c] != -3) {
                    rainbowVisited = new boolean[N][N];
                    int color = mapView[r][c];
                    colorVisited[r][c] = true;
                    size = 1;
                    rainbowCount = 0;
                    search(r, c, color);
                    if (size >= 2) {
                        blockGroups.add(new BlockGroup(r, c, color, size, rainbowCount));
                    }
                }
            }
        }
    }

    private static void search(int r, int c, int color) {

        for (int[] direction : directions) {
            int newR = r + direction[0];
            int newC = c + direction[1];

            if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                continue;
            }

            if (mapView[newR][newC] != color && mapView[newR][newC] != 0) {
                continue;
            }

            if (rainbowVisited[newR][newC] || colorVisited[newR][newC]) {
                continue;
            }

            if (mapView[newR][newC] == 0) {
                rainbowVisited[newR][newC] = true;
                rainbowCount++;
            }
            if (mapView[newR][newC] == color) {
                colorVisited[newR][newC] = true;
            }

            size++;
            search(newR, newC, color);
        }
    }

    static class BlockGroup implements Comparable<BlockGroup> {

        int r;
        int c;
        int color;
        int size;
        int rainbowCount;

        public BlockGroup(int r, int c, int color, int size, int rainbowCount) {
            this.r = r;
            this.c = c;
            this.color = color;
            this.size = size;
            this.rainbowCount = rainbowCount;
        }

        @Override
        public int compareTo(BlockGroup b) {

            if (this.size == b.size) {
                if (this.rainbowCount == b.rainbowCount) {
                    if (this.r == b.r) {
                        return this.c - b.c;
                    }
                    return this.r - b.r;
                }
                return this.rainbowCount - b.rainbowCount;
            }

            return this.size - b.size;
        }
    }
}