CREATE TABLE TBL_CUSTOM(					-- 고객번호
	custom_num number(3) PRIMARY KEY,
	p_or_s varchar2(10)		--포장 'P', 매장'S'
);


--CREATE SEQUENCE cus_seq;
--DROP SEQUENCE cus_seq;



SELECT * FROM TBL_CUSTOM tc ; -- 고객 조회

CREATE TABLE tbl_prod(						-- 상품
	prod_name varchar2(30),					-- 상품이름
	price number(8) NOT NULL,				-- 가격
	PRIMARY KEY (prod_name)
);

--INSERT INTO TBL_PROD (PROD_NAME,PRICE) VALUES ('카페라떼',3000);
--INSERT INTO TBL_PROD (PROD_NAME,PRICE) VALUES ('아메리카노',2500);
--INSERT INTO TBL_PROD (PROD_NAME,PRICE) VALUES ('에스프레소',2000);
--INSERT INTO TBL_PROD (PROD_NAME,PRICE) VALUES ('카라멜마끼야또',3500);

SELECT * FROM TBL_PROD ;	--메뉴 조회

CREATE TABLE tbl_order(
	order_num number(3) PRIMARY KEY,		-- 주문 번호
	custom_num number(3),					-- 고객 번호 (fk)
	prod_name varchar2(30),					-- 상품 이름 (fk)
	quantity number(2),						-- 수량
	buy_date timestamp DEFAULT sysdate,		-- 주문 날짜
	FOREIGN KEY(prod_name) REFERENCES tbl_prod (prod_name),
	FOREIGN KEY(custom_num) REFERENCES tbl_custom (custom_num)
);
	
CREATE SEQUENCE buy_seq;
DROP SEQUENCE buy_seq;

INSERT INTO TEAM_A.TBL_ORDER (ORDER_NUM, CUSTOM_NUM, PROD_NAME, QUANTITY, BUY_DATE) 
VALUES(buy_seq.nextval,1, '카페라떼', 2, sysdate);
INSERT INTO TEAM_A.TBL_ORDER (ORDER_NUM, CUSTOM_NUM, PROD_NAME, QUANTITY, BUY_DATE) 
VALUES(buy_seq.nextval,1, '아메리카노', 1, sysdate);
INSERT INTO TEAM_A.TBL_ORDER (ORDER_NUM, CUSTOM_NUM, PROD_NAME, QUANTITY, BUY_DATE) 
VALUES(buy_seq.nextval,1, '에스프레소', 3, sysdate);

INSERT INTO TEAM_A.TBL_ORDER (ORDER_NUM, CUSTOM_NUM, PROD_NAME, QUANTITY, BUY_DATE) 
VALUES(buy_seq.nextval,2, '카페라떼', 1, sysdate);
INSERT INTO TEAM_A.TBL_ORDER (ORDER_NUM, CUSTOM_NUM, PROD_NAME, QUANTITY, BUY_DATE) 
VALUES(buy_seq.nextval,2, '아메리카노', 2, sysdate);
INSERT INTO TEAM_A.TBL_ORDER (ORDER_NUM, CUSTOM_NUM, PROD_NAME, QUANTITY, BUY_DATE) 
VALUES(buy_seq.nextval,2, '카라멜마끼야또', 1, sysdate);

-- view 만들기
CREATE VIEW V_ORDER
as
SELECT o2.custom_num , o2.prod_name, o2.quantity, o2.PRICE, tc.p_or_s 
FROM TBL_CUSTOM tc
JOIN (SELECT o.custom_num , tp.prod_name, o.quantity, tp.PRICE, o.QUANTITY*tp.price AS pprice
FROM TBL_PROD tp
JOIN TBL_ORDER o ON tp.PROD_NAME = o.PROD_NAME ) o2
ON tc.custom_num = o2.custom_num 
ORDER BY CUSTOM_NUM ;
 
--모든 주문 조회
SELECT * FROM TBL_ORDER;	

--고객 번호가 1인 주문 조회
SELECT * FROM TBL_ORDER to2 WHERE CUSTOM_NUM =1;

--고객 번호별 주문 조회
SELECT o2.custom_num , o2.prod_name, o2.quantity, o2.PRICE, o2.pprice,c.p_or_s 
FROM TBL_CUSTOM c
JOIN (SELECT o.custom_num , p.prod_name, o.quantity, p.PRICE, o.QUANTITY*p.price AS pprice
FROM TBL_PROD p
JOIN TBL_ORDER o  
ON p.PROD_NAME = o.PROD_NAME ) o2
ON c.custom_num = o2.custom_num 
ORDER BY CUSTOM_NUM ;

--1번 고객 주문 조회
SELECT o2.custom_num , o2.prod_name, o2.quantity, o2.PRICE, o2.pprice, c.p_or_s 
FROM TBL_CUSTOM c
JOIN (SELECT o.custom_num , p.prod_name, o.quantity, p.PRICE, o.QUANTITY*p.price AS pprice
FROM TBL_PROD p
JOIN TBL_ORDER o  
ON p.PROD_NAME = o.PROD_NAME ) o2
ON c.custom_num = o2.custom_num 
WHERE o2.CUSTOM_NUM =1 
ORDER BY CUSTOM_NUM;

--고객 번호별 총 결제 금액
SELECT o2.custom_num , sum(o2.pprice) 
FROM TBL_CUSTOM c
JOIN (SELECT o.custom_num , p.prod_name, o.quantity, p.PRICE, o.QUANTITY*p.price AS pprice
FROM TBL_PROD p
JOIN TBL_ORDER o  
ON p.PROD_NAME = o.PROD_NAME ) o2
ON c.custom_num = o2.custom_num
GROUP BY o2.CUSTOM_NUM 
ORDER BY CUSTOM_NUM ;

--2번 고객 결제 금액
SELECT o2.custom_num , sum(o2.pprice) 
FROM TBL_CUSTOM c
JOIN (SELECT o.custom_num , p.prod_name, o.quantity, p.PRICE, o.QUANTITY*p.price AS pprice
FROM TBL_PROD p
JOIN TBL_ORDER o  
ON p.PROD_NAME = o.PROD_NAME ) o2
ON c.custom_num = o2.custom_num
WHERE o2.custom_num  =2
GROUP BY o2.CUSTOM_NUM
ORDER BY CUSTOM_NUM ;

-- 주문 순서 총 판매 현황
SELECT p.prod_name, o.quantity, p.PRICE, o.QUANTITY*p.price AS pprice
FROM TBL_PROD p
JOIN TBL_ORDER o  
ON p.PROD_NAME = o.PROD_NAME ;

--메뉴별 총 판매 현황
SELECT p.prod_name, sum(o.quantity) , sum(o.QUANTITY*p.price) AS pprice
FROM TBL_PROD p
JOIN TBL_ORDER o  
ON p.PROD_NAME = o.PROD_NAME 
GROUP BY p.PROD_NAME ;


--총 매출
SELECT SUM(pprice)
FROM 
(SELECT p.prod_name, o.quantity,p.PRICE, (o.QUANTITY*p.price) AS pprice
FROM TBL_PROD p
JOIN TBL_ORDER o  
ON p.PROD_NAME = o.PROD_NAME);