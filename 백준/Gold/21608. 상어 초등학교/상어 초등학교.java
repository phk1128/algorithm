// 1. 인접한 칸에 좋아하는 학생이 가장 많은 칸으로 자리를 정한다. (좋아하는 학생은 4명)
// 2. 1을 만족칸이 여러개면, 인접한 칸 중에서 비어 있는 칸이 가장 많은 칸으로 자리를 정한다.
// 3. 2를 만족하는 칸이 여러개면, 행번호가 가장 작은 칸으로, 그러한 칸도 여러개면 열의 번호가 가장 작은 칸으로 자리를 정한다.
// 4. 만족도는 각 학생별로 인접한 칸에 좋아하는 학생이 몇명인지에 따라 결정된다.
// 5. 인접한 칸에 좋아하는 학생이 0 -> 1 / 1 -> 2 / 2 -> 10 / 3 -> 100 / 4 -> 1000
// 만족도의 총 합을 구해보자

// Seat 객체 만들기
// Seat 객체 compareTo 오버라이딩 1,2,3 조건에 맞게 구현
// 입력을 받는 동시에 자리 배정하기
// 모든 자리를 배치 후 맵을 돌면서 주변에 좋아하는 학생이 몇명인지 체크하면서 답 계산


import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static List<Integer>[][] students;
    private static int[][] mapView;
    private static int[][] directions;
    private static List<Seat> seats;
    private static int[] score;

    static class Seat implements Comparable<Seat> {

        int r;
        int c;
        int favorite;
        int empty;

        public Seat(int r, int c, int favorite, int empty) {
            this.r = r;
            this.c = c;
            this.favorite = favorite;
            this.empty = empty;
        }

        @Override
        public int compareTo(Seat s) {

            if (this.favorite != s.favorite) {
                return s.favorite - this.favorite;
            }
            if (this.empty != s.empty) {
                return s.empty - this.empty;
            }
            if (this.r != s.r) {
                return this.r - s.r;
            }
            return this.c - s.c;
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        mapView = new int[N][N];
        students = new ArrayList[N * N + 1][1];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        score = new int[]{0, 1, 10, 100, 1000};

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            students[number][0] = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                students[number][0].add(Integer.parseInt(st.nextToken()));
            }
            solve(number);
        }

        bw.write(String.valueOf(getAnswer()));
        bw.flush();
        bw.close();
    }

    private static int getAnswer() {
        int answer = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int number = mapView[r][c];
                int favorite = 0;
                for (int[] direction : directions) {
                    int newR = r + direction[0];
                    int newC = c + direction[1];
                    if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                        continue;
                    }

                    if (students[number][0].contains(mapView[newR][newC])) {
                        favorite++;
                    }

                }

                answer += score[favorite];
            }
        }

        return answer;
    }

    private static void solve(int number) {

        seats = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (mapView[r][c] == 0) {
                    addSeat(r, c, number);
                }
            }
        }
        Collections.sort(seats);
        mapView[seats.get(0).r][seats.get(0).c] = number;
    }

    private static void addSeat(int r, int c, int number) {

        int favorite = 0;
        int empty = 0;

        for (int[] direction : directions) {

            int newR = r + direction[0];
            int newC = c + direction[1];

            if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                continue;
            }

            int tmpNumber = mapView[newR][newC];

            if (tmpNumber == 0) {
                empty++;
            }

            if (students[number][0].contains(tmpNumber)) {
                favorite++;
            }
        }
        seats.add(new Seat(r, c, favorite, empty));
    }
}


