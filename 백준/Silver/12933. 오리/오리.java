import java.io.*;
import java.util.*;



public class Main {
	
	static int idx(char ch) {
		
		
		if(ch == 'q') return 0;
		if(ch == 'u') return 1;
		if(ch == 'a') return 2;
		if(ch == 'c') return 3;
		if(ch == 'k') return 4;
		
		else {
			return -1;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine().trim();
		
		int[] stage = new int[5]; // q,u,a,c,k 단계 // quackquaqukcqua
		
		
		int activeDucks = 0;
		
		int maxDucks = 0;
		
		for(char ch : s.toCharArray()) {
			int cur = idx(ch);
			
			if(cur == 0) {
				// q 이므로 새로운 오리의 시작
				stage[0]++;
				
				activeDucks++;
				
				maxDucks = Math.max(maxDucks, activeDucks);
			
			} else {
				 // 'u', 'a', 'c', 'k' 는 이전 단계가 있어야 이동 가능
				if(stage[cur - 1] == 0) {
					System.out.println(-1);
					return;
					
				}
				
				// 이전 단계 오리 하나 제거
				stage[cur - 1]--;
				
				
				// 현재 단계로 이동
				stage[cur]++;
				
				// k 이면 한마리 끝 --> 제거
				if(cur == 4) {
					stage[4]--;
					activeDucks--;
					
				}
				
			}
		}
		
		// 모든 quack 이 완주 했다면, stge[0 .. 3]까지도 모두 0이어야 한다
		for(int i = 0; i <= 4; i++) {
			if(stage[i] != 0) {
				System.out.println(-1);
                return;
			}
		}
		
		System.out.println(maxDucks);
		
	}
}
