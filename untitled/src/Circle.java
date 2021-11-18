//todo：钻石类，组件之一，用于控制钻石行为

/**
 * 钻石类，组件之一，用于控制钻石行为
 */
public class Circle extends Shape {

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public Circle(int x, int y) {
        super(x, y, "diamond");
    }

    /**
     * 钻石的碰撞处理方法
     *
     * @param position
     * @param speed
     * @return
     */
    @Override
    public Pair<Double> act(Pair<Integer> position, Pair<Double> speed) {

        //判断碰撞点是否在圆内
        double distance = Math.pow(location.point.x + (double) size.x / 2 - position.x, 2) + Math.pow(location.point.y + (double) size.y / 2 - position.y, 2);

        //若在
        if (distance <= Math.pow(size.x, 2)) {
            double theta = Math.asin((double) (position.x - location.point.x - size.x / 2) / size.x);//计算碰撞点与圆心连线与垂直方向的角度
            boolean isBottom = location.point.y + size.y / 2 < position.y;//判断碰撞点是否在钻石底部
            theta = isBottom ? Math.PI - theta : theta;//解决碰撞点在钻石底部时角度区间的问题

            /*构造并接触碰撞可能的情况下碰撞后速度方程
             * -----------------------------------------------------------------------
             * | y * sin(theta) + x * cos(theta) = cos(theta) * x' + sin(theta) * y' |
             * | y * cos(theta) - x * sin(theta) = sin(theta) * x' - cos(theta) * y' |
             * -----------------------------------------------------------------------
             * */
            double a = Math.cos(theta);
            double b = Math.sin(theta);
            double c = speed.y * b + speed.x * a;
            double d = b;
            double e = -a;
            double f = speed.y * a - speed.x * b;
            if (f > 0) {
                double speedY = (f * a - d * c) / (a * e - d * b);
                double speedX = (c - b * speedY) / a;
                return new Pair<>(speedX, speedY);
            }
        }

        //若没与圆形碰撞或速度不符合碰撞条件，返回原速度
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
