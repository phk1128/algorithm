import java.util.*;
import java.io.*;


public class Main {

    private static int[][] mapView;
    private static int max;
    private static int min;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        mapView = new int[N][M];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                final int h = Integer.parseInt(st.nextToken());
                mapView[r][c] = h;
                min = Math.min(min, h);
                max = Math.max(max, h);
            }
        }
        final int[] answer = getAnswer(N, M, B);

        StringBuilder sb = new StringBuilder();
        sb.append(answer[0]);
        sb.append(" ");
        sb.append(answer[1]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int[] getAnswer(int N, int M, int B) {
        List<int[]> results = new ArrayList<>();
        for (int h = min; h <= max; h++) {
            final int[] result = getResult(N, M, B, h);
            if (result[0] >= 0) {
                results.add(new int[]{result[1], h});
            }
        }
        results.sort((r1, r2) -> {
            if (r1[0] != r2[0]) {
                return r1[0] - r2[0];
            }
            return r2[1] - r1[1];
        });
        return results.get(0);
    }

    private static int[] getResult(int N, int M, int B, int h) {
        int time = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                final int num = mapView[r][c];
                final int diff = Math.abs(num - h);
                if (num > h) {
                    time += diff * 2;
                    B += diff;
                }
                if (num < h) {
                    time += diff;
                    B -= diff;
                }
            }
        }
        return new int[]{B, time};
    }
}
