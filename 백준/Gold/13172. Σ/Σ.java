import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	final static int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		
		long answer = 0;
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			int gcd = gcd(s, n);
			int numerator = s / gcd;
			int denominator = n / gcd;
			
			long inverse = modularInverse(denominator);
			long expectation = (1L * numerator * inverse) % MOD;
			
			answer = (answer + expectation) % MOD;
		}
		
		System.out.println(answer);
	}
	
	private static int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	private static long modularInverse(int n) {
		return power(n, MOD - 2);
	}
	
	private static long power(long base, int exp) {
		long result = 1;
		base %= MOD;
		
		while (exp > 0) {
			if (exp % 2 == 1) {
				result = (result * base) % MOD;
			}
			base = (base * base) % MOD;
			exp /= 2;
		}
		
		return result;
	}
}