import java.io.*;
import java.util.*;

public class Main {

	static char[] s;

	static boolean[] picked;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 문자 배열 초기화
		s = br.readLine().toCharArray();

		// 선택 배열 초기화
		picked = new boolean[s.length];

		solve(0, s.length - 1);
        
        System.out.println(sb.toString());

	}

	static void solve(int l, int r) {
		if(l > r) {
			return;
		}

		// 기본적으로 가장 작은 인덱스는 l
		int minIdx = l;

		// 정해진 범위내에 탐색하면서, 사전적으로 가장 작은 문자 출력
		for(int i = l; i <= r; i++) {
			if(s[i] < s[minIdx]) {
				minIdx = i;
			}
		}

		// 가장 작은 문자 선택
		picked[minIdx] = true;

		for(int i = 0; i < s.length; i++) {
			if(picked[i]) {
				sb.append(s[i]);
			}
		}
		sb.append("\n");

		// 오른쪽, 본인보다 뒤쪽 인덱스부터 처리
		solve(minIdx + 1, r);

		// 나머지 왼쪽 처리
		solve(l, minIdx - 1);
	}
}
