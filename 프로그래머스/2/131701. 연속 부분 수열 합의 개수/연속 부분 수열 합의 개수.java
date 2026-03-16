import java.io.*;
import java.util.*;


class Solution {
    public int solution(int[] elements) {
		int size = elements.length;

		int[] arr = new int[size * 2];

		for(int i = 0; i < elements.length; i++) {
			// 크기를 2배로 만든 배열을 초기화 해주기
			arr[i] = elements[i];
			arr[i + size] = elements[i];
		}

		// 서로 다른 합만 저장할거기 때문에, Set을 선언
		Set<Integer> set = new HashSet<>();


		for(int i = 0; i < size; i++) {
			int sum = 0;

			for(int j = i; j < i + size; j++) {
				sum = sum + arr[j];
				set.add(sum);
			}
		}

		return set.size();
	}
}