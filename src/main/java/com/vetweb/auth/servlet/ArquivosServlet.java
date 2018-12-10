package com.vetweb.auth.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/arquivo/*")
public class ArquivosServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nomeArquivo = req.getRequestURI().split("/arquivo/")[1];
		Path caminhoCompleto = Paths.get(nomeArquivo.replaceAll("%20", " "));
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String tipoArquivo = fileNameMap.getContentTypeFor("file:" + caminhoCompleto);
		res.reset();
		res.setContentType(tipoArquivo);
		res.setHeader("Content-Length", String.valueOf(Files.size(caminhoCompleto)));
		res.setHeader("Content-Disposition", "filename=\"" + caminhoCompleto.getFileName().toString() + "\"");
		try {
			FileInputStream fileInputStream = new FileInputStream(caminhoCompleto.toFile());
			try(
					ReadableByteChannel readableByteChannel = Channels.newChannel(fileInputStream);
					WritableByteChannel writableByteChannel = Channels.newChannel(res.getOutputStream())) {
				ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10240);
				while(readableByteChannel.read(byteBuffer) != -1) {
					byteBuffer.flip();
					writableByteChannel.write(byteBuffer);
					byteBuffer.clear();
				}
			} catch (IOException ioException) {
				throw new RuntimeException("ERRO AO TRANSFERIR ARQUIVO DO SERVIDOR PARA A APLICAÇÃO");
			}
		} catch (FileNotFoundException fileNotFoundException) {
			throw new RuntimeException("ARQUIVO ESPECIFICADO NÃO ENCONTRADO");
		}
	}	

}
