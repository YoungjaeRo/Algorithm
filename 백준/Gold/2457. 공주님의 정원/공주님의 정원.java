import java.io.*;
import java.util.*;

public class Main {
	
	static class Flower implements Comparable<Flower> {
		
		int start;
		
		int end;
		
		Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Flower o) {
			if(this.start != o.start) {
				return this.start - o.start;
			} else {
				return o.end - this.end;
			}
		}
	}
	
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		List<Flower> flowers = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());

			int startDate = sm * 100 + sd;
			int endDate = em * 100 + ed;

			flowers.add(new Flower(startDate, endDate));
		}

		// 아까 지정해놓은 기준으로 정렬시작
		Collections.sort(flowers);

		// 가장 빠르게 시작하면서, 종료 시점이 가장 늦은 꽃들로 그리디 시작
		int ans = 0;
		int curDate = 301;
		int idx = 0;
		final int END = 1201;

		while(curDate < 1201) {

			int maxEnd = curDate;

			//start <= curDate 인 후보들을 전부 보면서 maxEnd 갱신
			while(idx < N && flowers.get(idx).start <= curDate) {
				maxEnd = Math.max(maxEnd, flowers.get(idx).end);
				idx++;
			}

			if(curDate == maxEnd) {
				System.out.println(0);
				return;
			}

			curDate = maxEnd;
			ans++;
		}
        
        System.out.println(ans);

	}
}
