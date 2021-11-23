import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//todo：游戏的控制，存储游戏所有数据

/**
 * 游戏的控制，存储游戏所有数据
 */
public class GameSystem {

    //游戏的数据信息
    public static boolean isPlay;//是否在游玩模式
    public static Shape[][] shapeMap;//游戏每个方格内形状组件的信息
    public static ArrayList<Shape> shapes;//所有在游戏内的组件
    public static Pair<Integer> mousePoint;//鼠标指针在游戏框内的位置
    public static int cell = 40;//一个框的像素，场景里每个组件大小都是40乘40像素，每一个格子也是40*40像素，刚好组件都放置在格子内。
    public static PlayGame play;//游玩模式的控制对象
    public static GUI gui;//游戏的界面
    public static double gravity;//地心引力的常量
    public static int towards;//游戏内托板的朝向

    /**
     * 程序的入口，游戏的启动
     *
     * @param args
     */
    public static void main(String[] args) {

        //将游戏系统启动
        init();
    }

    /**
     * 返回鼠标指针所在位置的形状对象
     *
     * @return
     */
    public static Shape getShape() {
        if (mousePoint != null)
            return shapeMap[GameSystem.mousePoint.x / GameSystem.cell + 1][GameSystem.mousePoint.y / GameSystem.cell + 1];
        return null;
    }

    /**
     * 初始化游戏，设置基本游戏数据的初始值，初始化界面以及空气墙
     */
    public static void init() {

        //游戏基本变量设置初始化
        towards = 0;
        isPlay = false;
        gravity = 0.1;
        play = null;
        mousePoint = null;
        shapeMap = new Shape[22][22];
        shapes = new ArrayList<>();

        //初始化界面
        gui = new GUI();
        gui.init();

        //初始化空气墙，在格子标号为0的点中存放了空气墙，（空气墙的朝向1在上面，2在下面，3在左面，4在右面）空气墙分别布置在四周，一碰撞小球就会反弹，保证小球不会离开界面。
        //（空气墙不会显示在游玩的界面之中，超出了画面之外）
        for (int i = 0; i < 22; i++) {
            shapeMap[0][i] = new AirWall(0, i, 3);
            shapeMap[i][0] = new AirWall(0, i, 1);
            shapeMap[21][i] = new AirWall(0, i, 4);
            shapeMap[i][21] = new AirWall(0, i, 2);
        }
    }

    /**
     * 开始一轮游玩模式
     */
    public static void start() {

        //判断是否在游玩模式
        if (isPlay) {
            JOptionPane.showMessageDialog(gui.frame, "已进入游玩模式，请勿重复点击", "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            //设置新的一轮游戏控制
            play = new PlayGame();

            //游玩的演进过程
            while (isPlay) {//当没有结束游玩
                play.ball.move();//小球位置移动
                try {
                    Thread.sleep(10);//游戏帧率（刷新时间）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //游戏结束服务子例程
            play.end();
            end();

            //抓取小球掉出界面异常
        } catch (ArrayIndexOutOfBoundsException e) {
            //游戏结束
            play.end();
            end();

            //抓取游玩时没有小球异常
        } catch (NullPointerException e) {
            //打印信息并结束
            System.out.println("no ball");
            play.end();
            end();
        }
    }

    /**
     * 结束一轮游玩模式
     */
    public static void end() {
        isPlay = false;//更新状态
        play = null;//回收一轮游戏
        gui.window.repaint();//更新界面
    }

    /**
     * 开始一轮游玩模式的控制类，在游玩时创建，游玩结束销毁
     */
    public static class PlayGame {

        //小球的对象以及位置
        Ball ball;
        int x;
        int y;

        /**
         * 创建一轮新游戏
         */
        public PlayGame() {

            //初始化游玩模式
            gui.defaultToolkit.addAWTEventListener(gui, AWTEvent.KEY_EVENT_MASK);//允许键盘监听（平台移动）
            isPlay = true;//更新状态
            gui.fileMenu.setEnabled(false);//不允许文件保存

            //找到游戏中的球，否则初始化失败
            ball = null;
            for (Shape shape : GameSystem.shapes) {
                if (shape.name.equals("ball"))
                    ball = (Ball) shape;
            }
            if (ball == null)
                return;

            //保存球的初始位置信息
            x = ball.location.x;
            y = ball.location.y;

            //将球的碰撞体清空（球不会与初始位置碰撞）
            shapeMap[x][y] = null;

            //初始化球
            ball.init();
        }

        /**
         * 结束一轮游戏
         */
        public void end() {

            //状态回复
            isPlay = false;//更新状态
            gui.fileMenu.setEnabled(true);//打开文件功能
            gui.defaultToolkit.removeAWTEventListener(gui);//关闭键盘监听
            shapes.forEach(Shape::home);//将平台回位
            shapes.removeIf((shapePanel -> shapePanel.name.equals("ball")));//移除游戏小球

            //在游戏有球的情况下，在初始位置新创建一个球
            if (ball != null) {
                gui.window.remove(0);
                Ball thePanel = new Ball(x, y);
                GameSystem.shapeMap[x][y] = thePanel;
                GameSystem.shapes.add(thePanel);
                gui.window.add(thePanel, 0);
            }
        }
    }
}
