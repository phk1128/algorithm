import java.util.*;
import java.util.stream.*;

class Solution {
    private Map<String, List<Song>> songs;
    private List<Integer> result;
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        this.songs = new HashMap<>();
        this.result = new ArrayList<>();
        
        for (int i = 0; i < genres.length; i++) {
            if (Objects.equals(songs.get(genres[i]), null)) {
                songs.put(genres[i], new ArrayList<>());
            }
            List<Song> song = songs.get(genres[i]);
            song.add(new Song(i, genres[i], plays[i]));
            Collections.sort(song);
        }
        
        PriorityQueue<Song> queue = new PriorityQueue<>();
        
        for (Map.Entry<String, List<Song>> entry : songs.entrySet()) {
            queue.offer(new Song(-1,entry.getKey(),calculatePlay(entry.getValue())));
        }
        
        while (!queue.isEmpty()) {
            Song song = queue.poll();
            int count = 0;
            for (Song s : songs.get(song.getGenre())) {
                if (count == 2) {
                    break;
                }
                count++;
                result.add(s.getUniqueNumber());
            }
        }
        
        answer = result.stream().mapToInt(n -> n).toArray();
        
        return answer;
    }
    
    private int calculatePlay(List<Song> songs) {
        return songs.stream().mapToInt(Song::getPlay).sum();
    }
    
    static class Song implements Comparable<Song> {
        
        private int uniqueNumber;
        private String genre;
        private int play;
        
        public Song(int uniqueNumber, String genre, int play) {
            this.uniqueNumber = uniqueNumber;
            this.genre = genre;
            this.play = play;
        }
        
        public int getUniqueNumber() {
            return this.uniqueNumber;
        }
        
        public String getGenre() {
            return this.genre;
        }
        
        public int getPlay() {
            return this.play;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this != o) {
                return false;
            }
            
            if (!(o instanceof Song)) {
                return false;
            }
            
            Song song = (Song) o;
            
            return Objects.equals(this.uniqueNumber, song.uniqueNumber) &&
                Objects.equals(this.genre, song.genre);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.uniqueNumber);
        }
        
        @Override
        public int compareTo(Song s) {
            // 플레이 횟수가 같을 시 고유번호 오름 차순
            if (this.play == s.play) {
                return this.uniqueNumber - s.uniqueNumber;
            }
            
            // play 내림 차순
            return (s.play - this.play);
        }
    }
}

// 장르, 노래, 같은 장르내에서 재생횟수가 같다면 고유번호가 낮은거