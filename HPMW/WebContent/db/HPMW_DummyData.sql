-- 은행 구분 테이블 데이터 입력 -- 
--    BTNO NUMBER(3) PRIMARY KEY,
--    BTNAME VARCHAR2(30) NOT NULL
INSERT INTO BANKTYPE VALUES(100, '하나은행' );
INSERT INTO BANKTYPE VALUES(200, '국민은행' );
INSERT INTO BANKTYPE VALUES(300, '카카오뱅크' );
INSERT INTO BANKTYPE VALUES(400, '토스' );
SELECT * FROM banktype;

-- 관리자 등급 테이블 데이터 입력 -- 
--    ALLEVEL NUMBER(1) PRIMARY KEY,
--    ALNAME VARCHAR2(20) NOT NULL
INSERT INTO ADMINLEVEL VALUES(1, 'AssistantManager' );
INSERT INTO ADMINLEVEL VALUES(2, 'DepartmentHead' );


-- 행사 타입 테이블 데이터 입력 --
--    ETNO NUMBER(2) PRIMARY KEY,
--    ETNAME VARCHAR2(20) NOT NULL
INSERT INTO EVENTSTYPE VALUES(10, 'DOLParty');
INSERT INTO EVENTSTYPE VALUES(20, 'PrivateEvent');
INSERT INTO EVENTSTYPE VALUES(30, 'Wedding');
INSERT INTO EVENTSTYPE VALUES(40, 'Business');
INSERT INTO EVENTSTYPE VALUES(50, 'OffSiteCatering');
SELECT * FROM EVENTSTYPE;

-- 관리자 테이블 데이터 입력 --
--    MNO NUMBER(8) PRIMARY KEY,
--    MPW VARCHAR2(30) NOT NULL,
--    MNAME VARCHAR2(20) NOT NULL,
--    ALLEVEL NUMBER(1) REFERENCES ADMINLEVEL(ALLEVEL) NOT NULL
INSERT INTO MANAGER(MNO, MPW, MNAME, ALLEVEL) VALUES(1111111, '1', 'ROMAN', 2);

INSERT INTO MANAGER(MNO, MPW, MNAME, ALLEVEL) VALUES(1811009, '1811009', '김진태', 1);
INSERT INTO MANAGER(MNO, MPW, MNAME, ALLEVEL) VALUES(1311117, '1311117', '박동규', 1);
INSERT INTO MANAGER(MNO, MPW, MNAME, ALLEVEL) VALUES(1212120, '1212120', '전하윤', 1);
SELECT * FROM MANAGER;

-- 행사 테이블 데이터 입력
--    EVNO VARCHAR2(9) PRIMARY KEY,
--    EVTITLE VARCHAR2(150) NOT NULL,
--    EVDETAIL VARCHAR2(4000),
--    EVSTARTDATE DATE NOT NULL,
--    ETNO NUMBER(2) REFERENCES EVENTSTYPE(ETNO) NOT NULL,
--    MNO NUMBER(8) REFERENCES MANAGER(MNO) NOT NULL
-- 행사테이블 용 시퀀스 생성
DROP SEQUENCE EVENTS_SEQ;
CREATE SEQUENCE EVENTS_SEQ NOCACHE NOCYCLE MAXVALUE 999;

INSERT INTO EVENTS(EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ETNO, MNO)
    VALUES(TO_CHAR(SYSDATE, 'RRMMDD') ||TRIM(TO_CHAR(EVENTS_SEQ.NEXTVAL, '009')), 'KIM-SOMIN DOLPARTY', '행사내용', 
             TO_DATE('2022/06/23', 'YYYY/MM/DD'), 10, 1811009);
INSERT INTO EVENTS(EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ETNO, MNO)
    VALUES(TO_CHAR(SYSDATE, 'RRMMDD') ||TRIM(TO_CHAR(EVENTS_SEQ.NEXTVAL, '009')), 'KIM PRIVATE PARTY', '개인행사', 
             TO_DATE('2022/07/23', 'YYYY/MM/DD'), 20, 1811009);             
INSERT INTO EVENTS(EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ETNO, MNO)
    VALUES(TO_CHAR(SYSDATE, 'RRMMDD') ||TRIM(TO_CHAR(EVENTS_SEQ.NEXTVAL, '009')), 'SON KIM WEDDING', '결혼식', 
             TO_DATE('2022/07/28', 'YYYY/MM/DD'), 30, 1811009);
INSERT INTO EVENTS(EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ETNO, MNO)
    VALUES(TO_CHAR(SYSDATE, 'RRMMDD') ||TRIM(TO_CHAR(EVENTS_SEQ.NEXTVAL, '009')), 'SINHANCARD BUSINESS', '기업행사', 
             TO_DATE('2022/07/04', 'YYYY/MM/DD'), 40, 1811009);
SELECT * FROM EVENTS;
commit;
--  파트타이머 테이블 데이터 입력
--    PTID VARCHAR2(5) PRIMARY KEY,
--    PTPW VARCHAR2(30) NOT NULL,
--    PTEMPCONCHEK NUMBER(1) DEFAULT 0 NOT NULL
INSERT INTO PARTTIMER(PTID, PTPW) VALUES('0586', '0586');
INSERT INTO PARTTIMER(PTID, PTPW) VALUES('1234', '1234');
INSERT INTO PARTTIMER(PTID, PTPW) VALUES('5894', '5894');
INSERT INTO PARTTIMER(PTID, PTPW) VALUES('1111', '1111');
INSERT INTO PARTTIMER(PTID, PTPW) VALUES('2804', '2804');

SELECT * FROM PARTTIMER;
 
-- 파트타이머 근로계약서 테이블 데이터 입력
--    PTCONNO VARCHAR2(8) PRIMARY KEY,
--    PTID VARCHAR2(5) REFERENCES PARTTIMER(PTID) NOT NULL,
--    PTNAME VARCHAR2(10) NOT NULL,
--    PTTEL VARCHAR2(13) NOT NULL,
--    PTEMAIL VARCHAR2(50),
--    PTADDRESS VARCHAR2(150),
--    BTNO NUMBER(3) REFERENCES BANKTYPE(BTNO) NOT NULL,
--    PTACCOUNTNO VARCHAR2(14) NOT NULL,
--    EVNO NUMBER(9),
--    PTSTATUS NUMBER(1) DEFAULT 0 NOT NULL, 
--    PTWORKTIME NUMBER(2,1),
--    PTHOURLYWAGE NUMBER(5),
--    PTTOTALPAY NUMBER(6)
DROP SEQUENCE PTCONT_SEQ;
CREATE SEQUENCE PTCONT_SEQ NOCACHE NOCYCLE;

INSERT INTO PARTTIMERCONTRACT(PTCONNO, PTID, PTNAME, PTTEL, PTEMAIL, PTADDRESS, BTNO, PTACCOUNTNO)
    VALUES(TRIM(TO_CHAR(PTCONT_SEQ.NEXTVAL, '00000009')), '0586', '홍길동', '010-1111-1111', 'HONG@NAVER.COM', '서울시 강남구 압구정', 300, '55444466668888');
INSERT INTO PARTTIMERCONTRACT(PTCONNO, PTID, PTNAME, PTTEL, PTEMAIL, PTADDRESS, BTNO, PTACCOUNTNO)
    VALUES(TRIM(TO_CHAR(PTCONT_SEQ.NEXTVAL, '00000009')), '1234', '장준길', '010-6568-4567', 'JANG@NAVER.COM', '충남 기산면', 200, '11112222555544');
INSERT INTO PARTTIMERCONTRACT(PTCONNO, PTID, PTNAME, PTTEL, PTEMAIL, PTADDRESS, BTNO, PTACCOUNTNO)
    VALUES(TRIM(TO_CHAR(PTCONT_SEQ.NEXTVAL, '00000009')), '5894', '서수화', '010-5546-8521', 'SAE@NAVER.COM', '남양주 다산동', 100, '55556666555544');

INSERT INTO PARTTIMERCONTRACT(PTCONNO, PTID, PTNAME, PTTEL, PTEMAIL, PTADDRESS, BTNO, PTACCOUNTNO, 
                              EVNO, PTSTATUS, PTWORKTIME, PTHOURLYWAGE, PTTOTALPAY)
    VALUES(TRIM(TO_CHAR(PTCONT_SEQ.NEXTVAL, '00000009')), '1111', '양효재', '010-5546-1111', 'YANG@NAVER.COM', '부산시 남구', 100, '55556666555544',
           '220627001', 1, 9, 10000, 90000);

INSERT INTO PARTTIMERCONTRACT(PTCONNO, PTID, PTNAME, PTTEL, PTEMAIL, PTADDRESS, BTNO, PTACCOUNTNO, 
                              EVNO, PTSTATUS, PTWORKTIME, PTHOURLYWAGE, PTTOTALPAY)
    VALUES(TRIM(TO_CHAR(PTCONT_SEQ.NEXTVAL, '00000009')), '2804', '송지승', '010-5546-2804', 'SONG@NAVER.COM', '인천', 200, '55556666555577',
           '220627002', 0, NULL, NULL, NULL);

SELECT * FROM PARTTIMERCONTRACT;


-- 건의 사항 게시판 테이블 데이터 입력
--    SBNO NUMBER(8) PRIMARY KEY,
--    PTID VARCHAR2(5) REFERENCES PARTTIMER(PTID) NOT NULL,
--    MNO NUMBER(8),
--    SBTITLE VARCHAR2(150) NOT NULL,
--    SBCONTENT VARCHAR2(4000),
--    SBFILENAME VARCHAR2(100),
--    SBPW VARCHAR2(10),
--    SBSTEP NUMBER(8) NOT NULL,
--    SBINDENT NUMBER(8) NOT NULL,
--    SBGROUP NUMBER(8) NOT NULL,
--    SBHIT NUMBER(8) DEFAULT 0 NOT NULL,
--    SBIP VARCHAR2(15) NOT NULL,
--    SBRDATE TIMESTAMP DEFAULT SYSDATE NOT NULL
DROP SEQUENCE SUGGBOARD_SEQ;
CREATE SEQUENCE SUGGBOARD_SEQ NOCACHE NOCYCLE;
-- 건의게시판 원글
INSERT INTO SUGGESTIONBOARD(SBNO, PTID , SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '0586', '건의사항 제목', '건의사항 내용', 'NOTHING.JPG', '1', 0, 0, SUGGBOARD_SEQ.CURRVAL, '123.123.123.12' );
INSERT INTO SUGGESTIONBOARD(SBNO, PTID , SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '1234', '건의사항 제목2', '건의사항 내용2', 'NOTHING.JPG', '1', 0, 0, SUGGBOARD_SEQ.CURRVAL, '123.123.123.12' );
INSERT INTO SUGGESTIONBOARD(SBNO, PTID , SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '1234', '건의사항 제목3', '건의사항 내용3', 'NOTHING.JPG', '1', 0, 0, SUGGBOARD_SEQ.CURRVAL, '123.123.123.12' );
INSERT INTO SUGGESTIONBOARD(SBNO, PTID , SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '5894', '건의사항 제목4', '건의사항 내용4', 'NOTHING.JPG', '1', 0, 0, SUGGBOARD_SEQ.CURRVAL, '123.123.123.12' );
SELECT * FROM SUGGESTIONBOARD;
-- 건의사항 답글 
INSERT INTO SUGGESTIONBOARD(SBNO, PTID, MNO, SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '5894', 1811009, '건의사항 제목4', '건의사항 내용4', 'NOTHING.JPG', '1', 1, 1, SUGGBOARD_SEQ.CURRVAL, '123.123.123.12' );




-- 공지사항 게시판 테이블 데이터 입력
--    NBNO NUMBER(8) PRIMARY KEY,
--    MNO NUMBER(8) REFERENCES MANAGER(MNO) NOT NULL,
--    NBTITLE VARCHAR2(150) NOT NULL,
--    NBCONTENT VARCHAR2(4000) NOT NULL,
--    NBFILENAME VARCHAR2(100),
--    NBHIT NUMBER(8) DEFAULT 0 NOT NULL,
--    NBRDATE TIMESTAMP DEFAULT SYSDATE NOT NULL
DROP SEQUENCE NOTICEBOARD_SEQ;

CREATE SEQUENCE NOTICEBOARD_SEQ NOCACHE NOCYCLE; 

INSERT INTO NOTICEBOARD(NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME) 
    VALUES(NOTICEBOARD_SEQ.NEXTVAL, 1811009, '공지사항1', '공지사항 내용', 'IMG.JPG');
INSERT INTO NOTICEBOARD(NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME) 
    VALUES(NOTICEBOARD_SEQ.NEXTVAL, 1811009, '공지사항2', '공지사항 내용2', 'IMG.JPG');
INSERT INTO NOTICEBOARD(NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME) 
    VALUES(NOTICEBOARD_SEQ.NEXTVAL, 1811009, '공지사항3', '공지사항 내용3', 'IMG.JPG');
SELECT * FROM NOTICEBOARD;

-- 근로계약서 테이블 데이터 입력
--    ECNO VARCHAR2(10) PRIMARY KEY,
--    ECTITLE VARCHAR2(150) NOT NULL,
--    ECCONTENT VARCHAR2(4000) NOT NULL
DROP SEQUENCE EMPLOYCONTRACT_SEQ;
CREATE SEQUENCE EMPLOYCONTRACT_SEQ NOCACHE NOCYCLE;

INSERT INTO EMPLOYCONTRACT(ECNO, ECTITLE, ECCONTENT)
    VALUES( TO_CHAR(SYSDATE, 'YYMMDD')||TRIM(TO_CHAR(EMPLOYCONTRACT_SEQ.NEXTVAL, '0009')), '현대산업개발호텔HDC근로계약서', 
               '사업주와 근로자는 다음과같은 근로계약을 체결한다. 1. 계약기간: 2. 근무장소:  3.근무내용 등등');
SELECT * FROM EMPLOYCONTRACT;

COMMIT;