import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int tc = 0; tc < T; tc++) {
			int N = sc.nextInt(); // 현재

			int min = Integer.MAX_VALUE;

			int max = Integer.MIN_VALUE;

			for(int i = 0; i < N; i++) {
				int x = sc.nextInt();

				if(x < min) {
					min = x;
				}

				if(x > max) {
					max = x;
				}
			}

			System.out.println(min + " " + max);
		}

		sc.close();
	}
}
