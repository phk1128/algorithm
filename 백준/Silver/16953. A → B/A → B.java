import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A =  Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        System.out.println(solve(B, 0, A));
    }

    private static int solve(int num, int depth, int target) {
        if (num == target) {
            return depth + 1;
        }

        if (num % 2 == 1) {
            StringBuilder sb = new StringBuilder(String.valueOf(num));
            if (sb.length() > 1 && sb.charAt(sb.length() - 1) == '1') {
                sb.deleteCharAt(sb.length() -1);
                int newNum2 = Integer.parseInt(sb.toString());
                return solve(newNum2, depth + 1, target);
            }
        }
        if (num % 2 == 0) {
            return  solve(num / 2, depth + 1, target);
        }
        return -1;
    }
}
