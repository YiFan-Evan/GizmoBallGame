import java.io.Serial;
import java.io.Serializable;

//todo：记录组件位置的类

/**
 * 记录组件位置的类
 */
public class Location implements Serializable {

    //序列化编号
    @Serial
    private static final long serialVersionUID = -5L;

    //组件的各个参数
    public int x;
    public int y;
    public Pair<Integer> point;
    public int left;
    public int right;
    public int top;
    public int bottom;
    public Pair<Integer> size;

    /**
     * 设置组件初始位置
     *
     * @param x
     * @param y
     */
    public Location(int x, int y) {
        size = new Pair<>(GameSystem.cell, GameSystem.cell);
        this.x = x;
        this.y = y;
        point = new Pair<>((x - 1) * GameSystem.cell, (y - 1) * GameSystem.cell);
        left = (x - 1) * GameSystem.cell;
        right = left + size.x;
        top = (y - 1) * GameSystem.cell;
        bottom = top + size.y;
    }

    /**
     * 改变组件大小是位置变化
     *
     * @param newSize
     */
    public void setSize(Pair<Integer> newSize) {
        this.size = newSize;
        left = (x - 1) * GameSystem.cell;
        right = left + size.x;
        top = (y - 1) * GameSystem.cell;
        bottom = top + size.y;
    }
}
