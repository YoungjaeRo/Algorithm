import java.io.*;
import java.util.*;

class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String input = br.readLine();
    int M = Integer.parseInt(br.readLine());


    Deque<String> back = new LinkedList(); // 앞으로만 삽입,삭제
    Deque<String> front = new LinkedList(); // 뒤로만 삽입, 삭제

    for(int i=0; i<input.length(); ++i) {
      front.addLast(Character.toString(input.charAt(i)));
    }

    for(int i=0; i<M; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String cmd = st.nextToken();

      if(cmd.equals("P")) { // front에 문자 넣기
        front.addLast(st.nextToken());
      } else if(cmd.equals("L")) {
        if(front.isEmpty()) continue;
        back.addFirst(front.pollLast());
      } else if(cmd.equals("D")) {
        if(back.isEmpty()) continue;
        front.addLast(back.pollFirst());
      } else { // B. 지우기
        if(front.isEmpty()) continue;
        front.removeLast();
      }
    }

    while(!front.isEmpty())
      bw.write(front.pollFirst());
    while(!back.isEmpty())
      bw.write(back.pollFirst());

    bw.flush();
    bw.close();
    br.close();
  }



}