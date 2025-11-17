import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] a, b;
    static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i<m; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        ans = new ArrayList<>();
        int idxa = 0;
        int idxb = 0;

        while(idxa < n && idxb < m){
            int max = -1;
            int nextA = -1, nextB = -1;

            for(int i = idxa; i<n; i++){
                for(int j = idxb; j<m; j++){
                    if(a[i] == b[j] && a[i] > max){
                        max = a[i];
                        nextA = i;
                        nextB = j;
                    }
                }
            }

            if(max == -1){
                break;
            }

            ans.add(max);
            idxa = nextA + 1;
            idxb = nextB + 1;
        }

        sb.append(ans.size()).append('\n');
        for(int i = 0; i<ans.size(); i++){
            sb.append(ans.get(i)).append(' ');
        }

        System.out.println(sb);

    }
}
