import java.io.*;
import java.util.*;


public class Main {
	/**
	 * 서로 다른 L개의 알파벳 소문자 ㅇㅇ 일단 prev가 필요가 없다
	 * 최소 한개의 모음(a,e,i,o,u) 과 최소 두개의 자음
	 *
	 * 알파벳 기준 오름차순
	 *
	 * C개중에서 4개를 고르기 (조합)
	 */

	static int L;
	static int C;

	static char[] letters;
	static char[] pick;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		letters = new char[C];
		pick = new char[L];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < C; i++) {
			letters[i] = st.nextToken().charAt(0);
		}

		// 문자 기준 오름차순으로 정렬
		Arrays.sort(letters);

		backtrack(0, 0, 0, 0); // depth, start, 모음 개수, 자음 개수

		System.out.println(sb.toString());
	}

	static void backtrack(int depth, int start, int mo, int ja) {

		if(depth == L) {
			if(mo >= 1 && ja >= 2) {
				for(char ans : pick) {
					sb.append(ans);
				}
				sb.append("\n");
			}
			return;
		}


		for(int i = start; i < C; i++) {
			char ch = letters[i];
			pick[depth] = ch;

			// 모음 / 자음을 카운트를 갱신함
			if(isVowel(ch)) {
				backtrack(depth + 1, i + 1, mo + 1, ja);
			} else {
				backtrack(depth + 1, i + 1, mo, ja + 1);
			}

		}
	}

	static boolean isVowel(char ch) {
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';

	}

}
