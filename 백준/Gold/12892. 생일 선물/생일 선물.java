import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Friend {
        int birthday;
        long cost;
        
        Friend(int birthday, long cost) {
            this.birthday = birthday;
            this.cost = cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        
        Friend[] friends = new Friend[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int birthday = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            friends[i] = new Friend(birthday, cost);
        }
        
        Arrays.sort(friends, (a, b) -> Integer.compare(a.birthday, b.birthday));
        
        long maxCost = 0;
        long currentCost = 0;
        int left = 0;
        
        for (int right = 0; right < N; right++) {
            currentCost += friends[right].cost;
            
            while (friends[right].birthday - friends[left].birthday >= D) {
                currentCost -= friends[left].cost;
                left++;
            }
            
            maxCost = Math.max(maxCost, currentCost);
        }
        
        System.out.println(maxCost);
    }
}