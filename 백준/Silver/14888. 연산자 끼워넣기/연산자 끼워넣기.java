import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] nums;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     StringTokenizer st;

	     N = Integer.parseInt(br.readLine());

	     nums = new int[N];
	     
	     // 숫자들 입력
	     st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < N; i++) {
	            nums[i] = Integer.parseInt(st.nextToken());
	        }
	        
	        // 연산자 개수 입력: +, -, *, /
	        st = new StringTokenizer(br.readLine());
	        int plus  = Integer.parseInt(st.nextToken());
	        int minus = Integer.parseInt(st.nextToken());
	        int mul   = Integer.parseInt(st.nextToken());
	        int div   = Integer.parseInt(st.nextToken());
	        
	        // 첫 숫자에서 시작함
	        dfs(1, nums[0], plus, minus, mul, div);
	        
	       StringBuilder sb = new StringBuilder();
	       sb.append(max).append("\n").append(min);
	       
	       System.out.print(sb);
	        
	}
	
	
	  /**
     * idx : 다음에 사용할 숫자의 인덱스
     * cur : 지금까지의 계산 결과
     * plus, minus, mul, div : 앞으로 쓸 수 있는 연산자 개수
     */
	
	
	static void dfs(int idx, int cur, int plus, int minus, int mul, int div) {
		if(idx == N) { // 숫자를 전부 다 쓴 경우
			max = Math.max(max, cur);
			min = Math.min(min, cur);
			return;
		}
		
		int next = nums[idx];
		
		// + 사용
		if(plus > 0) {
			dfs(idx + 1, cur + next, plus - 1, minus, mul, div);
		}
		
		if(minus > 0) {
			dfs(idx + 1, cur - next, plus, minus - 1, mul, div);
			
		}
		
		// * 연산 사용
        if (mul > 0) {
            dfs(idx + 1, cur * next, plus, minus, mul - 1, div);
        }

        // / 연산 사용 (Java int 나눗셈은 0 쪽으로 버림이라 그대로 써도 됨)
        if (div > 0) {
            dfs(idx + 1, cur / next, plus, minus, mul, div - 1);
        }
	}
}
