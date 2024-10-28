import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//길이가 같은 정수 배열 A와 B가 있음
		// 함수 S = A[0] * B[0] ....A[N-1] * B[N-1]의 합이 가장 작게 만들어라. 단! 배열 B는 움직이면 안됨

		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 문자열로 입력을 받아오기 때문에,
		int[] A = new int[N];

		//내림차순 정렬 (Collections.reverseOrder()을 하려면, int가 아닌, Integer로 선언
		Integer[] B = new Integer[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());

		// 각 배열에
		for(int i = 0; i < N; i++){
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st2.nextToken());
		}

		//A의 배열을 오름차순으로 정렬:
		Arrays.sort(A);

		//B의 배열은 내림차순으로 정렬
		Arrays.sort(B,Collections.reverseOrder());

		int sum = 0;
		for(int i = 0; i< N; i++){
			sum = sum + A[i] * B[i];
		}
		System.out.println(sum);
	}
}