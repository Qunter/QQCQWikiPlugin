package cqwiki.cqp.me.service;


public interface WikiMsgHandle {
    
    //群消息处理业务接口
    String Group_Msg_Handle(String msg);
    //私聊消息处理业务接口
    String Private_Msg_Handle(String msg);
    
    
}
