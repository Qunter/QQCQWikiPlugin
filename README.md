# 酷Q,QQ机器人

#### 项目介绍
CQ WIKI QQ机器人插件

#### 软件架构
- controller层 ：接收消息 发送消息 
- dao层 ：查询并返回数据 
- service层 ：业务层 主要的逻辑放这里
- entity层 :这里放实体类

#### 安装教程

1. 数据组群内下载Java1.832.rar,解压至任意目录(不要包含中文名),该压缩文件为jdk1.8 32位的免安装文件,因为酷Q是32位程序,只支持32位jre,如机器上已有则可以直接使用(jdk中包含jre)(不需配置环境变量)
2. 下载 com.sobte.cqp.jcq.cpk 文件,放入酷Q程序根目录下的app文件夹下

#### 使用说明

##### 一,正常使用
1. 需要将工程打包成jar,改名为me.cqp.qunter.cqkq.jar,放入酷Q程序根目录下的data\app\com.sobte.cqp.jcq\app文件夹下
2. 将工程里的me.cqp.qunter.cqkq.json一并放入上述文件夹下
3. 打开酷Q -> 应用管理 -> [JCQ]开发工具 -> 启用
4. [JCQ]开发工具 -> 菜单 -> 管理 -> CQKQMianApp -> 启用

##### 二,调试开发
1. 可直接运行工程,在CQKQMianApp类的Main函数中模拟发送信息,通过断点或者打控制台的方式来测试

##### 三,JCQ相关文档
说明文档
> https://gitee.com/meowy/JCQ-CoolQ/wikis/pages?sort_id=242078

API文档
> https://sobte.gitee.io/jcq-wiki/

#### 参与贡献

- 哈利玛奥流
- w1n
- 曜战

