import java.util.*;
import java.io.*;

public class Main {
	/**
	 * 최소의 거스름돈을 주기 위해서 5원을 최대한 많이 줘야함
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		int N = Integer.parseInt(br.readLine().trim());

		int count = 0;

		while(N >= 0) {
			if(N % 5 == 0) { // 남은 금액을 전부 5원으로 채울 수 있으면
				count = count + (N / 5);
				System.out.println(count);
				return;
			}
			// 5로 안나눠지면 2원 하나 사용
			N = N -2;
			count++;
		}

		System.out.println(-1);


	}
}
