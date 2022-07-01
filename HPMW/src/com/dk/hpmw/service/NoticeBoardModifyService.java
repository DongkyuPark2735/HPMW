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

public class NoticeBoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("noticeBoardFile");
		int maxSize = 1024*1024*100; 
		MultipartRequest mRequest = null;
		String fFileName = "";
		String oldnbfilename = "";
		
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fFileName = mRequest.getFilesystemName(param);
			oldnbfilename = mRequest.getParameter("oldnbfilename");
			if(fFileName==null) {
				fFileName = oldnbfilename;
			}
			HttpSession session = request.getSession();
			ManagerDTO manager = (ManagerDTO)session.getAttribute("manager");
			request.setAttribute("pageNum", mRequest.getParameter("pageNum")); 
			
			if(manager!=null) {
				int mno = manager.getMno();
				int nbno = Integer.parseInt(mRequest.getParameter("nbno"));
				String mbtitle = mRequest.getParameter("nbtitle");
				String mbcontent = mRequest.getParameter("nbcontent");
				NoticeBoardDAO nbdao = NoticeBoardDAO.getInstance();
				NoticeBoardDTO nbdto = new NoticeBoardDTO();
				nbdto.setNbno(nbno);
				nbdto.setMno(mno);
				nbdto.setNbtitle(mbtitle);
				nbdto.setNbcontent(mbcontent);
				nbdto.setNbfilename(fFileName);
				int result = nbdao.modifyNoticeBoard(nbdto);
				if(result == NoticeBoardDAO.NoticeBoardModifySUCCESS) { 
					request.setAttribute("noticeBoardResult", "글수정 성공");
				}else {
					request.setAttribute("noticeBoardResult", "글수정 실패");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("noticeBoardResult", "글수정 실패");
		}

		if(fFileName!=null && fFileName.equals(oldnbfilename)) {
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
			} 
		}
	}

}
