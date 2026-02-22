import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] students;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		students = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(students);

		long answer = 0;

		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				int sum = students[i] + students[left] + students[right];

				if (sum == 0) {

					// 케이스 A: left~right가 전부 같은 값
					if (students[left] == students[right]) {
						int num = right - left + 1;
						answer += (long) num * (num - 1) / 2;
						break; 
					}

					// 케이스 B: left 값 묶음 개수 세기
					int leftValue = students[left];
					int leftCount = 0;
					while (left <= right && students[left] == leftValue) {
						left++;
						leftCount++;
					}

					// 케이스 B: right 값 묶음 개수 세기
					int rightValue = students[right];
					int rightCount = 0;
					while (right >= left && students[right] == rightValue) {
						right--;
						rightCount++;
					}

					answer += (long) leftCount * rightCount;
				}
				else if (sum < 0) {
					left++;
				}
				else {
					right--;
				}
			}
		}

		System.out.println(answer);
	}
}