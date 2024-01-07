import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = Integer.parseInt(st.nextToken());
        solution(count, br); 
    }

    private static void solution(int count, BufferedReader br) throws IOException{
        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x1 = Double.parseDouble(st.nextToken());
            double y1 = Double.parseDouble(st.nextToken());
            double r1 = Double.parseDouble(st.nextToken());
            double x2 = Double.parseDouble(st.nextToken());
            double y2 = Double.parseDouble(st.nextToken());
            double r2 = Double.parseDouble(st.nextToken());

            double distance = Math.pow(((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)), 0.5);

            if (x1 == 0 && y1 == 0 && r1 == 0 && x2 == 0 && y2 == 0 && r2 == 0) {
                System.out.println(0);
                continue;
            }
            if (x1 == x2 && y1 == y2 && r1 == r2) {
                System.out.println(-1);
                continue;
            }

            if (distance > Math.abs(r1-r2) && distance < Math.abs(r1+r2)) {
                System.out.println(2);
                continue;
            }
            if (distance > Math.abs(r1+r2) || distance < Math.abs(r1-r2)) {
                System.out.println(0);
                continue;
            }
            if (distance == Math.abs(r1-r2) || distance == Math.abs(r1+r2)) {
                System.out.println(1);
                continue;
            }
            
        }
    }
}
