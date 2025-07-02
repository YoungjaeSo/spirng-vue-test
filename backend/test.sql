DROP TABLE IF EXISTS tbl_board_attachment;
CREATE TABLE tbl_board_attachment
(
    no INTEGER AUTO_INCREMENT PRIMARY KEY, -- PK
    filename VARCHAR(256) NOT NULL, -- 원본 파일 명
    path VARCHAR(256) NOT NULL, -- 서버에서의 파일 경로
    content_type VARCHAR(56), -- content-type
    size INTEGER, -- 파일의 크기
    bno INTEGER NOT NULL, -- 게시글 번호, FK
    reg_date DATETIME DEFAULT now(),
    CONSTRAINT FOREIGN KEY (bno) REFERENCES tbl_board (no) ON DELETE CASCADE
);