package com.sshgui.demo.Device;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by xp110 on 2018/9/6.
 */

@Component
public   class Sshcontroller {


    @Autowired
    private SshMapper sshMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    //工具类 Controller
    public static Sshcontroller sshcontroller;

    // 初始化  工具类Controller
    @PostConstruct
    public void init() {
        sshcontroller = this;
    }

    //获取所有命令
    public static List<CommandDO> getAllCommands(){
        return  sshcontroller.sshMapper.getAllCommand();
    }

    //根据Id 获取命令
    public static CommandDO getCommandById(String id){
        return  sshcontroller.sshMapper.getCommandById(id);
    }

    public  List<DeviceDO> getAllDevice(){
        return  sshcontroller.deviceMapper.getAllDevice();
    }

    public static List<CommandDO>selectCommand(String param){
        return  sshcontroller.sshMapper.selectCommand(param);
    }

    public static void insert(String command,String number,String type,String name ){
         sshcontroller.sshMapper.insertCommand(command,number,type,name);
    }

    //更新命令
    public static void updateCommand(String command,String number,String type,String name,String id){
        sshcontroller.sshMapper.updateCommand(command,number,type,name,id);
    }

    //查找交换机 搜索
    public static List<DeviceDO> selectDevice(String param){
        return sshcontroller.deviceMapper.selectDevice(param);
    }

    public  String ssh(String cm) throws JSchException, IOException {


        JSch jsch = new JSch();
        //String pubKeyPath = "C:\\Users\\yutao\\.ssh\\id_rsa";

        //jsch.addIdentity(pubKeyPath);

        String username = "root";
        String host = "192.168.245.128";
        String passwd = "12345678";
        Session session = null;//为了连接做准备
        try {
            session = jsch.getSession(username, host, 22);
            session.setPassword(passwd);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();


        // Channel channel = session.openChannel("shell");
        ChannelExec channel=(ChannelExec)session.openChannel("exec");

        channel.setCommand(cm);

        /*channel.setInputStream(System.in);
        channel.setOutputStream(System.out);
        InputStream in = channel.getInputStream();*/

        BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));

        channel.connect();

        String msg;
        StringBuffer sf=new StringBuffer();//接收返回字符串

        while((msg = in.readLine()) != null){
            System.out.println(msg);
             sf.append("\r\n");
            sf.append(msg);
        }

        channel.disconnect();
        session.disconnect();
        return String.valueOf(sf);

    }

    }



