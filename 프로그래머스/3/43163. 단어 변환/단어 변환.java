import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Queue<Word> queue = new LinkedList<>();
        queue.offer(new Word(begin,answer));
        boolean[] visited = new boolean[words.length];
        
        
        while (!queue.isEmpty()) {
            Word word = queue.poll();
            
            if (Objects.equals(word.getWord(), target)) {
                return word.getAnswer();
            }
            String[] splitWord = word.getWord().split("");
            for (int i = 0; i < words.length; i++) {
                int count = 0;
                String[] tempWord = words[i].split("");
                for (int j = 0; j < splitWord.length; j++) {
                    if (Objects.equals(splitWord[j],tempWord[j])) {
                        count++;
                    }
                }
                if (count >= (splitWord.length-1) && !visited[i]) {
                    visited[i] = true;
                    queue.offer(new Word(words[i],word.getAnswer()+1));
                }
            }
        }
        return answer;
    }
    static class Word {
        private int answer;
        private String word;
        
        public Word(String word, int answer) {
            this.word = word;
            this.answer = answer;
        }
        
        public String getWord() {
            return this.word;
        }
        
        public int getAnswer() {
            return this.answer;
        }
    }
}