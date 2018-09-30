package com.sshgui.demo.Device;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by xp110 on 2018/9/11.
 */
public class DeviceTypeModel extends AbstractTableModel {

    Sshcontroller sc=new Sshcontroller();

    String[] columnNames = new String[] { "id",  "端口","类型","描述", };

    public List<DeviceDO> DeviceDOList =sc.getAllDevice();

    //搜索 后更新单元格
    public void update(String str){
        this.DeviceDOList=Sshcontroller.selectDevice(str);
    }

    // 返回一共有多少行
    public int getRowCount() {
        return DeviceDOList.size();
    }

    // 返回一共有多少列
    public int getColumnCount() {
        return columnNames.length;
    }

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
        DeviceDO deviceDO=DeviceDOList.get(rowIndex);
        if (0 == columnIndex)
            return deviceDO.id;
        if (1 == columnIndex)
            return deviceDO.port_number;
        if (2 == columnIndex)
            return deviceDO.device_describe;
        if (3 == columnIndex)
            return deviceDO.device_name;
        return null;
    }

}
