package controller;

import com.mumu.listenner.KQMSGAdapter;
import com.mumu.webclient.KQWebClient;

import dao.daoImp.queryWikiDao;
import dao.queryWiki;
import service.WikiFilterDataService;
import service.serviceImp.WikiFilterDataServiceImp;
import service.serviceImp.wikiMsgHandleImp;
import service.wikiMsgHandle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CQMSGAdapter extends KQMSGAdapter{
    private KQWebClient cc;
    private static List<String> allData=new ArrayList<String>();
    private static queryWiki wikiData;
    private static WikiFilterDataService wikiFilterDataService;
    private static wikiMsgHandle msgHandle;
    public CQMSGAdapter(KQWebClient cc){
        this.cc=cc;
       
         //发送消息的群:
        allData.add("-");
        //发送消息QQ:
        allData.add("-");
        //打印配置信息
        //System.out.println(allData);
        //初始化wiki页面数据方法
        wikiData = new queryWikiDao();
        //初始化过虐方法
        wikiFilterDataService = new WikiFilterDataServiceImp();
        //初始业务层
        msgHandle = new wikiMsgHandleImp();
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
        cc.sendPrivateMSG(msg.getFromqq(),msgHandle.Warrior_Msg_Handle_RE_MSG_Private(msg));

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
        //实现消息过滤 类
        //打印日志
        System.out.println("从群号: "+msg.getFromGroup()+"中的("+msg.getFromGroupName()+") [接收_"+msg.getFromQQ()+"("+msg.getUsername()+")_消息]:"+msg.getMsg());
        //判断是否是想要的消息                        //allData 是配置数据 参数一：判断消息,参数二：群号 ,参数三：机器人QQ号
        if(wikiFilterDataService.msgFilterSpecific(msg,allData.get(0),allData.get(1))){
            //msg.getFromQQ() :回复要@的qq ; msg.getFromGroup():发送消息的群 ;msgHandle.Warrior_Msg_Handle(msg) :消息处理并返回处理结果 ; isAT 回复消息是否要@人 true是
        	cc.sendGroupMSG(msg.getFromQQ(),msg.getFromGroup(),msgHandle.Warrior_Msg_Handle(msg),false);
          
        }

    }

}

