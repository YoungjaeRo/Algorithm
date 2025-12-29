import java.util.ArrayDeque;
import java.util.Stack;

class Solution {
    boolean solution(String s) {
Stack<Character> stack = new Stack<>();

		//문자열의 길이 -1  만큼 루프
		int length = s.length();

		for(int i = 0; i < length; i++) {
			char c = s.charAt(i);

			// 열린 괄호라면, 스택에 삽입
			if(c == '(') {
				stack.push(c);
			} else { // 닫는 괄호이면, pop 대신 스택 자체가 비어있으면('(') 이 없으면 바로 false 출력
				if(stack.isEmpty()) {
					return false;
				}

				stack.pop();
			}
		}

		// 스택이 비어있으면 완벽한 문장, 아니면, 비완벽
		boolean answer = stack.isEmpty();

		return answer;
		
	}
}