package com.sshgui.demo;
/**
 * Created by xp110 on 2018/9/9.
 */



import com.jcraft.jsch.JSchException;
import com.sshgui.demo.Device.CommandDO;
import com.sshgui.demo.Device.CommandTypeTestModel;
import com.sshgui.demo.Device.DeviceTypeModel;
import com.sshgui.demo.Device.Sshcontroller;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

@SpringBootApplication
public class SSHGUI {

    Sshcontroller sc=new Sshcontroller();
     static int table =0;
    public static void main(String[] args) {
        SpringApplication.run(SSHGUI.class, args);

        System.setProperty("java.awt.headless", "false");
        // 主窗体
        JFrame f = new JFrame("SSH");
        f.setResizable(false);


        // 主窗体设置大小
        f.setSize(540, 500);

        // 主窗体设置位置
        f.setLocation(200, 200);

        // 主窗体中的组件设置为绝对定位
        f.setLayout(null);

        JPanel pLeft = new JPanel();
        pLeft.setBounds(50, 50, 300, 60);

        pLeft.setBackground(Color.white);

        JPanel pLeft2 = new JPanel();



        pLeft.setLayout(new FlowLayout());
        JButton b1 = new JButton("设备类型");
        b1.setName("DeviceType");
        JButton b2 = new JButton("命令管理");
        b2.setName("CommandManage");
        JButton b3 = new JButton("设备列表");

        JButton b4=new JButton("设备管理");

        pLeft.add(b1);
        pLeft.add(b2);
        pLeft.add(b3);

        JPanel pRight = new JPanel();

        JPanel p = new JPanel();


        JTextField selectField = new JTextField();
        selectField.setPreferredSize(new Dimension(80,30));
        JButton selectButton = new JButton("搜索");
        JButton addButton =new JButton("增加");
        JButton editButton=new JButton("编辑");
        JButton deleteButton=new JButton("删除");

        pRight.add(selectField);
        pRight.add(selectButton);
        pRight.add(addButton);
        pRight.add(editButton);
        pRight.add(deleteButton);


        CommandTypeTestModel commandTypeTestModel=new CommandTypeTestModel();

        JTable t = new JTable(commandTypeTestModel);

        // 根据t创建 JScrollPane
        JScrollPane jsp = new JScrollPane(t);
        pRight.add(jsp, BorderLayout.CENTER);

        DeviceTypeModel deviceTypeModel=new DeviceTypeModel();
        JTable t1 = new JTable(deviceTypeModel);
        JScrollPane jsDevice=new JScrollPane(t1);

        // 菜单栏
        JMenuBar mb = new JMenuBar();

        // 菜单
        JMenu mHero = new JMenu("设备");
        JMenu mItem = new JMenu("命令");


        // 把菜单加入到菜单栏
        mb.add(mHero);
        mb.add(mItem);


        // 把菜单栏加入到frame，这里用的是set而非add
        f.setJMenuBar(mb);


        pRight.setBounds(70, 150, 200, 60);



        // 创建一个水平JSplitPane，左边是p1,右边是p2
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
        // 设置分割条的位置
        sp.setDividerLocation(70);

        // 把sp当作ContentPane
        f.setContentPane(sp);

        //双击事件
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0){
                if(arg0.getClickCount()==2) {
                    int row = t.getSelectedRow();
                    Object obj = t.getValueAt(row,1);
                    Sshcontroller sc=new Sshcontroller();
                    try {
                        //执行选中的命令
                        String re=sc.ssh((String)obj);
                        String msg=new String(re.getBytes("utf-8"));
                        JFrame new_jf=new JFrame("命令返回信息");
                        new_jf.setSize(540, 500);

                        // 主窗体设置位置
                        new_jf.setLocation(200, 200);
                        JTextArea ta = new JTextArea();
                        JScrollPane jp=new JScrollPane(ta);
                        jp.setVerticalScrollBarPolicy(
                                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        ta.setText(msg);
                        ta.setLineWrap(true);
                        new_jf.add(jp);

                        new_jf.setVisible(true);

                    } catch (JSchException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(f,  "连接服务器失败");
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(f,  "连接服务器失败");
                    }

                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    f.setVisible(true);

                }
            }
        });

        //搜索功能
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=selectField.getText();
                //命令管理表
                if(table==0) {
                    commandTypeTestModel.update(str); //搜索命令
                    t.updateUI();
                }
                //设备管理表 搜索
                if(table==1){
                    deviceTypeModel.update(str);
                    t1.updateUI();
                }
                if(table==2){

                }



                pRight.updateUI();

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=t.getSelectedRow();
                CommandDO cm=commandTypeTestModel.commandDOList.get(row);
                String id=cm.getId();
                commandTypeTestModel.updateCommand(id);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=t.getSelectedRow();

            }
        });


        // 切换到命令管理
        b2.addActionListener(new ActionListener() {
            // 当按钮被点击时，就会触发 ActionEvent事件
            public void actionPerformed(ActionEvent e) {
                pRight.remove(t);
                table=0;
                pRight.add(jsDevice, BorderLayout.CENTER);
                jsp.updateUI();
                pRight.updateUI();
            }
        });

        //切换到设备管理表
       b1.addActionListener(new ActionListener() {
            // 当按钮被点击时，就会触发 ActionEvent事件
            public void actionPerformed(ActionEvent e) {
                pRight.remove(t);
                table=1;
                pRight.add(jsp, BorderLayout.CENTER);
                pRight.updateUI();

            }
        });

       //切换到设备列表
       b3.addActionListener(new ActionListener() {
            // 当按钮被点击时，就会触发 ActionEvent事件
            public void actionPerformed(ActionEvent e) {
                pRight.remove(t);
                table=2;
                pRight.add(jsp, BorderLayout.CENTER);
                pRight.updateUI();

            }
        });

        // 同时设置组件的大小和位置
      /*  b.setBounds(100, 100, 100, 30);// 左右 上下  长  宽
        jtext.setBounds(100,50,100,30);
        jtext2.setBounds(100,200,300,300);*/
        // 把按钮加入到主窗体中
      /*  f.add(b);

        f.add(jtext);
       f.add(jtext2);*/
        // 关闭窗体的时候，退出程序
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        f.setVisible(true);
    }
}
