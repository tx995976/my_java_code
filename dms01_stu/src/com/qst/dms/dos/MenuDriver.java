package com.qst.dms.dos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.qst.dms.db.DBUtil;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.Transport;
import com.qst.dms.gather.LogRecAnalyse;
import com.qst.dms.gather.TransportAnalyse;
import com.qst.dms.service.LogRecService;
import com.qst.dms.service.TransportService;

public class MenuDriver {
	public static void main(String[] args) {
		// 建立一个从键盘接收数据的扫描器
		Scanner scanner = new Scanner(System.in);

		// 创建一个泛型ArrayList集合存储日志数据
		ArrayList<LogRec> logRecList = new ArrayList<>();
		// 创建一个泛型ArrrayList集合存储物流数据
		ArrayList<Transport> transportList = new ArrayList<>();

		// 创建一个日志业务类
		LogRecService logService = new LogRecService();
		// 创建一个物流业务类
		TransportService tranService = new TransportService();

		// 日志数据匹配集合
		ArrayList<MatchedLogRec> matchedLogs = null;
		ArrayList<MatchedLogRec> matchedLogs_old = new ArrayList<>();
		// 物流数据匹配集合
		ArrayList<MatchedTransport> matchedTrans = null;
		ArrayList<MatchedTransport> matchedTrans_old = new ArrayList<>();

		DBUtil db = new DBUtil();
		try{
			System.out.println("connecting....");
			db.getConnection();
			db.closeAll();
		}catch(Exception e){
			System.out.println("failed");
		}

		System.out.println("尝试读取文件....");
		try{
			matchedLogs_old = new ArrayList<MatchedLogRec>(logService.ReadMatchLog());
		}catch(Exception e){
			System.out.println("没有成功读取日志,使用记录以新建文件");
		}

		try{
			matchedTrans_old = new ArrayList<MatchedTransport>(tranService.readMatchedTransport());
		}catch(Exception e){
			System.out.println("没有成功读取物流,使用记录以新建文件");
		}

		try {
			while (true) {
				// 输出菜单界面，需补充 
				System.out.println("1.数据采集    2.数据匹配\n3.数据记录    4.数据显示\n5.数据发送    0.退出应用");
				// 提示用户输入要操作的菜单项
				System.out.println("请输入菜单项（0~5）：");
                
				// 接收键盘输入的选项
				int choice = scanner.nextInt();

				switch (choice) {
				case 1: {
					System.out.println("请输入采集数据类型：1.日志    2.物流");
					// 接收键盘输入的选项
					int type = scanner.nextInt();
					if (type == 1) {
						System.out.println("正在采集日志数据，请输入正确信息，确保数据的正常采集！");
						// 采集日志数据，需补充LogService类中的inputLog方法
						LogRec log = logService.inputLog();
						// 将采集的日志数据添加到logRecList集合中
						logRecList.add(log);
					} else if (type == 2) {
						System.out.println("正在采集物流数据，请输入正确信息，确保数据的正常采集！");
						// 采集物流数据，需补充tranService类中的inputTransport方法
						Transport tran = tranService.inputTransport();
						// 将采集的物流数据添加到transportList集合中
						transportList.add(tran);
					}
				}
					break;
				case 2: {
					System.out.println("请输入匹配数据类型：1.日志    2.物流");
					// 接收键盘输入的选项
					int type = scanner.nextInt();
					if (type == 1) {
						System.out.println("正在日志数据过滤匹配...");
						// 创建日志数据分析对象，用于日志数据筛选与匹配
						LogRecAnalyse logAn = new LogRecAnalyse(logRecList);
						// 需实现doFilter抽象方法，对日志数据进行过滤，根据日志登录状态
						//分别放在登录和登出两个集合中
						logAn.doFilter();
						// 日志数据分析
                        // "should_add_in_end"
						matchedLogs = logAn.matchData();
                        System.out.println("日志数据过滤匹配完成！");

					} else if (type == 2) {
						System.out.println("正在物流数据过滤匹配...");
						// 创建物流数据分析对象
						TransportAnalyse transAn = new TransportAnalyse(
								transportList);
						// 物流数据过滤
						transAn.doFilter();
						// 物流数据分析
						matchedTrans = transAn.matchData();
						System.out.println("物流数据过滤匹配完成！");
					}
				}
					break;
				case 3:
					System.out.println("数据记录中...");
						ArrayList<MatchedLogRec> logs_buffer = new ArrayList<>(matchedLogs_old);
						ArrayList<MatchedTransport> trans_buffer = new ArrayList<>(matchedTrans_old);
						/////////////////////
						if(matchedLogs != null)
							logs_buffer.addAll(matchedLogs);
						if(matchedTrans != null)
							trans_buffer.addAll(matchedTrans);
						/////////////////////
						logService.SaveMacthLog(logs_buffer);
                        tranService.saveMatchedTransport(trans_buffer);
					System.out.println("写入至文件完成");

						logService.SaveMacthLogToDB(matchedLogs);
						tranService.saveMatchTransportToDB(matchedTrans);

					System.out.println("写入至数据库完成");
					break;
				case 4: {
					System.out.println("显示至文件匹配的数据：");
					//输出匹配的日志信息
					if(!matchedLogs_old.isEmpty())
						logService.showMatchLog(matchedLogs_old);
						if(matchedLogs != null && !matchedLogs.isEmpty())
						logService.showMatchLog(matchedLogs);
						
						if ((matchedLogs == null || matchedLogs.size() == 0) && (matchedLogs_old.isEmpty())){
							System.out.println("匹配的物流记录是0条！");
						} 
						System.out.println("------------------------");
						// 输出匹配的物流信息
						if(!matchedTrans_old.isEmpty())
						tranService.showMatchTransport(matchedTrans_old);
						if(matchedTrans != null && !matchedTrans.isEmpty())
						tranService.showMatchTransport(matchedTrans);
						if ((matchedTrans == null || matchedTrans.size() == 0) && (matchedTrans_old.isEmpty())){
							System.out.println("匹配的物流记录是0条！");
						} 
					System.out.println("显示至数据库匹配的数据：");
					logService.showMatchLog(logService.readMatchedLogFromDB());
					System.out.println("------------------------");
					tranService.showMatchTransport(tranService.readMatchedTransportFromDB());
						
				}
					break;
				case 5:
					System.out.println("数据发送 中...");
					try{
					}catch(Exception e){
						e.printStackTrace();
					}
					break;
				case 0:
					// 应用程序退出
					System.exit(0);
				default:
					System.out.println("请输入正确的菜单项（0~5）！");
				}

			}
		} catch (Exception e) {
            e.printStackTrace();
			System.out.println("输入的数据不合法！");
		}
	}
}
