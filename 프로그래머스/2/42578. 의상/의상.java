import java.io.*;
import java.util.*;

/**
코니는 각 종류별로 최대 1가지 의상만 착용할 수 있다.

착용한 의상의 일부가 겹치더라도, 다른 의상이 겹치지 않거나, 혹은 의상을 추가로 더 착용한 
경우, 서로 다른 방법으로 옷을 착용한 것으로 계산한다.

일단은 각 카테고리별로 몇가지 옷이 있는지 Map으로 저장한다
그다음, 각 카테고리 별 + 1을 해줌 (안입는 경우도 있다고 했길래)
그 이후 마지막으로 1을 빼준다(아무것도 안입는 경우는 빼줘야함)


**/

class Solution {
    public int solution(String[][] clothes) {
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < clothes.length; i++) {
            String category = clothes[i][1];
            
            map.put(category, map.getOrDefault(category, 0) + 1);
        }
        
        /**
        각 종류마다 + 1을 한 후 곱해준다 ㅇㅇ
        **/
        int ans = 1;
        
        for(int a : map.values()) {
            ans = ans * (a + 1);
        }
        
        ans = ans - 1; // 알몸인 경우를 빼준다
        
        return ans;
        
        
    }
}