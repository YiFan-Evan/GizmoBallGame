import javax.swing.*;
import java.awt.*;

//todo：图片面板，用于加载带图片的界面面板

/**
 * 图片面板，用于加载带图片的界面面板
 */
public class PicPanel extends JPanel {

    //图片存储
    ImageIcon icon;
    Image img;

    /**
     * 得到图片的构造方法
     *
     * @param name
     */
    public PicPanel(String name) {
        icon = new ImageIcon(this.getClass().getResource(name));
        img = icon.getImage();
    }

    /**
     * 绘制方法
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
