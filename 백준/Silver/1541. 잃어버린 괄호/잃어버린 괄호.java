import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String equation;
    private static String[] equationSplit;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        equation = st.nextToken();
        int answer = 0;
        if (equation.contains("-")) {
            equationSplit = equation.split("-");
            for (int i = 1; i < equationSplit.length; i++) {
                String[] numSplit = equationSplit[i].split("\\+");
                for (String num : numSplit) {
                    answer -= Integer.parseInt(num);
                }
            }
            if (!Objects.equals(equationSplit[0], "")) {
                for (String num : equationSplit[0].split("\\+")) {
                    answer += (Integer.parseInt(num));
                }
            }
        } else {
            equationSplit = equation.split("\\+");
            for (String num : equationSplit) {
                answer += Integer.parseInt(num);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}