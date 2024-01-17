import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private final RecordGroup recordGroup = new RecordGroup();
    
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        List<Integer> result = new ArrayList<>();
        
        for (String record : records) {
            String[] splitRecord = record.split(" ");
            String time = splitRecord[0];
            String carNumber = splitRecord[1];
            
            if (!recordGroup.isExistByCarNumber(carNumber)) {
                recordGroup.addRecord(new Record(carNumber));
            }
            
            Record rec = recordGroup.findByCarNumber(carNumber);
            int convertedTime = (Integer.parseInt(time.split(":")[0]) * 60) + (Integer.parseInt(time.split(":")[1]));
            rec.addTime(convertedTime);
        }
        
        
        for (Record record : recordGroup.getRecords()) {
            int price = 0;
            double usedTime = record.getUsedTime();
            if (usedTime > fees[0]) {
                price += Math.ceil((usedTime - fees[0]) / fees[2]) * fees[3];
            }
            price += fees[1];
            result.add(price);
        }
        
        answer = result.stream().mapToInt(i->i).toArray();
        
        return answer;
    }
    
    static class RecordGroup {
        private final List<Record> records;
        
        public RecordGroup() {
            this.records = new ArrayList<Record>();
        }
        
        public void addRecord(Record record) {
            records.add(record);
        }
        
        public Record findByCarNumber(String carNumber) {
            return this.records.stream()
                .filter(record -> Objects.equals(carNumber, record.getCarNumber()))
                .findAny()
                .orElse(null);
        }
        
        public List<Record> getRecords() {
            return this.records.stream().sorted(Comparator.comparing(Record::getCarNumber)).collect(Collectors.toList());
        }
        
        private boolean isExistByCarNumber(String carNumber) {
            return this.records.stream()
                .anyMatch(record -> Objects.equals(carNumber, record.getCarNumber()));
        }
    }
    
    static class Record {
        private String carNumber; 
        private final List<Integer> times;
        
        public Record(String carNumber) {
            this.carNumber = carNumber;
            this.times = new ArrayList<>();
        }
        
        public String getCarNumber() {
            return this.carNumber;
        }
        
        public void addTime(int time) {
            this.times.add(time);
        }
        
        public double getUsedTime() {
            if (times.size() % 2 != 0) {
                times.add(1439); // 23:59를 분단위로 환산
            }
            
            return calculateTime(0,0);
            
        }
        private double calculateTime(double time, int depth) {
            if (depth == times.size()) {
                return time;
            }
            if (depth % 2 == 0) {
                time -= times.get(depth);
            } else {
                time += times.get(depth);
            }
            
            return calculateTime(time, depth+1);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Record)) {
                return false;
            }
            Record record = (Record) o;
            return Objects.equals(this.carNumber, record.carNumber);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.carNumber);
        }
        
    }
}