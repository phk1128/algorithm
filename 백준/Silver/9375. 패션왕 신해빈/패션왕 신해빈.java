import java.util.*;
import java.io.*;


public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Clothes> clothesGroup;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int testCount = Integer.parseInt(st.nextToken());

        clothesGroup = new ArrayList<>();
        sb = new StringBuilder();

        while (testCount-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String cloth = st.nextToken();
                String type = st.nextToken();
                Clothes newClothes = new Clothes(type);
                if (!clothesGroup.contains(newClothes)) {
                    newClothes.addClothes(cloth);
                    clothesGroup.add(newClothes);
                    continue;
                }
                Clothes clothes = clothesGroup.stream()
                        .filter(c -> Objects.equals(c, newClothes))
                        .findAny()
                        .orElse(null);
                clothes.addClothes(cloth);
            }
            solve();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve() {
        int count = 1;
        for (Clothes clothes : clothesGroup) {
            count *= (clothes.getClothes().size() + 1);
        }
        count--;
        sb.append(count);
        sb.append("\n");
        clothesGroup.clear();
    }

    static class Clothes {

        private String type;
        private List<String> clothes;

        public Clothes(String type) {
            this.type = type;
            this.clothes = new ArrayList<>();
        }

        public String getType() {
            return type;
        }

        public List<String> getClothes() {
            return clothes;
        }

        public void addClothes(String cloth) {
            this.clothes.add(cloth);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Clothes)) {
                return false;
            }
            Clothes clothes = (Clothes) o;
            return Objects.equals(this.type, clothes.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.type);
        }

    }
}