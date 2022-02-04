--플레이어 테이블
DROP TABLE PLAYER CASCADE CONSTRAINTS;
create table PLAYER (
    PLAYERNO number primary key,
    NICK VARCHAR2(50),
    tribe VARCHAR2(50),
    Lv number,
    HP number,
    MP number,
    ATK number,
    ATKSpeed number,
    SHEELD number,
    DEX number,
    STARTTIME DATE,
    SHOPCHECK VARCHAR2(5) default 'n',
    money number,
    ITEMNO number
);
--스킬테이블
DROP TABLE SKILL CASCADE CONSTRAINTS;
create table SKILL (
   SKNUM NUMBER primary key,
	tribe varchar2(50),
	SKNAME varchar2(50),
	SKDETAIL varchar2(50),
	SKTIME NUMBER,
	SKMP NUMBER,
	EFFECT NUMBER
);
--아이템 테이블
DROP TABLE ITEM CASCADE CONSTRAINTS;
create table ITEM (
    ITEMNO NUMBER primary key,
    ITEMNAME varchar2(50),
    tribe varchar2(50),
    PRICE NUMBER
);
--베틀테이블
DROP TABLE BATTLE CASCADE CONSTRAINTS;
create table BATTLE (
    BATTLENO NUMBER primary key,
	STAGE NUMBER,
	PLAYERNO NUMBER,
	MOnSTERNO NUMBER
);
--레코드 테이블
DROP TABLE RECORD CASCADE CONSTRAINTS;
create table RECORD (
   RECORDNO NUMBER primary key,
	STAGE NUMBER,
	NICK VARCHAR2(50),
	ENDTIME DATE
);
--몬스터 테이블
DROP TABLE monster CASCADE CONSTRAINTS;
create table monster (
    monsterNO number primary key,
    monsterNAME varchar2(50),
    tribe varchar2(50),
    Lv number,
    HP number,
    MP number,
    ATK number,
    ATKSpeed number,
    SHEELD number,
    DEX number
);