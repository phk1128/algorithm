import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		double N = Double.parseDouble(input[0]);
		double M = Double.parseDouble(input[1]);
		double water = N + M;
		double solt = (7.0 / 100.0) * N;
		double result = (solt / water) * 100.0;
		String answer = String.valueOf(result).substring(0,4);
		System.out.println(answer);
	}
}