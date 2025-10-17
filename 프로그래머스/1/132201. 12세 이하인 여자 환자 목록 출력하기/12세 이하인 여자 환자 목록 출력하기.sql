-- 코드를 입력하세요
## 이름, 환자번호, 성별 코드, 나이, 전화번호
## 전화번호가 없는 경우 NONE으로 출력하고, 결과는 나이순 desc, 환자이름 asc

SELECT DISTINCT PT_NAME, PT_NO, GEND_CD, AGE, IFNULL(TLNO, 'NONE') AS TLNO
FROM PATIENT
WHERE AGE <= 12
AND GEND_CD = 'W'
ORDER BY AGE DESC, PT_NAME ASC;