class Solution {
   /**
	 * 최소 공배수, LCM을 구하는 문제이다.
	 * LCM(a, b, c, d)
	 * = LCM(LCM(LCM(a, b), c), d)
	 *
	 *
	 * 최소 공배수는 최대 공약수(유클리드 호제법)를 통해서 구하면 된다 ㅇㅇ
	 *
	 */

	// 최대 공약수(유클리드 호제법)
	static int gcd(int a, int b) {
		while(b != 0) {
			int t = a % b;
			a = b;
			b = t;
		}

		return a;
	}

	// 두수의 최소공배수 == 두 수의 곱 / 두수의 최대 공약수
	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	public int solution(int[] arr) {
		// 배열의 첫 번째 값을 기준으로 시작
		int answer = arr[0];

		//  차례대로 최소공배수 확장하기
		for(int i = 1; i < arr.length; i++) {
			answer = lcm(answer, arr[i]);
		}

		return answer;
	}
}