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

		List<Integer> list = new ArrayList<>();

		// 정렬하기 위해서 list에 담음
		for(int a : arr) {
			list.add(a);
		}

		Collections.sort(list, Collections.reverseOrder());

		StringBuilder sb = new StringBuilder();
		
		for(int ans : list) {
			sb.append(ans).append("\n");
		}
        System.out.print(sb);

	}
}
