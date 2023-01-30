--====================================
-- MEMBER_ROLE
--====================================
create table MEMBER_ROLE (
    ROLE_ID CHAR(1), 
    ROLE_NAME VARCHAR2(20) NOT NULL,
    
    constraint PK_ROLE_ID primary key(ROLE_ID),
    constraint CK_ROLE_ID check(ROLE_ID in ('C', 'V', 'A')),
    constraint CK_ROLE_NAME check(ROLE_NAME in ('COMMON', 'VIP', 'ADMIN'))
);

-- comment
comment on column MEMBER_ROLE.ROLE_ID is '권한아이디';
comment on column MEMBER_ROLE.ROLE_NAME is '권한명';

-- 권한아이디 - 권한명
insert into MEMBER_ROLE values ('C', 'COMMON');
insert into MEMBER_ROLE values ('V', 'VIP');
insert into MEMBER_ROLE values ('A', 'ADMIN');

-- 커밋
commit;

select * from member_role;


--====================================
-- MEMBER
--====================================
CREATE TABLE MEMBER (
	MEMBER_ID VARCHAR2(30),
	FK_MEMBER_ROLE_ID CHAR(1) NOT NULL,
	PASSWORD VARCHAR2(300)	NOT NULL,
	MEMBER_NAME	VARCHAR2(20) NOT NULL,
	BIRTHDAY DATE,
	GENDER CHAR(1),
	EMAIL VARCHAR2(30) NOT NULL,
	PHONE VARCHAR2(11) NOT NULL,
	ADDRESS	VARCHAR2(50),
	ENROLL_DATE	DATE default sysdate,
    
    constraint PK_MEMBER_ID primary key(MEMBER_ID),
    constraint FK_MEMBER_ROLE_ID foreign key(FK_MEMBER_ROLE_ID) references MEMBER_ROLE(ROLE_ID),
    constraint CK_GENDER check(GENDER in ('F', 'M'))
);
-- GENDER not null 추가
alter table MEMBER modify GENDER NOT NULL;

-- comment
comment on column MEMBER.MEMBER_ID is '회원아이디';
comment on column MEMBER.FK_MEMBER_ROLE_ID is '회원권한아이디';
comment on column MEMBER.PASSWORD is '비밀번호';
comment on column MEMBER.MEMBER_NAME is '이름';
comment on column MEMBER.BIRTHDAY is '생년월일';
comment on column MEMBER.GENDER is '성별';
comment on column MEMBER.EMAIL is '이메일';
comment on column MEMBER.PHONE is '휴대폰번호';
comment on column MEMBER.ADDRESS is '주소';
comment on column MEMBER.ENROLL_DATE is '회원가입일자';

-- insert
insert into MEMBER
values (
    'admin', 'A', 'admin1234!', '관리자', NULL, 'F', 'admin@naver.com', '01012341234', NULL, default
);

insert into MEMBER
values (
    'honggd', 'C', 'honggd123!', '홍길동', NULL, 'M', 'honggd@naver.com', '01012341234', NULL, default
);

insert into MEMBER
values (
    'sinsa', 'C', 'sinsa123!', '신사임당', NULL, 'M', 'sinsa@naver.com', '01012341234', NULL, default
);

insert into MEMBER
values (
    'hyojung', 'V', 'phj123!', '박효정', NULL, 'F', 'phj@naver.com', '01012341234', NULL, default
);


select * from member
order by
    FK_MEMBER_ROLE_ID asc;


select
    *
from
    user_col_comments
where
    table_name = 'MEMBER';


--===========================
--RECIPE
--===========================
create table RECIPE(
    RECIPE_NO number,
    RECIPE_MEMBER_ID varchar2(30),
    RECIPE_TITLE varchar2(200) NOT NULL,
    RECIPE_CONTENT varchar2(4000) NOT NULL,
    RECIPE_READ number default 0,
    RECIPE_DATE date default sysdate,
    
    constraint PK_RECIPE_NO primary key(RECIPE_NO),
    -- 회원삭제시 레시피 작성자 null
    constraint FK_RECIPE_MEMBER_ID foreign key(RECIPE_MEMBER_ID) references MEMBER(MEMBER_ID) on delete set null
); 

-- comment
comment on column RECIPE.RECIPE_NO is '게시글번호';
comment on column RECIPE.RECIPE_MEMBER_ID is '작성자';
comment on column RECIPE.RECIPE_TITLE is '제목';
comment on column RECIPE.RECIPE_CONTENT is '내용';
comment on column RECIPE.RECIPE_READ is '조회수';
comment on column RECIPE.RECIPE_DATE is '등록일';

-- sequence
create sequence seq_recipe_no;

-- 확인
select
    *
from
    user_col_comments
where
    table_name = 'RECIPE';
    
--===========================
-- RECIPE_ATTACHMENT
--===========================
create table RECIPE_ATTACHMENT(
    RECIPE_FILE_NO number,
    RECIPE_ATTACHMENT_NO number NOT NULL,
    RECIPE_FILENAME varchar2(255) NOT NULL,
    RE_RECIPE_FILENAME varchar2(255) NOT NULL,
    RECIPE_FILE_DATE date default sysdate,
    
    constraint PK_RECIPE_FILE_NO primary key(RECIPE_FILE_NO),
    -- 레시피 게시글 삭제시 첨부파일도 삭제 
    constraint FK_RECIPE_ATTACHMENT_NO foreign key(RECIPE_ATTACHMENT_NO) references RECIPE(RECIPE_NO) on delete cascade
);

-- comment
comment on column RECIPE_ATTACHMENT.RECIPE_FILE_NO is '첨부파일번호';
comment on column RECIPE_ATTACHMENT.RECIPE_ATTACHMENT_NO is '게시글번호';
comment on column RECIPE_ATTACHMENT.RECIPE_FILENAME is '원본파일명';
comment on column RECIPE_ATTACHMENT.RE_RECIPE_FILENAME is '서버파일명'; -- 날짜 || - || 첨부파일번호 || .jpg
comment on column RECIPE_ATTACHMENT.RECIPE_FILE_DATE is '업로드일시'; 

-- sequence
create sequence seq_recipe_attachment_no;

-- 확인        
select
    *
from
    user_col_comments
where
    table_name = 'RECIPE_ATTACHMENT';
    
--===========================
-- CART
--===========================
create table CART (
    CART_NO number,
    CART_MEMBER_ID varchar2(30) not null, 
    CART_PRODUCT_NO VARCHAR2(20) not null,
    AMOUNT number default 1,
    
    constraint PK_CART_NO primary key(CART_NO),
    constraint FK_CART_MEMBER_ID foreign key(CART_MEMBER_ID) references MEMBER(MEMBER_ID) on delete cascade,
    constraint FK_CART_PRODUCT_NO foreign key(CART_PRODUCT_NO) references PRODUCT(PRODUCT_NO) on delete cascade
);

comment on column CART.CART_NO is '장바구니번호';
comment on column CART.CART_MEMBER_ID is '회원 아이디';
comment on column CART.CART_PRODUCT_NO is '제품 번호';
comment on column CART.AMOUNT is '수량';

-- sequence
create sequence seq_cart_no;

-- 확인        
select
    *
from
    user_col_comments
where
    table_name = 'CART';

----------------------------------------------------------
-- 1 CART - insert
insert into CART values (seq_cart_no.nextval, ?, ? ,?) -- 로그인중인 사용자 아이디, 해당 상품 no , 사용자가 선택한 수량

-- 2 ORDER - insert
insert into ORDER values (seq_order_no.nextval, ?, sysdate, ? , default) -- 로그인중인 사용자 아이디, 총액 (PRODUCT / CART DB)

-- 3 ORDER_DETAIL - insert
insert into ORDER_DETAIL values (seq_order_details_no, ?, ?, ?)
-- select fk_product_no from CART where 

-- 4 CART - delete
delete * from CART where FK_MEMBER_ID = ? 

----------------------------------------------------------


--===========================
-- ORDERS
--===========================
CREATE TABLE ORDERS (
	ORDER_NO VARCHAR2(20),
	ORDER_MEMBER_ID	VARCHAR2(30) NOT NULL,
	ORDER_DATE DATE DEFAULT SYSDATE,
	TOTALPRICE NUMBER NOT NULL,
	ORDER_STATE	NUMBER DEFAULT 1, -- 1 배송전 / 2 배송중 / 3 배송완료
    
    constraint PK_ORDER_NO primary key (ORDER_NO),
    constraint FK_ORDER_MEMBER_ID foreign key(ORDER_MEMBER_ID) references MEMBER(MEMBER_ID),
    constraint CK_ORDER_STATE check (ORDER_STATE in ('1', '2', '3'))
);
-- comment
     comment on column ORDERS.ORDER_NO is '주문번호';
     comment on column ORDERS.ORDER_MEMBER_ID is '주문자아이디';
     comment on column ORDERS.ORDER_DATE is '주문일자';
     comment on column ORDERS.TOTALPRICE is '주문총액';
     comment on column ORDERS.ORDER_STATE is '주문상태';

-- order_state 컬럼 수정
alter table ORDERS drop column ORDER_STATE;
alter table ORDERS add ORDER_STATE CHAR(1) default 'B' not null;
alter table ORDERS add constraint CK_ORDER_STATE check (ORDER_STATE in ('B', 'D', 'F'));

-- sequence
create sequence seq_order_no;

-- 확인        
select
    *
from
    user_col_comments
where
    table_name = 'ORDERS';

-- ORDER_NO
-- yes-230119-0001
'yes-' || to_char(sysdate, 'yymmdd') || '-' || lpad(seq_order_no.nextval ,4,'0')


--===========================
-- ORDER_DETAIL
--===========================
create table ORDER_DETAIL (
    ORDER_DETAIL_NO number, -- 제품주문번호
    FK_ORDER_NO VARCHAR2(20) not null, -- 주문번호
    DETAIL_PRODUCT_NO VARCHAR2(20) not null, -- 제품번호
    ORDER_DETAIL_AMOUNT NUMBER,
    
    constraint PK_ORDER_DETAIL_NO primary key(ORDER_DETAIL_NO),
    constraint FK_ORDER_NO foreign key(FK_ORDER_NO) references ORDERS(ORDER_NO) on delete cascade,
    constraint FK_DETAIL_PRODUCT_NO foreign key(DETAIL_PRODUCT_NO) references PRODUCT(PRODUCT_NO) on delete set null
);

-- sequence
create sequence seq_order_detail_no;

commit;

-- trigger
create or replace trigger ORDER_DETAIL_TRIGGER
    after 
    insert on ORDERS
    for each row
begin 
    proc_order_detail(:new.ORDER_NO, :new.ORDER_MEMBER_ID);
--    insert into 
--        ORDER_DETAIL(ORDER_DETAIL_NO, FK_ORDER_NO, DETAIL_PRODUCT_NO, ORDER_DETAIL_AMOUNT)
--    values (
--        seq_order_detail_no.nextval,
--        :new.ORDER_NO,
--        null,
--        null     
--    );
end;
/

---------
create or replace procedure proc_order_detail(
    order_no in ORDERS.ORDER_NO%type
    order_mem in ORDERS.ORDER_MEMBER_ID%type
)
is
    i number := 1;
    cnt number;
begin
    select
        count(*)
    into 
        cnt
    from 
        CART
    where
        CART_MEMBER_ID = order_mem;

        while i <= cnt loop 
            insert into
                ORDER_DETAIL(ORDER_DETAIL_NO, FK_ORDER_NO, DETAIL_PRODUCT_NO, ORDER_DETAIL_AMOUNT)
            values(
                    seq_order_detail_no.nextval, 
                    order_no,
                    (select CART_PRODUCT_NO from CART where CART_MEMBER_ID = order_mem),
                    (select AMOUNT from CART where CART_MEMBER_ID = order_mem)
            );
            i := i + 1;
        end loop;
        commit;
end;
/
--


--===========================
-- PRODUCT_CATEGORY
--===========================
create table PRODUCT_CATEGORY (
    CATEGORY_ID CHAR(2),
    CATEGORY_NAME VARCHAR2(50) NOT NULL,
    
    constraint PK_CATEGORY_ID primary key(CATEGORY_ID),
    constraint CK_CATEGORY_ID check(CATEGORY_ID in ('CO', 'MA', 'AC'))
);

-- comment
comment on column PRODUCT_CATEGORY.CATEGORY_ID is '카테고리ID';
comment on column PRODUCT_CATEGORY.CATEGORY_NAME is '카테고리명';

-- insert 
insert into PRODUCT_CATEGORY
values (
   'CO', 'coffee'
);
insert into PRODUCT_CATEGORY
values (
   'MA', 'machine'
);
insert into PRODUCT_CATEGORY
values (
   'AC', 'accessories'
);

-- 확인
select * from product_category;


--===========================
-- PRODUCT
--===========================
CREATE TABLE PRODUCT (
    PRODUCT_NO VARCHAR2(20),
	FK_CATEGORY_ID CHAR(2),
    PRODUCT_NAME VARCHAR2(50) NOT NULL,
	PRODUCT_PRICE NUMBER NOT NULL,
	PRODUCT_STOCK NUMBER DEFAULT 0,
	PRODUCT_SALECNT	NUMBER DEFAULT 0,
	PRODUCT_DATE DATE DEFAULT sysdate,
	THUMBNAIL_FILENAME VARCHAR2(255) NOT NULL,
	TYPE VARCHAR2(30) NOT NULL,
	AROMA VARCHAR2(30),
	ACIDITY	NUMBER,
	ROASTING NUMBER,
	CUP_SIZE CHAR(1),

    constraint PK_PRODUCT_NO primary key(PRODUCT_NO),
     -- CATEGORY_NO : restrict / 제품이 존재하는데 카테고리를 삭제할 수 없음
    constraint FK_CATEGORY_ID foreign key(FK_CATEGORY_ID) references PRODUCT_CATEGORY(CATEGORY_ID),
    constraint CK_PRODUCT_STOCK check(PRODUCT_STOCK >= 0),
    constraint CK_TYPE check(TYPE in('vertuo', 'original')),
    constraint CK_AROMA check(AROMA in('cocoa', 'biscuit', 'fruit')),
    constraint CK_ACIDITY check(ACIDITY >= 1 and ACIDITY <= 5), -- 1 ~ 5
    constraint CK_ROASTING check(ROASTING >= 1 and ROASTING <= 13), -- 1 ~ 13
    constraint CK_CUP_SIZE check(CUP_SIZE in('S', 'M', 'L'))
);
-- comment
comment on column PRODUCT.PRODUCT_NO is '제품 번호';
comment on column PRODUCT.FK_CATEGORY_ID is '카테고리 번호';
comment on column PRODUCT.PRODUCT_NAME is '제품명';
comment on column PRODUCT.PRODUCT_PRICE is '가격';
comment on column PRODUCT.PRODUCT_STOCK is '재고';
comment on column PRODUCT.PRODUCT_SALECNT is '판매량';
comment on column PRODUCT.PRODUCT_DATE is '제품 등록일';
comment on column PRODUCT.THUMBNAIL_FILENAME is '썸네일 파일';
comment on column PRODUCT.TYPE is '제품 타입';
comment on column PRODUCT.AROMA is '아로마';
comment on column PRODUCT.ACIDITY is '산미감';
comment on column PRODUCT.ROASTING is '강도';
comment on column PRODUCT.CUP_SIZE is '컵사이즈';

-- sequence
create sequence seq_product_no;

-- p001 
insert into 
    PRODUCT
values (
    'p' || lpad(seq_product_no.nextval, 4, '0'),
    'CO',
    '인피니망 더블 에스프레소',
    '10390',
    default,
    default, 
    default,
    'p' || lpad(seq_product_no.nextval, 4, '0') || '.jpg',
    'vertuo',
    'fruit',
    '3',
    '6',
    'M'
);

-- 확인        
select * from product;


--===========================
-- PRODUCT_DETAIL
--===========================
create table PRODUCT_DETAIL (
    DETAILS_NO NUMBER,
    PRODUCT_ATTACHMENT_NO VARCHAR2(20) NOT NULL,
    PRODUCT_FILENAME VARCHAR2(255) NOT NULL,
    RE_PRODUCT_FILENAME VARCHAR2(255) NOT NULL,
    PRODUCT_FILE_DATE DATE DEFAULT SYSDATE,
   
    constraint PK_DETAILS_NO primary key(DETAILS_NO),
    constraint FK_PRODUCT_ATTACHMENT_NO foreign key(PRODUCT_ATTACHMENT_NO) references PRODUCT(PRODUCT_NO) on delete cascade
);

-- comment
comment on column PRODUCT_DETAIL.DETAILS_NO is '첨부파일번호';
comment on column PRODUCT_DETAIL.PRODUCT_ATTACHMENT_NO is '제품번호';
comment on column PRODUCT_DETAIL.PRODUCT_FILENAME is '원본파일명';
comment on column PRODUCT_DETAIL.RE_PRODUCT_FILENAME is '서버파일명'; -- 날짜 || - || 첨부파일번호 || .jpg
comment on column PRODUCT_DETAIL.PRODUCT_FILE_DATE is '업로드일시';

-- sequence
create sequence seq_product_details_no;


-- 확인        
select
    *
from
    user_col_comments
where
    table_name = 'PRODUCT_DETAIL';

commit;

--===========================
-- REVIEW
--===========================
CREATE TABLE REVIEW (
	REVIEW_NO NUMBER,
	REVIEW_MEMBER_ID VARCHAR2(30) not null,
	REVIEW_ORDER_NO VARCHAR2(20) not null,
	REVIEW_PRODUCT_NO VARCHAR2(20) not null,
	REVIEW_TITLE VARCHAR2(200) not null,
	REVIEW_CONTENT VARCHAR2(500) not null,
	REVIEW_RATING NUMBER not null,
	REVIEW_DATE	DATE DEFAULT SYSDATE,

    constraint PK_REVIEW_NO primary key(REVIEW_NO),
    -- 회원탈퇴시 리뷰아이디 null
    constraint FK_REVIEW_MEMBER_ID foreign key(REVIEW_MEMBER_ID) references MEMBER(MEMBER_ID) on delete set null,
    constraint FK_REVIEW_ORDER_NO foreign key(REVIEW_ORDER_NO) references ORDERS(ORDER_NO), -- restricted
    constraint FK_REVIEW_PRODUCT_NO foreign key(REVIEW_PRODUCT_NO) references PRODUCT(PRODUCT_NO) on delete cascade,
    constraint CK_REVIEW_RATING check(REVIEW_RATING in('1', '2', '3', '4', '5')) -- 첫째자리 이후 반올림
);
-- comment
comment on column REVIEW.REVIEW_NO is '리뷰번호';
comment on column REVIEW.REVIEW_MEMBER_ID is '작성자';
comment on column REVIEW.REVIEW_ORDER_NO is '주문번호';
comment on column REVIEW.REVIEW_PRODUCT_NO is '제품번호';
comment on column REVIEW.REVIEW_TITLE is '제목';
comment on column REVIEW.REVIEW_CONTENT is '내용';
comment on column REVIEW.REVIEW_RATING is '별점';
comment on column REVIEW.REVIEW_DATE is '등록일';

-- sequence
create sequence seq_review_no;

-- 확인
select
    *
from
    user_col_comments
where
    table_name = 'REVIEW';

--===========================
-- REVIEW_ATTACHMENT
--===========================
DROP TABLE REVIEW_ATTACHMENT;

create table REVIEW_ATTACHMENT (
    REVIEW_FILE_NO NUMBER,
    FK_REVIEW_NO NUMBER NOT NULL,
    REVIEW_FILENAME VARCHAR2(255) NOT NULL,
    RE_REVIEW_FILENAME VARCHAR2(255) NOT NULL,
    REVIEW_FILE_DATE DATE DEFAULT SYSDATE,
   
    constraint PK_REVIEW_FILE_NO primary key(REVIEW_FILE_NO),
    constraint FK_REVIEW_NO foreign key(FK_REVIEW_NO) references REVIEW(REVIEW_NO) on delete cascade
);
-- comment
comment on column REVIEW_ATTACHMENT.REVIEW_FILE_NO is '첨부파일번호';
comment on column REVIEW_ATTACHMENT.FK_REVIEW_NO is '리뷰번호';
comment on column REVIEW_ATTACHMENT.REVIEW_FILENAME is '원본파일명';
comment on column REVIEW_ATTACHMENT.RE_REVIEW_FILENAME is '서버파일명'; -- 날짜 || - || 첨부파일번호 || .jpg
comment on column REVIEW_ATTACHMENT.REVIEW_FILE_DATE is '업로드일시';

-- sequence
create sequence seq_review_attachment_no;

-- 확인        
select
    *
from
    user_col_comments
where
    table_name = 'REVIEW_ATTACHMENT';


--===========================
-- QUESTION
--===========================
create table QUESTION (
    QUESTION_NO NUMBER,
    QUESTION_LEVEL NUMBER default 1, -- 문의(1) / 문의답변(2)
    QUESTION_REF_NO NUMBER, -- 문의(NULL) / 문의답변 (부모 문의 no 컬럼 / SELF-FK)
    QUESTION_MEMBER_ID VARCHAR2(30),
    QUESTION_PRODUCT_NO VARCHAR2(20) not null,
    QUESTION_TITLE VARCHAR2(60) not null,
    QUESTION_CONTENT VARCHAR2(500) not null,
    QUESTION_DATE DATE default sysdate,

    constraint PK_QUESTION_NO primary key(QUESTION_NO),
    constraint FK_QUESTION_MEMBER_ID foreign key(QUESTION_MEMBER_ID) references MEMBER(MEMBER_ID) on delete set null,
    constraint FK_QUESTION_PRODUCT_NO foreign key(QUESTION_PRODUCT_NO) references PRODUCT(PRODUCT_NO) on delete cascade,
    constraint FK_QUESTION_REF_NO foreign key(QUESTION_REF_NO) references QUESTION(QUESTION_NO) on delete cascade -- self fk
);

-- sequence
create sequence seq_question_no;

-- 확인
select
    *
from
    user_col_comments
where
    table_name = 'QUESTION';


--===========================
-- QUESTION_ATTACHMENT
--===========================
CREATE TABLE QUESTION_ATTACHMENT (
    QUESTION_FILE_NO Number,
	FK_QUESTION_NO NUMBER NOT NULL,
	QUESTION_FILENAME VARCHAR2(255)	NOT NULL,
	RE_QUESTION_FILENAME VARCHAR2(255) NOT NULL,
	QUESTION_FILE_DATE DATE DEFAULT SYSDATE,
    
    constraint QUESTION_FILE_NO primary key (QUESTION_FILE_NO),
    constraint FK_QUESTION_NO foreign key(FK_QUESTION_NO) references QUESTION(QUESTION_NO) on delete cascade
);

-- COMMENT
comment on column QUESTION_ATTACHMENT.QUESTION_FILE_NO is '첨부파일번호';
comment on column QUESTION_ATTACHMENT.FK_QUESTION_NO is '문의번호';
comment on column QUESTION_ATTACHMENT.QUESTION_FILENAME is '원본파일명';
comment on column QUESTION_ATTACHMENT.RE_QUESTION_FILENAME is '서버파일명'; -- 날짜 || - || 첨부파일번호 || .jpg
comment on column QUESTION_ATTACHMENT.QUESTION_FILE_DATE is '업로드일시';

-- sequence
create sequence seq_question_attachment_no;
select 
-- 확인
select
    *
from
    user_col_comments
where
    table_name = 'QUESTION_ATTACHMENT';
    
SELECT * FROM    ALL_CONSTRAINTS
WHERE    TABLE_NAME = 'order_detail';
