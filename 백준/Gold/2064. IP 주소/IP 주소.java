import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String[][] addressGroup;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        addressGroup = new String[n][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            addressGroup[i] = st.nextToken().split("\\.");
        }

        StringBuilder[] result = getResult(n);
        StringBuilder sb = new StringBuilder();
        sb.append(result[0].toString());
        sb.append("\n");
        sb.append(result[1].toString());

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static StringBuilder[] getResult(int n) {

        StringBuilder[] result = new StringBuilder[2];
        boolean flag = false;
        StringBuilder networkAddress = new StringBuilder();
        StringBuilder networkMask = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int min = Integer.parseInt(addressGroup[0][i]);
            int max = Integer.parseInt(addressGroup[0][i]);
            for (int j = 1; j < n; j++) {
                min &= Integer.parseInt(addressGroup[j][i]);
                max |= Integer.parseInt(addressGroup[j][i]);
            }

            if (!flag) {
                networkAddress.append(min);
                networkAddress.append(".");
                networkMask.append(255 - (max - min));
                networkMask.append(".");
            } else {
                networkAddress.append("0");
                networkAddress.append(".");
                networkMask.append("0");
                networkMask.append(".");
            }
            if (min != max) {
                flag = true;
            }
        }


        result[0] = networkAddress.deleteCharAt(networkAddress.length() - 1);
        result[1] = networkMask.deleteCharAt(networkMask.length() - 1);
        return result;
    }
}