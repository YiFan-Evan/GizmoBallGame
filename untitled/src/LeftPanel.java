import java.awt.*;

//todo：左平台类，组件之一，用于控制左部平台行为

/**
 * 左平台类，组件之一，用于控制左部平台行为
 */
public class LeftPanel extends ShapePanel {

    //记录平台偏移量
    int shift;

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public LeftPanel(int x, int y) {
        super(x, y, "left");
        shift = 0;
    }

    /**
     * 平台移动需要的动作
     */
    public void move() {

        //记录偏移量
        shift -= GameSystem.towards;

        //改变平台位置
        GameSystem.shapeMap[location.x][location.y] = null;
        location = new Location(location.x + GameSystem.towards, location.y);
        size = new Pair<>(GameSystem.cell, GameSystem.cell);
        GameSystem.shapeMap[location.x][location.y] = this;
        this.setBounds(location.point.x, location.point.y, size.x, size.y);
        this.setOpaque(false);
        this.setLayout(new GridLayout(1, 1));
    }

    /**
     * 平台归位
     */
    public void home() {

        //改变平台位置
        GameSystem.shapeMap[location.x][location.y] = null;
        location = new Location(location.x + shift, location.y);
        size = new Pair<>(GameSystem.cell, GameSystem.cell);
        GameSystem.shapeMap[location.x][location.y] = this;
        this.setBounds(location.point.x, location.point.y, size.x, size.y);
        this.setOpaque(false);
        this.setLayout(new GridLayout(1, 1));
        shift = 0;
    }

    /**
     * 小球碰撞处理方法
     *
     * @param position
     * @param speed
     * @return
     */
    @Override
    public Pair<Double> act(Pair<Integer> position, Pair<Double> speed) {

        //计算碰撞点距平台位置
        int distance = position.y - location.top - 15;

        //在平台碰撞可能的情况下改变速度
        if (distance > 0) {
            if (speed.y > 0)
                return new Pair<>(speed.x, -speed.y);
        }

        //未在平台下则返回原速度
        return new Pair<>(speed.x, speed.y);
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
