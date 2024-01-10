import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public List<String> solution(String[] orders, int[] course) { 
        List<String> answer = new ArrayList<>();
        for (int quantity : course) {
            Map<String, Integer> courseMenus = new HashMap<>();
            for (String order : orders) {
                List<String> combination = new ArrayList<>();
                List<String> covertedOrder = Arrays.stream(order.split("")).collect(Collectors.toList());
                Collections.sort(covertedOrder);
                order = String.join("",covertedOrder);
                boolean[] visited = new boolean[order.length()];
                addCombination(order, combination, 0, visited, 0, quantity);
                for (String menus : combination) {
                    if (Objects.equals(courseMenus.get(menus), null)) {
                        courseMenus.put(menus, 1);
                        continue;
                    }
                    courseMenus.replace(menus, courseMenus.get(menus) + 1);
                }
                
            }
            if (!courseMenus.isEmpty()) {
                int maxQuantity = Collections.max(courseMenus.values());
                for (Map.Entry<String, Integer> entry : courseMenus.entrySet()) {
                    int value = entry.getValue();
                    if (value == maxQuantity && value >= 2) {
                        answer.add(entry.getKey());
                    }
            }
                
            }
           
        }
        Collections.sort(answer);
        return answer;
    }
        
    
    public void addCombination(String order, List<String> combination, int start, boolean[] visited, int depth, int quantity) {
        if (quantity == depth) {
            String menus = "";
            for (int i = 0; i < order.length(); i++) {
                if (visited[i]) {
                    menus += order.split("")[i];
                }
            }
            combination.add(menus);
        }
        for (int i = start; i < order.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                addCombination(order, combination, i+1, visited, depth+1, quantity);
                visited[i] = false;
            }
        }
    }

//     static class Courses {
//         private final List<Course> courses;
        
//         public Courses() {
//             this.courses = new ArrayList<>();
//         }
        
//         public List<String> findCourseByQuantity(int quantity) {
//             return courses.stream()
//                 .filter(course -> course.getQuantity() == quantity)
//                 .map(Course::getMenus)
//                 .collect(Collectors.toList());
//         }
        
//         public void addCourse(String menus) {
//             courses.add(new Course(menus));
//         }
        
//         public Course findCourseByMenus(String menus) {
//             return courses.stream().filter(course -> Objects.equals(course.getMenus(), menus)).findAny().orElse(null);
//         }
        
//         private int calculateCourseMaxQuantity() {
//             return courses.stream().mapToInt(Course::getQuantity).max().orElse(0);
//         }
//     }
    
//     static class Course {
//         private final String menus;
//         private int quantity;
        
//         public Course(String menus) {
//             this.menus = menus;
//             this.quantity = 1;
//         }
        
//         public String getMenus() {
//             return menus;
//         }
        
//         public int getQuantity() {
//             return quantity;
//         }
        
//         public void addQuantity() {
//             quantity++;
//         }
//     }
}


//코스요리 -> 최소 2가지 이상의 단품 메뉴, 메뉴는 최소 2명 이상의 손님이 주문한거