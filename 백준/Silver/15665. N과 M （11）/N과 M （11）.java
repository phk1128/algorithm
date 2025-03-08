import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    private static int N;
    private static int M;
    private static List<Integer> numbers;
    private static List<String> combinations;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = Arrays.stream(br.readLine().split(" "))
                .distinct()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        numbers.sort(Integer::compareTo);
        combinations = new ArrayList<>();

        recursiveSolve(0, "");
        List<String> answer = new ArrayList<>(combinations);
        String answerMessage = String.join("\n", answer);
        System.out.println(answerMessage);
    }

    private static void recursiveSolve(int depth, String combination) {
        if (depth >= M) {
            combinations.add(combination.trim());
            return;
        }

        for (int i = 0; i < numbers.size(); i++) {
            recursiveSolve(depth + 1, combination + numbers.get(i) + " ");
        }
    }
}
