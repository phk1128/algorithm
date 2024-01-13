import java.util.*;
import java.util.stream.Collectors;

class Solution {
    
    private final List<FileName> fileNames = new ArrayList<>();
    
    public List<String> solution(String[] files) {
        List<String> answer = new ArrayList<>();
        boolean[] visited = new boolean[files.length];
        
        
        for (int i = 0; i < files.length; i++) {
            String head = "";
            String number = "";
            String[] splitFile = files[i].split("");
            for (int j = 0; j < splitFile.length; j++) {
                if (!Character.isDigit(files[i].charAt(j))) {
                    head += splitFile[j];
                    continue;
                }
                for (int k = j; k < splitFile.length; k++) {
                    
                    if (Character.isDigit(files[i].charAt(k))) {
                        number += String.valueOf(Integer.parseInt(splitFile[k]));
                        continue;
                    }
                    break;
                }
                break;
            }
            fileNames.add(new FileName(head, number, files[i]));
        }
        
        answer = fileNames.stream()
            .sorted(Comparator.comparing(FileName::getHead).thenComparing(Comparator.comparing(FileName::getNumber)))
            .map(FileName::getFullName)
            .collect(Collectors.toList());
        
        return answer;
    }
    static class FileName {
        
        private String head;
        private int number;
        private String fullName;
        
        public FileName(String head, String number, String fullName) {
            this.head = head.toLowerCase();
            this.number = Integer.parseInt(number);
            this.fullName = fullName;
        }
        
        public String getHead() {
            return this.head;
        }
        
        public int getNumber() {
            return this.number;
        }
        
        public String getFullName() {
            return this.fullName;
        }
    }
}