import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 최소 한개의 모음(a, e, i, o, u)와 2개의 자음으로 구성되어 있어야 하므로
	 *
	 * 나중에 depth에 도달하면, 구성 요소들의 모음과 자음 현황을 검사한다. ㅇㅇ
	 */

	static int L; // 암호문의 길이(depth)
	static int C; // 주어진 문자들 개수

	static String[] chars;
	static String[] pick;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 후보 문자들을 저장해놓을 배열 초기화
		chars = new String[C];

		// 선택 현황을 담아놓을 배열 초기화
		pick = new String[L];


		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < C; i++) {
		chars[i] = st.nextToken();
		}

		// 오름차순으로 정렬해주기
		Arrays.sort(chars);

		backtrack(0, 0);

		System.out.println(sb);
	}

	static void backtrack(int depth, int start) {
		if(depth == L) {
			if(isValid(pick)){
				for(String s : pick) {
					sb.append(s);
				}
				sb.append("\n");
			}
			return;
		}

		for(int i = start;  i < chars.length; i++) {
			pick[depth] = chars[i];

			backtrack(depth + 1, i + 1);
		}
	}

	static boolean isValid(String[] pick) {
		int moCount = 0;
		int jaCount = 0;

		for(String s : pick) {
			if(s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u")) {
				moCount++;
			} else {
				jaCount++;
			}
		}

		if(moCount >= 1 && jaCount >= 2) {
			return true;
		} else {
			return false;
		}
	}
}
