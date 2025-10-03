import java.util.*;
import java.io.*;

class Solution {
   /**
	 * Programmers 42839 - 소수 찾기 (완전탐색)
	 *
	 * 아이디어 요약
	 * 1) 입력 숫자카드로 만들 수 있는 "모든 길이(1~N)의 수"를 만든다. => 부분순열(perm. of subset)
	 * 2) 만들어진 수를 Set<Integer>에 넣어 "같은 수" 중복 제거
	 * 3) 각 수가 소수인지 판별해 개수 합산
	 *
	 * 상태 변화 핵심
	 * - used[i] : i번째 '카드'를 현재 경로에서 사용 중인지 (같은 카드를 두 번 쓰지 않기 위해)
	 * - cur     : 지금까지 이어붙인 '숫자 문자열'. append -> 재귀 -> delete 로 되돌림(백트래킹)
	 * - all     : 지금까지 만든 모든 정수(중복 제거됨)
	 *
	 * 난이도 낮추기 위해 '같은 깊이에서의 가지 중복 컷(pickedHere)'은 쓰지 않았다.
	 * (중복 수는 Set이 제거해주고, N<=7이라 시간 통과 가능)
	 */
	public int solution(String numbers) {
		char[] digits = numbers.toCharArray();
		boolean[] used = new boolean[digits.length];

		// 완전 탐색으로 만들어지는 모든 수 (중복 제거)

		Set<Integer> all = new HashSet<>();

		// 길이 1 ~ N의 모든 부분수열 생성(완전탐색)
		dfs(digits, used, new StringBuilder(), all);

		//Set에 쌓인 수들 중 소수만 카운트

		int count = 0;

		for(int n : all) {
			if(isPrime(n)) {
				count++;
			}
		}

		return count;


	}

	/**
	 * 완전탐색(백트래킹)
	 * - 매 호출 시점의 cur(부분해)가 "길이 1 이상"이면 그 순간의 수를 all에 넣는다.
	 * - 그런 다음, 아직 쓰지 않은 카드 하나를 골라 cur에 붙이고 더 내려간다.
	 * - 내려갔다가 올라올 때는
	 * (1) cur에서 마지막 글자 제거, (2) used[i] 원복 (백트래킹의 기본 패턴)
	 */

	private void dfs(char[] digits, boolean[] used, StringBuilder cur, Set<Integer> all) {
		// 길이 1 이상이 되는 모든 순간마다 수를 하나 확장해서 Set에다가 넣는다

		if(cur.length() > 0) {
			int val = Integer.parseInt(cur.toString());
			all.add(val); // 이미 해당하는 숫자가 있으면 알아서 무시됨 (Set 특징)
		}


		// 더 붙일 카드가 없는 경우(최대 길이 도달)하면 여기서 끝
		if(cur.length() == digits.length) {
			return;
		}

		// 아직 쓰지 않은 카드를 하나 고른다
		for(int i = 0; i < digits.length; i++) {
			if(used[i]) { // 같은 카드를 두번 쓰지 않기 위해서 패스
				continue;
			}

			// 선택 단계
			used[i] = true; // i 번째 카드 사용 시작 (상태 변화 1)
			cur.append(digits[i]); // 현재 경로에 문자 1개 추가

			// 여기서 재귀로 더 내려가면:
			// - cur는 "지금까지의 선택"이 누적된 상태로 하위 길이를 탐색
			// - used는 "지금 선택한 카드가 사용 중"인 상태로 전달

			dfs(digits, used, cur, all);

			// 되돌리기, 백 트래킹
			// 내려갔다 오면, 이 호출에서 바꿨던 상태를 정확히 원위치로 되돌린다.
			cur.deleteCharAt(cur.length() - 1); // 방금 붙인 문자를 제거 (맨뒤)
			used[i] = false; // i 번째 카드 사용 해제
		}
		/**
		 * 소수 판별: √n 까지 나눠보기 (0,1은 소수 아님)
		 * - 입력 길이 최대 7 → 최댓값 9,999,999 → 이 방식으로 충분히 빠름
		 */
	}

	private boolean isPrime(int n) {
		if(n < 2) { // 0과 1은 소수가 아님
			return false;
		}
		if(n == 2) { // 2는 소수
 			return true;
		}

		if(n % 2 == 0) {
			return false;
		}

		int limit = (int) Math.sqrt(n);

		for(int i = 3; i <= limit; i += 2) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
}