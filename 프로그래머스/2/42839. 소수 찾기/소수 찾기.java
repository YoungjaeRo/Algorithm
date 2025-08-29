import java.util.*;
import java.io.*;

class Solution {
   public int solution(String numbers) {
		// 중복된 숫자 조합("011" → 11, 11 같은 중복)을 제거하기 위해 HashSet 사용
		HashSet<Integer> set = new HashSet<>();

		// 각 자리수를 이미 사용했는지 표시하기 위한 배열
		boolean[] used = new boolean[numbers.length()];

		// DFS(백트래킹)으로 숫자 만들기 시작
		dfs(numbers.toCharArray(), used, new StringBuilder(), set);

		// Hashmap에 모인 숫자들 중 소수만 카운드
		int ans = 0;
		for(int x : set) {
			if(isPrime(x)) {
				ans++;
			}
		}

		return ans;
	}

	/**
	 * dfs : 모든 자릿수를 사용해 만들 수 있는 수를 전부 생성
	 * @param a : 입력된 숫자들의 char 배열
	 * @param used : 해당 자리의 숫자를 썼는지 여부
	 * @param sb : 현재까지 이어붙인 숫자 문자열
	 * @param set : 만든 숫자들을 저장하는 HashSet
	 */

	public void dfs(char[] a, boolean[] used, StringBuilder sb, HashSet<Integer> set) {
		// 현재까지 만든 문자열이 1자리 이상이면 숫자로 변환해서 저장

		if(sb.length() > 0) {
			set.add(Integer.parseInt(sb.toString()));
		}

		// 숫자의 최대길이에 도달하면, 더 이상 진행하지 않음

		if(sb.length() == a.length) {
			return;
		}

		// 아직 사용하지 않은 숫자들을 하나씩 붙여가며 모든 경우를 탐색함
		for(int i = 0; i < a.length; i++) {
			if(used[i]) {
				continue;  // 이미 사용한 숫자는 건너뜀
			}

			used[i] = true; // 이번에 사용하게 되는 숫자에 대해서 true로 처리
			sb.append(a[i]); // 숫자를 뒤에 붙이기

			dfs(a, used, sb, set); // 재귀 호출로 다음 자리를 탐색

			sb.setLength(sb.length() - 1); // 마지막에 붙인 숫자 제거

			used[i] = false; // 사용 표시 해제

		}
	}

	/**
	 * isPrime: 소수 판별 함수
	 * @param n : 판별할 숫자
	 * @return true면 소수, false면 소수 아님
	 */
	private boolean isPrime(int n) {
		if (n < 2) return false;          // 0, 1은 소수가 아님
		if (n % 2 == 0) return n == 2;    // 2는 소수, 짝수는 소수 아님

		int r = (int)Math.sqrt(n);        // √n까지만 나눠보면 충분
		for (int d = 3; d <= r; d += 2) { // 홀수들만 검사
			if (n % d == 0) return false; // 나눠떨어지면 소수 아님
		}
		return true;
	}
}