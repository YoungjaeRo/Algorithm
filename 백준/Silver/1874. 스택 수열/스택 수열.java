
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 수열의 개수 받기
        int N = scanner.nextInt();
        int A[] = new int[N];

        for(int i = 0; i < N; i++){
            A[i] = scanner.nextInt();
        }

        Stack<Integer> stack = new Stack<>();

        int num = 1; // 수열의 시작
        boolean result = true;

        StringBuffer bf = new StringBuffer(); // 나중에 출력할 스택 현황


        for(int i = 0; i < N; i++) {
            int su = A[i];
            if (su >= num) {
                while (su >= num) {
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else { // 수열에 있는 수가 스택안에 있는 숫자보다 작을때,
                int n = stack.pop();
                if (n > su) { // 스택에 있는 수가 수열안에 있는 수보다 크면
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
        if(result ) {
            System.out.println(bf);
        }

    }
}

