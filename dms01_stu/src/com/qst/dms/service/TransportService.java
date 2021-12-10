package com.qst.dms.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;

import com.qst.dms.db.DBUtil;
import com.qst.dms.dos.DBDemo;
import com.qst.dms.entity.DataBase;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.Transport;

public class TransportService {
	// 物流数据采集
	public Transport inputTransport() {
		Transport trans = null;

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
			System.out.println("请输入地址：");
			// 接收键盘输入的字符串信息
			String address = scanner.next();
			// 数据状态是“采集”
			int type = DataBase.GATHER;

			// 提示用户输入登录用户名
			System.out.println("请输入货物经手人：");
			// 接收键盘输入的字符串信息
			String handler = scanner.next();
			// 提示用户输入主机IP
			System.out.println("请输入 收货人:");
			// 接收键盘输入的字符串信息
			String reciver = scanner.next();
			// 提示用于输入物流状态
			System.out.println("请输入物流状态：1发货中，2送货中，3已签收");
			// 接收物流状态
			int transportType = scanner.nextInt();
			// 创建物流信息对象
			trans = new Transport(id, nowDate, address, type, handler, reciver,
					transportType);
		} catch (Exception e) {
			System.out.println("采集的日志信息不合法");
		}
		// 返回物流对象
		return trans;
	}

	// 物流信息输出
	public void showTransport(Transport... transports) {
		for (Transport e : transports) {
			if (e != null) {
				System.out.println(e.toString());
			}
		}
	}

	// 匹配的物流信息输出，可变参数
	public void showMatchTransport(MatchedTransport... matchTrans) {
		for (MatchedTransport e : matchTrans) {
			if (e != null) {
				System.out.println(e.toString());
			}
		}
	}

	// 匹配的物流信息输出，参数是集合
	public void showMatchTransport(ArrayList<MatchedTransport> matchTrans) {
		for (MatchedTransport e : matchTrans) {
			if (e != null) {
				System.out.println(e.toString());
			}
		}
	}

	// 匹配物流信息保存，参数是集合
	public void saveMatchedTransport(ArrayList<MatchedTransport> matchTrans) {
		// 创建一个ObjectOutputStream对象输出流，并连接文件输出流
		// 以可追加的方式创建文件输出流，数据保存到MatchedTransports.txt文件中
		try (ObjectOutputStream obs = new ObjectOutputStream(
				new FileOutputStream("MatchedTransports.txt"))) {
			// 循环保存对象数据
			for (MatchedTransport e : matchTrans) {
				if (e != null) {
					// 把对象写入到文件中
					obs.writeObject(e);
					obs.flush();
				}
			}
			// 文件末尾保存一个null对象，代表文件结束
			obs.writeObject(null);
			obs.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 读匹配物流信息保存，参数是集合
	public ArrayList<MatchedTransport> readMatchedTransport() {
		ArrayList<MatchedTransport> matchTrans = new ArrayList<>();
		// 创建一个ObjectInputStream对象输入流，并连接文件输入流，读MatchedTransports.txt文件中
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"MatchedTransports.txt"))) {
			MatchedTransport matchTran;
			// 循环读文件中的对象
			while ((matchTran = (MatchedTransport) ois.readObject()) != null) {
				// 将对象添加到泛型集合中
				matchTrans.add(matchTran);
			}
		} catch (Exception ex) {
			System.out.println("未成功读入物流信息");
		}
		return matchTrans;
	}

	public void saveMatchTransportToDB(ArrayList<MatchedTransport> matchTrans){
		DBUtil db = new DBUtil();
		try{
			db.getConnection();
			for(MatchedTransport match : matchTrans){
				Transport send = match.getSend();
				Transport trans = match.getTrans();
				Transport receive = match.getReceive();
				// INSERT ?
				String sql = "INSERT INTO gather_transport(id,time,address,type,handler,reciver,transporttype) VALUES(?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE id = id";
				Object [] ob_socket = new Object [] {
					send.getId(),
					new java.sql.Timestamp(send.getTime().getTime()),
					send.getAddress(),
					send.getType(),
					send.getHandler(),
					send.getReciver(),
					send.getTransportType()
				};
				db.executeUpdate(sql, ob_socket);

				ob_socket = new Object []{
					trans.getId(),
					new java.sql.Timestamp(trans.getTime().getTime()),
					trans.getAddress(),
					trans.getType(),
					trans.getHandler(),
					trans.getReciver(),
					trans.getTransportType()
				};
				db.executeUpdate(sql, ob_socket);

				ob_socket = new Object[]{
					receive.getId(),
					new java.sql.Timestamp(receive.getTime().getTime()),
					receive.getAddress(),
					receive.getType(),
					receive.getHandler(),
					receive.getReciver(),
					receive.getTransportType()
				};
				db.executeUpdate(sql, ob_socket);

				sql = "INSERT INTO matched_transport(sendid,transid,receiveid) VALUES(?,?,?) ON DUPLICATE KEY UPDATE transid = transid";
				ob_socket = new Object[]{
					send.getId(),
					trans.getId(),
					receive.getId()
				};
				db.executeUpdate(sql, ob_socket);
			}
			db.closeAll();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void saveMatchTransportToDB_nonid(ArrayList<MatchedTransport> matchTrans){
		DBUtil db = new DBUtil();
		try{
			db.getConnection();
			for(MatchedTransport match : matchTrans){
				Transport send = match.getSend();
				Transport trans = match.getTrans();
				Transport receive = match.getReceive();
				// INSERT ?
				String sql = "INSERT INTO gather_transport(time,address,type,handler,reciver,transporttype) VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE id = id";
				Object [] ob_socket = new Object [] {
					new java.sql.Timestamp(send.getTime().getTime()),
					send.getAddress(),
					send.getType(),
					send.getHandler(),
					send.getReciver(),
					send.getTransportType()
				};
				send.setId(db.executeSQLAndReturnPrimaryKey(sql, ob_socket));

				ob_socket = new Object []{
					new java.sql.Timestamp(trans.getTime().getTime()),
					trans.getAddress(),
					trans.getType(),
					trans.getHandler(),
					trans.getReciver(),
					trans.getTransportType()
				};
				trans.setId(db.executeSQLAndReturnPrimaryKey(sql, ob_socket));

				ob_socket = new Object[]{
					new java.sql.Timestamp(receive.getTime().getTime()),
					receive.getAddress(),
					receive.getType(),
					receive.getHandler(),
					receive.getReciver(),
					receive.getTransportType()
				};
				receive.setId(db.executeSQLAndReturnPrimaryKey(sql, ob_socket));

				sql = "INSERT INTO matched_transport(sendid,transid,receiveid) VALUES(?,?,?) ON DUPLICATE KEY UPDATE transid = transid";
				ob_socket = new Object[]{
					send.getId(),
					trans.getId(),
					receive.getId()
				};
				db.executeUpdate(sql, ob_socket);
			}
			db.closeAll();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<MatchedTransport> readMatchedTransportFromDB(){
		ArrayList<MatchedTransport> matchedTransports = new ArrayList<>();
		DBUtil db = new DBUtil();
		try{
			db.getConnection();
			String sql = "SELECT s.ID,s.TIME,s.ADDRESS,s.TYPE,s.HANDLER,s.RECIVER,s.TRANSPORTTYPE,"
				+" t.ID,t.TIME,t.ADDRESS,t.TYPE,t.HANDLER,t.RECIVER,t.TRANSPORTTYPE,"
				+" r.ID,r.TIME,r.ADDRESS,r.TYPE,r.HANDLER,r.RECIVER,r.TRANSPORTTYPE"
				+" FROM matched_transport m,gather_transport s,gather_transport t,gather_transport r"
				+" WHERE m.SENDID=s.ID AND m.TRANSID=t.ID AND m.RECEIVEID=r.ID;";
			java.sql.ResultSet rs = db.executeQuery(sql,null);
			while(rs.next()){
				Transport send = new Transport (rs.getInt (1), rs.getDate(2),
								rs.getString(3), rs.getInt(4) , rs.getString(5) ,
								rs.getString(6), rs.getInt(7));

				Transport trans = new Transport (rs.getInt (8), rs.getDate(9),
								rs.getString(10), rs.getInt(11) , rs.getString(12) ,
								rs.getString(13), rs.getInt(14));

				Transport receive = new Transport (rs.getInt (15), rs.getDate(16),
								rs.getString(17), rs.getInt(18) , rs.getString(19) ,
								rs.getString(20), rs.getInt(21));
				MatchedTransport matchedtrans = new MatchedTransport(send, trans, receive);
				matchedTransports.add(matchedtrans);
			}
			db.closeAll();
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return matchedTransports;
	}

	public ResultSet readTransResult() {		
		DBUtil db = new DBUtil();
		ResultSet rs=null;
		try {
			// 获取数据库链接
			Connection conn=db.getConnection();
			// // 查询匹配物流，设置ResultSet可以使用除了next()之外的方法操作结果集
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String sql = "SELECT * from gather_transport";
			rs = st.executeQuery(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
