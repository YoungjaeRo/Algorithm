import java.io.*;
import java.util.*;

public class Main {
	static int N;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		Map<String, Integer> map = new TreeMap<>();


		for(int i = 0; i < N; i++) {
			String s = br.readLine();

			int p = s.lastIndexOf("."); // 점의 위치

			String ext = s.substring(p + 1); // 확장자 추출
			map.put(ext, map.getOrDefault(ext, 0) + 1);

		}


		StringBuilder sb = new StringBuilder();

		/**
		 * Set<Map.Entry<Key, Value> e = map.keySet()키, 값 세트로 둘다 추출
		 * Set<Key> k = map.keySet() // 키값만 추출
		 * Collection<Values> v  = map.values() // 값만 추출
		 */

		for(Map.Entry<String, Integer> e : map.entrySet()) {
			sb.append(e.getKey()).append(" ").append(e.getValue()).append("\n");
		}

		System.out.println(sb);

	}
}
