import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[][] mapView;
    private static int[] dr;
    private static int[] dc;
    private static Student[] students;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        mapView = new int[N][N];
        dr = new int[]{-1, 1, 0, 0};
        dc = new int[]{0, 0, -1, 1};
        students = new Student[N * N + 1];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                st = new StringTokenizer(br.readLine());
                int number = Integer.parseInt(st.nextToken());
                final Student student = new Student(number, new ArrayList<>());
                student.addLikeNumber(Integer.parseInt(st.nextToken()));
                student.addLikeNumber(Integer.parseInt(st.nextToken()));
                student.addLikeNumber(Integer.parseInt(st.nextToken()));
                student.addLikeNumber(Integer.parseInt(st.nextToken()));
                students[number] = student;
                searchSit(number, student.likeNumbers);
            }
        }
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += calculateScore(r, c);
            }
        }
        System.out.println(answer);
    }

    private static void searchSit(int number, List<Integer> likeNumber) {
        List<Sit> sits = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (mapView[r][c] != 0) {
                    continue;
                }
                int empty = countEmpty(r, c);
                int like = countLike(r, c, likeNumber);
                sits.add(new Sit(r, c, like, empty));
            }
        }
        Collections.sort(sits);
        Sit sit = sits.get(0);
        mapView[sit.r][sit.c] = number;
    }

    private static int calculateScore(int r, int c) {
        int number = mapView[r][c];
        List<Integer> likeNumbers = students[number].likeNumbers;
        int score = 0;
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];
            if(!(nR >= 0 && nR < N && nC >= 0 && nC < N)) {
                continue;
            }
            if (!likeNumbers.contains(mapView[nR][nC])) {
                continue;
            }
            count++;
        }
        if (count != 0) {
            score = (int) Math.pow(10, count - 1);
        }
        return score;
    }


    private static int countEmpty(int r, int c) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];
            if (!(nR >= 0 && nR < N && nC >= 0 && nC < N)) {
                continue;
            }
            if (mapView[nR][nC] != 0) {
                continue;
            }
            count++;
        }
        return count;
    }

    private static int countLike(int r, int c, List<Integer> likeNumber) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];
            if (!(nR >= 0 && nR < N && nC >= 0 && nC < N)) {
                continue;
            }
            if (mapView[nR][nC] == 0) {
                continue;
            }
            if (!likeNumber.contains(mapView[nR][nC])) {
                continue;
            }
            count++;
        }
        return count;
    }

    static class Student{
        int number;
        List<Integer> likeNumbers;

        public Student(final int number, final List<Integer> likeNumbers) {
            this.number = number;
            this.likeNumbers = likeNumbers;
        }

        public void addLikeNumber(int number) {
            likeNumbers.add(number);
        }
    }

    static class Sit implements Comparable<Sit>{
        int r;
        int c;
        int likeCount;
        int emptyCount;

        public Sit(final int r, final int c, final int likeCount, final int emptyCount) {
            this.r = r;
            this.c = c;
            this.likeCount = likeCount;
            this.emptyCount = emptyCount;
        }

        @Override
        public int compareTo(Sit other) {
            if (this.likeCount != other.likeCount) {
                return other.likeCount - this.likeCount;
            }
            if (this.emptyCount != other.emptyCount) {
                return other.emptyCount - this.emptyCount;
            }
            return this.r - other.r;
        }
    }
}
