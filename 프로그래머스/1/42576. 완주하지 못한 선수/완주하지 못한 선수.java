import java.util.*;
import java.io.*;


class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        // 해시맵을 선언하여 참가자의 이름을 키로, 이름의 횟수를 값으로 지정
        HashMap<String, Integer> map = new HashMap<>();

        // participant 배열을 순회하면서 각 참가자의 이름을 해시맵에 저장
        for(String name : participant) {
            // getOrDefault 메서드를 활용해서 이미 해시맵에 존재하는 이름이면 값을 증가시키고
            // 존재하지 않으면 0을 기본값으로 설정하고 1을 더해준다.

            map.put(name, map.getOrDefault(name, 0) + 1);

        }

        // completion 배열을 순회하면서 각 완주자의 이름의 값을 해시맵에서 감소시킨다.
        for(String name : completion) {
            map.put(name, map.get(name) - 1);
        }

        // 해시맵을 순회하면서 값이 0이 아닌 키 (참가자의 이름)을 찾는다
        for(String key : map.keySet()) {
            if(map.get(key) != 0) {
                return key;
            }
        }
        return answer;
    }
}