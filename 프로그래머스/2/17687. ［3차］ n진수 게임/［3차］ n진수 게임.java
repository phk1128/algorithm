class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int num = 0;
        int count = 0;
        boolean flag = true;
        while(flag) {
            String str = Integer.toString(num, n);
            for (int i = 0; i < str.length(); i++) {
                count++;
                if (count == p) {
                    answer += String.valueOf(str.charAt(i)).toUpperCase();
                    t--;
                }
                if (count >= m) {
                    count = 0;
                }
                if (t == 0) {
                    flag = false;
                    break;
                }
            }
            num++;
        }
        return answer;
    }
}