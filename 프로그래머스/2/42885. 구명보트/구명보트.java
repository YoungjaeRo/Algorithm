import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
       Arrays.sort(people); // 오름차순으로 정렬

		int i = 0; // 가장 가벼운 사람 인덱스
		int j = people.length - 1; // 가장 무거운 사람 인덱스

		int boats = 0;

		while(i <= j) {
			boats++; // 보트 태운 횟수 1증가


			// 가장 무거운 j와 가장 가벼운 i를 왠만해선 같이 태워서 보내야함
			if(people[i] + people[j] <= limit) {
				i++; // 가벼운 사람도 탑승
			}

			j--; // 무거운 사람 탑승 처리



		}
		return boats;
	}
    
}