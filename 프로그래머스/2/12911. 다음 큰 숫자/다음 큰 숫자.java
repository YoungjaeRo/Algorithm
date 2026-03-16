import java.io.*;
import java.util.*;


class Solution {
   public int solution(int n) {

		// 2진수 변환한 문자열을 받음
		int count = Integer.bitCount(n);
		
		int next = n + 1;
		
		while(true) {
			
			if(count == Integer.bitCount(next)) {
				break;
			} else {
				next++;
			}
		}
		
		return next;
	}
}