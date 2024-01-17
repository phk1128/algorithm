import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();
        Map<String,List<String>> recordsMap= new TreeMap<>();
        
        for(String record : records) {
            String[] recordList = record.split(" ");
            List<String> value = recordsMap.get(recordList[1]);
            if(value == null) {value = new ArrayList<>();}
            value.add(recordList[0]);
            recordsMap.put(recordList[1], value);
        }
        
        for(String carNum : recordsMap.keySet()) {
            List<String> times = recordsMap.get(carNum);
            if(times.size()%2 != 0) {times.add("23:59");}
            recordsMap.put(carNum, times);
        }
        
        for(String carNum : recordsMap.keySet()) {
            List<String> times = recordsMap.get(carNum);
            List<Integer> convertResult = convertToMinutes(times);
            System.out.println(convertResult); 
            int tax = calculateTax(convertResult, fees);
            answer.add(tax);
        }
        
        
        return answer.stream().mapToInt(i->i).toArray();
    
    }
    
    
    public List<Integer> convertToMinutes(List<String> times) {
        List<Integer> result = new ArrayList<>();
        
        for(String time : times) {
            String[] timeList = time.split(":");
            int hours = Integer.parseInt(timeList[0])*60;
            int minutes = Integer.parseInt(timeList[1]);
            result.add(hours+minutes);
        }
            
        return result;
    }
    
    public int calculateTax(List<Integer> times, int[] fees) {
        double usedTime = 0;
        
        while(!times.isEmpty()) {
            double inTime = times.remove(0);
            double outTime = times.remove(0);
            
            usedTime += (outTime - inTime);
        }
        double tmp = 0;
        if (usedTime > fees[0]) {
            tmp = (usedTime-fees[0])/fees[2];
        }
        
        int used = (int) Math.ceil(tmp);
        int tax = fees[1] + used*fees[3];
        
        
        return tax;
    }
}
    