// 파란색칸에 도착하면 파란색 화살표로 이동
// A말을 선택하여 이동시키려 하는데 만약 이동을 마치는칸에 다른 말이 있으면 A말을 선택할 수 없다.
// 단, 도착칸이라면 가능
// 말이 이동을 마치는칸에 점수를 획득
// 말은 4개 존재

// 루트는 4 가지
// 2 -> 40 파란색칸에 멈추지 않았을때
// 10 -> 40 파란색 10에 멈췄을때
// 20 -> 40 파란색 20에 멈췄을때
// 30 -> 40 파란색 30에 멈췄을때

import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[] dice;
    private static Horse[] horseGroup;
    private static int[][] roots;
    private static int max;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        dice = new int[10];
        horseGroup = new Horse[4];
        max = 0;

        for (int i = 0; i < 4; i++) {
            horseGroup[i] = new Horse(0, 0, 0, false);
        }

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        roots = new int[4][21];

        int score = 2;
        for (int i = 1; i <= 20; i++) {
            roots[0][i] = score;
            score += 2;
        }
        roots[1] = new int[]{0, 13, 16, 19, 25, 30, 35, 40}; // 10에 멈췄을때 사이즈 8
        roots[2] = new int[]{0, 22, 24, 25, 30, 35, 40}; // 20에 멈췄을때 사이즈 7
        roots[3] = new int[]{0, 28, 27, 26, 25, 30, 35, 40}; // 30에 멈췄을때 사이즈 8

        solve(0, 0);
        System.out.println(max);
    }

    private static void solve(int depth, int score) {

        if (depth == 10) {
            max = Math.max(score, max);
            return;
        }

        for (int i = 0; i < 4; i++) {

            Horse horse = horseGroup[i];
            if (horse.position == -1) {
                continue;
            }
            if (!isMove(horse, depth, i)) {
                continue;
            }
            solve(depth + 1, score + move(i, depth));
            horseGroup[i] = horse;
        }

    }

    private static boolean isMove(Horse horse, int depth, int idx) {

        int[] root = roots[horse.root];
        if (root.length - 1 < horse.idx + dice[depth]) {
            return true;
        }
        int nextPosition = root[horse.idx + dice[depth]];

        for (int i = 0; i < 4; i++) {
            if (i != idx) {
                if (horseGroup[i].position == nextPosition) {
                    if (horseGroup[i].root == horse.root) {
                        return false;
                    } else {
                        if (
                                nextPosition == 10
                                || nextPosition == 20
                                || nextPosition == 25
                                || nextPosition == 40
                                || nextPosition == 35
                        ) {
                            return false;
                        }
                        if (nextPosition == 30) {
                            if (horse.root != 0 && !horseGroup[i].isBlue) {
                                return false;
                            }

                            if (horse.root == 0 && horseGroup[i].root == 3 && horseGroup[i].isBlue) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private static int move(int idx, int depth) {

        Horse horse = horseGroup[idx];
        int horseIdx = horse.idx;
        int[] root = roots[horse.root];

        if (root.length - 1 < horseIdx + dice[depth]) {
            horseGroup[idx] = new Horse(-1, -1, -1, false);
            return 0;
        }

        if (root[horseIdx + dice[depth]] == 10 && horse.root == 0) {
            horseGroup[idx] = new Horse(0, 1, 10, true);
            return 10;
        }

        if (root[horseIdx + dice[depth]] == 20 && horse.root == 0) {
            horseGroup[idx] = new Horse(0, 2, 20,true);
            return 20;
        }

        if (root[horseIdx + dice[depth]] == 30 && horse.root == 0) {
            horseGroup[idx] = new Horse(0, 3, 30, true);
            return 30;
        }

        horseGroup[idx] = new Horse(horseIdx + dice[depth], horse.root, root[horseIdx + dice[depth]], false);
        return root[horseIdx + dice[depth]];
    }

    static class Horse {

        int idx;
        int root;
        int position;
        boolean isBlue;

        public Horse(int idx, int root, int position, boolean isBlue) {
            this.idx = idx;
            this.root = root;
            this.position = position;
            this.isBlue = isBlue;
        }
    }

}