import java.util.*;
import java.io.*;

public class Main {
	/**
	 * 절반만 들어가는 쪽이 가장 작으면 됨,
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		double [] arr = new double[n];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < n; i++) {
			arr[i] = Double.parseDouble(st.nextToken());
		}

		// 오름차순 정렬
		Arrays.sort(arr);
		
		
		double result = arr[n-1]; // 가장 큰 드링크를 마지막에 남김
		for(int i = 0; i < n - 1; i++) {
			result = result + (arr[i] / 2);
		}

		System.out.println(result);
	}
}
