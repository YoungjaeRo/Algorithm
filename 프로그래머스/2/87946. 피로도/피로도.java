import java.util.*;
import java.io.*;


class Solution {
    static boolean[] visited;
    
    static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
       int n = dungeons.length;
        
        visited = new boolean[n];
        
        dfs(k, dungeons, 0);
        
        return answer;
    }
    
    
    // k: 현재 피로도, cnt: 지금까지 클리어한 던전 수
    public void dfs(int k, int[][] dungeons, int cnt) {
        // 더 못 가더라도 지금까지의 개수로 최댓값 갱신
        answer = Math.max(answer, cnt);
        
        for(int i = 0; i < dungeons.length; i++) {
            
            int req = dungeons[i][0];
            int cost = dungeons[i][1];
            
            if(visited[i]) { // 이미 입장한 던전은 스킵함
                continue;
            }
            
            // 입장 가능하면, 다른 던전 도전!
            if(k >= req) {
                visited[i] = true;
                
                dfs(k - cost, dungeons, cnt + 1);
                
                visited[i] = false; // 백트래킹
                
                }
            }
        }
    }