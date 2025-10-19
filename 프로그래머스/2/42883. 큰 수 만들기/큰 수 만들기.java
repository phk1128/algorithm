class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        
        for (char num : number.toCharArray()) {
            while (k > 0 && answer.length() > 0 && answer.charAt(answer.length() - 1) < num) {
                answer.deleteCharAt(answer.length() - 1);
                k--;
            }
            answer.append(num);
        }
        
        return answer.substring(0, answer.length() - k);
    }
}