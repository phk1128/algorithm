import java.util.*;
import java.util.stream.*;

class Solution {
    private List<Root> roots;
    private List<String> result;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        roots = new ArrayList<Root>();
        result = new ArrayList<>();
        
        for (String[] ticket : tickets) {
            Root tempRoot = new Root(ticket[0]);
            if (!roots.contains(tempRoot)) {
                roots.add(tempRoot);
            }
            Root root = roots.stream()
                .filter(r -> Objects.equals(r,tempRoot))
                .findAny()
                .orElse(null);
            
            root.addDestination(ticket[1]);
        }
        
        dfs("ICN");
        
        Collections.reverse(result);
        
        answer = result.toArray(new String[0]);
        
        return answer;
    }
    
    private void dfs(String start) {
        Root root = findByRoot(start);
        if (!Objects.equals(root, null)) {
            PriorityQueue<String> destinations = root.getDestinations();
            while (!root.isEmpty()) {
                dfs(destinations.poll());
            }
            result.add(start);
        } else {
            result.add(start);
        }
    }
    
    
    public Root findByRoot(String start) {
        return roots.stream()
                .filter(r -> Objects.equals(r,new Root(start)))
                .findAny()
                .orElse(null);
    }
    
    static class Root {
        
        private String start;
        private PriorityQueue<String> destinations = new PriorityQueue<>();
        
        public Root(String start) {
            this.start = start;
        }
        
        public String getStart() {
            return this.start;
        }
        
        public boolean isEmpty() {
            return destinations.isEmpty();
        }
        
        public PriorityQueue<String> getDestinations() {
            return this.destinations;
        }
        
        public void addDestination(String destination) {
            this.destinations.offer(destination);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Root)) {
                return false;
            }
            
            Root root = (Root) o;
            return Objects.equals(this.start, root.start);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(start);
        }
        
    }
}