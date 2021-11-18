//todo：三角形类，组件之一，用于控制三角形行为

/**
 * 三角形类，组件之一，用于控制三角形行为
 */
public class Triangle extends Shape {

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public Triangle(int x, int y) {
        super(x, y, "triangle");
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

        //分类讨论三角形角度情况
        switch (angle) {

            //原角度
            case Zero -> {

                //判断与三角形的距离
                int distance = location.size.x - Math.abs(position.x - location.left) - Math.abs(position.y - location.bottom);

                //三角形碰撞
                if (distance >= 0) {

                    //计算与两个边缘距离
                    int vertical = location.bottom - position.y;
                    int horizontal = position.x - location.left;

                    //与斜边碰撞
                    if (distance < vertical && distance < horizontal && ((speed.x > 0 && speed.y > 0 && speed.y > speed.x) ||
                            (speed.x < 0 && speed.y < 0 && speed.y > speed.x)
                            || (speed.x <= 0 && speed.y >= 0))) {
                        return new Pair<>(speed.y - GameSystem.gravity, speed.x);
                    }

                    //与竖直边碰撞
                    else if (vertical < distance && vertical < horizontal && speed.y < 0) {
                        return new Pair<>(speed.x, -speed.y);
                    }

                    //与水平边碰撞
                    else if (horizontal < distance && horizontal < vertical && speed.x > 0) {
                        return new Pair<>(-speed.x, speed.y);
                    }
                }
            }

            //旋转90度
            case Right -> {

                //判断与三角形的距离
                int distance = location.size.x - Math.abs(position.x - location.left) - Math.abs(position.y - location.top);

                //三角形碰撞
                if (distance >= 0) {

                    //计算与两个边缘距离
                    int vertical = position.y - location.top;
                    int horizontal = position.x - location.left;

                    //与斜边碰撞
                    if (distance < vertical && distance < horizontal && ((speed.x > 0 && speed.y < 0 && -speed.y > speed.x) ||
                            (speed.x < 0 && speed.y > 0 && speed.y < -speed.x)
                            || (speed.x <= 0 && speed.y <= 0))) {
                        return new Pair<>(-speed.y, -speed.x);
                    }

                    //与竖直边碰撞
                    else if (vertical < distance && vertical < horizontal && speed.y > 0) {
                        return new Pair<>(speed.x, -speed.y);
                    }

                    //与水平边碰撞
                    else if (horizontal < distance && horizontal < vertical && speed.x > 0) {
                        return new Pair<>(-speed.x, speed.y);
                    }
                }
            }

            //旋转180度
            case Half -> {

                //判断与三角形的距离
                int distance = location.size.x - Math.abs(position.x - location.right) - Math.abs(position.y - location.top);

                //三角形碰撞
                if (distance >= 0) {

                    //计算与两个边缘距离
                    int vertical = position.y - location.top;
                    int horizontal = location.right - position.x;

                    //与斜边碰撞
                    if (distance < vertical && distance < horizontal && ((speed.x > 0 && speed.y > 0 && speed.y < speed.x) ||
                            (speed.x < 0 && speed.y < 0 && speed.y < speed.x)
                            || (speed.x >= 0 && speed.y <= 0))) {
                        return new Pair<>(speed.y, speed.x);
                    }

                    //与竖直边碰撞
                    else if (vertical < distance && vertical < horizontal && speed.y > 0) {
                        return new Pair<>(speed.x, -(speed.y));
                    }

                    //与水平边碰撞
                    else if (horizontal < distance && horizontal < vertical && speed.x < 0) {
                        return new Pair<>(-speed.x, speed.y);
                    }
                }
            }

            //旋转270度
            case Reflex -> {

                //判断与三角形的距离
                int distance = location.size.x - Math.abs(position.x - location.right) - Math.abs(position.y - location.bottom);

                //三角形碰撞
                if (distance >= 0) {

                    //计算与两个边缘距离
                    int vertical = location.bottom - position.y;
                    int horizontal = location.right - position.x;

                    //与斜边碰撞
                    if (distance < vertical && distance < horizontal && ((speed.x < 0 && speed.y > 0 && speed.y > -speed.x) ||
                            (speed.x > 0 && speed.y < 0 && -speed.y < speed.x)
                            || (speed.x >= 0 && speed.y >= 0))) {
                        return new Pair<>(-speed.y - GameSystem.gravity, -speed.x);
                    }


                    //与竖直边碰撞
                    else if (vertical < distance && vertical < horizontal && speed.y < 0) {
                        return new Pair<>(speed.x, -speed.y);
                    }

                    //与水平边碰撞
                    else if (horizontal < distance && horizontal < vertical && speed.x < 0) {
                        return new Pair<>(-speed.x, speed.y);
                    }
                }
            }
        }

        //若没与三角形碰撞或速度不符合碰撞条件，返回原速度
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
        return true;
    }
}
