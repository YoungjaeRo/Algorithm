import java.util.*;
import java.io.*;


class Solution {
    public int solution(int n, int[][] computers) {
        boolean [] visited = new boolean[n];
        int networks = 0;
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(i, computers, visited);
                networks++;
            }
        }
        return networks;
    }
    
    public void bfs(int start, int[][]computers, boolean[] visited) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()) {
            
            int cur = q.poll();
            
            for(int next = 0; next < computers.length; next++) {
                if(computers[cur][next] == 1 && !visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}