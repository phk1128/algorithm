class Solution {
    public int solution(int n) {
        long a = 0;
        long b = 1;

        for (int i = 0; i < n; i++) {
            long temp = (a + b) % 1234567;
            a = b;
            b = temp;
        }

        return (int) a;
    }
}
