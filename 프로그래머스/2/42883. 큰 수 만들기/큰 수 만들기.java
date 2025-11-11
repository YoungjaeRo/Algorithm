import java.util.*;
import java.io.*;



class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(); // 남길 숫자들을 담을 스택
        
        for(int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            
            // 스택에 현재 들어있는 숫자가 지금 숫자보다 작으면 빼버림
            
            while(k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) < c) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            
            sb.append(c);
        }
        
        // 아직 못뺀 K가 남아있으면 뒤에서부터 잘라보기
        if(k > 0) {
            sb.setLength(sb.length() - k);
        }
        
        return sb.toString();
    }
}