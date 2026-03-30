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

		for(int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(students);

		long answer = 0;

		// 일단 선수 한명은 고정
		for(int i = 0; i < N - 2; i++) {

			int left = i + 1;
			int right = N - 1;

			while(left < right) {
				int sum = students[i] + students[left] + students[right];

				if(sum < 0) {
					left++;

				} else if(sum > 0) {
					right--;

				} else {
					// 합이 0일때

					// 1. 왼쪽부터, 오른쪽 값이 다 같을 경우 -- > 조합 공식
					if(students[left] == students[right]) {
						int len = right - left + 1;
						answer = answer + (long) (len * (len - 1) / 2);

						break;

					} else {
						// 2. 왼쪽 값과 오른쪽 값이 다를때, 최대한 같은 개수 구한뒤 곱해주기

						int leftCnt = 0;
						int rightCnt = 0;

						int leftV = students[left];
						int rightV = students[right];

						while(left <= right && leftV == students[left]) {
							leftCnt++;
							left++;
						}

						while(right >= left && rightV == students[right]) {
							rightCnt++;
							right--;
						}

						answer = answer + leftCnt * rightCnt;

					}

				}
			}
		}

		System.out.println(answer);
	}
}
