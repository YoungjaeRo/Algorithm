import java.util.*;
import java.io.*;


class Solution {
    public int solution(int n, int[][] computers) {
        boolean [] visited = new boolean[n];
        int networks = 0;
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, computers, visited);
                networks++;
            }
        }
        return networks;
    }
    
    public void dfs(int start, int[][]computers, boolean[] visited) {
        visited[start] = true;
    
        for(int next = 0; next < computers.length; next++) {
            if(computers[start][next] == 1 && !visited[next]) {
                dfs(next, computers, visited);
            }
        }
    }
}