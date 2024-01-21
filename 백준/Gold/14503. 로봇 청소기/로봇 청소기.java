import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<List<Integer>> mapView;
    private static List<List<Integer>> directions;
    private static int answer = 1;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        mapView = new ArrayList<>();

        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로

        st = new StringTokenizer(br.readLine());

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        directions = new ArrayList<>(List.of(List.of(0,-1), List.of(1,0), List.of(0,1), List.of(-1,0)));

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> tempMapView = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                tempMapView.add(Integer.parseInt(st.nextToken()));
            }
            mapView.add(tempMapView);
        }
        mapView.get(y).set(x,2);
        cleanRoom(y,x,d,true);
        bw.close();

    }

    private static void cleanRoom(int y, int x, int d, boolean result) throws IOException {
        // 4 방향을 체크한다.
        for (int i = 0; i < directions.size(); i++) {
            int ny = y + directions.get(i).get(1);
            int nx = x + directions.get(i).get(0);

            if (0 <= nx && nx < mapView.get(0).size() && 0 <= ny && ny < mapView.size()) {
                if (mapView.get(ny).get(nx) == 0) {
                    result = true;
                    break;
                }
            }
            result = false;
        }
        if (!result) {
            int ny = y - directions.get(d).get(1);
            int nx = x - directions.get(d).get(0);
            if (!(0 <= nx && nx < mapView.get(0).size() && 0 <= ny && ny < mapView.size())) {
                bw.write(String.valueOf(answer));
                bw.flush();
                return;
            }
            if (mapView.get(ny).get(nx) == 1) {
                bw.write(String.valueOf(answer));
                bw.flush();
                return;
            }
            cleanRoom(ny, nx, d, true);
        } else {
            for (int i = 0; i < 4; i++) {
                d = (d+3) % 4;
                List<Integer> direction = directions.get(d);
                int ny = y + direction.get(1);
                int nx = x + direction.get(0);
                if ((0 <= nx && nx < mapView.get(0).size() && 0 <= ny && ny < mapView.size())) {
                    if (mapView.get(ny).get(nx) == 0) {
                        y = ny;
                        x = nx;
                        mapView.get(y).set(x, 2);
                        answer++;
                        break;
                    }
                }
            }
            cleanRoom(y,x,d,true);
        }
    }
}
