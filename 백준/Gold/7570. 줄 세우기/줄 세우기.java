import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 줄 서 있는 어린이 중 한명을 선택하여, 제일 앞이나 뒤로 보낸다
	 *
	 * 이동해서 빈자리가 생기는 경우, 빈자리의 뒤에 있는 어린이가 앞으로 걸어와서 빈자리를 매꾼다
	 *
	 *
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		int N = Integer.parseInt(br.readLine());

		int[] pos = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		// 각 숫자들의 등장 위치(인덱스)를 저장
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			pos[num] = i;
		}

		int curLen = 1;
		int maxLen = 1;

		for(int i = 2; i <= N; i++) {

			if(pos[i] > pos[i - 1]) {
				curLen++;
			} else {
				// 연속으로 끊기면
				curLen = 1;
			}

			maxLen = Math.max(maxLen, curLen);
		}

		int ans = N - maxLen;

		System.out.println(ans);

	}
}
