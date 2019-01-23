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
        System.out.println("\033[36;4m" + "[接收私聊消息] : "+msg.getMsg()+ "\033[0m");
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
        System.out.println("\033[36;4m" +"从群号: "+"\033[33;4m"+msg.getFromQQ()+ "\033[36;4m" + " [接收消息] : "+"\033[31;4m"+msg.getMsg()+ "\033[0m");
        //qq:需要@的qq,groupid:发送的群号，msg :发送的消息 ,isAT: 是否需要@发送 true是 false否
        cc.sendGroupMSG("2426076950","100936163","@并发送了: "+msg.getMsg(),true);
    }

}
