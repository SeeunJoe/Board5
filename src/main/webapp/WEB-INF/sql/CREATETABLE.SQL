----------------- 메뉴 목록---------------------
CREATE  TABLE   MENUS (
     MENU_ID     VARCHAR2(6)   PRIMARY KEY
    ,MENU_NAME   VARCHAR2(100)
    ,MENU_SEQ    NUMBER(5)
);

INSERT  INTO   MENUS  VALUES ('MENU01', 'JAVA', 1 );
COMMIT;

-------------------회원정보----------------------

CREATE TABLE TUSER(
  
    USERID       VARCHAR2(12)  PRIMARY KEY
  , PASSWD       VARCHAR2(12)  NOT NULL
  , USERNAME     VARCHAR2(30)  NOT NULL
  , EMAIL        VARCHAR2(320) 
  , UPOINT       NUMBER(10)    DEFAULT 0
  , INDATE       DATE          DEFAULT SYSDATE
  
  );
  
  COMMIT;
  
  --이 방식은 springboot에서 안먹힘 -> between and 사용하기
  10,000P <= VIP
  6,001 <= 우수회원 <= 8,000
  4,001 <= 일반회원 <= 6,000
  2,001 <= 준 회 원 <= 4,000
      0 <=  손  님  <= 2,000