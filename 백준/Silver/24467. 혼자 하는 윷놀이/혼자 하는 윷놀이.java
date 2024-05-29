import java.util.*;
import java.io.*;

class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Integer> turns;

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        turns = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            int turn = 0;
            for (int j = 0; j < 4; j++) {
                if (Objects.equals(input.charAt(j), '0')) {
                    turn++;
                }
            }
            if (Objects.equals(input, "1111")) {
                turn = 5;
            }
            turns.add(turn);
            if (Objects.equals(input, "0000") || Objects.equals(input, "1111")) {
                i--;
            }
        }

        String answer = "LOSE";
        if (isGoal()) {
            answer = "WIN";
        }

        bw.write(answer);
        bw.flush();
        bw.close();
    }

    private static boolean isGoal() {

        int[] route = new int[]{0,1,2,3,4,5,6};
        int cp = 0;
        int cr = 0;
        for (int turn : turns) {
            cp += turn;
            if (cr != route[6] && cr != route[5]) {
                cr = route[cp / 5];
            }

            // 턴지점 1
            if (cp == 5 && cr == route[1]) {
                cp = 0;
                cr = route[6];
            }

            // 턴지점 2
            if (cp == 3 && cr == route[6]) {
                cr = route[5];
            }

            // 턴지점 3
            if (cp == 10 && cr == route[2]) {
                cr = route[5];
                cp = 0;
            }

            if (cp > 20 && cr == route[4]) {
                return true;
            }
            if (cp > 6 && cr == route[5]) {
                return true;
            }
            if (cp > 11 && cr == route[6]) {
                return true;
            }
        }
        return false;
    }
}