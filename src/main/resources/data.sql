-- 1. Brand 테이블 생성
CREATE TABLE IF NOT EXISTS Brand (
                                     ID   INTEGER AUTO_INCREMENT not null primary key,  -- 자동 증가 설정
                                     BRAND_NAME CHARACTER VARYING(50) not null
);

-- 2. Category 테이블 생성
CREATE TABLE IF NOT EXISTS Category (

                                        ID INTEGER AUTO_INCREMENT not null primary key,  -- 자동 증가 설정
                                        CATEGORY_NAME CHARACTER VARYING(50) not null
);

-- 3. Product 테이블 생성
CREATE TABLE IF NOT EXISTS Product (
                                       ID          INTEGER AUTO_INCREMENT PRIMARY KEY,  -- H2에서 자동 증가를 위해 AUTO_INCREMENT 사용
                                       BRAND_ID    INTEGER NOT NULL,
                                       CATEGORY_ID INTEGER NOT NULL,
                                       PRICE       INTEGER NOT NULL,
                                       FOREIGN KEY (BRAND_ID) REFERENCES BRAND(ID),
                                       FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
);
--> 추후 수량을 위해서는 stock 테이블을 확장하여 사용 할 수 있음

-- 1. Brand 데이터 삽입


INSERT INTO Brand (BRAND_NAME) VALUES
                                   ( 'A'),
                                   ( 'B'),

                                   ( 'C'),
                                   ( 'D'),
                                   ( 'E'),
                                   ( 'F'),
                                   ( 'G'),
                                   ( 'H'),
                                   ( 'I');

-- 2. Category 테이블 삽입
INSERT INTO Category (CATEGORY_NAME) VALUES
                                         ( '상의'),
                                         ( '아우터'),
                                         ( '바지'),
                                         ( '스니커즈'),
                                         ( '가방'),
                                         ( '모자'),
                                         ( '양말'),
                                         ( '액세서리');



INSERT INTO Product (BRAND_ID, CATEGORY_ID, Price) VALUES
-- Brand A
(1, 1, 11200), (1, 2, 5500), (1, 3, 4200), (1, 4, 9000),
(1, 5, 2000), (1, 6, 1700), (1, 7, 1800), (1, 8, 2300),

-- Brand B
(2, 1, 10500), (2, 2, 5900), (2, 3, 3800), (2, 4, 9100),
(2, 5, 2100), (2, 6, 2000), (2, 7, 2000), (2, 8, 2200),

-- Brand C
(3, 1, 10000), (3, 2, 6200), (3, 3, 3300), (3, 4, 9200),
(3, 5, 2200), (3, 6, 1900), (3, 7, 2200), (3, 8, 2100),

-- Brand D
(4, 1, 10100), (4, 2, 5100), (4, 3, 3000), (4, 4, 9500),
(4, 5, 2500), (4, 6, 1500), (4, 7, 2400), (4, 8, 2000),

-- Brand E
(5, 1, 10700), (5, 2, 5000), (5, 3, 3800), (5, 4, 9900),
(5, 5, 2300), (5, 6, 1800), (5, 7, 2100), (5, 8, 2100),

-- Brand F
(6, 1, 11200), (6, 2, 7200), (6, 3, 4000), (6, 4, 9300),
(6, 5, 2100), (6, 6, 1600), (6, 7, 2300), (6, 8, 1900),

-- Brand G
(7, 1, 10500), (7, 2, 5800), (7, 3, 3900), (7, 4, 9000),
(7, 5, 2200), (7, 6, 1700), (7, 7, 2100), (7, 8, 2000),

-- Brand H
(8, 1, 10800), (8, 2, 6300), (8, 3, 3100), (8, 4, 9700),
(8, 5, 2100), (8, 6, 1600), (8, 7, 2000), (8, 8, 2000),

-- Brand I
(9, 1, 11400), (9, 2, 6700), (9, 3, 3200), (9, 4, 9500),
(9, 5, 2400), (9, 6, 1700), (9, 7, 1700), (9, 8, 2400);


--