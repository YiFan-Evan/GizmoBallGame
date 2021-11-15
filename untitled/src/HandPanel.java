//todo：手指类，组件之一，用于控制手指行为

/**
 * 手指类，组件之一，用于控制手指行为
 */
public class HandPanel extends ShapePanel {

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public HandPanel(int x, int y) {
        super(x, y, "hand");
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
