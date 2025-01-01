import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 1. 정렬할 수의 개수
		int N = scanner.nextInt();

		// 2. 이제 그 수들을 저장할 배열을 선언하고 저장
		int[] A = new int[N];
		for(int i = 0; i < N; i++) {
			A[i] = scanner.nextInt();

		}

		// 3. 이진탐색은 꼭 정렬 후에 시행되어야 함으로, 이제 주입받은 데이터 값들을 정렬한다.
		Arrays.sort(A); // Arrays.sort()를 통해 오름차순으로 정렬한다.

		// 4. 탐색할 숫자 입력받기
		int M = scanner.nextInt();

		// 5. 탐색할 숫자의 갯수만큼 반복문 실행하기
		for(int i = 0; i < M; i++) {
			// 찾으려는 값을 찾았는지 판단하기 위해서 find변수를 선언함
			boolean find = false;

			// 6. 찾고자 하는 숫자들을 입력받음
			int target = scanner.nextInt();

			// 7. 이진탐색을 위한 시작점과 끝점을 설정
			int start = 0;
			int end = A.length - 1;

			while(start <= end) {
				// 이진탐색을 시작할 중간값을 지정헤줌
				int mid_index = (start + end) / 2;
				// 중간지점에 있는 실제 값
				int mid_value = A[mid_index];

				// 찾으려는 값이 중간값보다 작을때 --> 중간값보다 큰 지점은 탐색 영역에서 제외시킴
				if(target < mid_value) {
					end = mid_index - 1;
				}

				// 찾으려는 값이 중간값보다 클때 --> 중간값보다 작은 지점은 탐색 영역에서 제외시킴
				else if (target > mid_value) {
					start = mid_index + 1;

				}
				// 해당 값을 찾은 경우
				else {
					find = true;
					break;
				}
			}

			//find가 true이면, 1을 출력 아니면 0을 출력
			if(find) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}
}