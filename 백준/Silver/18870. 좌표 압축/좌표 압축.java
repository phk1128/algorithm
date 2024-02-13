import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Integer> numbers;
    private static List<Integer> numbersSet;
    private static Map<Integer, Integer> compression;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        numbers = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        numbersSet = new ArrayList<>(new HashSet<>(numbers));
        Collections.sort(numbersSet);
        compression = new HashMap<>();
        for (int i = 0; i < numbersSet.size(); i++) {
            compression.put(numbersSet.get(i), i);
        }

        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(compression.get(number));
            sb.append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}