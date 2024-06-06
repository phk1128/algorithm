import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        List<String> strList1 = new ArrayList<>();
        List<String> strList2 = new ArrayList<>();
        addStr(strList1, str1);
        addStr(strList2, str2);
        Map<String, Double> strMap1= new HashMap<>();
        Map<String, Double> strMap2= new HashMap<>();
        putStr(strMap1, strList1);
        putStr(strMap2, strList2);
        Set<String> set = new HashSet<>();
        for (String str : strList1) {
            set.add(str);
        }
        for (String str : strList2) {
            set.add(str);
        }
        double inter = 0.0;
        double union = 0.0;
        for (String str : set) {
            double num1 = strMap1.getOrDefault(str, 0.0);
            double num2 = strMap2.getOrDefault(str, 0.0);
            double max = Math.max(num1, num2);
            double min = Math.min(num1, num2);
            inter += min;
            union += max;
        }
        
        double jakad = 1.0;
        if (union != 0.0) {
            jakad = inter / union;
        }
        answer = (int) (jakad * 65536);
        
        return answer;
    }
    
    private void putStr(Map<String, Double> strMap , List<String> strList) {
        for (String str : strList) {
            strMap.put(str, strMap.getOrDefault(str, 0.0) + 1.0);
        }
    }
    
    private void addStr(List<String> strList, String str) {
        for (int i = 0; i < str.length()-1; i++) {
            String s1 = String.valueOf(str.charAt(i));
            String s2 = String.valueOf(str.charAt(i + 1));
            String tmpStr = s1 + s2;
            tmpStr = tmpStr.replaceAll("[^a-zA-Z]", "");
            if (tmpStr.length() == 2) {
                strList.add(tmpStr.toUpperCase());
            }
        }
    }
}

// 영문자로 된 글자 쌍만 유효, 나머지는 다 버린다.
// 대문자 소문자 차이는 무시
// 자카드 유사도에 65536을 곱한 후 소수점 아래를 버리고 반환
// 자카드 유사도는 double 자료형
