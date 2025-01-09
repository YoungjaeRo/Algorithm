import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// 일반적인 출력을 하면, 시간 초과가 뜰 수 있기 떄문에, BufferedWriter을 사용한다.

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		long a = sc.nextLong();
		long b = sc.nextLong();

		long result = gcd(a, b);
		
		while(result > 0) { // 최대공약수가 0보다 클때까지 계속해서 1을 찍는다 ex : 3이면 111로 출력해야 하기 때문에 3 에서 1한번, 2에서 1한번, 1에서 1한번 이런식으로 
			bw.write("1");
			result--;
		}
		bw.flush();
		bw.close();

	}

	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
}
