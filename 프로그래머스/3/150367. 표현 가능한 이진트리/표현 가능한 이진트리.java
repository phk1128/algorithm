import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String binaryStr = convertToBinary(numbers[i], "");
            int height = getHeight(binaryStr);
            int diff =  (1 << height) - binaryStr.length() - 1;
            
            for (int j = 0; j < diff; j++) {
                binaryStr = "0" + binaryStr;
            }
            
            int root = binaryStr.length() / 2;
            int result = 1;
            if (!isPerfectBinaryTree(root, height - 1, binaryStr)) {
                result = 0;
            }
            answer[i] = result;
            
        }
        return answer;
    }
    
    private static int getHeight(String binaryStr) {
        
        int height = 1;
        int len = binaryStr.length();
        
        if (len == 1) {
            return height;
        }
        
        while (true) {
            if ((len / (1 << height)) == 0) {
                return height;
            }
            height++;
        }
    }
    
    private static String convertToBinary(long number, String result) {
        
        if (number == 0) {
            return result;
        }
        
        if ((number % 2) == 0) {
            result = convertToBinary((number / 2), "0" + result);
        }
        
        if ((number % 2) != 0) {
            result = convertToBinary((number / 2), "1" + result);
        }
        
        return result;
    }
    
    private static boolean isPerfectBinaryTree(int idx, int height, String binaryStr) {
        
        if (height == 0) {
            return true;
        }
        
        int diff =  (1 << height - 1);
        int left = idx - diff;
        int right = idx + diff;
        
        if (Objects.equals(binaryStr.charAt(idx), '0')) {
            if (Objects.equals(binaryStr.charAt(left), '1') || Objects.equals(binaryStr.charAt(right), '1')) {
                return false;
            }
        }
        
        return isPerfectBinaryTree(left, height - 1, binaryStr) && isPerfectBinaryTree(right, height - 1, binaryStr);
        
    }
}