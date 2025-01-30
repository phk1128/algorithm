import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

    private static StringBuilder sb = new StringBuilder();

    static class Node {

        int number;
        Node left;
        Node right;

        public Node(int number) {
            this.number = number;
        }

        void insert(int number) {
            if (this.number > number) {
                if (Objects.equals(left, null)) {
                    left = new Node(number);
                    return;
                }
                this.left.insert(number);
                return;
            }
            if (this.number < number) {
                if (Objects.equals(right, null)) {
                    right = new Node(number);
                    return;
                }
                this.right.insert(number);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Node root = new Node(Integer.parseInt(br.readLine()));
        while (true) {
            String input = br.readLine();
            if (Objects.equals(input, null) || input.isBlank()) {
                break;
            }
            root.insert(Integer.parseInt(input));
        }
        postOrder(root);

        bw.flush();
        bw.write(sb.toString());
        bw.close();
    }


    private static void postOrder(Node node) {
        if (!Objects.equals(node.left, null)) {
            postOrder(node.left);
        }
        if (!Objects.equals(node.right, null)) {
            postOrder(node.right);
        }
        sb.append(node.number).append(System.lineSeparator());
    }
}
