import java.util.*;

class Solution {
    
    private int[] discounts = new int[]{10, 20, 30, 40};
    private int[] combi;
    private List<Result> results;
    
    static class Result implements Comparable<Result> {
        int memberCount;
        int price;
        
        public Result(int memberCount, int price) {
            this.memberCount = memberCount;
            this.price = price;
        }
        
        @Override
        public int compareTo(Result r) {
            if (this.memberCount != r.memberCount) {
                return r.memberCount - this.memberCount;
            }
            return r.price - this.price;
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        combi = new int[emoticons.length];
        results = new ArrayList<>();
        compute(0, emoticons.length, users, emoticons);
        Collections.sort(results);
        Result result = results.get(0);
        
        return new int[]{result.memberCount, result.price};
    }
    
    private void compute(int depth, int limit, int[][] users, int[] emoticons) {
        if (depth == limit) {
            results.add(computeResult(users, emoticons));
            return;
        }
        
        for (int i = 0; i < discounts.length; i++) {
            combi[depth] = i;
            compute(depth + 1, limit, users, emoticons);
        }
    }
    
    private Result computeResult(int[][] users, int[] emoticons) {
        int memberCount = 0;
        int price = 0;
        for (int[] user : users) {
            int tmpPrice = 0;
            for (int i = 0; i < emoticons.length; i++) {
                int discount = discounts[combi[i]];
                if (user[0] > discount) {
                    continue;
                }
                tmpPrice += emoticons[i] * (100 - discount) / 100;
            }
            if (tmpPrice >= user[1]) {
                memberCount++;
            }
            if (tmpPrice < user[1]) {
                price += tmpPrice;
            }
        }
        return new Result(memberCount, price);
    }
}