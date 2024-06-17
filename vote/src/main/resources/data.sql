-- 투표 주제 초기 데이터 삽입 (중복 삽입 방지)
INSERT IGNORE INTO topic (name, minimum_votes_required)
VALUES ('프론트엔드 파트장 투표', 10), ('백엔드 파트장 투표', 10), ('데모데이 투표', 20);

-- voting_option 초기 데이터 삽입 (기존 vote_count 유지)
INSERT INTO voting_option (name, topic_id, vote_count)
SELECT vo.name, t.topic_id, 0 AS vote_count
FROM (
         SELECT '김수현' AS name, 1 AS topic_id -- '프론트엔드 파트장 투표'의 topic_id
         UNION ALL SELECT '김승완', 1
         UNION ALL SELECT '조유담', 1
         UNION ALL SELECT '김동혁', 1
         UNION ALL SELECT '김다희', 1
         UNION ALL SELECT '김민영', 1
         UNION ALL SELECT '송은수', 1
         UNION ALL SELECT '안혜연', 1
         UNION ALL SELECT '이지인', 1
         UNION ALL SELECT '이나현', 1

         UNION ALL SELECT '임형준', 2  -- '백엔드 파트장 투표'의 topic_id
         UNION ALL SELECT '장영환', 2
         UNION ALL SELECT '이도현', 2
         UNION ALL SELECT '이진우', 2
         UNION ALL SELECT '전민', 2
         UNION ALL SELECT '김성현', 2
         UNION ALL SELECT '박수빈', 2
         UNION ALL SELECT '박시영', 2
         UNION ALL SELECT '정기민', 2
         UNION ALL SELECT '권찬', 2

         UNION ALL SELECT 'Couplelog', 3 -- '데모데이 투표'의 topic_id
         UNION ALL SELECT 'TIG', 3
         UNION ALL SELECT 'Buldog', 3
         UNION ALL SELECT 'BeatBuddy', 3
         UNION ALL SELECT 'AZITO', 3
     ) AS vo
         JOIN topic t ON t.topic_id = vo.topic_id
         LEFT JOIN voting_option vo2 ON vo2.name = vo.name AND vo2.topic_id = vo.topic_id
WHERE vo2.voting_option_id IS NULL;