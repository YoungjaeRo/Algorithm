import java.util.*;


public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//입력 받기

		// 레슨의 개수
		int N = sc.nextInt();

		// 블루레이 개수
		int M = sc.nextInt();

		// 기타 레슨 데이터를 저장해놓을 배열
		int[] A = new int[N];

		// 시작점과 끝점 초기화
		int start = 0;
		int end = 0;


		// 기타레슨 데이터를 저장해놓는 배열에 값 주입
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();

			if(start < A[i]) { // 레슨의 최댓값을 시작 인덱스로 설정
				start = A[i];
			}
			end = end + A[i]; // 모든 레슨의 총합을 종료 인덱스로 설정
		}

		// 해당 중간값의 크기의 블루레이를 가지고 주어진 강의들 담아보기 시작
		while(start <= end) {
			int middle = (start + end) / 2; // (9 + 45) /2 = 27
			int sum = 0;
			int count = 0;

			for(int i = 0; i < N; i++) { // 해당 middle 크기 만큼의 블루레이로 강의들을 저장할 수 있는지 확인.
			if(sum + A[i] > middle) { // 해당 크기의 블루레이 용량이 초과되면 count(블루레이 개수)에 +1
				count++;
				sum = 0; // 그리고 다시 총합을 0으로 초기화
			}
			// 이게 중요 : 초과하지 않으면 계속해서 해당 강의들의 시간을 축적해서 블루레이에서 저장함
			sum = sum + A[i];
			}

			if (sum != 0) { // 마지막 강의가 남아있다면 추가적인 블루레이를 하나 더 세어줍니다
				count++;
			}

			if (count > M) { // 주어진 개수보다 (3개)보다 많으면, 블루레이의 크기가 너무 작은것이므로 시작 인덱스를 middle + 1 큰 지점부터 탐색
				start = middle + 1;
			} else { // 주어진 개수보다 (3개)보다 적으면, 블루레이의 크기가 너무 큰것이므로 시작 인덱스를 middle + 1 큰 지점부터 탐색
				end = middle -1;
			}
		}
		System.out.println(start);
	}
}