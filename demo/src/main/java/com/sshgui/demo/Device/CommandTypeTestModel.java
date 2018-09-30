package com.sshgui.demo.Device;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.sasl.SaslServer;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by xp110 on 2018/9/10.
 */
public class CommandTypeTestModel extends AbstractTableModel {


    private String str;

    //搜索 后更新单元格
    public void update(String str){
    this.commandDOList=Sshcontroller.selectCommand(str);
    }

    public void updateCommand(String id){

    }

    public List<CommandDO> commandDOList = Sshcontroller.getAllCommands();



    String[] columnNames = new String[] { "id", "命令", "端口","类型","名称", };


    // 返回一共有多少行
    public int getRowCount() {
        return commandDOList.size();
    }

    // 返回一共有多少列
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    // 获取每一列的名称
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    // 单元格是否可以修改
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CommandDO cm=commandDOList.get(rowIndex);
        if (0 == columnIndex)
            return cm.id;
        if (1 == columnIndex)
            return cm.command;
        if (2 == columnIndex)
            return cm.number;
        if (3 == columnIndex)
            return cm.type;
        if (4 == columnIndex)
            return cm.name;

        return null;
    }




}
