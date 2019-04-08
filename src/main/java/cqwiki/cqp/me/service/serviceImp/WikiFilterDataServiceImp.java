package cqwiki.cqp.me.service.serviceImp;


import java.io.BufferedReader;
import java.io.FileReader;

import cqwiki.cqp.me.service.WikiFilterDataService;

public class WikiFilterDataServiceImp implements WikiFilterDataService {
	public static String GROUPQQ;
	public void init(String pathname){
		try {
			FileReader reader = new FileReader(pathname);
	    	    // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
	    	    //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
	    	    //不关闭文件会导致资源的泄露，读写文件都同理
	    	    //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271  
		//System.out.println(pathname);
		BufferedReader br = new BufferedReader(reader);// 建立一个对象，它把文件内容转成计算机能读懂的语言
	    String line;	
	    while ((line = br.readLine()) != null) {
	          // 一次读入一行数据
	    	  GROUPQQ=line;
	          //System.out.println(line);
	         }
	         
	     } catch (Exception e) {
	         e.printStackTrace();
	   }
	}
    public boolean msgFilterSpecific(String msg,String groupQQ,String atQQ){
    	//只监听该群
     	// 这里处理消息,忽略非攻略组群
    	
         if(!groupQQ.equals(GROUPQQ))
         {
        	 System.out.println("接收"+groupQQ);
         	 System.out.println("目标"+GROUPQQ);
             System.out.println("群不一致");
             return false;
         }
        //忽略图片和表情
        if(msg.indexOf("[CQ:image")>=0||msg.indexOf("[CQ:emoji")>=0){
            return false;
        }
        if(msg.trim().indexOf("帮助")==0){
        	return true;
        }
        if(msg.trim().indexOf("查询")!=0){
        	System.out.println("非查询，无法执行");
        	return false;
        }
        return true;
    }

}
