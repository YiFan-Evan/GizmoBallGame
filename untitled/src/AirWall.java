//todo：空气墙类，组件之一，用于阻挡游戏界面的边界

/**
 * 空气墙类，组件之一，用于阻挡游戏界面的边界
 */
public class AirWall extends Shape {

    //空气墙的朝向（1在上面，2在下面，3在左面，4在右面）
    int towards;

    /**
     * 构造方法
     *
     * @param x
     * @param y
     * @param towards
     */
    public AirWall(int x, int y, int towards) {
        super(x, y, "wall");
        this.towards = towards;
    }

    /**
     * 空气墙的碰撞处理方法
     *
     * @param position
     * @param speed
     * @return
     */
    @Override
    public Pair<Double> act(Pair<Integer> position, Pair<Double> speed) {
        switch (towards) {

            //上面的空气墙
            case 1 -> {
                if (speed.y < 0)
                    return new Pair<>(speed.x, -speed.y);
            }

            //下面的空气墙
            case 2 -> {
                if (speed.y > 0)
                    return new Pair<>(speed.x, -speed.y);
            }

            //左边的空气墙
            case 3 -> {
                if (speed.x < 0)
                    return new Pair<>(-speed.x, speed.y);
            }

            //右边的空气墙
            case 4 -> {
                if (speed.x > 0)
                    return new Pair<>(-speed.x, speed.y);
            }
        }
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
