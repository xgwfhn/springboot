package com.example.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import oracle.sql.BLOB;

@Controller
public class HelloWorldController {
    
	//页面跳转   页面必须在/src/main/webapp/下
	@RequestMapping("/info")
	public String info() {
		return "aa";//如果要省略后缀  则src/main/resources/application.properties 配置
	}
	
	
	@ResponseBody
	@RequestMapping("/ret")
	public String ret() {
		return "aa1";//如果要省略后缀  则src/main/resources/application.properties 配置
	}
	
	
	
	//将读取的图片插入数据库中
	@RequestMapping("/insertImg")
	public static void insertImg() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException{
	//JDBC连接格式
	Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
	String url="jdbc:oracle:thin:@192.168.100.11:1521:orcl"; //ORCL 是sid 
	String user="labour"; 
	String password="labour_JYJ_#qazwsx_2019";
	Connection con = DriverManager.getConnection(
	url,user, password);
	con.setAutoCommit(false);

	//切近！！！无论是blob还是clob，在插入前都需要先插入一个空的blob或则clob内容
	PreparedStatement pstmt = con.prepareStatement("insert into TAMP_10(fj) values(empty_blob())"); 
	pstmt.executeUpdate(); 
	pstmt.close(); 
	//使数据库处于可编辑状态
	String sql1 = "select * from TAMP_10 for update";
	ResultSet rs=null; 
	rs=con.createStatement().executeQuery(sql1); 
	while(rs.next()){
	//取到数据库中blob字段
	BLOB b=(BLOB)rs.getBlob("fj"); 
	//文件路径
	File f=new File("C:\\Users\\Administrator\\Desktop\\led\\aa.png"); 
	//用输入流接收文件内容
	BufferedInputStream in=new BufferedInputStream(new FileInputStream(f)); 
	//用输出流得到blob的二进制文件流
	BufferedOutputStream out=new BufferedOutputStream(b.getBinaryOutputStream()); 
	int c; 
	while ((c=in.read())!=-1) { 
	try {
	//将输入流接收到的，写入输出流
	out.write(c);
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} 
	} 
	in.close(); 
	out.close(); 
	}
	//关闭，提交
	con.commit();
	}
	
	@RequestMapping("/showImg")
	public void getReportImageByCheckNo(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
		String url="jdbc:oracle:thin:@192.168.100.11:1521:orcl"; //ORCL 是sid 
		String user="labour"; 
		String password="labour_JYJ_#qazwsx_2019";
		Connection conn = DriverManager.getConnection(
		url,user, password);
		conn.setAutoCommit(false);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		InputStream is = null;
		String sql = "select *  from TAMP_10 ";
		try {
			// conn = serviceDao.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 获取报告单二进制流
				is = rs.getBinaryStream("fj");
				is.skip(0);
				showReportImage(is,response);// 将二进制流显示至JSP页面
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// serviceDao.CLOSE(conn, ps, rs);
		}
	}
			
	
	/**
	 * 根据获取的二进制流,显示图片信息至JSP页面
	 * @param is
	 */
	private void showReportImage(InputStream is,HttpServletResponse res) {
		OutputStream os = null;
		byte buf[] = new byte[1024 * 1024 * 5]; // 定义你需要的缓存大小
		//HttpServletResponse res = response.getResponse();
		res.setContentType("image/*");// 设置图片的类型.jpeg等类型，此处我用了*，也没报错，是否通配待验证。
		try {
			while (is.read(buf) != -1) {
				os = res.getOutputStream();
				os.write(buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
/*	@RequestMapping(value = "queryPic")
	public void queryPic(@RequestParam(required=false) String adress,HttpServletRequest request, HttpServletResponse response) throws IOException  {  
	
	if (adress != null){  
	   response.setContentType("image/jpeg");
	    FileInputStream is =this.query_getPhotoImageBlob(adress);  
	    
	    if (is != null){   
	    	int i = is.available(); // 得到文件大小  
			   byte data[] = new byte[i];  
			   is.read(data); // 读数据  
			   is.close();  
			   response.setContentType("image/jpeg");  // 设置返回的文件类型  
			   OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
			   toClient.write(data); // 输出数据  
			   toClient.close();  
	    }  
	   }  
	} 
}
*/

}
