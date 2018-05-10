package com.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

public class FileUploadTools {

	private HttpServletRequest request = null;
	private List<FileItem>items = null;
	private Map<String , List<String>>params = new HashMap<String , List<String>>();
	private Map<String , FileItem> files = new HashMap<String ,FileItem>();
	private int maxSize = 3*1024*1024;
	public FileUploadTools(HttpServletRequest request, int maxSize ,String tempDir)throws Exception{
		this.request = request;
		request.setCharacterEncoding("utf-8");
//		FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(request.getSession().getServletContext());
		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setFileCleaningTracker(fileCleaningTracker);
		if(tempDir!=null){
			factory.setRepository(new File(tempDir));
		}
		ServletFileUpload upload = new ServletFileUpload(factory);
		if(maxSize>0){
			this.maxSize = maxSize;
		}
		upload.setFileSizeMax(this.maxSize);
		try{
			this.items = upload.parseRequest(request);
		}catch(FileUploadException e){throw e;}
//		System.out.println("构造……"+request);
		this.init();
	}
	
	private void init(){
//		System.out.println("init()……");
		Iterator<FileItem> iter = this.items.iterator();
		IPTimeStamp its = new IPTimeStamp();
		while(iter.hasNext()){
			FileItem item =iter.next();
			if(item.isFormField()){
				String name = item.getFieldName();
				String value = item.getString();
				List<String> temp = null;
				if(this.params.containsKey(name)){
					temp = this.params.get(name);
				}else{
					temp = new ArrayList<String>();
				}
				temp.add(value);
				this.params.put(name, temp);
			}else{
				String temp[] = item.getFieldName().split("\\.");
				String fileName = its.getTimeStamp()+"."+temp[temp.length-1];
				this.files.put(fileName, item);
			}
		}
	}
	
	public String getParameter(String name){
		String ret = null;
		List<String>temp = this.params.get(name);
		if(temp!=null){
			ret = temp.get(0);
		}
		return ret;
	}
	public String[] getParameterValues(String name){
		String ret[] =null;
		List<String> temp = this.params.get(name);
		if(temp!=null){
			ret = temp.toArray(new String[]{});
		}
		return ret;
	}
	public Map<String , FileItem> getUploadFiles(){
		return this.files;
	}
	
	public List<String> saveAll(String saveDir)throws IOException{
		List<String> names = new ArrayList<String>();
		if(this.files.size()>0){
			Set<String> keys = this.files.keySet();
			Iterator<String> iter = keys.iterator();
			File saveFile = null;
			InputStream input = null;
			OutputStream outputStream = null;
			while(iter.hasNext()){
				FileItem item = this.files.get(iter.next());
				String[] Ntemp = item.getName().split("\\.");
				String fileName = new IPTimeStamp().getTimeStamp()+"."+Ntemp[Ntemp.length-1];
				saveFile = new File(saveDir+fileName);
				//System.out.println(Ntemp[Ntemp.length-1]);
				if(Ntemp[Ntemp.length-1].equals("JPG")||Ntemp[Ntemp.length-1].equals("PNG")||Ntemp[Ntemp.length-1].equals("jpg")||Ntemp[Ntemp.length-1].equals("png")){
				//这里上传的文件后缀对大小写敏感，需要单独区分
					names.add(fileName);
					try{
						input = item.getInputStream();
						outputStream = new FileOutputStream(saveFile);
						int temp = 0;
						byte data[] = new byte[512];
						while((temp=input.read(data))!=-1){
							outputStream.write(data);
						}
					}catch(IOException e){throw e;}
					finally{
						input.close();
						outputStream.close();
					}
				}
			}
		}
		return names==null?null:names;
	}
}
