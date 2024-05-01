import java.util.*;

class Solution {
    
    static class BookTime {
        
        int start;
        int end;
        
        public BookTime(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;
        List<BookTime> bookTimes = new ArrayList<>();
        for (int i = 0; i < book_time.length; i++) {
            String[] startStr = book_time[i][0].split(":");
            String[] endStr = book_time[i][1].split(":");
            int start = Integer.parseInt(startStr[0]) * 60 + Integer.parseInt(startStr[1]);
            int end = Integer.parseInt(endStr[0]) * 60 + Integer.parseInt(endStr[1]);
            bookTimes.add(new BookTime(start, end + 10));
        }
        Collections.sort(bookTimes, (b1, b2) -> b1.start - b2.start);
        
        PriorityQueue<BookTime> queue = new PriorityQueue<>((b1, b2) -> b1.end - b2.end);
        
        for (BookTime bookTime : bookTimes) {
            if (queue.isEmpty()) {
                queue.offer(bookTime);
                answer++;
                continue;
            }
            
            int end = queue.peek().end;
            
            if (bookTime.start >= end) {
                queue.poll();
            } else {
                answer++;
            }
            queue.offer(bookTime);
        }
        
        return answer;
    }
}