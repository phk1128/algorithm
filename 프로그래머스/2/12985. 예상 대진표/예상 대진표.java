class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        while (true) {
            if (Math.abs(a - b) == 1) {
                if (a > b) {
                    if (a % 2 == 0 && a - 1 == b) {
                        break;
                    }
                }
                if (b > a) {
                    if (b % 2 == 0 && b - 1 == a) {
                        break;
                    }
                }
            }
            a = (a / 2) + (a % 2);
            b = (b / 2) + (b % 2);   
            answer++;
        }
        return answer;
    }
}