//todo：弯轨道类，组件之一，用于控制弯轨道动作

/**
 * 弯轨道类，组件之一，用于控制弯轨道动作
 */
public class BendPanel extends ShapePanel {

    //弯轨道的深入量
    int shift = 10;

    /**
     * 构造方法
     * @param x
     * @param y
     */
    public BendPanel(int x, int y) {
        super(x, y, "bend");
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

        //计算与两个边缘距离
        int vertical = Math.min(position.y - location.top, location.bottom - position.y);
        int horizontal = Math.min(location.right - position.x, position.x - location.left);

        //分类讨论弯轨道角度情况
        switch (angle) {

            //原角度
            case Zero -> {

                //与左右碰撞
                if (vertical > horizontal) {

                    //与左边碰撞，进入轨道
                    if (speed.x > 0 && position.x - location.left < location.right - position.x) {

                        //取消重力
                        GameSystem.gravity = 0;

                        //进入前速度保持
                        if (position.x < location.left + shift)
                            return new Pair<>(speed.x, 0.0);

                        //进入后改变方向
                        if (speed.x != 0) {
                            speed.y = speed.x;
                        }
                        return new Pair<>(0.0, speed.y);
                    }

                    //与右边碰撞，反弹
                    else if (speed.x < 0 && position.x - location.left > location.right - position.x)
                        return new Pair<>(-speed.x, speed.y);
                }

                //与上下碰撞
                else {

                    //与上边碰撞，反弹
                    if (speed.y > 0 && location.bottom - position.y > position.y - location.top)
                        return new Pair<>(speed.x, -speed.y);

                    //与下边碰撞，进入轨道
                    else if (speed.y < 0 && location.bottom - position.y < position.y - location.top) {

                        //取消重力
                        GameSystem.gravity = 0;

                        //进入前速度保持
                        if (position.y > location.bottom - shift) {
                            return new Pair<>(0.0, speed.y);
                        }

                        //进入后改变方向
                        if (speed.y != 0) {
                            speed.x = speed.y;
                        }
                        return new Pair<>(speed.x, 0.0);
                    }
                }
            }

            //旋转90度
            case Right -> {

                //与左右碰撞
                if (vertical > horizontal) {

                    //与左边碰撞，进入轨道
                    if (speed.x > 0 && position.x - location.left < location.right - position.x) {

                        //取消重力
                        GameSystem.gravity = 0;

                        //进入前速度保持
                        if (position.x < location.left + shift)
                            return new Pair<>(speed.x, 0.0);

                        //进入后改变方向
                        if (speed.x != 0) {
                            speed.y = -speed.x;
                        }
                        return new Pair<>(0.0, speed.y);

                        //与右边碰撞，反弹
                    } else if (speed.x < 0 && position.x - location.left > location.right - position.x)
                        return new Pair<>(-speed.x, speed.y);
                }

                //与上下碰撞
                else {

                    //与上边碰撞，进入轨道
                    if (speed.y > 0 && location.bottom - position.y > position.y - location.top) {

                        //取消重力
                        GameSystem.gravity = 0;

                        //进入前速度保持
                        if (position.y < location.top + shift) {
                            return new Pair<>(0.0, speed.y);
                        }

                        //进入后改变方向
                        if (speed.y != 0) {
                            speed.x = -speed.y;
                        }
                        return new Pair<>(speed.x, 0.0);
                    }

                    //与下边碰撞，反弹
                    else if (speed.y < 0 && location.bottom - position.y < position.y - location.top)
                        return new Pair<>(speed.x, -speed.y);
                }
            }

            //旋转180度
            case Half -> {

                //与左右碰撞
                if (vertical > horizontal) {

                    //与左边碰撞，反弹
                    if (speed.x > 0 && position.x - location.left < location.right - position.x)
                        return new Pair<>(speed.x, speed.y);

                    //与右边碰撞，进入轨道
                    else if (speed.x < 0 && position.x - location.left > location.right - position.x) {

                        //取消重力
                        GameSystem.gravity = 0;

                        //进入前速度保持
                        if (position.x > location.right - shift)
                            return new Pair<>(speed.x, 0.0);

                        //进入后改变方向
                        if (speed.x != 0) {
                            speed.y = speed.x;
                        }
                        return new Pair<>(0.0, speed.y);
                    }
                }

                //与上下碰撞
                else {

                    //与上边碰撞，进入轨道
                    if (speed.y > 0 && location.bottom - position.y > position.y - location.top) {

                        //取消重力
                        GameSystem.gravity = 0;

                        //进入前速度保持
                        if (position.y < location.top + shift) {
                            return new Pair<>(0.0, speed.y);
                        }

                        //进入后改变方向
                        if (speed.y != 0) {
                            speed.x = speed.y;
                        }
                        return new Pair<>(speed.x, 0.0);
                    }

                    //与下边碰撞，反弹
                    else if (speed.y < 0 && location.bottom - position.y < position.y - location.top)
                        return new Pair<>(speed.x, -speed.y);
                }
            }

            //旋转270度
            case Reflex -> {

                //与左右碰撞
                if (vertical > horizontal) {

                    //与左边碰撞，反弹
                    if (speed.x > 0 && position.x - location.left < location.right - position.x)
                        return new Pair<>(speed.x, speed.y);

                    //与右边碰撞，进入轨道
                    else if (speed.x < 0 && position.x - location.left > location.right - position.x) {

                        //取消重力
                        GameSystem.gravity = 0;

                        //进入前速度保持
                        if (position.x > location.right - shift)
                            return new Pair<>(speed.x, 0.0);

                        //进入后改变方向
                        if (speed.x != 0) {
                            speed.y = -speed.x;
                        }
                        return new Pair<>(0.0, speed.y);
                    }
                }

                //与上下碰撞
                else {

                    //与上边碰撞，反弹
                    if (speed.y > 0 && location.bottom - position.y > position.y - location.top)
                        return new Pair<>(speed.x, -speed.y);

                    //与下边碰撞，进入轨道
                    else if (speed.y < 0 && location.bottom - position.y < position.y - location.top) {

                        //取消重力
                        GameSystem.gravity = 0;

                        //进入前速度保持
                        if (position.y > location.bottom - shift) {
                            return new Pair<>(0.0, speed.y);
                        }

                        //进入后改变方向
                        if (speed.y != 0) {
                            speed.x = -speed.y;
                        }
                        return new Pair<>(speed.x, 0.0);
                    }
                }
            }
        }

        //若没与弯轨道碰撞或速度不符合碰撞条件，返回原速度
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
