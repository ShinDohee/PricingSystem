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
                                       ID          INT AUTO_INCREMENT PRIMARY KEY,  -- H2에서 자동 증가를 위해 AUTO_INCREMENT 사용
                                       BRAND_ID    INTEGER NOT NULL,
                                       CATEGORY_ID INTEGER NOT NULL,
                                       PRICE       INTEGER NOT NULL,
                                       FOREIGN KEY (BRAND_ID) REFERENCES BRAND(ID),
                                       FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
);
--> 추후 수량을 위해서는 stock 테이블을 확장하여

