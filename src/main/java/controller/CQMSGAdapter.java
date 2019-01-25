package controller;

import com.mumu.listenner.KQMSGAdapter;
import com.mumu.webclient.KQWebClient;
import dao.ExcelUtil;
import dao.daoImp.ExcelUtilDao;
import service.WikiFilterDataService;
import service.serviceImp.WikiFilterDataServiceImp;

import java.io.File;
import java.util.List;

public class CQMSGAdapter extends KQMSGAdapter{
    private KQWebClient cc;
    private static List<List<String>> allData;
    public CQMSGAdapter(KQWebClient cc){
        this.cc=cc;
        File file = new File("allData.xls");
        //读取Excel配置数据
        ExcelUtil excel = new ExcelUtilDao();
        this.allData=excel.readExcel(file);
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
        WikiFilterDataService wikiFilterDataService = new WikiFilterDataServiceImp();
        //打印日志
        System.out.println("\033[36;0m" +"从群号: "+"\033[33;0m"+msg.getFromGroup()+"("+msg.getFromGroupName()+")"+ "\033[36;0m" + " [接收_"+msg.getFromQQ()+"("+msg.getUsername()+")_消息] : "+"\033[31;0m"+msg.getMsg()+ "\033[0m");
        //判断是否是想要的消息
        if(wikiFilterDataService.msgFilterSpecific(msg)){
            //qq:需要@的qq,groupid:发送的群号，msg :发送的消息 ,isAT: 是否需要@发送 true是 false否
            //qq为""则不会返回@，为msg.getFromQQ()则@提问者
            //groupid为msg.getFromGroup() ，返回所有群；为群号字符串则只回答该群
            //msg为msg.getMsg()  ，复读
            cc.sendGroupMSG(msg.getFromQQ(),msg.getFromGroup(),msg.getMsg().split("]")[1],true);
        }

    }

}
