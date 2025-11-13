import java.io.*;
import java.util.*;

/**
 * 단어 S가 주어짐, 각각의 소문자가 몇개씩 있는지 구하기
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		
		String S = sc.next();
		
		int[] cnt = new int[26]; // 각 알파벳 개수 저장용 배열임
		
		// 각 문자열에 대해서 개수 세기
		for(int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			
			int idx = c - 'a';
			cnt[idx]++;
			
		}
		
		//a 부터 z까지 순서대로 출력
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < cnt.length; i++) {
			sb.append(cnt[i]).append(" ");
		}
		
		System.out.println(sb.toString());
	}
}
