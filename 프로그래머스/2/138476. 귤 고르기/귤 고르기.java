import java.io.*;
import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> map = new HashMap<>();

		for(int t : tangerine) {
			map.put(t, map.getOrDefault(t, 0) + 1);
		}

		// 개수들만 꺼내서 정렬준비
		ArrayList<Integer> counts = new ArrayList<>(map.values());

		// 많이 있는 크기부터
		Collections.sort(counts, Collections.reverseOrder());

		int answer = 0; // 사용한 서로 다른 크기의 수
		int sum = 0; // 현재까지 담은 귤 개수

		for(int cnt : counts) {
			sum = sum + cnt;
			answer++; // 가지수 1 증가시키기

			if(sum >= k) {
				break;

			}
		}

		return answer;
    }
}