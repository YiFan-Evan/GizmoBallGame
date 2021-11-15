//todo：立方石类，组件之一，用于控制立方石行为

/**
 * 立方石类，组件之一，用于控制立方石行为
 */
public class CubePanel extends ShapePanel {

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public CubePanel(int x, int y) {
        super(x, y, "cube");
    }

    /**
     * 立方石的碰撞处理方法
     *
     * @param position
     * @param speed
     * @return
     */
    @Override
    public Pair<Double> act(Pair<Integer> position, Pair<Double> speed) {

        //得到碰撞点距离水平和垂直方向的边界的距离
        int vertical = Math.min(position.y - location.top, location.bottom - position.y);
        int horizontal = Math.min(location.right - position.x, position.x - location.left);

        //如果碰撞点在立方石的竖直方向上，则立方石y轴速度取反
        if (vertical > horizontal) {
            if (speed.x > 0 && position.x - location.left < location.right - position.x)
                return new Pair<>(-speed.x, speed.y);
            else if (speed.x < 0 && position.x - location.left > location.right - position.x)
                return new Pair<>(-speed.x, speed.y);
        }

        //如果碰撞点在立方石的水平方向上，则立方石x轴速度取反
        else {
            if (speed.y > 0 && location.bottom - position.y > position.y - location.top)
                return new Pair<>(speed.x, -speed.y);
            else if (speed.y < 0 && location.bottom - position.y < position.y - location.top)
                return new Pair<>(speed.x, -speed.y);
        }

        //若速度不符合碰撞条件，返回原速度
        return new Pair<>(speed.x, speed.y);
    }

    /**
     * 是否能改变大小
     *
     * @return
     */
    @Override
    public boolean canSize() {
        return true;
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
