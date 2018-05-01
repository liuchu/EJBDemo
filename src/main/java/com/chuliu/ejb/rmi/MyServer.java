package com.chuliu.ejb.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by chuliu on 2018/4/29.
 */
public class MyServer {

    public static void main(String[] args) {

        try{
            Adder stub=new AdderRemote();

            //创建并导出接受指定port请求的本地主机上的Registry实例。
            LocateRegistry.createRegistry(5555);

            /** Naming 类提供在对象注册表中存储和获得远程对远程对象引用的方法
             *  Naming 类的每个方法都可将某个名称作为其一个参数，
             *  该名称是使用以下形式的 URL 格式（没有 scheme 组件）的 java.lang.String:
             *  //host:port/name
             *  host：注册表所在的主机（远程或本地)，省略则默认为本地主机
             *  port：是注册表接受调用的端口号，省略则默认为1099，RMI注册表registry使用的著名端口
             *  name：是未经注册表解释的简单字符串
             */

            Naming.rebind("rmi://localhost:5555/sonoo",stub);
            System.out.println("Server started up");
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
