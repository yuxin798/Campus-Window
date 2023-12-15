package com.campuswindow.server;

public class API {
//总IP:
    public static final String SERVER_URL = "http://10.7.88.211:8080/";
//关于用户的地址:
    //1.用户的登录、注册、忘记密码：
    public static final String USER = SERVER_URL + "user/";
    //2.根据userId，查询个人信息
    public static final String USER_FIND_INFO = SERVER_URL + "user/findInformation?userId=";
    //3.上传用户头像
    public static final String USER_AVATAR = SERVER_URL + "user/avatar";
    //4.修改用户用户信息
    public static final String USER_MODIFY_INFO = SERVER_URL + "user/modifyInformation";

//关于活动的地址:
    //1.获取 活动数据
    public static final String ACTIVITY = SERVER_URL + "activity/";
    //2.根据标题 搜索 帖子
    public static final String ACTIVITY_FIND_SEARCH = SERVER_URL + "activity/findAllLikeActivityTitle";
    //3.文件（照片、视频等）上传
    public static final String FILE_UPLOAD = "activity/avatar";
    //4.帖子点赞
    public static final String ADD_LOVE_ACTIVITY = "activity/addLove";
    //5.帖子取消点赞
    public static final String DECREASE_LOVE_ACTIVITY = "activity/decreaseLove";

//发送帖子的设置:
    public static final String SEND_ACTIVITY = "activity/sendActivity";


//聊天界面:
    //查询聊天列表的API
    public static final String FIND_CHAT_LIST = SERVER_URL + "chat/getChatList";
    //查询聊天记录的API
    public static final String FIND_CHAT_MESSAGE = SERVER_URL + "chat/getChatRecords";
    //根据userId查询某一个人的头像和姓名的API
    public static final String FIND_CHAT_USER_DTO = SERVER_URL + "user/findOne";
    //根据fromUserId和toUserId,更改用户状态的API
    public static final String UPDATE_CHAT_LIST_STATUS = SERVER_URL + "chat/updateChatListStatus";
    public static final String UPDATE_CHAT_LIST_UNREAD = SERVER_URL + "chat/clearUnreadForMyself";
    //与服务构建WebSocket连接的API
    public static final String WEB_SOCKET = "ws://10.7.88.211:8080/websocket/";


//   402宿舍:192.168.3.6      402:10.7.88.211       10.7.81.172  10.7.88.175   10.7.81.179




    //    public static final String SERVER_URL="http://192.168.43.22:8080/user/";
//    public static final String SERVER_URL2="http://192.168.43.22:8080/activity/";
//    public static final String SERVER_URL3="http://192.168.43.22:8080/activity/findAllLikeActivityTitle/";
    //查询用户信息
//    public static final String SERVER_URL4="http://192.168.43.22:8080/user/findInformation?userId=";

    //上传用户头像
//    public static final String SERVER_URL5="http://192.168.43.22:8080/user/avatar";
    //修改用户信息
//    public static final String SERVER_URL6="http://192.168.43.22:8080/user/modifyInformation";

}
