import java.util.*;
import java.io.*;

public class Main {
	static int N;

	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];

		for(int i = 0; i< N; i++) {
			arr[i] = Integer.parseInt(br.readLine());

		}

		// 오름차순으로 정렬
		Arrays.sort(arr);
		
		// 출력을 빠르게 하기 위해서 
		StringBuilder sb = new StringBuilder();

		
		for(int i = N - 1; i >= 0; i--) {
			sb.append(arr[i]).append('\n');
		}

		System.out.println(sb);
		
	}
}
