// TODO: 87% fail
import java.io.*;

public class Main {
    static String toString(int[] ip) {
        StringBuilder sb = new StringBuilder();
        for(int n:ip) {
            sb.append(n).append('.');
        }
        return sb.toString().substring(0, sb.length()-1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] ips = new int[N][4];
        int[] network = new int[4];
        int[] subnet = new int[4];

        for(int i=0; i<N; i++) {
            String[] ipsStr = br.readLine().split("\\.");
            for(int j=0; j<4; j++) {
                ips[i][j] = Integer.parseInt(ipsStr[j]);
            }
        }

        boolean isSubnet = true;

        for(int i=0; i<4; i++) {
            network[i] = 0;
            subnet[i] = 0;
            for(int[] ip:ips) {
                network[i] = network[i] & ip[i];
            }

            if(!isSubnet) {
                continue;
            }
            for(int j=7; j>=0; j--) {
                int bit=1<<j;
                for(int[] ip:ips) {
                    if((ips[0][i]&bit) != (ip[i]&bit)) {
                        isSubnet = false;
                        break;
                    }
                }
                if(!isSubnet) {
                    break;
                }
                subnet[i] |= bit;
            }
            network[i] = ips[0][i] & subnet[i];
        }
        System.out.println(toString(network));
        System.out.println(toString(subnet));
    }
}