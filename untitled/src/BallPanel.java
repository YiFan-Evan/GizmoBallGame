import java.awt.*;
import java.util.ArrayList;

//todo：小球类，组件之一，用于控制小球动作

/**
 * 小球类，组件之一，用于控制小球动作
 */
public class BallPanel extends ShapePanel {

    //小球速度
    double speedX;
    double speedY;

    //小球位置点
    ArrayList<Pair<Integer>> round;
    Pair<Integer> eight;
    Pair<Integer> six;
    Pair<Integer> four;
    Pair<Integer> two;
    Pair<Integer> nine;
    Pair<Integer> seven;
    Pair<Integer> three;
    Pair<Integer> one;
    Pair<Integer> a;
    Pair<Integer> b;
    Pair<Integer> c;
    Pair<Integer> d;
    Pair<Integer> e;
    Pair<Integer> f;
    Pair<Integer> g;
    Pair<Integer> h;
    Pair<Integer> center;

    /**
     * 初始化小球游玩时的数据
     */
    public void init() {
        speedX = 0;
        speedY = 0;
        center = new Pair<>(location.point.x + 20, location.point.y + 20);
        round = new ArrayList<>();
        update(center.x, center.y);
    }

    /**
     * 更新小球每一帧后位置点
     *
     * @param x
     * @param y
     */
    public void update(int x, int y) {

        //清空位置点
        round.clear();

        //计算边界碰撞检测点
        eight = new Pair<>(x, y - 20);
        six = new Pair<>(x + 20, y);
        four = new Pair<>(x - 20, y);
        two = new Pair<>(x, y + 20);
        nine = new Pair<>(x + 14, y - 14);
        seven = new Pair<>(x - 14, y - 14);
        three = new Pair<>(x + 14, y + 14);
        one = new Pair<>(x - 14, y + 14);
        a = new Pair<>(x + 8, y - 18);
        b = new Pair<>(x + 18, y - 8);
        c = new Pair<>(x + 18, y + 8);
        d = new Pair<>(x + 8, y + 18);
        e = new Pair<>(x - 8, y + 18);
        f = new Pair<>(x - 18, y + 8);
        g = new Pair<>(x - 18, y - 8);
        h = new Pair<>(x - 8, y - 18);

        //添加边界碰撞检测点
        round.add(eight);
        round.add(six);
        round.add(four);
        round.add(two);
        round.add(nine);
        round.add(seven);
        round.add(three);
        round.add(one);
        round.add(a);
        round.add(b);
        round.add(c);
        round.add(d);
        round.add(e);
        round.add(f);
        round.add(g);
        round.add(h);
    }

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public BallPanel(int x, int y) {
        super(x, y, "ball");
    }

    /**
     * 小球每帧移动需要的动作
     */
    public void move() {

        //检测下一帧有无发生碰撞
        Pair<Double> collision = collision(center.x + (int) speedX, center.y + (int) speedY);

        //更新速度位置
        speedX = collision.x;
        speedY = collision.y;
        speedY += GameSystem.gravity;
        center.x += (int) speedX;
        center.y += (int) speedY;
        update(center.x, center.y);
        this.setBounds(center.x - 20, center.y - 20, super.size.x, super.size.y);
        this.setOpaque(false);
        this.setLayout(new GridLayout(1, 1));
        this.repaint();
    }

    /**
     * 小球碰撞检测方法
     *
     * @param x
     * @param y
     * @return
     */
    public Pair<Double> collision(int x, int y) {

        //预判下一帧位置
        update(x, y);

        //遍历检测是否与其余碰撞体重叠
        for (Pair<Integer> pair : round) {
            if (GameSystem.shapeMap[pair.x / 40 + 1][pair.y / 40 + 1] != null) {
                ShapePanel shapePanel = GameSystem.shapeMap[pair.x / 40 + 1][pair.y / 40 + 1];
                return shapePanel.act(pair, new Pair<>(speedX, speedY));//碰撞则返回各自碰撞处理办法
            }
        }

        //若没有碰撞，回复地心引力，返回原速度
        GameSystem.gravity = 0.1;
        return new Pair<>(speedX, speedY);
    }

    /**
     * 小球碰撞处理方法（没有）
     *
     * @param position
     * @param speed
     * @return
     */
    @Override
    public Pair<Double> act(Pair<Integer> position, Pair<Double> speed) {
        return null;
    }

    /**
     * 是否能改变大小
     *
     * @return
     */
    @Override
    public boolean canSize() {
        return false;
    }

    /**
     * 是否能旋转
     *
     * @return
     */
    @Override
    public boolean canSpin() {
        return false;
    }
}
