﻿package com.qst.dms.service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
        catch(Exception e){
            e.printStackTrace();
        }
		return matchLogs;
    }

    public ArrayList<MatchedLogRec> ReadMatchLogFromDB(){

    }

    public void SaveMacthLogToDB(){
        

    } 
}
