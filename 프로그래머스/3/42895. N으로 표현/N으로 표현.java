import java.io.*;
import java.util.*;

class Solution {
    public int solution(int N, int number) {
       // 1. N을 한번만 사용해서 바로 number을 만들수 있으면 바로 1을 리턴
		if(N == number) {
			return 1;
		}

		// dp.get(k) == N을 정확히 k번 사용해서 만들 수 있는 "정수"들의 집합(Set)
		// 인덱스 편의를 위해 0~8까지 만들고, 0번 인덱스는 비워둠
		List<Set<Integer>> dp = new ArrayList<>();

		for(int i = 0; i <=8; i++) {
			dp.add(new HashSet<>());

		}

		// 붙여쓰기 값을 누적 계산하기 위한 변수
		// 예: N=5라면 k=1: 5, k=2: 55, k=3: 555 ... 이런 식으로 만듦
		int concat = 0;

		// k 1~8 까지만 탐색 (문제 제한)
		for(int k = 1; k <= 8; k++) {
			// 1) 붙여쓰기 수 하나 추가 (사칙연산만으로는 못 만드는 형태)
			//    concat = 이전 concat * 10 + N
			//    k=1 -> 5, k=2 -> 55, k=3 -> 555 ...
			concat = concat * 10 + N;
			dp.get(k).add(concat); // 각 1....8까지 에 대해서 일단은 concat(일반 붙여쓰기 숫자 넣어주기)

			//dp[k] = dp[i] + dp[k-i]

			if(concat == number) {
				return k;
			}
			// 2) k를 두 부분으로 쪼개서(i, k-i) 각각의 집합을 사칙연산으로 모두 결합
			//    예: k=4면 (1,3), (2,2), (3,1)을 모두 시도
			for(int i = 1; i < k; i++) {
				Set<Integer> left = dp.get(i);  // i번 사용해서 만들 수 있는 수들
				Set<Integer> right = dp.get(k - i); // (k-i)번 사용해서 만들 수 있는 수들

				for(int a : left) {
					for(int b : right) {
						// 덧셈/곱셈: 교환법칙이 있지만, Set이라 중복은 자동 제거됨
						dp.get(k).add(a + b);
						dp.get(k).add(a * b);

						// 뺄셈/나눗셈: 비가환 → a-b, b-a / a/b, b/a 모두 시도
						dp.get(k).add(a - b);
						dp.get(k).add(b - a);

						// 정수 나눗셈. 0으로 나누기 금지. 자바 int 나눗셈은 몫을 취함(버림).
						if(b != 0) {
							dp.get(k).add(a / b);
						}

						if(a != 0) {
							dp.get(k).add(b / a);
						}
					}
				}

				// 3) 이번 단계에서 목표 수가 만들어졌다면 k가 최소 사용횟수이므로 바로 반환
				if(dp.get(k).contains(number)) {
					return k;
				}
			}


		}

		// 8 번이상 사용했음에도 number를 만들 수 없으면 -1을 반환
		return -1;

	}
    
}