import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 민식이는 다음과 같은 폴리오미노 2개를 무한개만큼 가지고 있다 ㅇㅇ
	 * AAAA와 BB
	 *
	 * 이제 여기서 .와 X로 이루어진 보드판이 주어졌을때, 민식이는 겹침없이 X를 모두
	 * 폴리오미노로 덮으려고 한다
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		s = s.replace("XXXX", "AAAA");

		s = s.replace("XX", "BB");

		// 이 이후에도  X가 남아있으면,
		if(s.contains("X")) {
			System.out.println("-1");
		} else {
			System.out.println(s);
		}
	}
}
