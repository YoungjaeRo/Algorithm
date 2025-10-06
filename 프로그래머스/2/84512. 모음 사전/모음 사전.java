class Solution {
   /**
	 * 0번째 자리를 한칸 올리기 = 1 + 5 + 5^2 + 5^3 + 5^4 = 781
	 * 1번째 자리를 한칸 올리기 = 1 + 5 + 5^2 + 5 ^3 = 156
	 * 2번째 자리를 한칸 올리기 = 1 + 5 + 5^2 = 31
	 * 3번째 자리를 한칸 올리기 = 1 + 5 = 6
	 * 4번째 자리 = 1
	 *
	 * 가중치는[781, 156, 31, 6, 1]
	 */
	public int solution(String word) { // [AAAAE]
		int[] w = {781, 156, 31, 6, 1};

		String letter = "AEIOU";

		int answer = word.length();

		for (int i = 0; i < word.length(); i++) {
			int idx = letter.indexOf(word.charAt(i));

			answer = answer + w[i] * idx;
		}

		return answer;
	}
}