package cn.tedu.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import cn.tedu.entity.User;

public class UploadUtil {
	private List<String> fileTypes=new ArrayList<String>();
	public  User uploadFile(HttpServletRequest request, HttpServletResponse response){
		User user=new User();
		try {
			fileTypes.add("image/png");
			fileTypes.add("image/jpg");
			fileTypes.add("image/jpeg");
			fileTypes.add("image/bmp");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			// Check that we have a file upload request
			// 检查我们有一个文件上传请求
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			System.out.println(isMultipart);
			if(isMultipart){
				//说明request中有一个文件
				// Create a factory for disk-based file items
				//创建一个磁盘文件条目工厂,工厂生产对象
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				// Parse the request
				//把request中的数据,转换成list集合,元素类型为fileItem
				List<FileItem> items = upload.parseRequest(request);
				// Process the uploaded items
				//处理上传的条目
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();

				    if (item.isFormField()) {
				    	//是非文件域,除了文件之外的input
				        String name=item.getFieldName();//<input name>
				        String value=item.getString();//<input value>
				        value=new String(value.getBytes("ISO8859-1"),"utf-8");
				        System.out.println(name+" "+value);
				        if("userName".equals(name)){
				        	user.setName(value);
				        }
				        if("userPassword".equals(name)){
				        	user.setPassword(value);
				        }
				        if("age".equals(name)){
				        	if (value!=null) {
								user.setAge(Integer.parseInt(value));
							}
				        }
				        if("address".equals(name)){
				        	user.setAddress(value);
				        }
				    } else {
				    	//是文件域<input type="file"processUploadFile(item)
				    	String fieldName=item.getFieldName();
				    	String fileName=item.getName();//获取真实文件名
				    	System.out.println(fileName);
				    	String contentType=item.getContentType();//获取文件内容类型
				    	boolean isInMemory=item.isInMemory();//是否在内存中
				    	long sizeInBytes=item.getSize();//获取文件的长度
				    	System.out.println(fieldName+" "+fileName+" "+contentType+" "+isInMemory+" "+sizeInBytes);
				    	//处理获取真实的文件名,因为浏览器的差异,导致文件名前带有路径,用此功能去掉路径
				    	if(fileName!=null){
				    		fileName=FilenameUtils.getName(fileName);
				    		user.setHeadimage(fileName);
				    	}
				    	if(fileTypes.contains(contentType)){
				    		if(sizeInBytes<4194304){
				    		//准备上传文件在服务器的存储路径
					    	String realPath=request.getServletContext().getRealPath("/images");
					    	System.out.println(realPath);
					    	File file=new File(realPath);
					    	if(!file.exists()){
					    		file.mkdir();
					    	}
					    	//把文件上传到upload文件夹
					    	File uploadFile=new File(file,fileName);
					    	item.write(uploadFile);//把文件写到具体路径
				    		}else{
				    			out.println("<div sty='color:red;'>文件太大</div>");
				    			out.flush();
				    			out.close();
				    		}
				    	}else{
				    		out.println("<div sty='color:red;'>文件类型不匹配</div>");
				    		out.flush();
							out.close();
				    	}
				    	
				    }
				    
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	
	/*public  void uploadUserHead(HttpServletRequest request, HttpServletResponse response){
		try {
			fileTypes.add("image/png");
			fileTypes.add("image/jpg");
			fileTypes.add("image/jpeg");
			fileTypes.add("image/bmp");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			// Check that we have a file upload request
			// 检查我们有一个文件上传请求
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			System.out.println(isMultipart);
			if(isMultipart){
				//说明request中有一个文件
				// Create a factory for disk-based file items
				//创建一个磁盘文件条目工厂,工厂生产对象
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				// Parse the request
				//把request中的数据,转换成list集合,元素类型为fileItem
				List<FileItem> items = upload.parseRequest(request);
				// Process the uploaded items
				//处理上传的条目
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();

				    if (item.isFormField()) {
				    	//是非文件域,除了文件之外的input
				        String name=item.getFieldName();//<input name>
				        String value=item.getString();//<input value>
				    } else {
				    	//是文件域<input type="file"processUploadFile(item)
				    	String fieldName=item.getFieldName();
				    	//String fileName=item.getName();//获取真实文件名
				    	String contentType=item.getContentType();//获取文件内容类型
				    	boolean isInMemory=item.isInMemory();//是否在内存中
				    	long sizeInBytes=item.getSize();//获取文件的长度
				    	//处理获取真实的文件名,因为浏览器的差异,导致文件名前带有路径,用此功能去掉路径
				    	String fileRealName=item.getName();
				    	System.out.println(fieldName+" "+fileRealName+" "+contentType+" "+isInMemory+" "+sizeInBytes);
				    	System.out.println(fileRealName);
				    	if(sizeInBytes<4194304){
				    		if(sizeInBytes==0){
				    			fileName="default.png";
				    			break;
				    		}
				    		if(fileTypes.contains(contentType)){
				    		//fileName=request.getParameter("userName")+"head"+fileRealName.split(".")[1];
				    		//准备上传文件在服务器的存储路径
					    	String realPath=request.getServletContext().getRealPath("/upload");
					    	System.out.println(realPath);
					    	File file=new File(realPath);
					    	if(!file.exists()){
					    		file.mkdir();
					    	}
					    	//把文件上传到upload文件夹
					    	File uploadFile=new File(file,fileName);
					    	item.write(uploadFile);//把文件写到具体路径
					    	out.println("<div style='color:red;'>上传文件成功</div>");
				    		}else{
				    			out.println("<div style='color:red;'>文件太大</div>");
				    		}
				    	}else{
				    		out.println("<div style='color:red;'>文件类型不匹配</div>");
				    	}
				    	
				    }
				    
				}
			}else{
				fileName="default.png";
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}*/
	

}
