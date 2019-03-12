package cqwiki.cqp.me.service.serviceImp;


import cqwiki.cqp.me.service.WikiFilterDataService;

public class WikiFilterDataServiceImp implements WikiFilterDataService {

    public boolean msgFilterSpecific(String msg,String groupQQ,String atQQ){
        /*去除群号和机器人qq号的判别，方便操作但qq不可加入其它群
    	//只监听该群
        if(!msg.getFromGroup().toString().equals(groupQQ))
        {
            System.out.println("群不一致");
            return false;
        }
        //识别@符号
        if(msg.getMsg().indexOf("[CQ:at")<0)
        {
            System.out.println("需要at");
            return false;
        }
        //@他才行
        if(msg.getMsg().indexOf("qq="+atQQ)<0)        {
            System.out.println("需要at他才行");
            return false;
        }
		*/
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
