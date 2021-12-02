package com.qst.dms.service;
import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;

import com.qst.dms.db.DBUtil;
import com.qst.dms.entity.DataBase;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
//日志业务类
public class LogRecService {
	// 日志数据采集
	public LogRec inputLog() {
		LogRec log = null;
		// 建立一个从键盘接收数据的扫描器
		Scanner scanner = new Scanner(System.in);
		try {
			// 提示用户输入ID标识
			System.out.println("请输入ID标识：");
			// 接收键盘输入的整数
		    int id = scanner.nextInt();
			// 获取当前系统时间
            Date nowDate = new Date();
			// 提示用户输入地址
			System.out.println("请输入地址:");
			// 接收键盘输入的字符串信息
			String address = scanner.next();
			// 数据状态是“采集”
			int type = DataBase.GATHER;
			// 提示用户输入登录用户名
    		System.out.println("请输入用户名: ");	
			// 接收键盘输入的字符串信息
			String user = scanner.next();
			// 提示用户输入主机IP
			//System.out.println("请输入ip地址：");
			// 接收键盘输入的字符串信息
            //string ip = scanner.next();
			InetAddress getip;
            getip = InetAddress.getLocalHost();
            String ip = getip.getHostAddress();
			// 提示用户输入登录状态、登出状态
			System.out.println("请输入登录状态:1是登录，0是登出");
			int logType = scanner.nextInt();
			// 创建日志对象
			log = new LogRec(id, nowDate, address, type, user, ip, logType);
		} catch (Exception e) {
			System.out.println("采集的日志信息不合法");
		}
		// 返回日志对象
		return log;
	}

	// 日志信息输出
	public void showLog(LogRec... logRecs) {
		for (LogRec e : logRecs) {
			if (e != null) {
				System.out.println(e.toString());
			}
		}
	}

	// 匹配日志信息输出，可变参数
	public void showMatchLog(MatchedLogRec... matchLogs) {
		for (MatchedLogRec e : matchLogs) {
			if (e != null) {
				System.out.println(e.toString());
			}
		}
	}

	// 匹配日志信息输出,参数是集合
	public void showMatchLog(ArrayList<MatchedLogRec> matchLogs) {
		for (MatchedLogRec e : matchLogs) {
			if (e != null) {
				System.out.println(e.toString());
			}
		}
	}

    public void SaveMacthLog(ArrayList<MatchedLogRec> Logs){
        try{
			ObjectOutputStream Log_writer = new ObjectOutputStream(new FileOutputStream("MatchedLogRec.txt"));
			for (MatchedLogRec this_log : Logs) {
				if (this_log != null) {
					Log_writer.writeObject(this_log);
					Log_writer.flush();
				}
			}
			Log_writer.writeObject(null);
			Log_writer.flush();
        }catch(Exception e){
           e.printStackTrace();
        }
    }

    public ArrayList<MatchedLogRec> ReadMatchLog(){
		ArrayList<MatchedLogRec> matchLogs = new ArrayList<>();
        try{
			ObjectInputStream Log_reader = new ObjectInputStream(new FileInputStream("MatchedLogRec.txt")) ;
			MatchedLogRec matchLog_temp;
			while ((matchLog_temp = (MatchedLogRec) Log_reader.readObject()) != null) {
				matchLogs.add(matchLog_temp);
				}
			}
        	catch(Exception ex){
				System.out.println("未成功读入日志");
        	}
		return matchLogs;
    }
	
	public void SaveMacthLogToDB(ArrayList<MatchedLogRec> Logs){
		DBUtil db = new DBUtil();
		try{
			db.getConnection();
			for(MatchedLogRec log : Logs){
				LogRec in = log.getLogin();
				LogRec out = log.getLogout();
				String sql = "INSERT INTO gather_logrec(id,time,address,type,username,ip,logtype) VALUES(?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE id = id";

				Object [] ob_socket = new Object[]{
					in.getId(),
					new Timestamp(in.getTime().getTime()),
					in.getAddress(),
					in.getType(),
					in.getUser(),
					in.getIp(),
					in.getLogType()
				};
				db.executeUpdate(sql,ob_socket);
				
				ob_socket = new Object[]{
					out.getId(),
					new Timestamp(out.getTime().getTime()),
					out.getAddress(),
					out.getType(),
					out.getUser(),
					out.getIp(),
					out.getLogType()
				};
				db.executeUpdate(sql,ob_socket);
				
				sql = "INSERT INTO matched_logrec(loginid,logoutid) VALUES(?,?) ON DUPLICATE KEY UPDATE loginid = loginid";
				ob_socket = new Object[]{
					in.getId(),
					out.getId()
				};
				db.executeUpdate(sql, ob_socket);
			}
			db.closeAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<MatchedLogRec> readMatchedLogFromDB(){
		ArrayList<MatchedLogRec> matchedlogs = new ArrayList<>();
		DBUtil db = new DBUtil();
		try{
			db.getConnection();
			String sql = "SELECT i.ID,i.TIME,i.ADDRESS,i.TYPE,i.USERNAME,i.IP,i.LOGTYPE,"
						+" o.ID,o.TIME,o.ADDRESS,o.TYPE,o.USERNAME,o.IP,o.LOGTYPE"
						+" FROM matched_logrec m,gather_logrec i,gather_logrec o"
						+" WHERE m.loginid=i.id AND m.logoutid=o.id;";
			java.sql.ResultSet rs = db.executeQuery(sql, null);
			while(rs.next()){
				LogRec in = new LogRec(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7));
				LogRec out = new LogRec(rs.getInt(8),rs.getDate(9),rs.getString(10),rs.getInt(11),rs.getString(12),rs.getString(13),rs.getInt(14));
				matchedlogs.add(new MatchedLogRec(in,out));
			}
			db.closeAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return matchedlogs;
	} 

	public ResultSet readLogResult() {		
		DBUtil db = new DBUtil();
		ResultSet rs=null;
		try {
			// 获取数据库链接
			Connection conn=db.getConnection();
			// 查询匹配日志，设置ResultSet可以使用除了next()之外的方法操作结果集
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String sql = "SELECT * from gather_logrec";
			rs = st.executeQuery(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
