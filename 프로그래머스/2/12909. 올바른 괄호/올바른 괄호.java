import java.util.ArrayDeque;
import java.util.Stack;

class Solution {
    boolean solution(String s) {

		Stack<Character> stack = new Stack<>();

		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if(c == '(') {
				stack.push(c);
			}

			else { // 닫는 괄호일때,
				if(stack.isEmpty()) {
					return false;
				}

				stack.pop(); // 정상적으로 짝을 맞춰서 하나를 꺼냄
			}

		}
		return stack.isEmpty();
	}
}