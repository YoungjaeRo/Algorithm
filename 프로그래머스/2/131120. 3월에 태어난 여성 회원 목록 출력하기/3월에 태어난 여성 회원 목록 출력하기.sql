-- 코드를 입력하세요
SELECT MEMBER_ID, MEMBER_NAME, GENDER, date_format(date_of_birth, '%Y-%m-%d') as date_of_birth

from MEMBER_PROFILE
WHERE gender = 'w'
    and tlno is not null
    and month(date_of_birth) = 3
order by member_id asc