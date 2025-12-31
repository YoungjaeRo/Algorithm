import java.io.*;
import java.util.*;

public class Main {
	static int K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		K = sc.nextInt();

		Stack<Integer> stack = new Stack<>();

		for(int i = 0; i < K; i++) {
			int num = sc.nextInt();
			
			if(num == 0) {
				// 0이 나오면, 직전에 쓴 수 취소, 가장 최근 값을 스택에서 빼기
				stack.pop();
			} else {
				stack.push(num);
			}
			
		}
		
		long sum = 0;
		
		while(!stack.isEmpty()) {
			sum = sum + stack.pop();
		}

		System.out.println(sum);
		
	}
}
