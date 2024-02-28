import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static Node[] nodes;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        nodes = new Node[N + 1];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int root = st.nextToken().charAt(0) - 'A' + 1;
            int left = st.nextToken().charAt(0) - 'A' + 1;
            int right = st.nextToken().charAt(0) - 'A' + 1;
            nodes[root] = new Node(left, right);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(preOrder(1, ""));
        sb.append("\n");
        sb.append(inOrder(1, ""));
        sb.append("\n");
        sb.append(postOrder(1, ""));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String preOrder(int start, String result) {

        Node node = nodes[start];
        int left = node.left;
        int right = node.right;
        result += (char) (start + 'A' - 1);
        if (left != -18) {
            result = preOrder(left, result);
        }

        if (right != -18) {
            result = preOrder(right, result);
        }

        return result;
    }

    private static String inOrder(int start, String result) {

        Node node = nodes[start];
        int left = node.left;
        int right = node.right;

        if (left != -18) {
            result = inOrder(left, result);
        }
        result += (char) (start + 'A' - 1);

        if (right != -18) {
            result = inOrder(right, result);
        }

        return result;
    }

    private static String postOrder(int start, String result) {

        Node node = nodes[start];
        int left = node.left;
        int right = node.right;

        if (left != -18) {
            result = postOrder(left, result);
        }

        if (right != -18) {
            result = postOrder(right, result);
        }

        result += (char) (start + 'A' - 1);

        return result;
    }

    static class Node {

        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }


    }
}