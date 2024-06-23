import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String A = st.nextToken();
		String B = st.nextToken();
		
		long max = Math.max(getResult(A), getResult(B));
		bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
	}
	
	private static long getResult(String eq) {
		String[] split = eq.split("[+-]");
		List<Long> numGroup = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			long num = 1;
			if (split[i].length() >= 2) {
				String[] nums = split[i].split("\\*");
				for (int j = 0; j < nums.length; j++) {
					num *= Long.parseLong(nums[j]);
				}
			} else {
				num = Long.parseLong(split[i]);
			}
			numGroup.add(num);
		}
		Pattern pattern = Pattern.compile("[+-]");
		Matcher matcher = pattern.matcher(eq);
		List<String> ops = new ArrayList();
		while (matcher.find()) {
			ops.add(matcher.group());
		}
		long result = numGroup.get(0);
		for (int i = 1; i < numGroup.size(); i++) {
			if (Objects.equals(ops.get(i - 1), "+")) {
				result += numGroup.get(i);
			}
			if (Objects.equals(ops.get(i - 1), "-")) {
				result -= numGroup.get(i);
			}
		}
		return result;
	}
}