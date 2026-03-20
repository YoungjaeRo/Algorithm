import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 숫자의 범위가 최대 100000이므로
		boolean[] used = new boolean[100001];

		int left = 0; // 시작점

		long answer = 0; // 부분 수열 개수

		// right를 한칸 씩 늘려가면서 탐색

		for(int right = 0; right < N; right++) {

			// 현재 넣으려는 값(right)이 이미 구간에 있다면, 중복이므로 중복이 없어질 때까지 left를 이동
			while(used[arr[right]]) {

				used[arr[left]] = false;
				left++;
			}

			used[arr[right]] = true;
			answer = answer + (right - left + 1);

		}

		System.out.println(answer);


	}

}
