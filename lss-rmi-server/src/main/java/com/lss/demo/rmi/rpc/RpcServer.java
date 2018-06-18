package com.lss.demo.rmi.rpc;

import com.lss.demo.rmi.rpc.anno.RpcAnnotion;
import com.lss.demo.rmi.rpc.zk.IRegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 *
 * 用于发布一个远程服务
 */
public class RpcServer {
    //创建一个线程池
    private static final ExecutorService executorService=Executors.newCachedThreadPool();

    private IRegisterCenter iRegisterCenter;//服务注册中心
    private String serviceAddress;//服务发布地址

    //服务名称和服务对象之间的关系
    Map<String,Object> handleMap = new HashMap<String, Object>();

    public RpcServer(IRegisterCenter iRegisterCenter, String serviceAddress) {
        this.iRegisterCenter = iRegisterCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * b绑定服务名称和服务对象
     * @param services
     */
    public void bind(Object...services){
        for (Object service:services){
            RpcAnnotion annotion = service.getClass().getAnnotation(RpcAnnotion.class);
            String serviceName = annotion.value().getName();
            String version = annotion.version();
            if (null!=version&&!version.equals("")){
                serviceName = serviceName+"-"+version;
            }
            handleMap.put(serviceName,service);//绑定服务接口名称和服务
        }

    }

    public void publisher(){
        ServerSocket serverSocket=null;
        try{
            String[] addrs = serviceAddress.split(":");
            serverSocket=new ServerSocket(Integer.parseInt(addrs[1]));  //启动一个服务监听

            for (String interfaceName:handleMap.keySet()){
                iRegisterCenter.register(interfaceName,serviceAddress);
                System.out.println("服务注册成功："+interfaceName+"->"+serviceAddress);
            }


            while(true){ //循环监听
                Socket socket=serverSocket.accept(); //监听服务
                //通过线程池去处理请求
                executorService.execute(new ProcessorHandler(socket,handleMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
