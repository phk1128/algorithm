import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;

    private static int fishCount;
    private static int simulationSteps;
    private static final int gridSize = 4;

    private static int[][] fishMoveDirs;
    private static int[][] sharkMoveDirs;
    private static int[] sharkMoveLog;
    private static int[] sharkBestRoute;
    private static int[][][] fishMap;
    private static int[][][] nextFishMap;
    private static int maxEatenFish;
    private static int[][] smellMap;
    private static int sharkRow;
    private static int sharkCol;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        fishCount = Integer.parseInt(st.nextToken());
        simulationSteps = Integer.parseInt(st.nextToken());

        fishMoveDirs = new int[][]{
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1},
            {0, 1}, {1, 1}, {1, 0}, {1, -1}
        };

        sharkMoveDirs = new int[][]{
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
        };

        smellMap = new int[gridSize + 1][gridSize + 1];
        sharkMoveLog = new int[3];
        fishMap = new int[gridSize + 1][gridSize + 1][8];

        for (int i = 0; i < fishCount; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            fishMap[row][col][dir - 1]++;
        }

        st = new StringTokenizer(br.readLine());
        sharkRow = Integer.parseInt(st.nextToken());
        sharkCol = Integer.parseInt(st.nextToken());

        while (simulationSteps-- > 0) {
            simulate();
        }
        System.out.println(getRemainingFish());
    }

    private static int getRemainingFish() {
        int total = 0;
        for (int row = 1; row <= gridSize; row++) {
            for (int col = 1; col <= gridSize; col++) {
                for (int dir = 0; dir < 8; dir++) {
                    total += fishMap[row][col][dir];
                }
            }
        }
        return total;
    }

    private static void simulate() {
        moveAllFish();
        moveShark();
        decreaseSmell();
        duplicateFish();
    }

    private static void duplicateFish() {
        for (int row = 1; row <= gridSize; row++) {
            for (int col = 1; col <= gridSize; col++) {
                for (int dir = 0; dir < 8; dir++) {
                    fishMap[row][col][dir] += nextFishMap[row][col][dir];
                }
            }
        }
    }

    private static void decreaseSmell() {
        for (int row = 1; row <= gridSize; row++) {
            for (int col = 1; col <= gridSize; col++) {
                if (smellMap[row][col] > 0) smellMap[row][col]--;
            }
        }
    }

    private static void moveShark() {
        maxEatenFish = Integer.MIN_VALUE;
        sharkBestRoute = new int[3];
        dfsSharkRoute(sharkRow, sharkCol, 0, 0);

        for (int dir : sharkBestRoute) {
            sharkRow += sharkMoveDirs[dir][0];
            sharkCol += sharkMoveDirs[dir][1];
            for (int i = 0; i < 8; i++) {
                if (nextFishMap[sharkRow][sharkCol][i] > 0) {
                    nextFishMap[sharkRow][sharkCol][i] = 0;
                    smellMap[sharkRow][sharkCol] = 3;
                }
            }
        }
    }

    private static void dfsSharkRoute(int row, int col, int eaten, int depth) {
        if (depth == 3) {
            if (maxEatenFish < eaten) {
                maxEatenFish = eaten;
                sharkBestRoute = Arrays.copyOf(sharkMoveLog, 3);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + sharkMoveDirs[i][0];
            int nextCol = col + sharkMoveDirs[i][1];
            if (!(nextRow >= 1 && nextRow <= gridSize && nextCol >= 1 && nextCol <= gridSize)) continue;

            int[] backup = Arrays.copyOf(nextFishMap[nextRow][nextCol], 8);
            int newEaten = eaten;
            for (int j = 0; j < 8; j++) {
                newEaten += nextFishMap[nextRow][nextCol][j];
                nextFishMap[nextRow][nextCol][j] = 0;
            }
            sharkMoveLog[depth] = i;
            dfsSharkRoute(nextRow, nextCol, newEaten, depth + 1);
            nextFishMap[nextRow][nextCol] = backup;
        }
    }

    private static void moveAllFish() {
        nextFishMap = new int[gridSize + 1][gridSize + 1][8];
        for (int row = 1; row <= gridSize; row++) {
            for (int col = 1; col <= gridSize; col++) {
                for (int dir = 0; dir < 8; dir++) {
                    if (fishMap[row][col][dir] > 0) {
                        moveSingleFish(row, col, dir);
                    }
                }
            }
        }
    }

    private static void moveSingleFish(int row, int col, int dir) {
        int rotatedDir = dir;
        boolean moved = false;
        for (int i = 0; i < 8; i++) {
            int nextRow = row + fishMoveDirs[rotatedDir][0];
            int nextCol = col + fishMoveDirs[rotatedDir][1];
            if (nextRow >= 1 && nextRow <= gridSize && nextCol >= 1 && nextCol <= gridSize) {
                if (!(nextRow == sharkRow && nextCol == sharkCol) && smellMap[nextRow][nextCol] == 0) {
                    nextFishMap[nextRow][nextCol][rotatedDir] += fishMap[row][col][dir];
                    moved = true;
                    break;
                }
            }
            rotatedDir = (8 + (rotatedDir - 1)) % 8;
        }
        if (!moved) {
            nextFishMap[row][col][dir] += fishMap[row][col][dir];
        }
    }
}
