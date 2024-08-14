import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        String data = br.readLine();
        double[] arr = new double[num];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if ('A' <= ch && ch <= 'Z') {
                stack.push(arr[ch - 'A']);
            } else {
                if (stack.size() >= 2) {  // Ensure there are at least two operands in the stack
                    double first = stack.pop();
                    double second = stack.pop();
                    switch (ch) {
                        case '+':
                            stack.push(second + first);
                            break;
                        case '-':
                            stack.push(second - first);
                            break;
                        case '*':
                            stack.push(second * first);
                            break;
                        case '/':
                            stack.push(second / first);
                            break;
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.printf("%.2f", stack.pop());
        }

        br.close();
    }
}
