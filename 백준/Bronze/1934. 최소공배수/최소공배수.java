import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		// 유클리드 호제법은 재귀함수로 구현한다.
		Scanner sc = new Scanner(System.in);

		// 테스트 케이스 횟수 입력받기
		int n = sc.nextInt();

		// 테스트 케이스 횟수 만큼 입력받기
		for(int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			// 최소 공배수를 구하는 식
			int result = a * b / gcd(a,b);

			System.out.println(result);
		}

	}
	public static int gcd(int a, int b) {
		// mod 연산의 결과 값인 b가 0이면, a가 최대공약수이다.
		if(b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
}
