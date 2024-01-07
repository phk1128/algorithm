import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        List<Integer> answer = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputCount = br.readLine();
        int count = Integer.parseInt(inputCount);
        int repeate = 0;
        solution(br, count, repeate, answer);
        answer.stream().forEach(ans -> System.out.println(ans));
    }

    private static void solution(BufferedReader br, int count, int repeate, List<Integer> answer) throws IOException{
        if (Objects.equals(repeate,count)) {
            return;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        double x1 = Double.parseDouble(st.nextToken());
        double y1 = Double.parseDouble(st.nextToken());
        double r1 = Double.parseDouble(st.nextToken());
        double x2 = Double.parseDouble(st.nextToken());
        double y2 = Double.parseDouble(st.nextToken());
        double r2 = Double.parseDouble(st.nextToken());

        double distance = Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), 0.5);
        if (!isAllZeroPosition(x1, y1, r1, x2, y2, r2) && !isSamePosition(x1, y1, r1, x2, y2, r2)) {

            if (isOnePoint(distance, r1, r2)) {
                answer.add(1);
            }
            if (isTwoPoint(distance, r1, r2)) {
                answer.add(2);
            }
            if (isZeroPoint(distance, r1, r2)) {
                answer.add(0);
            }
        }
        if (!isAllZeroPosition(x1, y1, r1, x2, y2, r2) && isSamePosition(x1, y1, r1, x2, y2, r2)) {
            answer.add(-1);
        }

        if (isAllZeroPosition(x1, y1, r1, x2, y2, r2)) {
            answer.add(0);
        }

        repeate ++;
        solution(br, count, repeate, answer);
    }

    private static boolean isSamePosition(double x1, double y1, double r1, double x2, double y2, double r2) {
        return Objects.equals(x1, x2) &&
                Objects.equals(y1, y2) &&
                Objects.equals(r1, r2);
    }

    private static boolean isTwoPoint(double distance ,double r1, double r2) {
        return distance < Math.abs(r1+r2) &&
                distance > Math.abs(r1-r2);
    }

    private static boolean isOnePoint(double distance ,double r1, double r2) {
        return Objects.equals(distance, Math.abs(r1-r2)) ||
                Objects.equals(distance, Math.abs(r1+r2));
    }

    private static boolean isZeroPoint(double distance ,double r1, double r2) {
        return distance > Math.abs(r1+r2) ||
                distance < Math.abs(r1-r2);
    }

    private static boolean isAllZeroPosition(double x1, double y1, double r1, double x2, double y2, double r2) {
        return x1 == 0 &&
                y1 == 0 &&
                r1 == 0 &&
                x2 == 0 &&
                y2 == 0 &&
                r2 == 0;
    }

}
