-- Drop any foreign key constraint on the team_id column in the member table
SET @fk_name := (SELECT CONSTRAINT_NAME
                 FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
                 WHERE TABLE_NAME = 'member'
                 AND COLUMN_NAME = 'team_id'
                 AND REFERENCED_TABLE_NAME = 'team');

SET @query := IF(@fk_name IS NOT NULL,
                 CONCAT('ALTER TABLE member DROP FOREIGN KEY ', @fk_name, ';'),
                 'SELECT "Foreign key does not exist";');

PREPARE stmt FROM @query;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Ensure that the team_id and leader_id are set to auto-increment if not already
ALTER TABLE team MODIFY team_id BIGINT AUTO_INCREMENT;
ALTER TABLE leader MODIFY leader_id BIGINT AUTO_INCREMENT;

-- Re-add the foreign key constraint to the member table with a new consistent name
ALTER TABLE member ADD CONSTRAINT FK_member_team_id FOREIGN KEY (team_id) REFERENCES team (team_id);

-- Insert initial values
INSERT INTO team (vote_count, name) VALUES (0, 'AZITO');
INSERT INTO team (vote_count, name) VALUES (0, 'BEATBUDDY');
INSERT INTO team (vote_count, name) VALUES (0, 'TIG');
INSERT INTO team (vote_count, name) VALUES (0, 'BULDOG');
INSERT INTO team (vote_count, name) VALUES (0, 'COUPLELOG');

INSERT INTO leader (vote_count, name, part) VALUES (0, '최현수', 'BACKEND');
INSERT INTO leader (vote_count, name, part) VALUES (0, '이영교', 'BACKEND');
INSERT INTO leader (vote_count, name, part) VALUES (0, '이유정', 'BACKEND');
INSERT INTO leader (vote_count, name, part) VALUES (0, '이윤서', 'BACKEND');
INSERT INTO leader (vote_count, name, part) VALUES (0, '배성준', 'FRONTEND');
INSERT INTO leader (vote_count, name, part) VALUES (0, '김지원', 'FRONTEND');
INSERT INTO leader (vote_count, name, part) VALUES (0, '노이진', 'FRONTEND');
INSERT INTO leader (vote_count, name, part) VALUES (0, '변지혜', 'FRONTEND');

