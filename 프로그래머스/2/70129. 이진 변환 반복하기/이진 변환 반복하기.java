class Solution {
    public int[] solution(String s) {
        int removeCount = 0;
        int conversionCount = 0;
        
        while (s.length() > 1) {
            conversionCount++;
            int ones = 0;
            
            for (char c : s.toCharArray()) {
                if (c == '1') {
                    ones++;
                } else {
                    removeCount++;
                }
            }
            
            s = Integer.toBinaryString(ones);
        }
        
        return new int[]{conversionCount, removeCount};
    }
}