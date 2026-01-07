import java.io.*;
import java.util.*;

public class Main {

	// 원본 문자열
	static String s;

	// 괄호 쌍 (여는 문자열, 닫는 문자열)
	static ArrayList<int[]> pairs = new ArrayList<>();

	// removePairs[i] = true면 i번째 괄호쌍을 제거한다는 뜻
	static boolean[] removePairs;

	// 결과 중복 방지용 set
	static HashSet<String> set = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		s = br.readLine();

		// 괄호 쌍 찾기

		Stack<Integer> stack = new Stack<>();

		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if(c == '(') {
				stack.push(i);
			} else if(c == ')') {
				int openIndex = stack.pop();
				int endIndex = i;

				pairs.add(new int[]{openIndex, endIndex});
			}
		}

		// 괄호쌍 개수 만큼 제거 여부 배열
		removePairs = new boolean[pairs.size()];

		/**
		 * 괄호쌍을 지울지 말지 전부 시도 (모든 조합)
		 */

		backtrack(0);

		// 사전순 출력
		ArrayList<String> result = new ArrayList<>(set);

		Collections.sort(result);

		StringBuilder out = new StringBuilder();

		for(String r : result) {
			out.append(r).append("\n");
		}

		System.out.println(out);

	}

	static void backtrack(int idx) {

		// 1. 모든 괄호쌍에 대해 "지울지 말지" 결정을 끝냈을 때
		if(idx == pairs.size()) {

			// 최소 하나는 지원야 함
			boolean removedSomething = false;

			for(boolean b: removePairs) {
				if(b == true) {
					removedSomething = true;
					break;
				}
			}

			if(removedSomething == false) {
				return;
			}


			// 2. 현재 결정 상태를 바탕으로 결과 생성
			// 삭제할 문자 위치 표시 (true면 삭제)
			boolean[] deleted = new boolean[s.length()];

			// removePairs가 true인 괄호쌍들의 '('와 ')'를 삭제 표시
			for(int i = 0; i < pairs.size(); i++) {
				if(removePairs[i]) {
					int open = pairs.get(i)[0];
					int close = pairs.get(i)[1];

					deleted[open] = true;
					deleted[close] = true;
				}
			}

			// 삭제 표시 된 애들 빼고, 문자열을 만들기
			StringBuilder result = new StringBuilder();

			for(int i = 0; i < s.length(); i++) {
				if(!deleted[i]) {
					result.append(s.charAt(i));
				}
			}

			set.add(result.toString());
			return;

		}


		// 괄호쌍을 지운다
		removePairs[idx] = true;
		backtrack(idx + 1);

		// 괄호쌍을 안지운다
		removePairs[idx] = false;
		backtrack(idx + 1);
	}
}
