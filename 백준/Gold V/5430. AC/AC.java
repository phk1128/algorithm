import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String commands;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            commands = st.nextToken();
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            String inputArray = st.nextToken();
            String[] inputArraySplit = inputArray.substring(1, inputArray.length() - 1).split(",");
            int leftIdx = 0;
            int rightIdx = size - 1;
            sb.append(solve(inputArraySplit, leftIdx, rightIdx, size));
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String solve(String[] inputArraySplit, int leftIdx, int rightIdx, int size) {
        boolean isReverse = false;

        for (String command : commands.split("")) {
            if (Objects.equals(command, "R")) {
                isReverse = !isReverse;
            }

            if (Objects.equals(command, "D")) {
                if (size == 0) {
                    return "error";
                }
                if (isReverse) {
                    rightIdx--;
                } else {
                    leftIdx++;
                }
                size--;
            }
        }
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (isReverse) {
            for (int i = rightIdx; i > leftIdx; i--) {
                sb.append(inputArraySplit[i]);
                sb.append(",");
            }
            sb.append(inputArraySplit[leftIdx]);
            sb.append("]");
        } else {
            for (int i = leftIdx; i < rightIdx; i++) {
                sb.append(inputArraySplit[i]);
                sb.append(",");
            }
            sb.append(inputArraySplit[rightIdx]);
            sb.append("]");
        }
        return sb.toString();
    }
}