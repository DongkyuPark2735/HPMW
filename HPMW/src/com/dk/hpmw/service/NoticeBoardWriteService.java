package com.dk.hpmw.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.manager.ManagerDTO;
import com.dk.hpmw.noticeboard.NoticeBoardDAO;
import com.dk.hpmw.noticeboard.NoticeBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeBoardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("noticeBoardFile");
		int maxSize = 1024*1024*100; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String fFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fFileName = mRequest.getFilesystemName(param);
			// mId, fTitle, fContent,  fileName, fIp
			HttpSession session = request.getSession();
			ManagerDTO manager = (ManagerDTO)session.getAttribute("manager");
			if(manager!=null) {
				int mno = manager.getMno(); // 로그인 한 사람의 mId
				String mbtitle = mRequest.getParameter("nbtitle");
				String mbcontent = mRequest.getParameter("nbcontent");
				NoticeBoardDAO nbdao = NoticeBoardDAO.getInstance();
				NoticeBoardDTO nbdto = new NoticeBoardDTO();
				nbdto.setMno(mno);
				nbdto.setNbtitle(mbtitle);
				nbdto.setNbcontent(mbcontent);
				nbdto.setNbfilename(fFileName);
				int result = nbdao.insertNoticeBoard(nbdto);
				if(result == NoticeBoardDAO.NoticeBoardInsetSUCCESS) { 
					request.setAttribute("noticeBoardResult", "글쓰기 성공");
				}else {
					request.setAttribute("noticeBoardResult", "글쓰기 실패");
				}
			}else {
				request.setAttribute("noticeBoardResult", "로그인 한 사람만 글쓸 수 있어요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("noticeBoardResult", "글쓰기 실패");
		}

		//서버에 있는 파일 소스폴더로 
		if(fFileName!=null) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+fFileName);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:/workspaces/HPMW-worspace/HPMW/WebContent/noticeBoardFile/"+fFileName);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt==-1) break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} // try
		}// 파일 복사 if
	}
}
