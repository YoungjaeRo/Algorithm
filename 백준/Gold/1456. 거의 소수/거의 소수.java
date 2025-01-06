import java.util.Scanner;

public class Main { // 거의 소수란 ? 소수의 N제곱을 거의 소수라고 한다
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		//소수의 개수를 구할 범위의 두 수를 입력받기

		long Min = scanner.nextLong();
		long Max = scanner.nextLong();

		// 입력값은 10의 14승 이므로 10의 7승까지 만 소수 탐색을 실행하면 된다.

		long [] A = new long[10000001];

		for(int i = 2; i < A.length; i++) { // 배열 초기화
			A[i] = i;
		}

		for(int i = 2; i <= Math.sqrt(A.length); i++) { // 제곱근까지만 소수 탐색을 해도 된다.
			if(A[i] == 0) { // 소수는 건너뜀
				continue;
			}

			for(int j = i + i; j < A.length; j = j + i) {
				A[j] = 0; // 각 배수들은 소수 처리 (0)
			}

		}
		int count = 0; // 소수의 개수가 몇개인지 담는 변수 선언

		for(int i = 2; i < A.length; i++) {
			if(A[i] != 0) { // 소수중에서 이제 그 소수의 N 제곱값들이 Min ~ Max 사이 인지 판별 & 이항정리를 활용
				long temp = A[i];
				while((double)A[i] <= (double)Max / (double)temp) {
					if((double)A[i] >= (double)Min / (double)temp) {
						count++;
					}
					temp = temp * A[i];
				}

			}
		}
		System.out.println(count);

	}
}
