package control;

import com.mumu.listenner.KQMSGAdapter;
import com.mumu.webclient.KQWebClient;

public class Jcqapp extends KQMSGAdapter{
    private KQWebClient cc;
    public Jcqapp(KQWebClient cc){
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
        //qq:需要@的qq,groupid:发送的群号，msg :发送的消息 ,isAT: 是否需要@发送 true是 false否
        cc.sendGroupMSG("","100936163","@并发送了: "+msg.getMsg(),true);
    }

}
