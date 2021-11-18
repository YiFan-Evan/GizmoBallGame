//todo：黑洞类，组件之一，用于控制黑洞行为

/**
 * 黑洞类，组件之一，用于控制黑洞行为
 */
public class Hole extends Shape {

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public Hole(int x, int y) {
        super(x, y, "hole");
    }

    /**
     * 小球碰撞处理方法（结束游玩）
     *
     * @param position
     * @param speed
     * @return
     */
    @Override
    public Pair<Double> act(Pair<Integer> position, Pair<Double> speed) {
        GameSystem.isPlay = false;
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
