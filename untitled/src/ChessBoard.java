import java.util.ArrayList;

//todo：游戏整个棋盘类

/**
 * 游戏整个棋盘类
 */
public class ChessBoard {

    public Shape[][] shapeMap;//游戏每个方格内形状组件的信息，格子索引从左上角【1，1】开始，（将0索引设为空气墙）
    public ArrayList<Shape> shapes;//所有在游戏内的组件
    public Pair<Integer> mousePoint;//鼠标指针在游戏框内的位置

    /**
     * 构造方法，初始化变量
     */
    public ChessBoard() {
        mousePoint = null;
        shapeMap = new Shape[22][22];
        shapes = new ArrayList<>();
    }

    /**
     * 清空游玩窗口
     */
    public void clear() {
        shapes.clear();
        shapeMap = new Shape[22][22];
        GameSystem.gui.window.removeAll();
        GameSystem.gui.window.repaint();
        for (int i = 0; i < 22; i++) {
            shapeMap[0][i] = new AirWall(0, i, 3);
            shapeMap[i][0] = new AirWall(0, i, 1);
            shapeMap[21][i] = new AirWall(0, i, 4);
            shapeMap[i][21] = new AirWall(0, i, 2);
        }
    }
}
