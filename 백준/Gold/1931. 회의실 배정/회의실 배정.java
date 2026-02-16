import java.io.*;
import java.util.*;

public class Main {

	static class Meeting implements Comparable<Meeting>{
		int start;
		int end;

		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if(this.end != o.end) {
				return this.end - o.end;
			} else {
				return this.start - o.start;
			}
		}
	}

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		// 정렬기준으로 정렬된 회의들을 저장해놓을 리스트
		List<Meeting> meetings = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings.add(new Meeting(start, end));
		}

		// 종료시간이 빠른것부터 정렬하기 ㅇㅇ
		Collections.sort(meetings);

		int ans = 0;

		int lastEnd = 0;

		for(Meeting cur : meetings) {
		// 이번 회의 시작 시간이, 이전 회의의 종료 시간 이후라면, 겹치지 않는다
			if(cur.start >= lastEnd) {
				ans++;
				lastEnd = cur.end;
			}
		}

		System.out.println(ans);
	}
}
