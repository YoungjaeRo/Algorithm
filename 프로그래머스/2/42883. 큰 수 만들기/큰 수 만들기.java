class Solution {
    public String solution(String number, int k) {
		// StringBuilder를 '스택'처럼 사용 (push: append, pop: deleteCharAt)

		StringBuilder stack = new StringBuilder();

		int remove = k; // 제거할 수 있는 수의 개수

		for(int i = 0; i < number.length(); i++) {
			char cur = number.charAt(i);

			// 뒤에서 더 큰 숫자(cur)가 왔으니,
			// 앞에 있던 작은 숫자들을 가능한 만큼 제거한다.

			while(remove > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) < cur) {
			stack.deleteCharAt(stack.length() -1); //pop
				remove --;
			}
			
			stack.append(cur); // 현재 숫자 채텍
		}
        
        // 모든 숫자가 내림차순이어서 아에 못지운 경우, 뒤에서 추가로 잘라내기
		if(remove > 0) {
			stack.setLength(stack.length() - remove);
		}
		
		return stack.toString();
	}
}