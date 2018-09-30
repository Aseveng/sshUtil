package com.sshgui.demo.Device;

/**
 * Created by xp110 on 2018/9/10.
 */
//ssh命令
public class CommandDO {
    //命令编号
    public  String  number;

    //id
    public String id;

    //类型
    public String type;

    //具体命令

    public String command;

    //名称
    public String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
