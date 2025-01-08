import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N을 입력받는다
		int N = sc.nextInt();

		// 소수 판별에 사용할 배열 선언
		int[] arr = new int[10000001];

		// 배열에 값을 할당
		for(int i = 2; i < arr.length; i++) {
			arr[i] = i;
		}

		// N 과 10000000사이의 소수 구하기 ==> 에라토스테네스의 채 10^7의 제곱근까지만 판별하면 된다
		for(int i = 2; i <= Math.sqrt(arr.length); i++) {
			if(arr[i] == 0) { // 소수가 아니면(0)이면 뛰어 넘음
				continue;
			}

			for(int j = i + i; j < arr.length; j = j + i) { // 처음 선택되는 수를 제외하고 그의 배수들은 0으로 표시해줌으로써, 소수 후보에서 제외시킴
				arr[j] = 0;
			}
		}


		int i = N;

		while(true) { //N부터 1씩 증가시키면서 소수와 펠린드롬 수가 맞는지 확인하기
			// i가 전체 배열의 크기인 10000000을 초과하지 않게 검증
			if(i > arr.length) {
				break;
			}

			if(arr[i] != 0) {
				int result = arr[i];
			if(isPalindrome(result)) { // 팰린드롬 이라면, 값을 출력하고, 반복문을 빠져나온다.
				System.out.println(result);
				break;
			}
		}
			i++; // 해당 i번째 배열이 소수도 아니고, 팰린드롬도 아니라면, 한칸으로 이동하여 다음 수의 소수와 팰린드롬 여부를 판별
	}

}

public static boolean isPalindrome(int target) { // 필랜드롬 판별 함수
		char temp[] = String.valueOf(target).toCharArray(); // "문자열"을  '문자'들의 배열로 바꿔줌
		int start = 0;
		int end = temp.length - 1;
		while(start < end) {
			if(temp[start] == temp[end]) {
				start++;
				end--;
				continue;
			}
			return false;
		}
		return true;
	}
}
