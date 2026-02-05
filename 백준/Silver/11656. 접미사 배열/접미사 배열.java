import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		StringBuilder sb = new StringBuilder();

		ArrayList<String> list = new ArrayList<>();

		for(int i = 0; i < line.length(); i++) {
			String cur = line.substring(i);

			list.add(cur);
		}

		// 사전순으로 정렬하기
		Collections.sort(list);
		
		for(String s : list) {
			sb.append(s).append("\n");
		}

		System.out.println(sb.toString());
	}
}
