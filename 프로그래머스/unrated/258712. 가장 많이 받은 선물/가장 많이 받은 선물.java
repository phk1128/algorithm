import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Integer> giftIndexs = new HashMap<>();
        Map<String, Map<String, Integer>> giftCounts = new HashMap<>();
        
        for (String friend : friends) {
            giftIndexs.put(friend, 0);
        }
        for (String friend : friends) {
            giftCounts.put(friend, new HashMap<>(giftIndexs));
        }
        
        for (String gift : gifts) {
            String[] splitGift = gift.split(" ");
            String giver = splitGift[0];
            String taker = splitGift[1];
            giftIndexs.replace(giver, giftIndexs.get(giver)+1);
            giftIndexs.replace(taker, giftIndexs.get(taker)-1);
            
            Map<String, Integer> giftCount = giftCounts.get(giver);
            giftCount.replace(taker, giftCount.get(taker)+1);
            
            giftCounts.replace(giver, giftCount);
            
        }
        
        for (String giver : friends) {
            int giverGiftIndex = giftIndexs.get(giver);
            Map<String, Integer> giverGiftCount = giftCounts.get(giver);
            int result = 0;
            
            for (String taker : giverGiftCount.keySet()) {
                if (!Objects.equals(giver, taker)) {
                    int takerGiftIndex = giftIndexs.get(taker);
                    Map<String, Integer> takerGiftCount = giftCounts.get(taker);
                    if (giverGiftCount.get(taker) > takerGiftCount.get(giver)) {
                        result++;
                        continue;
                    }
                    if (giverGiftCount.get(taker) == takerGiftCount.get(giver)) {
                        if (giverGiftIndex > takerGiftIndex) {
                            result++;
                            continue;
                        }
                    }
                }  
            }
            
            if (result >= answer) {
                answer = result;
            }
        }
        return answer;
    }
    
    
}