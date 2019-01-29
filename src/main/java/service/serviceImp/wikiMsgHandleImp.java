package service.serviceImp;

import com.mumu.msg.RE_MSG_Group;
import dao.daoImp.queryWikiDao;
import dao.queryWiki;
import service.wikiMsgHandle;

import java.util.Map;

public class wikiMsgHandleImp implements wikiMsgHandle {
    private static queryWiki wikiData;
    private static Map<String,String> map;
    public wikiMsgHandleImp(){
        wikiData =new queryWikiDao();
        //加载模糊匹配数据
        map = wikiData.blurryWarriorName();
    }

    /**
     * 消息处理中枢
     * @param msg
     * @return
     */
    @Override
    public String Warrior_Msg_Handle(RE_MSG_Group msg) {
        //查询勇士评价 查询格式 @机器人 #查询勇士评价:勇士名称
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询勇士评价")){
            if (map.containsKey(msg.getMsg().split("]")[1].split("#")[1].split(":")[1])){
                return Warrior_Hero_est_Msg_Handle(map.get(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]));
            }
           return Warrior_Hero_est_Msg_Handle(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
        }
        //如果不是查询消息则复读
        return msg.getMsg().split("]")[1];
    }
    /**
     * 处理消息:查询勇士评价
     * @return
     */
    private String Warrior_Hero_est_Msg_Handle(String name){
        //qq:需要@的qq,groupid:发送的群号，msg :发送的消息 ,isAT: 是否需要@发送 true是 false否
        //qq为""则不会返回@，为msg.getFromQQ()则@提问者
        //groupid为msg.getFromGroup() ，返回所有群；为群号字符串则只回答该群
        //msg为msg.getMsg()  ，复读
            try {
                return wikiData.getWarrior_Hero_est(name);
            } catch (Exception e) {
                e.printStackTrace();
                return "查询失败";
            }
    }


}
