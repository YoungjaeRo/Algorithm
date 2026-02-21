import java.io.*;
import java.util.*;

class Solution {
    
    static int[] digits;           // 숫자 배열
    static boolean[] visited;      // 방문 체크
    static Set<Integer> set = new HashSet<>();  // 중복 제거
    
    
    public int solution(String numbers) {
        int n = numbers.length();
        digits = new int[n];
        visited = new boolean[n];
        
        //문자열 숫자로 받기
        for(int i = 0; i < n; i++) {
            digits[i] = numbers.charAt(i) - '0';
        }
        
        // 길이 1 ~ n까지 모두 생성하기
        for(int len = 1; len <= n; len++) {
            dfs(0, 0, len); // current, depth, len
        }
        
        int ans = 0;
        
        for(int s : set) {
            if(isPrime(s)) {
                ans++;
            }
        }
        
        return ans;
        
        
    }
    
    static void dfs(int current, int depth, int target) {
        if(depth == target) {
            set.add(current);
            return;
        }
        
        for(int i = 0; i < digits.length; i++) {
            
            // 방문하지 않은것들에 대해서만 
            if(!visited[i]) {
                
                visited[i] = true;
                
                dfs(10 * current + digits[i], depth + 1, target);
                
                // 순열은 꼭 원복을 해줘야 한다
                visited[i] = false;
            }
                
        }
    }
    
    // 이건 꼭 외워두자
    static boolean isPrime(int n) {
        if(n < 2) {
            return false;
        }
        
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}