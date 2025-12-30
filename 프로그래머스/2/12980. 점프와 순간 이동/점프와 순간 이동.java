import java.util.*;

public class Solution {
    public int solution(int n) {
        int battery = 0; // 배터리 사용횟수

		// 도착지에서 출바지로 거꾸로 함
		while(n > 0) {

			// n이 홀수면 순간이동은 비효율적이고, 불가능
			if(n % 2 == 1) {

				// 그냥 건전지로 한칸 이동 가능
				n = n -1; // 그전으로 한칸 이동

				battery++; // 배터리 1개 사용
				
			} else {
				n = n / 2; // 순간이동 하기 전의 칸으로 이동

			}

			if(n == 0) {
				break;
			}
		}

		int answer = battery;

		return answer;
    }
}