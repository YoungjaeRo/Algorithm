import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		while(T-- > 0) {
			String s = br.readLine();

			Stack<Character> stack = new Stack<>();

			boolean isOk = true;

			for(char c : s.toCharArray()) {
				if(c == '(') {
					stack.push(c);
				} else {
					// c == ')'
					// 닫는 괄호인데 매칭할 '(' 가 없으면 바로 NO
					if(stack.isEmpty()) {
						isOk = false;
						break;
					}
					// '('이 있다면
					stack.pop(); // 정상적으로 짝이 맞기 때문에 하나 제거
				}
			}

				if(!isOk || !stack.isEmpty()) {
					sb.append("NO\n");
				} else {
					sb.append("YES\n");
				}
		}
		System.out.println(sb);
	}
}
