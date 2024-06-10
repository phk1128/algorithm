import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Map<String, Integer>> giftMap = new HashMap<>();
        Map<String, Integer> give = new HashMap<>();
        Map<String, Integer> recieve = new HashMap<>();
        
        for (String friend : friends) {
            giftMap.put(friend, new HashMap<>());
            give.put(friend, 0);
            recieve.put(friend, 0);
        }
        
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            String giver = split[0];
            String reciever = split[1];
            give.put(giver, give.getOrDefault(giver, 0) + 1);
            recieve.put(reciever, recieve.getOrDefault(reciever, 0) + 1);
            Map<String, Integer> map = giftMap.get(giver);
            map.put(reciever, map.getOrDefault(reciever, 0) + 1);
            giftMap.put(giver, map);
        }
        
        for (String giver : friends) {
            Map<String, Integer> map = giftMap.get(giver);
            int count = 0;
            for (String reciever : friends) {
                if (Objects.equals(giver, reciever)) {
                    continue;
                }
                int giCount = map.getOrDefault(reciever, 0);
                int reCount = giftMap.get(reciever).getOrDefault(giver, 0);
                
                if (giCount > reCount) {
                    count++;
                }
                if (giCount == reCount) {
                    int giNum = give.get(giver) - recieve.get(giver);
                    int reNum = give.get(reciever) - recieve.get(reciever);
                    if (giNum > reNum) {
                        count++;
                    }
                }
                
            }
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
    
}
//선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값
//선물을 더 많이 준사람이 다음달에 선물을 하나 받는다.
//선물을 주고 받은 횟수가 같은 경우 선물 지수가 더 큰 사람에게 선물을 줌
//선물지수도 같은 경우 주고 받지 않음