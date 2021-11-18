import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

//todo：游戏所有界面组件的父类

/**
 * 游戏所有界面组件的父抽象类
 */
public abstract class Shape extends JPanel implements Serializable {

    //序列化编号
    @Serial
    private static final long serialVersionUID = -2000L;

    Location location;//组件所在位置
    Pair<Integer> size;//组件大小
    String name;//组件名
    Angle angle;//组件旋转角度

    /**
     * 用于规定旋转角度的枚举类
     */
    public enum Angle {

        //四种角度
        Zero,
        Right,
        Half,
        Reflex;

        /**
         * 获得旋转一次的下一个角度
         *
         * @param angle
         * @return
         */
        public Angle next(Angle angle) {
            switch (angle) {
                case Zero -> {
                    return Right;
                }
                case Right -> {
                    return Half;
                }
                case Half -> {
                    return Reflex;
                }
                case Reflex -> {
                    return Zero;
                }
            }
            return null;
        }

        /**
         * 变为下一个角度
         *
         * @return
         */
        public Angle toNext() {
            return next(this);
        }
    }

    /**
     * 组件的构造方法
     *
     * @param x
     * @param y
     * @param name
     */
    public Shape(int x, int y, String name) {
        angle = Angle.Zero;//初始角度
        this.name = name;//设置组件的名字
        location = new Location(x, y);//设置组件的位置
        size = new Pair<>(GameSystem.cell, GameSystem.cell);//设置组件的大小
        this.setBounds(location.point.x, location.point.y, size.x, size.y);//设置组件在窗体位置和大小
        this.setOpaque(false);//设置组件透明
        this.setLayout(new GridLayout(1, 1));//设置组件的布局
    }

    /**
     * 组件的绘制方法
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //将组件通过名字和旋转角度得到相应的图片
        ImageIcon icon;
        Image img;
        if (angle == Angle.Zero)
            icon = new ImageIcon(getClass().getResource("/" + name + ".jpg"));
        else
            icon = new ImageIcon(getClass().getResource("/" + name + "-" + angle.toString() + ".jpg"));
        img = icon.getImage();
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    /**
     * 组件的碰撞抽象方法，在每个继承类细化实现
     *
     * @param position
     * @param speed
     * @return
     */
    public abstract Pair<Double> act(Pair<Integer> position, Pair<Double> speed);

    /**
     * 改变组件大小
     *
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        assert this.canSize();//判断是否可以改变大小
        size = new Pair<>(width, height);//设置组件的大小
        location.setSize(size);//设置组件的位置
        this.setBounds(location.point.x, location.point.y, width, height);//设置组件在窗体位置和大小
        this.setOpaque(false);//设置组件透明
        this.setLayout(new GridLayout(1, 1));//设置组件的布局
        this.repaint();//重绘组件
    }

    /**
     * 改变组件旋转角度
     */
    public void spin() {
        assert this.canSpin();
        angle = angle.toNext();
        this.repaint();
    }

    /**
     * 将组件移动的方法（仅限小球和平台）
     */
    public void move() {
    }

    /**
     * 将组件归为的方法（仅限平台）
     */
    public void home() {
    }

    /**
     * 判断组件是否能够被改变大小
     *
     * @return
     */
    public abstract boolean canSize();

    /**
     * 判断组件是否能够旋转
     *
     * @return
     */
    public abstract boolean canSpin();
}
