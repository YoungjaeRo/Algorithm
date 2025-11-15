import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	String line;

	// 입력이 끝날 때까지 한 줄씩 읽기 (EOF까지)
	while(( line = br.readLine() )!= null) {

		if(line.isEmpty()) {
			continue;
		}


		// s와 t는 공백을 두고 들어옴
		StringTokenizer st = new StringTokenizer(line);

		String s = st.nextToken();
		String t = st.nextToken();

		// s의 인덱스 (지금 찾고 있는 글자 위치)
		int idx = 0;

		// t를 왼쪽에서부터 한글자씩 훑는다
		for(int i = 0; i < t.length(); i++) {

			// 이미 s를 다 찾았으면 반복문을 종료하고 나와도 됨
			if(idx == s.length()) {
				break;
			}

			if(t.charAt(i) == s.charAt(idx)) {
				idx++;

			}
		}
		
		if(idx == s.length()) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
	
	}
}
