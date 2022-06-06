package com.baizhi.controller;

import com.baizhi.util.WebSocketMapUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;


@ServerEndpoint(value = "/WebSocketController")
@Component
public class WebSocketController {
    private static final String ENCODING = "UTF-8";
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    public static ConcurrentMap<String, WebSocketController> webSocketSet = WebSocketMapUtil.webSocketMap;
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("建立连接成功");
        this.session = session;
        WebSocketController.onlineCount++;
        webSocketSet.put(this.session.getId(),this);

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(this.session==null){
            System.out.println("异常退出");
            WebSocketController.onlineCount--;
        }else {
            System.out.println("正常退出");
            webSocketSet.remove(this.session.getId());
        }


    }

    /**
     *       * 收到客户端消息后调用的方法
     *       * @param message 客户端发送过来的消息
     *       * @param session 可选的参数
     *
     */
    @OnMessage
    public void onMessage(String message)throws IOException  {
       System.out.println("-------------------------------来自客户端的消息:" + message);
         sendMessage(message);
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }



    public void sendMessage(String result) throws IOException {
            this.session.getBasicRemote().sendText(result);
    }





}
