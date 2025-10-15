import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 중복 제거 :  자료구조 Set 사용
		Set<String> set = new HashSet<>();

		for(int i = 0; i < N; i++) {
			set.add(br.readLine().trim());
		}

		//  리스트로 변환 (정렬하기 위해서 ㅇㅇ)

		List<String> list = new ArrayList<>(set);


		// 정렬 시작
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				if(a.length() != b.length()) {
					return a.length() - b.length();
				}

				return a.compareTo(b);

			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(String s : list) {
			sb.append(s).append("\n");
		}

		System.out.println(sb);
	}
}
