-- 관리자 관련 MangerDAO 
-- o관리자 로그인 : ManagerDTO loginManager(int mno, String mpw)
SELECT * FROM MANAGER WHERE MNO = '1111111' AND MPW = '1';

-- o매니저 관리자 생성 : int inSertManager(int mno, String mpw, String mname)  
INSERT INTO MANAGER(MNO, MPW, MNAME, ALLEVEL) VALUES(1811009, '1811009', '김진태', 1);
SELECT * FROM MANAGER;

-- o매니저 관리자 삭제 : int deleteManager(int mno)
DELETE FROM MANAGER WHERE MNO = 1311117;

-- o매니저 관리자 목록 : ArrayList<ManagerDTO> listManager()
SELECT * FROM MANAGER WHERE ALLEVEL != 2;
-- o매니저 관리자 아이디 중복체크 : int managerIdConfirm(int mno)
SELECT * FROM MANAGER WHERE MNO = 1811009;


-- o근로계약서 목록 : ArrayList<EmployContractDTO> listEmployContract()
SELECT * FROM EMPLOYCONTRACT;

-- o근로계약서 작성 : int insertEmployContract(EmployContractDTO ecdto)
INSERT INTO EMPLOYCONTRACT(ECNO, ECTITLE, ECCONTENT)
    VALUES( TO_CHAR(SYSDATE, 'YYMMDD')||TRIM(TO_CHAR(EMPLOYCONTRACT_SEQ.NEXTVAL, '0009')), '현대산업개발호텔HDC근로계약서', 
               '사업주와 근로자는 다음과같은 근로계약을 체결한다. 1. 계<br/>약기간: 2. 근무장소:  3.근무내용 등등');

-- o근로계약서 상세보기용 DTO 가져오기: EmployContractDTO detailEmployContract(String ecno)
SELECT ECNO, ECTITLE, ECCONTENT FROM EMPLOYCONTRACT 
    WHERE ECNO = '2207010001';

-- o근로계약서 수정 : int modifyEmployContract(EmployContractDTO ecdto )
UPDATE EMPLOYCONTRACT SET ECTITLE = '현대HDC호텔아이파크',
                                       ECCONTENT = '사업주와 근로자는 다음과 같은 근로계약을 체결한다. 1. 계약기간 : 2. 근무장소 : 3. 근무내용 : 등등'
                              WHERE ECNO = '2206270012';
-- o근로계약서 삭제 : int deleteEmployContract(String ecno)
DELETE FROM EMPLOYCONTRACT WHERE ECNO = '2207010003';                    
                              
-- 파트타이머 입력 : int insertParttimer(int ptid int ptpw)
INSERT INTO PARTTIMER(PTID, PTPW) VALUES(8888, 8888);

commit;
-- 공지사항 관련 : NoticeBoardDAO
-- o공지사항 글 목록 : ArrayList<NoticeBoardDTO> listNoticeBoard(int startRow, int endRow)
SELECT * 
    FROM (SELECT  ROWNUM RN, A.*
                 FROM (SELECT * FROM NOTICEBOARD ORDER BY NBRDATE DESC) A)
                             WHERE RN BETWEEN 1 AND 10;

-- 공지사항글 상세보기용 DTO가져오기 : NoticeBoardDTO detailNoticeBoard(int nbno)
-- o글상세보기전 조회수 올리기 : void noticeBoardhitCountUp(int nbno)
UPDATE NOTICEBOARD SET NBHIT = NBHIT + 1
    WHERE NBNO = 1;
SELECT NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME, NBHIT, NBRDATE FROM NOTICEBOARD WHERE NBNO = 1; 

-- o공지사항 글 등록 : int insertNoticeBoard(NoticeBoardDTO nbdto)
INSERT INTO NOTICEBOARD(NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME) 
    VALUES(NOTICEBOARD_SEQ.NEXTVAL, 1212120, '공지사항1', '공지사항 내용', 'IMG.JPG');

-- o공지사항 글 수정 : int modifyNoticeBoard(NoticeBoardDTO nbdto)
UPDATE NOTICEBOARD SET MNO = 1212120,
                                  NBTITLE = '공지사항 수정',
                                  NBCONTENT = '공지사항 내용 수정',
                                  NBFILENAME = 'IMG.JPG'
                                  WHERE NBNO = 1;

-- o공지사항 글 삭제 : int deleteNoticeBoard(int mno, int nbno)
DELETE FROM NOTICEBOARD WHERE MNO = 1811009 AND NBNO = 3;
   
                                  
-- 행사 관련 : EventsDAO
-- o행사 목록 가져오기 : ArrayList<EventsDTO> listEvents(int startRow, int endRow)
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ET.ETNO, ET.ETNAME, MNO 
                            FROM EVENTS E, EVENTSTYPE ET 
                                    WHERE E.ETNO = ET.ETNO ORDER BY EVSTARTDATE, EVNO) A)
                             WHERE RN BETWEEN 2 AND 5;
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT * FROM EVENTS 
                               ORDER BY EVSTARTDATE, EVNO) A)
                             WHERE RN BETWEEN 2 AND 5;                             
SELECT * FROM EVENTS;

-- o행사 상세보기 DTO가져오기 : EventsDTO detailEvents(String evno)
SELECT EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ET.ETNO, ET.ETNAME, MNO
    FROM EVENTS E, EVENTSTYPE ET WHERE E.ETNO = ET.ETNO AND EVNO = '220627004';

-- o행사 등록 : int insertEvents(EventsDTO edto)
INSERT INTO EVENTS(EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ETNO, MNO)
    VALUES(TO_CHAR(SYSDATE, 'RRMMDD') ||TRIM(TO_CHAR(EVENTS_SEQ.NEXTVAL, '009')), 'KYUNGHEEUC', '기업행사', 
             TO_DATE('2022/08/09', 'YYYY/MM/DD'), 40, 1811009);

-- o행사 수정 : int modifyEvents(EventsDTO edto)
UPDATE EVENTS SET EVTITLE = 'KOREAUC',
                         EVDETAIL = '변경된 행사내용',
                         EVSTARTDATE = TO_DATE('2022/08/11', 'YYYY/MM/DD'),
                         ETNO = 40,
                         MNO = 1811009
                         WHERE EVNO = '220627009';

-- o행사 삭제 : int deleteEvents(String evno, int mno)
DELETE FROM EVENTS WHERE EVNO = '220627006' AND MNO = 1811009;


-- 건의사항 게시판 관련 : SuggestionBoardDAO
-- o건의사항 게시판 글 목록 : ArrayList<SuggestionBoardDTO> listSuggestionBoard(int startRow, int endRow)
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT *  
                            FROM SUGGESTIONBOARD 
                                  ORDER BY SBGROUP DESC, SBSTEP) A)
                            WHERE RN BETWEEN 1 AND 6;

-- o건의게시판 글 상세보기 dto가져오기 : SuggestionBoardDTO detailSuggestionBoard(int sbno) 
-- o글 상세보기전 조회수 올리기
UPDATE SUGGESTIONBOARD  SET SBHIT = SBHIT +1 WHERE SBNO=1;
SELECT * FROM SUGGESTIONBOARD WHERE SBNO = 4;

-- o건의사항게시판 글쓰기 : int insertSuggestionBoard(SuggestionBoardDTO sbdto)
INSERT INTO SUGGESTIONBOARD(SBNO, PTID , SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '0586', '건의사항 제목', '건의사항 내용', 'NOTHING.JPG', '1', 0, 0, SUGGBOARD_SEQ.CURRVAL, '123.123.123.12' );

-- o건의 게시판 글 삭제 : int deleteSuggestionBoard(int sbno, int sbpw)
DELETE FROM SUGGESTIONBOARD WHERE SBNO = 4 AND SBPW = 1; 
SELECT COUNT(*) FROM SUGGESTIONBOARD;                                                   
                                                   
-- 건의사항 게시판 답글 쓰기
-- o답글쓰기 전 : void replyStep(int sbgroup, int sbstep) 
UPDATE SUGGESTIONBOARD SET SBSTEP = SBSTEP+1 
    WHERE SBGROUP = 1 AND SBSTEP>0;
-- o답글쓰기 : int insetReplySuggestionBoard(SuggestionBoardDTO sbdto)
INSERT INTO SUGGESTIONBOARD(SBNO, PTID, MNO, SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '5894', 1811009, '건의사항 제목4', '건의사항 내용4', 'NOTHING.JPG', '1', 1, 1, 1, '123.123.123.12' );
-- o건의사항게시판 답글 수정 : int modifyReplySuggestionBoard(SuggestionBoardDTO sbdto)
UPDATE SUGGESTIONBOARD SET SBTITLE = '수정된 답글1',
                           SBCONTENT = '수정된 내용1',
                           SBFILENAME = 'NOTHING,JPG',
                           SBIP = '123.152.125.12'
                        WHERE SBNO = 5 AND SBPW = 1;

select * from events;


-- 파트타이머 근로계약서 관련 : ParttimerContractDAO PARTTIMERCONTRACT
-- 파트타이머 근로계약서 목록 보기 : ArrayList<ParttimerContractDTO> listParttimerContract(int startRow, int endRow)
-- o파트타이머 목록 보기 :  ArrayList<ParttimerContractDTO> listParttimerContract(int startRow, int endRow)
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT PC.*, P.PTEMPCONCHEK  
                            FROM PARTTIMER P ,PARTTIMERCONTRACT PC  
                                WHERE P.PTID = PC.PTID  ORDER BY PTCONNO DESC) A)
                            WHERE RN BETWEEN 1 AND 10;
                           
-- 파트타이머 근로계약서 일일 보기 : ArrayList<ParttimerContractDTO> dailyListParttimerContract(int startRow, int endRow)
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT PC.*, P.PTEMPCONCHEK  
                            FROM PARTTIMER P ,PARTTIMERCONTRACT PC  
                                WHERE P.PTID = PC.PTID ORDER BY PTCONNO DESC) A)
                            WHERE RN BETWEEN 1 AND 10 AND PTRDATE = TO_DATE(SYSDATE, 'YY/MM/DD');
 
-- o파트타이머 & 파트타이머 근로계약서 상세보기 : ParttimerContractDTO detailParttimerContract(String ptconno)
SELECT PC.*, P.PTEMPCONCHEK  
    FROM PARTTIMER P ,PARTTIMERCONTRACT PC  
    WHERE P.PTID = PC.PTID AND PTCONNO = '00000019';

-- ptid&ptpw로 오늘 날짜 파트타이머 근로계약서 상세보기 : ParttimerContractDTO detailParttimerContract(String ptid, String ptname)
SELECT PC.*, P.PTEMPCONCHEK  
    FROM PARTTIMER P ,PARTTIMERCONTRACT PC  
    WHERE P.PTID = PC.PTID AND PC.PTID = '8888' AND PC.PTNAME = '이슬기' AND PC.PTRDATE = TO_DATE(SYSDATE, 'YY/MM/DD');

SELECT * FROM PARTTIMERCONTRACT; 

SELECT PC.*, P.PTEMPCONCHEK FROM PARTTIMER P ,PARTTIMERCONTRACT PC  
				WHERE P.PTID = PC.PTID AND PC.PTID = '1234' 
                    AND PC.PTRDATE = TO_DATE(SYSDATE, 'YY/MM/DD') ORDER BY PTCONNO DESC;

COMMIT;

-- 파트타이머 근로계약서 총 갯수 : int 	parttimerContractTotcnt
SELECT COUNT(*) FROM PARTTIMERCONTRACT;

-- o파트타이머 근로계약서 삭제 : int deleteParttimerContract(String ptconno)
DELETE FROM PARTTIMERCONTRACT WHERE PTCONNO = '00000016';


-- o파트타이머 근로계약서 행사정보 입력 : int insertEventsParttimer(String evno, String ptconno)
UPDATE PARTTIMERCONTRACT SET EVNO = '220627003' WHERE PTCONNO = '00000015';
SELECT * FROM PARTTIMERCONTRACT ;
-- o파트타이머 근로계약서 시급, 마감 처리 : int insertParttimerHourlyWage(int ptworktime, int pthourlywage, String ptconno)
UPDATE PARTTIMERCONTRACT SET PTWORKTIME = 12,
                             PTHOURLYWAGE = 9000,
                             PTTOTALPAY = (PTWORKTIME * PTHOURLYWAGE) 
                        WHERE PTCONNO = '00000020'; 
-- 파트타이머 시급 입력 후 총 금액 입력 로직 수행 
UPDATE PARTTIMERCONTRACT SET PTTOTALPAY = (PTWORKTIME * PTHOURLYWAGE) 
                        WHERE PTCONNO = '00000019';
-- o파트타이머 근로계약서 마감 : int intsertParttimerPtstatus(String ptconno)
UPDATE PARTTIMERCONTRACT SET PTSTATUS = 1
                        WHERE PTCONNO = '00000051';
                        
-- 파트타이머 근로계약서 마감 후 수정 : 
insert into parttimer VALUES (2222, 2222, 0); 
SELECT * FROM PARTTIMER;
commit;

-- 파트타이머 관련 : ParttimerDAO
-- o파트타이머 로그인 : ParttimerDTO loginParttimer(String ptid, String ptpw) 
SELECT * FROM PARTTIMER WHERE PTID = 0586 AND PTPW = 0586;
-- o파트타이머 아이디 비밀번호 찾기 : ParttimerDTO SearchPtidPtpw(String ptid, String ptname)
SELECT P.PTID, PTNAME, PTPW, PTEMPCONCHEK FROM PARTTIMER P,PARTTIMERCONTRACT PC
    WHERE P.PTID = PC.PTID AND P.PTID = '2804' AND PTNAME = '송지승';

-- o파트타이머 근로계약서 작성 : int insertParttimerContract(ParttimerContractDTO pcdto)
SELECT * FROM PARTTIMERCONTRACT;

INSERT INTO PARTTIMERCONTRACT(PTCONNO, PTID, PTNAME, PTTEL, PTEMAIL, PTADDRESS, BTNO, PTACCOUNTNO)
    VALUES(TRIM(TO_CHAR(PTCONT_SEQ.NEXTVAL, '00000009')), '5555', '한만운', '010-1111-5555', 'HONG@NAVER.COM', '서울시 강남구 압구정', 300, '55444466664848');


-- 파트타이머 근로계약서 작성후 PTEMPCONCHEK 수정 
UPDATE PARTTIMER SET PTEMPCONCHEK = 1 WHERE PTID = '8888';

-- 파트타이머 근로계약서 가져오기 :  ParttimerContractDTO detailParttimerContract(String ptconno)   
SELECT PC.*, P.PTEMPCONCHEK  
    FROM PARTTIMER P ,PARTTIMERCONTRACT PC  
    WHERE P.PTID = PC.PTID AND PTCONNO = '00000019';
    
-- 파트타이머 근로계약서 수정 : int modifyParttimerContract(ParttimerContractDTO pcdto)
UPDATE PARTTIMERCONTRACT SET PTNAME = '정효원',
                             PTTEL = '010-1111-1111',
                             PTEMAIL = 'JEONG@.NAVER.COM',
                             PTADDRESS = '충북 음성군',
                             BTNO = 400,
                             PTACCOUNTNO = '44445555666677'
                        WHERE PTCONNO = '00000019';
UPDATE PARTTIMERCONTRACT SET PTrdate = '22/07/05' where PTCONNO = '00000052';


-- 파트타이머 공지사항 게시판 관련    
-- 공지사항 글 목록 : ArrayList<NoticeBoardDTO> listNoticeBoard(int startRow, int endRow)
SELECT * 
    FROM (SELECT  ROWNUM RN, A.*
                 FROM (SELECT * FROM NOTICEBOARD ORDER BY NBRDATE DESC) A)
                             WHERE RN BETWEEN 2 AND 3;

-- 공지사항글 상세보기용 DTO가져오기 : NoticeBoardDTO detailNoticeBoard(int nbno)
-- 글상세보기전 조회수 올리기 : void noticeBoardhitCountUp(int nbno)
UPDATE NOTICEBOARD SET NBHIT = NBHIT + 1
    WHERE NBNO = 1;
SELECT NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME, NBHIT, NBRDATE FROM NOTICEBOARD WHERE NBNO = 1; 

-- 파트타이머 건의사항 게시판 관련
-- 건의사항 게시판 글 목록 : ArrayList<SuggestionBoardDTO> listSuggestionBoard(int startRow, int endRow)
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT *  
                            FROM SUGGESTIONBOARD 
                                  ORDER BY SBGROUP DESC, SBSTEP) A)
                            WHERE RN BETWEEN 1 AND 6;

-- 건의게시판 글 상세보기 dto가져오기 : SuggestionBoardDTO detailSuggestionBoard(int sbno) 
-- 글 상세보기전 조회수 올리기
UPDATE SUGGESTIONBOARD  SET SBHIT = SBHIT +1 WHERE SBNO=1;
SELECT * FROM SUGGESTIONBOARD WHERE SBNO = 4;

-- 건의사항게시판 글쓰기 : int insertSuggestionBoard(SuggestionBoardDTO sbdto)
INSERT INTO SUGGESTIONBOARD(SBNO, PTID , SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '0586', '건의사항 제목', '건의사항 내용', 'NOTHING.JPG', '1', 0, 0, SUGGBOARD_SEQ.CURRVAL, '123.123.123.12' );

-- 건의 게시판 글 삭제 : int deleteSuggestionBoard(int sbno, int sbpw)
DELETE FROM SUGGESTIONBOARD WHERE SBNO = 4 AND SBPW = 1; 

-- 건의사항게시판 수정 : int modifySuggestionBoard(SuggestionBoardDTO sbdto)
UPDATE SUGGESTIONBOARD SET SBTITLE = '수정된 답글1',
                           SBCONTENT = '수정된 내용1',
                           SBFILENAME = 'NOTHING,JPG',
                           SBIP = '123.152.125.12'
                        WHERE SBNO = 5 AND SBPW = 1;
                        
-- 행사 목록 가져오기 : ArrayList<EventsDTO> listEvents()
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ET.ETNAME, MNO 
                            FROM EVENTS E, EVENTSTYPE ET 
                                    WHERE E.ETNO = ET.ETNO ORDER BY EVSTARTDATE, EVNO) A)
                             WHERE RN BETWEEN 1 AND 10;
SELECT * FROM EVENTS;

-- 당일 행사 목록 가져오기 : 
SELECT * FROM EVENTS WHERE EVSTARTDATE = TO_DATE(SYSDATE, 'YY/MM/DD');

-- 행사 상세보기 DTO가져오기 : EventsDTO detailEvents(int evno)
SELECT EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ET.ETNO, ET.ETNAME, MNO
    FROM EVENTS E, EVENTSTYPE ET WHERE E.ETNO = ET.ETNO AND EVNO = '220627004';

-- 파트타이머 비밀번호 수정 : int modifyParttimerPw(ParttimerDTO ptdto)
UPDATE PARTTIMER SET PTPW = '1111'
                WHERE PTID = '1111';

                        
                        
-- 은행 타입 리스트
SELECT * FROM BANKTYPE;

-- 행사 타입 리스트
SELECT * FROM EVENTSTYPE;
        
        
           
                        
                        
                        
                        
                        
COMMIT;