package com.lss.demo.rmi.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 *
 * 处理socket请求
 */
public class ProcessorHandler implements Runnable{

    private Socket socket;
    private Map<String, Object> handleMap; //服务端发布的服务

    public ProcessorHandler(Socket socket, Map<String,Object> handleMap) {
        this.socket = socket;
        this.handleMap = handleMap;
    }


    public void run() {
        //处理请求
        ObjectInputStream inputStream=null;
        try {
            //获取客户端的输入流
            inputStream=new ObjectInputStream(socket.getInputStream());
            //反序列化远程传输的对象RpcRequest
            RpcRequest request=(RpcRequest) inputStream.readObject();
            Object result=invoke(request); //通过反射去调用本地的方法

            //通过输出流讲结果输出给客户端
            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Object invoke(RpcRequest request) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //一下均为反射操作，目的是通过反射调用服务
        Object[] args=request.getParameters();
        Class<?>[] types=new Class[args.length];
        for(int i=0;i<args.length;i++){
            types[i]=args[i].getClass();
        }
        String serviceName = request.getClassName();
        String version = request.getVersion();
        if(null!=version&&!version.equals("")){
            serviceName = serviceName+"-"+version;
        }

        //从handleMap中，根据客户端请求的地址，去拿响应的服务，通过发射发起调用
        Object service =  handleMap.get(serviceName);
        Method method=service.getClass().getMethod(request.getMethodName(),types);
        return method.invoke(service,args);
    }
}
