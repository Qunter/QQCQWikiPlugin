package service;

import com.mumu.msg.RE_MSG_Group;
import com.mumu.msg.RE_MSG_Private;

public interface wikiMsgHandle {
    //群消息处理业务接口
    String Warrior_Msg_Handle(RE_MSG_Group msg);
    //私聊消息处理业务接口
    String Warrior_Msg_Handle_RE_MSG_Private(RE_MSG_Private msg);
}
