INSERT INTO topic (name, minimum_votes_required) VALUES ('파트장 투표', 10);
INSERT INTO topic (name, minimum_votes_required) VALUES ('데모데이 투표', 20);

-- ALTER TABLE voting_option ADD COLUMN vote_count INT DEFAULT 0;

-- voting_option 테이블에 데이터를 삽입할 때 voteCount 필드의 값도 함께 지정합니다.
INSERT INTO voting_option (name, topic_id, vote_count) VALUES ('리사', 1, 0);
INSERT INTO voting_option (name, topic_id, vote_count) VALUES ('제니', 1, 0);
INSERT INTO voting_option (name, topic_id, vote_count) VALUES ('로제', 1, 0);

INSERT INTO voting_option (name, topic_id, vote_count) VALUES ('커플로그', 2, 0);
INSERT INTO voting_option (name, topic_id, vote_count) VALUES ('TIG', 2, 0);
INSERT INTO voting_option (name, topic_id, vote_count) VALUES ('펫플레이트', 2, 0);
INSERT INTO voting_option (name, topic_id, vote_count) VALUES ('비트버디', 2, 0);
INSERT INTO voting_option (name, topic_id, vote_count) VALUES ('AZITO', 2, 0);
