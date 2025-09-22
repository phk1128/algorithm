import java.util.*;

class Solution {
    
    public int[] solution(int[] fees, String[] records) {
        int[] result = {};
        TreeMap<String, List<Double>> parkingLogs = new TreeMap<>();
        
        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");
            String[] timeTokens = record[0].split(":");
            String carNumber = record[1];
            String action = record[2];
            
            double timeInMinutes = Double.parseDouble(timeTokens[0]) * 60 + Double.parseDouble(timeTokens[1]);
            
            List<Double> timeList = parkingLogs.getOrDefault(carNumber, new ArrayList<>());
            timeList.add(timeInMinutes);
            parkingLogs.put(carNumber, timeList);
        }
        
        result = new int[parkingLogs.size()];
        int index = 0;
        
        for (String carNumber : parkingLogs.keySet()) {
            List<Double> timeList = parkingLogs.get(carNumber);
            
            if (timeList.size() % 2 != 0) {
                timeList.add(23.0 * 60.0 + 59.0);
            }
            
            int totalFee = fees[1];
            double totalParkingTime = 0;
            
            for (int i = 0; i < timeList.size() - 1; i += 2) {
                double entryTime = timeList.get(i);
                double exitTime = timeList.get(i + 1);
                totalParkingTime += (exitTime - entryTime);
            }
            
            double excessTime = totalParkingTime - fees[0];
            if (excessTime > 0) {
                totalFee += Math.ceil(excessTime / (double) fees[2]) * fees[3];
            }
            
            result[index++] = totalFee;
        }
        
        return result;
    }
}