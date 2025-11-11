import java.util.*;
import java.io.*;


class Solution {
    public int solution(int[] people, int limit) {
        int boat = 0;
        
        Arrays.sort(people); // 일단은 오름차순으로 정렬함
        
        int i = 0;  //  가장 가벼운 사람 인덱스
        
        int j = people.length - 1; // 가장 무거운 사람의 인덱스
        
        while(i <= j) {
            
            // 일단 보트 하나는 무조건 나감
            boat++;
            
            if(people[i] + people[j] <= limit) {
                i++; // 가벼운 사람도 같이 탈 수 있으면, 가벼운 사람도 태움
            }
            
            j--;
        }
        
        return boat;
        
    }
}