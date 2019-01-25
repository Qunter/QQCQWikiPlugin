package service.serviceImp;

import service.WikiFilterDataService;

public class WikiFilterDataServiceImp implements WikiFilterDataService {

    public boolean msgFilterSpecific(com.mumu.msg.RE_MSG_Group msg){
        //只监听该群
        if(!msg.getFromGroup().toString().equals("189309830"))
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
        if(msg.getMsg().indexOf("qq=210889912")<0)
        {
            System.out.println("需要at他才行");
            return false;
        }

        //忽略图片和表情
        if(msg.getMsg().indexOf("[CQ:image")>=0||msg.getMsg().indexOf("[CQ:emoji")>=0){
            return false;
        }
        return true;
    }

}
