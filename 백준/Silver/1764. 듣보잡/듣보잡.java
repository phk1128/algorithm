import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = n + m;
        Map<String, Integer> unknownMan = new HashMap<>();
        List<String> unknownList = new ArrayList<>();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            if (!Objects.equals(unknownMan.get(name), null)) {
                unknownMan.replace(name, 2);
                continue;
            }
            unknownMan.put(name, 1);
        }

        for (Map.Entry<String, Integer> entry : unknownMan.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            if (count == 2) {
                unknownList.add(name);
            }
        }

        Collections.sort(unknownList);

        StringBuilder sb = new StringBuilder();

        sb.append(unknownList.size());
        sb.append("\n");

        for (String name : unknownList) {
            sb.append(name);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}