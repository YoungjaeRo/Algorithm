import java.io.*;
import java.util.*;

public class Solution {
	/**
	 * 매일 한가지 제품을 할인한다
	 * 할인하는 제품은 하루에 하나씩만 구매 가능
	 *
	 * 알뜰한 정현이는, 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우에
	 * 가입을 하려고 한다.
	 *
	 *
	 * @param want : 정현이가 원하는 제품을 나타내는 문자열 배열
	 * @param number : 정현이가 원하는 제품의 수량
	 * @param discount : 마트에서 할인하는 제품을 나타내는 문자열 배열
	 * @return : 회원등록시, 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수
	 *
	 * 열흘간의 단위로 봐야하기 때문에 고정해서 탐색?
	 * 슬라이딩 윈도우로 풀면 어떨까
	 *
	 */
	public int solution(String[] want, int[] number, String[] discount) {

		// 정현이가 원하는 품목의 개수 정보
		HashMap<String, Integer> need = new HashMap<>();
		for(int i = 0; i < want.length; i++) {
			need.put(want[i], number[i]);
		}

		// 할인 일수가 10일 보다 작으면 애초에 불가능
		if(discount.length < 10) {
			return 0;
		}

		// window : 연속 10일
		HashMap<String, Integer> window = new HashMap<>();

		// 첫 10일 인덱스 0 ~ 9까지 할인 품목 현황 채우기
		for(int i = 0; i <= 9; i++) {
			String item = discount[i];
			window.put(item, window.getOrDefault(item, 0) + 1);
		}

		int answer = 0;

		// 첫 구간 체크
		if(isPossible(need, window)) {
			answer++;
		}

		// 그 다음 구간들에 대해서, 슬라이딩 윈도우 실행하기
		// start 가  1일때, 2일때 ...

		for(int i = 1; i <= discount.length - 10; i++) {

			// 오른쪽으로 움직이면서, 빼줄 품목
			String outItem = discount[i - 1];

			// 다 빼면 안되고, 수량을 하나만 차감
			int outCount = window.get(outItem) - 1;

			// 잔여 상품개수가 0개이면, 아예 Map에서 삭제하기
			if(outCount == 0) {
				window.remove(outItem);
			} else {
				window.put(outItem, outCount);
			}

			// 새로 들어올 품목
			String inItem = discount[i + 9];
			window.put(inItem, window.getOrDefault(inItem, 0) + 1);

			// 체크
			if(isPossible(need, window)) {
				answer++;
			}
		}

		return answer;
	}

	static boolean isPossible(HashMap<String, Integer> need, HashMap<String, Integer> window) {

		for(String item : need.keySet()) {
			// 정현이의 수요 현황
			int required = need.get(item);

			// 공급 현황
			int have = window.getOrDefault(item, 0);

			// 공급이 수요에 미치지 못한다면,
			if(required > have) {
				return false;
			}
		}
		return true;
	}
}
