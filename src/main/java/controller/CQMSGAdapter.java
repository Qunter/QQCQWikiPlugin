package controller;

import com.mumu.listenner.KQMSGAdapter;
import com.mumu.webclient.KQWebClient;
import dao.ExcelUtil;
import dao.daoImp.ExcelUtilDao;
import dao.daoImp.queryWikiDao;
import dao.queryWiki;
import service.WikiFilterDataService;
import service.serviceImp.WikiFilterDataServiceImp;
import service.serviceImp.wikiMsgHandleImp;
import service.wikiMsgHandle;

import java.io.File;
import java.util.List;

public class CQMSGAdapter extends KQMSGAdapter{
    private KQWebClient cc;
    private static List<List<String>> allData;
    private static queryWiki wikiData;
    private static WikiFilterDataService wikiFilterDataService;
    private static wikiMsgHandle msgHandle;
    public CQMSGAdapter(KQWebClient cc){
        this.cc=cc;
        File file = new File("allData.xls");
        //读取Excel配置数据
        ExcelUtil excel = new ExcelUtilDao();
        allData=excel.readExcel(file);
        //打印配置信息
        System.out.println(allData);
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
        //实现消息过滤 类
        //打印日志
        System.out.println("\033[36;0m" +"从群号: "+"\033[33;0m"+msg.getFromGroup()+"("+msg.getFromGroupName()+")"+ "\033[36;0m" + " [接收_"+msg.getFromQQ()+"("+msg.getUsername()+")_消息] : "+"\033[31;0m"+msg.getMsg()+ "\033[0m");
        //判断是否是想要的消息                        //allData 是 allData.xls文件里的数据 参数一：判断消息,参数二：群号 ,参数三：机器人QQ号
        if(wikiFilterDataService.msgFilterSpecific(msg,allData.get(0).get(1),allData.get(1).get(1))){
            //msg.getFromQQ() :回复要@的qq ; msg.getFromGroup():发送消息的群 ;msgHandle.Warrior_Msg_Handle(msg) :消息处理并返回处理结果 ; isAT 回复消息是否要@人 true是
            cc.sendGroupMSG(msg.getFromQQ(),msg.getFromGroup(),msgHandle.Warrior_Msg_Handle(msg),true);
        }

    }

}
