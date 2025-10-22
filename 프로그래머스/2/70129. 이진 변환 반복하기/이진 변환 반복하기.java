class Solution {
    public int[] solution(String s) {
int removeCount = 0;
        int conversionCount = 0;
        
        while (!s.equals("1")) {
            conversionCount++;
            int countOfOnes = 0;
            
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    removeCount++;
                } else {
                    countOfOnes++;
                }
            }
            
            s = Integer.toBinaryString(countOfOnes);
        }
        
        return new int[]{conversionCount, removeCount};
    }
}