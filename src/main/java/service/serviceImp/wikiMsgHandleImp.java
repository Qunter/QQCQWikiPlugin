package service.serviceImp;

import com.mumu.msg.RE_MSG_Group;
import com.mumu.msg.RE_MSG_Private;
import dao.daoImp.queryWikiDao;
import dao.queryWiki;
import service.wikiMsgHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** 前面有 * 的说明已经 就行处理可以返回消息
 * 图鉴的数据id
 综合评分=hero_score_all
 推图评分=hero_score_story
 竞技评分=hero_score_pvp
 挑战评分=hero_score_ch
 勇士标签=hero_tag
 勇士别名=hero_nickname
 *勇士评价=hero_est
 勇士详解=hero_link
 *背景故事=hero_story
 *吃书推荐=hero_book
 *方块技能=hero_skill
 *技能说明=hero_skill_info
 *消块机制=hero_skill_m
 *无专武技能判定=hero_skill_nwp
 *有专武技能判定=hero_skill_wp
 *特殊技能推荐=hero_skill_sp
 *专属精粹武器=hero_wp
 *专武评价=hero_wp_est
 *词条推荐=hero_wp_attr
 *符文推荐=hero_wp_ct
 *戒指词条推荐=hero_ring
 *推荐阵容搭配=hero_team
 */
public class wikiMsgHandleImp implements wikiMsgHandle {
    private static queryWiki wikiData;
    private static Map<String,String> map;
    public wikiMsgHandleImp(){
        wikiData =new queryWikiDao();
        //加载模糊匹配数据
        map = wikiData.blurryWarriorName();
    }

    /**
     * 群消息处理中枢
     * @param msg 群消息类
     * @return 查询结果
     */
    @Override
    public String Warrior_Msg_Handle(RE_MSG_Group msg) {
        //查询类型处理
        List<String> myList = Handle_Msg(msg);
        //过滤出勇士名称
        try {
            if(myList!=null&&myList.size()>0){
                return getWarriorData(myList.get(1),myList.get(0));
            }
            return "未查询到数据";
        }catch (Exception e){
            e.printStackTrace();
            return "消息异常";
        }
    }
    /**
     * 私聊消息处理中枢
     * @param msg 群消息类
     * @return 查询结果
     */
    @Override
    public String Warrior_Msg_Handle_RE_MSG_Private(RE_MSG_Private msg){
        //查询类型处理
        List<String> myList = Handle_Msg_Private(msg);
        //过滤出勇士名称
        try {
            if(myList!=null&&myList.size()>0){
                return getWarriorData(myList.get(1),myList.get(0));
            }
            return "抱歉未查询到数据";
        }catch (Exception e){
            e.printStackTrace();
            return "消息异常";
        }
    }
    /**
     * name :勇士名称
     * 处理消息:查询勇士评价
     * @return 查询结果
     */
    @Deprecated
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

    /**
     * 返回 勇士数据方法
     * @param name 勇士名称
     * @param id 数据id
     * @return 返回勇士数据
     * @throws IOException IO异常
     */
    private String getWarriorData(String name ,String id) throws IOException {
        if (map.containsKey(name)){
            return wikiData.getWarrior_data(map.get(name),id);
        }else {
            return wikiData.getWarrior_data(name,id);
        }
    }
    /**
     * 查询类型处理并返回
     * 查询的勇士名称
     * @param msg
     * @return
     */
    private List<String> Handle_Msg(RE_MSG_Group msg){
        List<String> list = new ArrayList<>();
        //查询勇士评价 查询格式 @机器人 #查询勇士评价:勇士名称
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询勇士评价")){
            list.add("hero_est");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询勇士方块技能")){
            list.add("hero_skill");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询专属精粹武器")){
            list.add("hero_wp");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询专武评价")){
            list.add("hero_wp_est");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询背景故事")){
            list.add("hero_story");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        //web id 暂时没加上 hero_skill_sp
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询特殊技能推荐")){
            list.add("hero_skill_sp");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询词条推荐")){
            list.add("hero_wp_attr");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询符文推荐")){
            list.add("hero_wp_ct");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询戒指词条推荐")){
            list.add("hero_ring");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询推荐阵容搭配")){
            list.add("hero_team");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询有专武技能判定")){
            list.add("hero_skill_wp");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询无专武技能判定")){
            list.add("hero_skill_nwp");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询消块机制")){
            list.add("hero_skill_m");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        if(msg.getMsg().split("]")[1].split("#")[1].split(":")[0].equals("查询吃书推荐")){
            list.add("hero_book");
            list.add(msg.getMsg().split("]")[1].split("#")[1].split(":")[1]);
            return list;
        }
        return null;
    }

    /**
     * 私聊查询处理并返回查询的勇士名称
     * @param msg 私聊类
     * @return 处理结果
     */
    private List<String> Handle_Msg_Private(RE_MSG_Private msg){
        List<String> list = new ArrayList<>();
        //查询勇士评价 查询格式 @机器人 #查询勇士评价:勇士名称
        if(msg.getMsg().split(":")[0].equals("查询勇士评价")){
            list.add("hero_est");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询勇士方块技能")){
            list.add("hero_skill");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询专属精粹武器")){
            list.add("hero_wp");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询专武评价")){
            list.add("hero_wp_est");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询背景故事")){
            list.add("hero_story");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        //web id 暂时没加上 hero_skill_sp
        if(msg.getMsg().split(":")[0].equals("查询特殊技能推荐")){
            list.add("hero_skill_sp");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询词条推荐")){
            list.add("hero_wp_attr");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询符文推荐")){
            list.add("hero_wp_ct");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询戒指词条推荐")){
            list.add("hero_ring");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询推荐阵容搭配")){
            list.add("hero_team");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询有专武技能判定")){
            list.add("hero_skill_wp");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询无专武技能判定")){
            list.add("hero_skill_nwp");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询消块机制")){
            list.add("hero_skill_m");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        if(msg.getMsg().split(":")[0].equals("查询吃书推荐")){
            list.add("hero_book");
            list.add(msg.getMsg().split(":")[1]);
            return list;
        }
        return null;
    }

}
