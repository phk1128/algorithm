import java.io.*;
import java.util.*;

class Main {
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String answer = st.nextToken("");
		st = new StringTokenizer(br.readLine());
		String input = st.nextToken("");
		System.out.println(getResult(input, answer, 1));

	}
	
    private static int getResult(String input, String answer, int count) {
        if (Objects.equals(input, answer)) {
            return count;
        }
        StringBuilder sb = new StringBuilder(input);
        boolean ball = false;
        int len = answer.length();
        
        // 2번 과정
        for (int i = 0; i < len; i++) {
            char num = sb.charAt(i);
            if (!answer.contains(String.valueOf(num))) {
                int newNum = Character.getNumericValue(num);
                while (sb.toString().contains(String.valueOf(newNum))) {
                    newNum++;
                    newNum %= 10;
                }
                num = (char) (newNum + '0');
            }
            sb.setCharAt(i, num);
        }
        
        for (int i = 0; i < len; i++) {
            if (!Objects.equals(answer.charAt(i), input.charAt(i)) && answer.contains(String.valueOf(input.charAt(i)))) {
                ball = true;
                break;
            }
        }
        
        StringBuilder copySb = new StringBuilder(sb);
        if (ball) {
            //3번 과정
            for (int i = 0; i < len; i++) {
                char num = sb.charAt(i);
                if (Objects.equals(answer.charAt(i), input.charAt(i))) {
                    continue;
                }
                int newIdx = (i + 1) % len;
                while (Objects.equals(answer.charAt(newIdx), input.charAt(newIdx))) {
                    newIdx = (newIdx + 1) % len;
                }
                copySb.setCharAt(newIdx, num);
            }
        }

        return getResult(copySb.toString(), answer, count + 1);
    }
}