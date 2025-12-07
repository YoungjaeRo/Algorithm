import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		int[] sorted = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			arr[i] = x;
			sorted[i] = x;
		}

		/**
		 * 가장 작은 수부터 랭크를 매긴후, 본래의 배열 순으로 랭크 출력
		 */

		// 1. 랭크를 주기 위해서 오름차순으로 정렬함
		Arrays.sort(sorted);


		// 2. 각 랭크와, 숫자를 Map에 입력함
		Map<Integer, Integer> map = new HashMap<>();

		int rank = 0; // 당연히, 가장 높은 순위는 0
		for(int i = 0; i < N; i++) {
			int val = sorted[i]; // 가장 작은 수 부터 꺼내고

			if(!map.containsKey(val)) { // 중복제거 (해당 값, 즉 키를 가지고 있지 않다면)
				map.put(val, rank);
				rank++;
			}
		}
		
		// 3. 본래 배열의 순서에 맞게 각 랭크 출력
		for(int i = 0; i < N; i++) {
			sb.append(map.get(arr[i])).append(" ");
		}
		System.out.println(sb);

	}
}
