

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		int sum = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}

		Arrays.sort(arr); // 한 번만 정렬하고 나머지 함수에서 재활용

		System.out.println(mean(sum, N));
		System.out.println(median(arr));
		System.out.println(freq(arr));
		System.out.println(range(arr));
	}

	// 1. 산술평균
	static int mean(int sum, int N) {
		return (int) Math.round((double) sum / N);
	}

	// 2. 중앙값
	static int median(int[] arr) {
		return arr[arr.length / 2];
	}

	// 3. 최빈값
	static int freq(int[] arr) {
		int[] freq = new int[8001]; // 인덱스 0 ~ 8000 → 값 -4000 ~ 4000

		for (int n : arr) {
			freq[n + 4000]++;
		}

		int maxFreq = 0;
		for (int f : freq) {
			if (f > maxFreq) maxFreq = f;
		}

		ArrayList<Integer> modes = new ArrayList<>();
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] == maxFreq) {
				modes.add(i - 4000);
			}
		}

		Collections.sort(modes);
		return modes.size() > 1 ? modes.get(1) : modes.get(0);
	}

	// 4. 범위
	static int range(int[] arr) {
		return arr[arr.length - 1] - arr[0];
	}
}
