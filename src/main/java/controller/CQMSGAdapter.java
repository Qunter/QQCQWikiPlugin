package controller;

import com.mumu.listenner.KQMSGAdapter;
import com.mumu.webclient.KQWebClient;

public class CQMSGAdapter extends KQMSGAdapter{
    private KQWebClient cc;
    public CQMSGAdapter(KQWebClient cc){
        this.cc=cc;
    }
    //以下是会自动调用的方法
    /**
     * 私聊消息处理
     * 有私聊消息自动触发
     * @param msg
     */
    public void Re_MSG_Private(com.mumu.msg.RE_MSG_Private msg) {
        super.Re_MSG_Private(msg);
        System.out.println("\033[36;0m" + "[接收_"+msg.getNick()+"_私聊消息] : "+"\033[31;0m"+msg.getMsg()+ "\033[0m");
        //参数1 :需要发送的qq ,参数2:发送的消息
        cc.sendPrivateMSG(msg.getFromqq(),"你发送了: "+msg.getMsg());

    }

    /**
     * 讨论组消息处理
     * 有讨论组消息自动触发
     * @param msg
     */
    public void RE_MSG_FORUM(com.mumu.msg.RE_MSG_Forum msg) {
        super.RE_MSG_FORUM(msg);
    }

    /**
     * 群消息处理
     * 有群消息消息自动触发
     * @param msg
     */
    public void RE_MSG_Group(com.mumu.msg.RE_MSG_Group msg) {
        super.RE_MSG_Group(msg);
        
        System.out.println("\033[36;0m" +"从群号: "+"\033[33;0m"+msg.getFromGroup()+"("+msg.getFromGroupName()+")"+ "\033[36;0m" + " [接收_"+msg.getFromQQ()+"("+msg.getUsername()+")_消息] : "+"\033[31;0m"+msg.getMsg()+ "\033[0m");
        
        //只监听该群
        if(!msg.getFromGroup().toString().equals("189309830"))
        {	
        	System.out.println("群不一致");
        	return;
        }
        
        //识别@符号
        if(msg.getMsg().indexOf("[CQ:at")<0)	
        {
        	System.out.println("需要at");
        	return;
        }
        //@他才行
        if(msg.getMsg().indexOf("qq=210889912")<0)	
        {
        	System.out.println("需要at他才行");
        	return;
        }
        
        //忽略图片和表情
        if(msg.getMsg().indexOf("[CQ:image")>=0||msg.getMsg().indexOf("[CQ:emoji")>=0){
            return;
        }
        //qq:需要@的qq,groupid:发送的群号，msg :发送的消息 ,isAT: 是否需要@发送 true是 false否
        cc.sendGroupMSG(msg.getFromQQ(),msg.getFromGroup(),msg.getMsg().split("]")[1],true);
        //qq为""则不会返回@，为msg.getFromQQ()则@提问者
        //groupid为msg.getFromGroup() ，返回所有群；为群号字符串则只回答该群
        //msg为msg.getMsg()  ，复读
    }

}
