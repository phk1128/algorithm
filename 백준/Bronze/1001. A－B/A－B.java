import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] splitInput = input.split(" ");
        int a = Integer.parseInt(splitInput[0]);
        int b = Integer.parseInt(splitInput[1]);

        System.out.println(a-b);
    }
}