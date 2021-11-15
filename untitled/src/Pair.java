import java.io.Serial;
import java.io.Serializable;

//todo：辅助类，用于存储坐标键值对

/**
 * 辅助类，用于存储坐标键值对
 *
 * @param <T>
 */
public class Pair<T> implements Serializable {

    //序列化编号
    @Serial
    private static final long serialVersionUID = -4L;

    //存储的数据
    public T x;
    public T y;

    /**
     * 构造方法
     *
     * @param x
     * @param y
     */
    public Pair(T x, T y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 显示方法
     *
     * @return
     */
    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
