import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 알고리즘 진행 순서
		// 1. 입력된 정보를 저장한다
		// 2. 5원을 거슬러 주는 개수를 내림차순으로 탐색한다.
		// 3.탐색 중에 2원과 더불어 거슬러 줄 때 동전의 개수를 결과로 출력한다.

		// 입력값을 처리하는 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 결과값을 출력하는 BufferedWriter
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int answer = -1;
		int N = Integer.parseInt(br.readLine());

		//5원을 사용할 수 있는 횟수 기준 내림차순 탐색
		for (int i = N / 5; i >=0; i--) { //13을 5원으로 나누고 그 몫에서 부터 시작
			if((N - (5 * i)) % 2 == 0) { //만약 5를 최대로 가지는 수의 나머지가 2원으로 거슬러 지면
				answer = i + (N -(5 * i))/2; // 5원의 개수와 2원의 개수를 저장
				break; //가장 먼저 거슬러 줄때가 최소의 개수임
			}
		}

		bw.write(answer + " ");
		bw.flush();
		bw.close();
		br.close();
	}
}