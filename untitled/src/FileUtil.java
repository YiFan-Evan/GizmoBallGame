import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

//todo：掌管文件功能的单例类

/**
 * 掌管文件功能的单例类
 */
public class FileUtil {
    private static SaveFile saveFile;
    private static OpenFile openFile;

    public static SaveFile getSave() {
        if(saveFile == null) {
            saveFile = new SaveFile();
        }
        return saveFile;
    }

    public static OpenFile getOpen() {
        if(openFile == null) {
            openFile = new OpenFile();
        }
        return openFile;
    }

    /**
     * 用来保存文件的类
     */
    private static class SaveFile implements Runnable {

        /**
         * 保存文件的方法
         */
        @Override
        public void run() {

            System.out.println(1);

            //得到保存的文件位置
            File file;
            GameSystem.gui.saveDia.setVisible(true);//显示保存文件对话框
            String dirpath = GameSystem.gui.saveDia.getDirectory();//获取保存文件路径并保存到字符串中。
            String fileName = GameSystem.gui.saveDia.getFile();////获取打保存文件名称并保存到字符串中

            if (dirpath == null || fileName == null)//判断路径和文件是否为空
                return;//空操作
            else
                file = new File(dirpath, fileName);//文件不为空，新建一个路径和名称

            //将游戏的占位碰撞体和游戏的组件数组序列化后写入文件
            try {
                FileOutputStream fs = new FileOutputStream(file);//写文件流
                ObjectOutputStream os = new ObjectOutputStream(fs);//写对象流
                os.writeObject(GameSystem.board.shapes);//写组件数组
                os.writeObject(GameSystem.board.shapeMap);//写地图信息
                os.close();

                //抛出IO异常
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 用来打开文件的类
     */
    private static class OpenFile implements Runnable {

        /**
         * 打开文件的方法
         */
        @Override
        public void run() {

            //得到打开的文件位置
            GameSystem.gui.openDia.setVisible(true);//显示打开文件对话框
            String dirpath = GameSystem.gui.openDia.getDirectory();//获取打开文件路径并保存到字符串中。
            String fileName = GameSystem.gui.openDia.getFile();//获取打开文件名称并保存到字符串中

            if (dirpath == null || fileName == null)//判断路径和文件是否为空
                return;

            File file = new File(dirpath, fileName);//创建新的路径和名称

            //将游戏的占位碰撞体和游戏的组件数组反序列化后生成对象
            try {

                //得到对象
                FileInputStream fi = new FileInputStream(file);//读文件流
                ObjectInputStream oi = new ObjectInputStream(fi);//读对象流
                Object o1 = oi.readObject();//得到第一个对象
                Object o2 = oi.readObject();//得到第二个对象

                //强转对象
                ArrayList<Shape> panels = (ArrayList<Shape>) o1;
                Shape[][] shapes = (Shape[][]) o2;
                oi.close();

                //将对象指向游戏
                GameSystem.board.shapes = panels;
                GameSystem.board.shapeMap = shapes;

                //回复窗体状态
                GameSystem.gui.window.removeAll();
                panels.forEach((shape -> {
                    if (shape.name.equals("ball"))
                        GameSystem.gui.window.add(shape, 0);
                    else
                        GameSystem.gui.window.add(shape);
                }));//顺序添加每个组件，如果是球，则放在最上层
                GameSystem.gui.window.repaint();


                //抛出IO异常、抛出文件路径找不到异常
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof StreamCorruptedException)
                    JOptionPane.showMessageDialog(GameSystem.gui.frame, "此文件类型未知，应用无法读取", "错误", JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(GameSystem.gui.frame, "系统找不到指定的文件。", "错误", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
