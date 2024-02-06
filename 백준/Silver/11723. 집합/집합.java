import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int s = 0; // 0000 0000 / 0000 0000 / 0000 0000 / 0000 0000 총 32비트 이며 맨앞 32번째 비트가 1이면 음수, 0이면 양수
        
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();
            if (Objects.equals(commend, "all")) {
                s = (1 << 21) - 1;
            } else if (Objects.equals(commend, "empty")) {
                s = 0;
            } else {
                int idx = Integer.parseInt(st.nextToken());
                if (Objects.equals(commend, "add")) {
                    s |= (1 << idx);
                }
                if (Objects.equals(commend, "remove")) {
                    s &= ~(1 << idx);
                }
                if (Objects.equals(commend, "check")) {
                    if ((s & (1 << idx)) == 0) {
                        sb.append(0);
                        sb.append("\n");
                    } else {
                        sb.append(1);
                        sb.append("\n");
                    }
                }
                if (Objects.equals(commend, "toggle")) {
                    s ^= (1 << idx);
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
