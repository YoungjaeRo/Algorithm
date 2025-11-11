import java.io.*;
import java.util.*;


class Solution {
    // 입력 숫자들
    private char[] digits;
    
    // 각자리 사용 여부
    private boolean[] used;
    
    // 만들수 있는 모든 정수 후보 중복 제거용 HashSet 사용
    private Set<Integer> made = new HashSet<>();
    
    
    public int solution(String numbers) {
       digits = numbers.toCharArray();
       used = new boolean[numbers.length()];
        
        //  백트래킹으로 길이 1 ~ N의 모든 수를 생성한다
        dfs(0, 0);
        
        int count = 0;
        for(int n : made) {
            if(isPrime(n)) {
                count++;
            }
        }
        
        return count;
        
        
    }
    // 백트래킹: 아직 안 쓴 자리(i)를 하나 골라 붙인다.
    // curr: 지금까지 만든 정수값
    public void dfs(int depth, int curr) {
        // 길이가 1 이상이면, 일단 후보에 추가 (made)
        if(depth > 0) {
            made.add(curr);
        }
        
        // 더 붙여서 긴 숫자 만들기
        for(int i = 0; i < digits.length; i++) {
            
            if(used[i]) {
                continue;
            }
            
            used[i] = true;
            
            int next = curr * 10 + (digits[i] - '0'); // 새 자리 추가함
            dfs(depth + 1, next);
            
            used[i] = false; //  원복 (백트래킹)
            
        }
    }
    
    public boolean isPrime(int n) {
        if(n < 2) {
            return false; // 0과 1은 소수가 아님
        }
        
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    
}