class Solution {
    public int[] solution(String s) {
		int transferCount = 0;
		int zeroCount = 0;
		
		// 매개변수가 s가 될때까지 반복
		while(!s.equals("1")) {
			int ones = 0;
			
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '1') {
					ones++;
				}
			}
			
			// 삭제한 0의 개수 저장
			zeroCount = zeroCount + (s.length() - ones);
			
			s = Integer.toBinaryString(ones);
			transferCount++;
		}
		
		return new int[]{transferCount, zeroCount};

	}
}