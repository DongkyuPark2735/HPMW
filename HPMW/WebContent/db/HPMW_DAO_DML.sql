-- 관리자 관련 MangerDAO 
-- 관리자 로그인 : ManagerDTO loginManager(int mno, String mpw)
SELECT * FROM MANAGER WHERE MNO = '1111111' AND MPW = '1';

-- 매니저 관리자 생성 : int inSertManager(int mno, String mpw, String mname)  
INSERT INTO MANAGER(MNO, MPW, MNAME, ALLEVEL) VALUES(1811009, '1811009', '김진태', 1);
SELECT * FROM MANAGER;

-- 매니저 관리자 삭제 : int deleteManager(int mno)
DELETE FROM MANAGER WHERE MNO = 1311117;

-- 매니저 관리자 목록 : ArrayList<ManagerDTO> listManager()
SELECT * FROM MANAGER WHERE ALLEVEL != 2;

-- 근로계약서 목록 : ArrayList<EmployContractDTO> listEmployContract()
SELECT * FROM EMPLOYCONTRACT;

-- 근로계약서 상세보기용 DTO 가져오기: EmployContractDTO detailEmployContract(String ecno)
SELECT ECNO, ECTITLE, ECCONTENT FROM EMPLOYCONTRACT 
    WHERE ECNO = '2206270012';

-- 근로계약서 수정 : int modifyEmployContract(EmployContractDTO ecdto )
UPDATE EMPLOYCONTRACT SET ECTITLE = '현대HDC호텔아이파크',
                                       ECCONTENT = '사업주와 근로자는 다음과 같은 근로계약을 체결한다. 1. 계약기간 : 2. 근무장소 : 3. 근무내용 : 등등'
                              WHERE ECNO = '2206270012';


-- 공지사항 관련 : NoticeBoardDAO
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

-- 공지사항 글 등록 : int insertNoticeBoard(NoticeBoardDTO nbdto)
INSERT INTO NOTICEBOARD(NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME) 
    VALUES(NOTICEBOARD_SEQ.NEXTVAL, 1811009, '공지사항1', '공지사항 내용', 'IMG.JPG');

-- 공지사항 글 수정 : int modifyNoticeBoard(NoticeBoardDTO nbdto)
UPDATE NOTICEBOARD SET MNO = 1212120,
                                  NBTITLE = '공지사항 수정',
                                  NBCONTENT = '공지사항 내용 수정',
                                  NBFILENAME = 'IMG.JPG'
                                  WHERE NBNO = 1;

-- 공지사항 글 삭제 : int deleteNoticeBoard(String mno)
DELETE FROM NOTICEBOARD WHERE MNO = '1811009' AND NBNO = 3;
   
                                  
-- 행사 관련 : EventsDAO
-- 행사 목록 가져오기 : ArrayList<EventsDTO> listEvents()
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ET.ETNAME, MNO 
                            FROM EVENTS E, EVENTSTYPE ET 
                                    WHERE E.ETNO = ET.ETNO ORDER BY EVSTARTDATE, EVNO) A)
                             WHERE RN BETWEEN 2 AND 5;

SELECT * FROM EVENTS;

-- 행사 상세보기 DTO가져오기 : EventsDTO detailEvents(int evno)
SELECT EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ET.ETNO, ET.ETNAME, MNO
    FROM EVENTS E, EVENTSTYPE ET WHERE E.ETNO = ET.ETNO AND EVNO = '220627004';

-- 행사 등록 : int insertEvents(EventsDTO edto)
INSERT INTO EVENTS(EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ETNO, MNO)
    VALUES(TO_CHAR(SYSDATE, 'RRMMDD') ||TRIM(TO_CHAR(EVENTS_SEQ.NEXTVAL, '009')), 'KYUNGHEEUC', '기업행사', 
             TO_DATE('2022/08/09', 'YYYY/MM/DD'), 40, 1811009);

-- 행사 수정 : int modifyEvents(EventsDTO edto)
UPDATE EVENTS SET EVTITLE = 'KOREAUC',
                         EVDETAIL = '변경된 행사내용',
                         EVSTARTDATE = TO_DATE('2022/08/11', 'YYYY/MM/DD'),
                         ETNO = 40,
                         MNO = 1811009
                         WHERE EVNO = '220627009';

-- 행사 삭제 : int deleteEvents(String evno, int mno)
DELETE FROM EVENTS WHERE EVNO = '220627006' AND MNO = 1811009;

-- 건의사항 게시판 관련 : SuggestionBoardDAO
-- 건의사항 게시판 글 목록 : ArrayList<SuggestionBoardDTO> listSuggestionBoard(int startRow, int endRow)
-- 건의게시판 조회수 올리기 
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
                 FROM (SELECT *  
                            FROM SUGGESTIONBOARD 
                                  ORDER BY SBRDATE DESC, SBNO) A)
                            WHERE RN BETWEEN 2 AND 5;
-- 건의게시판 글 상세보기 dto가져오기 : SuggestionBoardDTO detailSuggestionBoard(int sbno) 
-- 글 상세보기전 조회수 올리기
UPDATE SUGGESTIONBOARD  SET SBHIT = SBHIT +1 WHERE SBNO=1;
SELECT * FROM SUGGESTIONBOARD WHERE SBNO = 4;

-- 건의 게시판 글 삭제 : int deleteSuggestionBoard(int sbno, int sbpw)
DELETE FROM SUGGESTIONBOARD WHERE SBNO = 4 AND SBPW = 1; 
                                                   
-- 건의사항 게시판 답글 쓰기
-- 답글쓰기 전 


INSERT INTO SUGGESTIONBOARD(SBNO, PTID, MNO, SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)
    VALUES(SUGGBOARD_SEQ.NEXTVAL, '5894', 1811009, '건의사항 제목4', '건의사항 내용4', 'NOTHING.JPG', '1', 1, 1, SUGGBOARD_SEQ.CURRVAL, '123.123.123.12' );

COMMIT;































