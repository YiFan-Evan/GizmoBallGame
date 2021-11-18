//todo：直轨道类，组件之一，用于控制直轨道行为

/**
 * 直轨道类，组件之一，用于控制直轨道行为
 */
public class StraightWay extends Shape {

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public StraightWay(int x, int y) {
        super(x, y, "straight");
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

        //计算与水平和垂直边缘距离
        int vertical = Math.min(position.y - location.top, location.bottom - position.y);
        int horizontal = Math.min(location.right - position.x, position.x - location.left);

        //分类讨论轨道旋转情况
        switch (angle) {

            //原角度和180度
            case Zero, Half -> {

                //与左右碰撞，横向速度反向
                if (vertical > horizontal) {
                    if (speed.x > 0 && position.x - location.left < location.right - position.x)
                        return new Pair<>(-speed.x, speed.y);
                    else if (speed.x < 0 && position.x - location.left > location.right - position.x)
                        return new Pair<>(-speed.x, speed.y);
                }

                //与上下碰撞
                else {

                    //取消重力
                    GameSystem.gravity = 0;

                    //竖直速度保持
                    if (speed.y > 0 && location.bottom - position.y > position.y - location.top)
                        return new Pair<>(0.0, speed.y);
                    else if (speed.y < 0 && location.bottom - position.y < position.y - location.top)
                        return new Pair<>(0.0, speed.y);
                }
            }

            //90度和270度
            case Right, Reflex -> {

                //与左右碰撞
                if (vertical > horizontal) {

                    //取消重力
                    GameSystem.gravity = 0;

                    //水平速度保持
                    if (speed.x > 0 && position.x - location.left < location.right - position.x)
                        return new Pair<>(speed.x, 0.0);
                    else if (speed.x < 0 && position.x - location.left > location.right - position.x)
                        return new Pair<>(speed.x, 0.0);
                }

                //与上下碰撞，纵向速度反向
                else {
                    if (speed.y > 0 && location.bottom - position.y > position.y - location.top)
                        return new Pair<>(speed.x, -speed.y);
                    else if (speed.y < 0 && location.bottom - position.y < position.y - location.top)
                        return new Pair<>(speed.x, -speed.y);
                }
            }
        }

        //若没与轨道碰撞或速度不符合碰撞条件，返回原速度
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
        return true;
    }
}
