import java.io.*;
import java.util.*;

class Solution {
    public String solution(String s) {
       StringTokenizer st = new StringTokenizer(s);
		StringBuilder sb = new StringBuilder();

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			// 최소값 업데이트 해주기
			if(num < min) {
				min = num;
			}
			
			// 최대값 업데이트 해주기
			if(num > max) {
				max = num;
			}
		}
		
		sb.append(min).append(" ").append(max);

		String answer = sb.toString();
		
		return answer;
    }
}