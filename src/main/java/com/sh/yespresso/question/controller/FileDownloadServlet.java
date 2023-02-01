package com.sh.yespresso.question.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.question.model.dto.QuestionAttachment;
import com.sh.yespresso.question.model.service.QuestionService;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/question/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService = new QuestionService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		System.out.println("questionNo = " + questionNo);

		// 2. 업무로직 - attachment 한건 조회
		QuestionAttachment questionAttach = questionService.selectOneAttachment(questionNo);
		System.out.println("questionAttach = " + questionAttach);

		// 3. 응답메세지에 파일출력
		// a. 응답헤더 작성 (다운로드할 파일명 originalFilename)
		String filename = URLEncoder.encode(questionAttach.getQuestionFilename(), "utf-8");
		System.out.println("filename = " + filename);
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "QuestionAttachment; filename=" + filename);

		// b. 실제파일(renamedFilename)을 읽어서(input) http응답메세지에 쓰기(output)
		String saveDirectory = getServletContext().getRealPath("/upload/question");
		File downFile = new File(saveDirectory, questionAttach.getReQuestionFilename());
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

		// c. 읽고 쓰기
		int len = 0;
		byte[] buffer = new byte[8192]; // 한번에 처리할 byte수
		while ((len = bis.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}

	}

}
