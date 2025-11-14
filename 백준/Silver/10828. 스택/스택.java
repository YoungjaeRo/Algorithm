import java.io.*;
import java.util.*;

public class Main {
	static Stack<Integer> stack;
	
	
	 public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     
	     int N = Integer.parseInt(br.readLine());
	     
	     int[] stack = new int[N];
	     
	     // 매우 중요
	     int top = -1;
	     
	     while(N -- > 0) {
	    	 
	    	 String line = br.readLine();
	    	 
	    	 String[] cmds = line.split(" ");
	    	 
	    	 String cmd = cmds[0];
	    	 
	    	 switch(cmd) {
	    	 
	    	 case "push" :
	    		 int num = Integer.parseInt(cmds[1]);
	    		 
	    		 top++; // 꼭 먼저 탑을 증가시켜줌
	    		 
	    		 stack[top] = num;
	    		 
	    		 break;
	    	
	    	 case "pop" : 
	    		 
	    		if(top == -1) {
	    			System.out.println(-1);
	    		} else {
	    			System.out.println(stack[top]);
	    			top--;	
	    		}
	    		break;
	    	 
	    	 case "size" :
	    		 System.out.println(top + 1);
	    		 break;
	    		 
	    	 case "empty" :
	    		 if(top == -1) {
	    			 System.out.println(1);
	    		 } else {
	    			 System.out.println(0); 
	    		 }
	    		 
	    		 break;
	    	
	    	 case "top" :
	    		 if(top == -1) {
	    			 System.out.println(-1);
	    		 } else {
	    			 System.out.println(stack[top]);
	    		 }
	    		 
	    		 break;
	    	 }
	    	 
	    	 
	     }
	 }
}
