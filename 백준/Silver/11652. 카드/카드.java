import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		// 카드 번호와 등장 횟수를 저장할 Map

		Map<Long, Integer> map = new HashMap<>();


		// 최대 등장 횟수와 그에 해당하는 숫자를 저장

		long maxCount = 0;
		long maxCard = 0;

		for(int i = 0; i < N; i++) {
			long num = Long.parseLong(br.readLine());


			// 현재 숫자의 등장횟수를 1 증가
			int count = map.getOrDefault(num, 0) + 1;
			map.put(num, count);

			// 현재 숫자가 가장 많이 나온 숫자라면 업데이트
			if(count > maxCount) {
				maxCount = count;
				maxCard = num;
			}

			// 등장 횟수는 같지만 숫자가 더 작다면 업데이트
			else if(count == maxCount && num < maxCard) {
				maxCard = num;
			}

		}
		
		// 정답 출력
		bw.write(Long.toString(maxCard));
		bw.newLine();

		// 스트림 종료
		bw.flush();
		bw.close();
		br.close();

	}
}
