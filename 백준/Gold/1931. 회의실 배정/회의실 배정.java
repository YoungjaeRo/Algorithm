import java.io.*;
import java.util.*;

public class Main {
	/**
	 * N개의 회의에 대해서, 회의실 사용표를 만들려고 한다
	 *
	 * 각 회의 I에 대해서, 시작시간과 끝나는 시간이 주어져 있고,
	 *
	 * 각 회의가 겹치지 않게 하면서, 회의실을 사용할 수 있는 화의의 최대 개수를 구하여라
	 *
	 * 회의는 한번 시작하면 중단할 수 없으며,
	 *
	 * 끝나는 시간이 가장 빠른 회의부터 선택한다
	 *
	 */

	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] meetings = new int[N][2];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			meetings[i][0] = Integer.parseInt(st.nextToken()); // 시작
			meetings[i][1] = Integer.parseInt(st.nextToken()); // 종료

		}

		// 빨리 끝나는 시간 순으로 정렬, 같으면, 빨리 시작하는 순으로 정렬하기
		Arrays.sort(meetings, new Comparator<int[]>() {

			// 외부에서 정렬기준을 정할땐, new Comparator<>() {}를 해주고,
			//  오버라이딩은 compareTo가 아닌 compare이다 ㅇㅇ
			@Override
			public int compare(int[] a, int[] b) {
				if(a[1] != b[1]) {
					return a[1] - b[1];
				} else {
					return a[0] - b[0];
				}
			}
		});

		int answer = 0; // 회의의 수

		int endTime = 0; // 마지막으로 진행된 회의의 종료시간
		
		for(int i = 0; i < N; i++) {
			// 지금 회의 시작 시간이 끝나는 시간보다 이상이면, 회의 시작가능
			if(meetings[i][0] >= endTime) {
				answer++;
				endTime = meetings[i][1];
			}
		}
		System.out.println(answer);

	}
}
