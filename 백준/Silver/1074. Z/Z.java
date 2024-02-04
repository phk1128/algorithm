import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int count;
    private static int r;
    private static int c;

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        count = 0;
        int size = (int) Math.pow(2, n);

        findTarget(size,0,0);

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();

    }

    // 타겟 좌표가 있는 사분면의 중심을 찾아가는 과정

    private static void findTarget(int size, int y, int x) {

        if (size == 1) {
            return;
        }
        int newSize = size / 2;
        // 제 2사분면에 타겟 존재
        if (r < y + newSize && c < x + newSize) {
            findTarget(newSize, y, x);
        }
        // 제 1사분면에 타겟 존재
        if (r < y + newSize &&  c >= x + newSize) {
            count += ((size * size) / 4) * 1;
            findTarget(newSize, y, x + newSize);
        }
        //제 3사분면에 타겟 존재
        if (r >= y + newSize && c < x + newSize) {
            count += ((size * size) / 4) * 2;
            findTarget(newSize, y + newSize, x);
        }
        //제 4사분면에 타겟 존재
        if (r >= y + newSize && c >= x + newSize) {
            count += ((size * size) / 4) * 3;
            findTarget(newSize, y + newSize, x + newSize);
        }
    }
}