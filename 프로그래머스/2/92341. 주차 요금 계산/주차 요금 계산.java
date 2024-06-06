import java.util.*;

class Solution {
    
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        TreeMap<String, List<Double>> cars = new TreeMap<>();
        
        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");
            String[] timeSplit = record[0].split(":");
            String carNum = record[1];
            String command = record[2];
            double time = Double.parseDouble(timeSplit[0]) * 60 + Double.parseDouble(timeSplit[1]);
            List<Double> carTimes = cars.getOrDefault(carNum, new ArrayList<>());
            carTimes.add(time);
            cars.put(carNum, carTimes);
        }
        answer = new int[cars.size()];
        int idx = 0;
        for (String key : cars.keySet()) {
            List<Double> carTimes = cars.get(key);
            if (carTimes.size() % 2 != 0) {
                carTimes.add(23.0 * 60.0 + 59.0);
            }
            int price = fees[1];
            double usedTime = 0;
            for (int i = 0; i < carTimes.size() - 1; i += 2) {
                double inTime = carTimes.get(i);
                double outTime = carTimes.get(i + 1);
                usedTime += (outTime - inTime);
            }
            double diffTime = usedTime - fees[0];
            if (diffTime > 0) {
                price += Math.ceil(diffTime / (double) fees[2]) * fees[3];
            }
            answer[idx++] = price;
        }
        
        return answer;
    }
}
// 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 배열에 담아서 반환
