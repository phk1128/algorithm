import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static List<Integer> numbers;
    private static List<Integer> targets;

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        numbers = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        Stream.iterate(0, i -> i + 1).limit(n).forEach(i -> {
            numbers.add(Integer.parseInt(st.nextToken()));
        });

        Collections.sort(numbers);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        targets = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        Stream.iterate(0, i -> i + 1).limit(m).forEach(i -> {
            targets.add(Integer.parseInt(st.nextToken()));
        });

        for (int target : targets) {
            binarySearch(0, n-1, target);
        }
        bw.close();
    }

    private static void binarySearch(int start, int end, int target) throws IOException{

        if (start == end) {
            if (numbers.get(start) == target) {
                bw.write("1" + "\n");
                bw.flush();
            } else {
                bw.write("0" + "\n");
                bw.flush();
            }
            return;
        }

        int mid = (start + end) / 2;

        if (target > numbers.get(mid)) {
            binarySearch(mid + 1, end, target);
        } else {
            binarySearch(start, mid, target);
        }
    }
}