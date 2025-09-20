

import java.util.*;
import java.io.*;

public class Solution {
	public int solution(String[][] clothes) {
		Map<String, Integer> map = new HashMap<>();


		// 종류별로 개수 세기
		for(String[] c : clothes) {
			String type = c[1];

			map.put(type, map.getOrDefault(type, 0) + 1);

		}

		int answer = 1;

		 // (개수 + 1) 을 모두 곱하기
		for(int cnt : map.values()) {
			answer = answer * (cnt + 1);

		}

		return answer - 1; // 아무것도 안입은거 제외
	}
}