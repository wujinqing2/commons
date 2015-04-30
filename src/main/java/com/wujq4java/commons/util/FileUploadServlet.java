package com.wujq4java.commons.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件上传
 */
public class FileUploadServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);
	private static final long serialVersionUID = 1L;
	
	private static final String tempDir = "/var/www/upload/tmp";// 存放临时文件的目录
	private static final String rootDir = "/var/www/upload";// 存放文件的根目录
	private static final String destDir = "feedback/";// 存放文件的目录
	private static final String fileTypeLimit = "jpg, png, gif, jpeg";// 支持的文件格式
	private static final long maxSize = 10 * 1024 * 1024;// 文件大小限制
	
	private static DiskFileItemFactory factory;
	
	@Override
	public void init() throws ServletException {
		logger.info("init FileUploadServlet start");
		
		factory = new DiskFileItemFactory();
		
		File repository = new File(tempDir);
		factory.setRepository(repository);
		
		logger.info("init FileUploadServlet end");
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
		Document doc = DocumentHelper.createDocument();
		doc.addElement("res");
		Element root = doc.getRootElement();
		Element staEle = root.addElement("status");
		String 	value = "-1";
		String 	msg = "";
		
		try
		{
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			upload.setSizeMax(maxSize);
			
			List<FileItem> items = upload.parseRequest(request);
			
			Iterator<FileItem> iter = items.iterator();
			
			Map<String, String> params = new HashMap<String, String>();
			Element paramsEle = root.addElement("params");
			
			while (iter.hasNext()) {
			    FileItem item = iter.next();
			    
			    String fieldName = item.getFieldName();
		        String fileName = (!item.isFormField()) ? item.getName() : item.getString();
		        
			    if (!item.isFormField()) {
			    	
			        String suffix = suffix(fileName);
			        fileName = getFilename(suffix);
			        
			        if(!fileTypeLimit.toLowerCase().contains(suffix))
			        {
			        	msg = "图片格式错误。";
			        	staEle.addElement("value").setText(value);
			    		staEle.addElement("msg").setText(msg);
			    		
			    		response.getWriter().print(doc.asXML());
			    		return;
			        }
			        	
				    File file = new File(rootDir, fileName);    	
			        item.write(file);
			    }
			    	 
			    params.put(fieldName, fileName);
			}
			
			value = "0";
		    msg = "success!";
		    
		    if(Assert.isNotEmpty(params))
		    {
		    	for(Entry<String, String> e : params.entrySet())
		    	{
			    	Element paramEle = paramsEle.addElement("param");
			    	
			    	paramEle.addElement("name").setText(e.getKey());
			    	paramEle.addElement("value").setText(e.getValue());
		    	}
		    }
		    staEle.addElement("value").setText(value);
			staEle.addElement("msg").setText(msg);
			
			response.getWriter().print(doc.asXML());
			return;
		}catch(SizeLimitExceededException e1)
		{
			msg = "图片大小超过限制。";
			staEle.addElement("value").setText(value);
			staEle.addElement("msg").setText(msg);
			
			response.getWriter().print(doc.asXML());
			return;
		}catch(Exception e)
		{
			logger.info("图片上传失败, 错误信息: {}", e.getMessage());
			msg = "图片上传失败。";
			staEle.addElement("value").setText(value);
			staEle.addElement("msg").setText(msg);
			
			response.getWriter().print(doc.asXML());
			return;
		}
		
	}
	
	private static String suffix(String fileName)
	{
		return (fileName.lastIndexOf(".") != -1) ? fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase() : "";
	}
	
	private static String getFilename(String suffix)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(destDir);
		sb.append("img-");
		sb.append(UUID.randomUUID().toString());
		sb.append(".");
		sb.append(suffix);
		return sb.toString();
	}
}
