import java.util.*;

class Solution {
    private boolean[] visited;
    private String numbers;
    private int answer;
    private List<Integer> primeNumbers;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        this.numbers = numbers;
        this.primeNumbers = new ArrayList<>();
        answer = 0;
        
        for (int i = 1; i <= numbers.length(); i++) {
            combination(i,"");
        }
        
        return answer;
    }
    
    private void combination(int count, String number) {
        if (count == number.length()) {
            if (Objects.equals(number.charAt(0),'0')) {
                return;
            }
            
            int result = Integer.parseInt(number);
            
            if (isPrime(result) && !primeNumbers.contains(result)) {
                primeNumbers.add(result);
                answer++;
            }
        }
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(count, number + numbers.split("")[i]);
                visited[i] = false;
            }
        }
    }
    
    private boolean isPrime(int number) {
        if (number == 0) {
            return false;
        }
        
        if (number == 1) {
            return false;
        }
        
        
        for (int i = 2; i < (int) Math.pow(number,0.5) + 1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}