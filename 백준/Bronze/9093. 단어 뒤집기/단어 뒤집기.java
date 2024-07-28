import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        // 효율적인 문자열 입출력을 위해 BufferedReader와 BufferedWriter을 사용한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine() + "\n";

            //문자열의 각 문자를 순회하며 문자가 공백이나 개행이면 스택에 있는 모든 문자를 출력한다.
            // 문자가 공백이나 개행이 아니면 스택에 푸시한다.
            for (char ch : str.toCharArray()) {
                if (ch == ' ' || ch == '\n') {
                    while(!stack.isEmpty()){
                        bw.write(stack.pop());
                    }
                    bw.write(ch);
                } else {
                    stack.push(ch);
                }
            }
        }
        bw.flush();
    }
}