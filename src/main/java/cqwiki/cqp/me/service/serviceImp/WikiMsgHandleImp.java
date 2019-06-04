package cqwiki.cqp.me.service.serviceImp;


import cqwiki.cqp.me.dao.daoImp.queryWikiDao;
import cqwiki.cqp.me.dao.queryWiki;
import cqwiki.cqp.me.service.WikiMsgHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;


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
 * 服装=hero_costume
 * 城镇对话=hero_dialogue
 * 全部评分=hero_score
 * 能力值=hero_state
 */
public class WikiMsgHandleImp implements WikiMsgHandle {
    private static queryWiki wikiData;
    private static Map<String,String> map;
    Map<String,String> keymap ;
    public WikiMsgHandleImp(){
        wikiData =new queryWikiDao();
        //加载模糊匹配数据
        map = wikiData.blurryWarriorName();
        putkeymap();
    }
    /**
     * 补充关键字库
     */
    public void putkeymap(){
        keymap= new HashMap<String,String>();
        keymap.put("勇士评价", "hero_est");
        keymap.put("评价", "hero_est");
        keymap.put("专武", "hero_wp_r6");
        keymap.put("专属武器", "hero_wp_r6");
        keymap.put("精粹武器", "hero_wp_r6");
        keymap.put("专属精粹武器", "hero_wp_r6");
        keymap.put("方块技能", "hero_skill");
        keymap.put("技能", "hero_skill");
        keymap.put("主动技能", "hero_skill_0");
        keymap.put("被动技能", "hero_skill_2");
        keymap.put("背景", "hero_story");
        keymap.put("故事", "hero_story");
        keymap.put("背景故事", "hero_story");
        keymap.put("特殊技能", "hero_skill_sp");
        keymap.put("sp", "hero_skill_sp");
        keymap.put("词条推荐", "hero_wp_attr");
        keymap.put("词条", "hero_wp_attr");
        keymap.put("符文推荐", "hero_wp_ct");
        keymap.put("符文", "hero_wp_ct");
        keymap.put("戒指", "hero_ring");
        keymap.put("戒指词条", "hero_ring");
        keymap.put("推荐阵容", "hero_team");
        keymap.put("组队", "hero_team");
        keymap.put("队伍", "hero_team");
        keymap.put("阵容", "hero_team");
        keymap.put("搭配", "hero_team");
        keymap.put("无专", "hero_skill_nwp");
        keymap.put("有专", "hero_skill_wp");
        keymap.put("消块", "hero_skill_m");
        keymap.put("消块机制", "hero_skill_m");
        //keymap.put("吃书", "hero_book");
        //keymap.put("继承", "hero_book");

        keymap.put("服装", "hero_costume");
        keymap.put("时装", "hero_costume");
        keymap.put("衣服", "hero_costume");
        keymap.put("小裙子", "hero_costume");
        keymap.put("对话", "hero_dialogue");
        keymap.put("彩蛋", "hero_dialogue");
        keymap.put("评分", "hero_score");
        keymap.put("英雄评分", "hero_score");
        keymap.put("属性", "hero_state");
    }

    /**
     * 群消息处理中枢
     * @param msg 群消息类
     * @return 查询结果
     */
    public String Warrior_Msg_Handle(String msg) {
        //帮助类型
        if(msg.trim().contains("帮助")){
            System.out.println("执行帮助指令");
            String helpHint =
                    "这里是可爱的wiki机器人,请按照如下方式调教我:"+lineSeparator
                    +"查tiao询jiao格式为：查询+空格+勇士简称或名称+空格+关键词"+lineSeparator
                    +"目前提供查询的关键词如下："+keymap.keySet().toString()+lineSeparator
                    +"勇士简称或名称可参考wiki简称页面"+lineSeparator
                    +"关键词\"属性\"后可加空格和数字1-25表示查询该继承书数下的属性"+lineSeparator;
            //System.out.println(usehelp);
            return helpHint;
        }
        if( msg.split(" ").length<=2){
            msg = msg.split(" ")[1];
            msg = msg.replace("吗","");
            msg = msg.replace("?","!");
            msg = msg.replace("？","!");
            return msg+" 如果是勇士,请加上正确的查询参数哦";
        }
        System.out.println(msg);
        //查询类型处理
        List<String> myList = Handle_Msg(msg);
        //过滤出勇士名称
        try {

            if(myList!=null&&myList.size()>0){
                //System.out.println(getWarriorData(myList.get(0),myList.get(1),myList.get(2)));
                return getWarriorData(myList.get(0),myList.get(1),myList.get(2));
            }
            return "未查询到勇士数据";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            return "查询规则不正确,正确规则输入帮助查看";
        }
    }

    /**
     * 私聊消息处理中枢
     * @param msg 群消息类
     * @return 查询结果
     */
    public String Warrior_Msg_Handle_RE_MSG_Private(String msg){
        //查询类型处理
        List<String> myList = Handle_Msg_Private(msg);
        //过滤出勇士名称
        try {
            if(myList!=null&&myList.size()>0){
                return getWarriorData(myList.get(0),myList.get(1),myList.get(2));
            }
            return "抱歉未查询到数据";
        }catch (Exception e){
            e.printStackTrace();
            return "消息异常";
        }
    }

    /**
     * 返回 勇士数据方法
     * @param name 勇士名称
     * @param id 数据id
     * @return 返回勇士数据
     * @throws IOException IO异常
     */
    private String getWarriorData(String name ,String id,String extra) throws IOException {
        //允许只输入一个字来查找勇士，按最先匹配到的数据返回结果

        boolean flag=false;
        if (map.containsKey(name)){
        }else {
            Set<String> keyset=  map.keySet();
            for (String str : keyset) {
                if(str.indexOf(name)!=-1) {
                    name=str;
                    flag=true;
                    break;
                }
            }
            if(flag==false)
                return "未查询到需要的数据";
        }

        switch(id){
            case "hero_dialogue":return wikiData.getWarrior_dialogue(map.get(name),id);
            case "hero_state":return wikiData.getWarrior_state(map.get(name),id,extra);
            case "hero_skill_sp":return wikiData.getWarrior_sp(map.get(name),id);
            default:break;
        }
        return wikiData.getWarrior_data(map.get(name),id);
    }

    /**
     * 查询类型处理并返回
     * 查询的勇士名称
     * @param msg
     * @return
     */
    private List<String> Handle_Msg(String msg){
        List<String> list = new ArrayList<String>();
        //查询格式   格式 勇士简称或名称 关键词
        String []keywords=msg.trim().replaceAll("\r|\n", "").split(" ");
        
        try{
            if(keywords.length>1)
            {
            String heroname=keywords[1];
            if(heroname.length()>0)
                list.add(heroname);
            else return null;
            }
            if(keywords.length>2)
            {
            String keyword=keymap.get(keywords[2]);
            if(keyword.length()>0)
                list.add(keyword);
            else return null;
            }
            if(keywords.length>3)
                list.add(keywords[3]);
            else list.add("0");
            return list;
            }
        catch (Exception e){
            return null;
      }
    }

    /**
     * 私聊查询处理并返回查询的勇士名称
     * @param msg 私聊类
     * @return 处理结果
     */
    private List<String> Handle_Msg_Private(String msg){
        List<String> list = new ArrayList<String>();
        //查询勇士评价 查询格式 @机器人 #查询勇士评价:勇士名称
        if(msg.split(":")[0].equals("查询勇士评价")){
            list.add("hero_est");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询勇士方块技能")){
            list.add("hero_skill");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询专属精粹武器")){
            list.add("hero_wp");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询专武评价")){
            list.add("hero_wp_est");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询背景故事")){
            list.add("hero_story");
            list.add(msg.split(":")[1]);
            return list;
        }
        //web id 暂时没加上 hero_skill_sp
        if(msg.split(":")[0].equals("查询特殊技能推荐")){
            list.add("hero_skill_sp");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询词条推荐")){
            list.add("hero_wp_attr");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询符文推荐")){
            list.add("hero_wp_ct");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询戒指词条推荐")){
            list.add("hero_ring");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询推荐阵容搭配")){
            list.add("hero_team");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询有专武技能判定")){
            list.add("hero_skill_wp");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询无专武技能判定")){
            list.add("hero_skill_nwp");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询消块机制")){
            list.add("hero_skill_m");
            list.add(msg.split(":")[1]);
            return list;
        }
        if(msg.split(":")[0].equals("查询吃书推荐")){
            list.add("hero_book");
            list.add(msg.split(":")[1]);
            return list;
        }
        return null;
    }

}
