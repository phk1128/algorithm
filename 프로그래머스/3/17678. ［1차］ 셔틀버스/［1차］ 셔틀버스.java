import java.util.*;

class Solution {
    
    private Queue<Integer> bus;
    private PriorityQueue<Integer> crews;
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        crews = new PriorityQueue<>();
        bus = new ArrayDeque<>();
        
        for (int i = 0; i < timetable.length; i++) {
            String[] timeSplit = timetable[i].split(":");
            crews.offer((Integer.parseInt(timeSplit[0]) * 60) + Integer.parseInt(timeSplit[1]));
        }
        
        int time = 540;
        for (int i = 0; i < n; i++) {
            bus.offer(time);
            time += t;
        }
        
        answer = getAnswer(n,m);
        
        return answer;
    }
    
    private String getAnswer(int n, int m) {
        
        int lastBus = 0;
        int lastCrew = 0;
        int lastCount = 0;
        while (!bus.isEmpty()) {
            int busTime = bus.poll();
            int count = 0;
            while (!crews.isEmpty() && crews.peek() <= busTime) {
                int crewTime = crews.poll();
                count++;
                lastCrew = crewTime;
                if (count >= m) {
                    break;
                }
            }
            lastBus = busTime; // 마지막 버스시간 
            lastCount = count; // 마지막 버스에 탄 크루원 수
        }
        
        // 버스에 탑승한 크루원이 없거나 (lastCrew = 0 이면 탑승한 크루원이 없는것), 마지막 버스에 탑승한 크루원 수가 허용인원보다 적다면
        // 마지막 버스를 타고 간다.
        if (lastCrew == 0 || lastCount < m) {
            return convertToDateTimeFormat(lastBus);
        }
        
        // 그렇지 않다면 마지막에 탑승한 크루원보다 1분더 일찍 간다.
        return convertToDateTimeFormat(lastCrew - 1);
    }
    
    private String convertToDateTimeFormat(int time) {
        int h = time / 60;
        int m = time % 60;
        String hStr = String.valueOf(h);
        String mStr = String.valueOf(m);
        if (h < 10) {
            hStr = "0" + hStr;
        }
        if (m < 10) {
            mStr = "0" + mStr;
        }
        return hStr + ":" + mStr;
    }
    
    
}

// 전부 분단위로 연산하기
// 버스를 큐에 담아 놓기
// 큐에서 버스를 꺼내서 크루원들 태우기
// 큐에 버스가 하나 남았으면, 콘이 탈 수 있는지 체크 탈수 없으면 전에 있던 버스에 태우기
// 23:59에 줄스고 있는 크루는 무시
// 마지막 버스를 탄 크루원 보다 1분 더 일찍 타기
// 만약 마지막 버스를 탄 크루원이 0 이면 막차에 타기