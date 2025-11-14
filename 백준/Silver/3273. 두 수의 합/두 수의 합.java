import java.io.*;
import java.util.*;

public class Main {
	static int N; // 수열의 크기 (배열의 크기)
	static int[] arr; //  숫자들을 저장해놓을 배열
	static int X; // 두 수를 통해 만들어야 하는 숫자
	static int count; //  합을 이루는 조합이 몇개인지 추출하기
	
public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	N = Integer.parseInt(br.readLine());
	
	
	arr =  new int[N];
	
	// 배열 초기홤
	StringTokenizer st = new StringTokenizer(br.readLine());
	for(int i = 0; i < arr.length; i++) {
		
		arr[i] = Integer.parseInt(st.nextToken());
		
	}
	
	// 목표값인 X 값 받아내기
	X = Integer.parseInt(br.readLine());
	
	// 등장 여부 체크용 배열
	boolean[] exist = new boolean[10000001];
	
	for(int i = 0; i < N; i++) {
		int num = arr[i];
		int target = X - num;
		
		
		if(target >= 0 && target <= 1000000 && exist[target]) {
			count++;
		}
		
		exist[num] = true;
		
	}
	
	System.out.println(count);
	
	
	}
}
