import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

//todo：游戏整个swing界面类

/**
 * 游戏整个swing界面类
 */
public class GUI implements AWTEventListener {

    //游戏界面框架类
    public JFrame frame;//整个界面
    public PicPanel window;//游玩区域
    public JPanel component;//组件区
    public JPanel tool;//工具区
    public JPanel mode;//模式区
    public JPanel bar;//右侧边栏
    public JPanel components;//组件集
    public JPanel tools;//工具集
    public JPanel modes;//模式集

    //文件与目录
    public JMenuBar menuBar;
    public JMenu fileMenu;

    //目录子项
    public JMenuItem openItem;
    public JMenuItem saveItem;
    public JMenuItem clearItem;
    public JMenuItem closeItem;

    //子项功能弹出窗
    private FileDialog openDia;
    private FileDialog saveDia;

    //游戏模式按钮
    public Button create_mode;
    public Button play_mode;

    //侧边栏说明文字
    public JTextArea text1;
    public JTextArea text2;
    public JTextArea text3;

    //游戏字体
    public Font f;

    //单选按钮集
    public JRadioButton radioButton1;
    public JRadioButton radioButton2;
    public JRadioButton radioButton3;
    public JRadioButton radioButton4;
    public JRadioButton radioButton5;
    public JRadioButton radioButton6;
    public JRadioButton radioButton7;
    public JRadioButton radioButton8;
    public JRadioButton radioButton9;
    public JRadioButton radioButton10;
    public ButtonGroup group;

    //每个组件
    public PicPanel hand;
    public PicPanel ball;
    public PicPanel hole;
    public PicPanel cube;
    public PicPanel diamond;
    public PicPanel triangle;
    public PicPanel left;
    public PicPanel right;
    public PicPanel straight;
    public PicPanel bend;

    //每个工具
    public PicPanel spin;
    public PicPanel delete;
    public PicPanel minus;
    public PicPanel plus;

    //游戏监听
    public Toolkit defaultToolkit;

    /**
     * 界面的初始化
     */
    public void init() {

        //todo：初始化界面框架
        {
            frame = new JFrame();
            frame.setBounds(600, 200, 1000, 820);
            frame.setBackground(Color.GRAY);
            frame.setLayout(null);
            frame.setResizable(false);
            window = new PicPanel("/pic/table.png");
            window.setBackground(Color.GRAY);
            window.setBounds(0, 0, 800, 800);
            window.setLayout(null);
            frame.add(window);
            component = new JPanel(null);
            tool = new JPanel(null);
            mode = new JPanel(null);
            bar = new JPanel();
            bar.setLayout(null);
            bar.setBounds(800, 0, 200, 820);
            bar.add(component);
            bar.add(tool);
            bar.add(mode);
            component.setBackground(Color.GRAY);
            component.setBounds(0, 0, 200, 370);
            tool.setBackground(Color.GRAY);
            tool.setBounds(0, 370, 200, 150);
            mode.setBackground(Color.GRAY);
            mode.setBounds(0, 520, 200, 300);
            components = new JPanel(new GridLayout(5, 4, 0, 24));
            tools = new JPanel(new GridLayout(2, 2));
            modes = new JPanel(new GridLayout(3, 1));
            components.setBackground(Color.WHITE);
            components.setBounds(5, 25, 175, 345);
            tools.setBackground(Color.WHITE);
            tools.setBounds(5, 25, 175, 125);
            modes.setBackground(Color.WHITE);
            modes.setBounds(5, 25, 175, 210);
            frame.add(bar);
        }

        //todo：初始化文件导航栏以及功能
        {
            menuBar = new JMenuBar();
            fileMenu = new JMenu("文件");
            openItem = new JMenuItem("打开");// 创建“打开"菜单项
            saveItem = new JMenuItem("保存");// 创建“保存"菜单项
            clearItem = new JMenuItem("清空");// 创建“清空"菜单项
            closeItem = new JMenuItem("退出");// 创建“退出"菜单项
            openDia = new FileDialog(frame, "打开", FileDialog.LOAD);
            saveDia = new FileDialog(frame, "保存", FileDialog.SAVE);
            fileMenu.add(openItem);// 将“打开”菜单项添加到“文件”菜单上
            fileMenu.add(saveItem);// 将“保存”菜单项添加到“文件”菜单上
            fileMenu.add(clearItem);// 将“清空”菜单项添加到“文件”菜单上
            fileMenu.add(closeItem);// 将“退出”菜单项添加到“文件”菜单上
            menuBar.add(fileMenu);
            frame.setJMenuBar(menuBar);
            saveItem.addActionListener(e -> new Thread(new SaveFile()).start());
            openItem.addActionListener(e -> new Thread(new OpenFile()).start());
            clearItem.addActionListener(e -> clear());
            closeItem.addActionListener(e -> System.exit(-1));
        }

        //todo：初始化侧边栏文字
        {
            text1 = new JTextArea();
            text1.setText("组件栏");
            text1.setEditable(false);
            f = new Font("Microsoft YaHei", Font.BOLD, 16);
            text1.setFont(f);
            text1.setForeground(Color.WHITE);
            text1.setBackground(Color.GRAY);
            text1.setBounds(70, 0, 200, 25);
            component.add(text1);
            text2 = new JTextArea();
            text2.setBounds(70, 0, 200, 25);
            text2.setText("工具栏");
            text2.setEditable(false);
            text2.setFont(f);
            text2.setForeground(Color.WHITE);
            text2.setBackground(Color.GRAY);
            tool.add(text2);
            text3 = new JTextArea();
            text3.setBounds(70, 0, 200, 25);
            text3.setText("模式栏");
            text3.setEditable(false);
            text3.setFont(f);
            text3.setForeground(Color.WHITE);
            text3.setBackground(Color.GRAY);
            mode.add(text3);
        }

        //todo：将图片区域添加到各个区
        {
            component.add(components);
            tool.add(tools);
            mode.add(modes);
        }

        //todo：加入各个工具图片到工具区
        {
            spin = new PicPanel("pic/spin.jpg");
            tools.add(spin);
            delete = new PicPanel("pic/delete.jpg");
            tools.add(delete);
            plus = new PicPanel("pic/add.jpg");
            tools.add(plus);
            minus = new PicPanel("pic/remove.jpg");
            tools.add(minus);
        }

        //todo：加入各个按钮和logo到模式区
        {
            create_mode = new Button("create mode");
            create_mode.setFont(f);
            play_mode = new Button("play mode");
            play_mode.setFont(f);
            modes.add(create_mode);
            modes.add(play_mode);
            modes.add(new PicPanel("pic/logo.jpg"));
        }

        //todo：加入单选按钮到组件区
        {
            radioButton1 = new JRadioButton();
            radioButton1.setBackground(Color.WHITE);
            radioButton2 = new JRadioButton();
            radioButton2.setBackground(Color.WHITE);
            radioButton3 = new JRadioButton();
            radioButton3.setBackground(Color.WHITE);
            radioButton4 = new JRadioButton();
            radioButton4.setBackground(Color.WHITE);
            radioButton5 = new JRadioButton();
            radioButton5.setBackground(Color.WHITE);
            radioButton6 = new JRadioButton();
            radioButton6.setBackground(Color.WHITE);
            radioButton7 = new JRadioButton();
            radioButton7.setBackground(Color.WHITE);
            radioButton8 = new JRadioButton();
            radioButton8.setBackground(Color.WHITE);
            radioButton9 = new JRadioButton();
            radioButton9.setBackground(Color.WHITE);
            radioButton10 = new JRadioButton();
            radioButton10.setBackground(Color.WHITE);
            group = new ButtonGroup();
            group.add(radioButton1);
            group.add(radioButton2);
            group.add(radioButton3);
            group.add(radioButton4);
            group.add(radioButton5);
            group.add(radioButton6);
            group.add(radioButton7);
            group.add(radioButton8);
            group.add(radioButton9);
            group.add(radioButton10);
            components.add(radioButton1);
        }

        //todo：加入各个组件图片到组件区
        {
            hand = new PicPanel("pic/hand.jpg");
            hand.setBackground(Color.WHITE);
            components.add(hand);
            components.add(radioButton2);
            ball = new PicPanel("pic/ball.jpg");
            ball.setBackground(Color.WHITE);
            components.add(ball);
            components.add(radioButton3);
            hole = new PicPanel("pic/hole.jpg");
            hole.setBackground(Color.WHITE);
            components.add(hole);
            components.add(radioButton4);
            triangle = new PicPanel("pic/triangle.jpg");
            triangle.setBackground(Color.WHITE);
            components.add(triangle);
            components.add(radioButton5);
            diamond = new PicPanel("pic/diamond.jpg");
            diamond.setBackground(Color.WHITE);
            components.add(diamond);
            components.add(radioButton6);
            cube = new PicPanel("pic/cube.jpg");
            cube.setBackground(Color.WHITE);
            components.add(cube);
            components.add(radioButton7);
            straight = new PicPanel("pic/straight.jpg");
            straight.setBackground(Color.WHITE);
            components.add(straight);
            components.add(radioButton8);
            bend = new PicPanel("pic/bend.jpg");
            bend.setBackground(Color.WHITE);
            components.add(bend);
            components.add(radioButton9);
            left = new PicPanel("pic/left.jpg");
            left.setBackground(Color.WHITE);
            components.add(left);
            components.add(radioButton10);
            right = new PicPanel("pic/right.jpg");
            right.setBackground(Color.WHITE);
            components.add(right);
        }

        //todo：在游玩界面添加鼠标监听用来放入组件
        {
            window.addMouseListener(new AddComponent());
        }

        //todo：在每个工具添加鼠标监听用来对游戏组件进行操作
        {
            plus.addMouseListener(new ChangeComponent(1));
            minus.addMouseListener(new ChangeComponent(2));
            delete.addMouseListener(new ChangeComponent(3));
            spin.addMouseListener(new ChangeComponent(4));
        }

        //todo：在每个文件功能添加鼠标监听用来对游戏组件进行操作
        {
            play_mode.addActionListener(e -> new Thread(GameSystem::start).start());
            create_mode.addActionListener(e -> GameSystem.isPlay = false);
            defaultToolkit = Toolkit.getDefaultToolkit();
        }

        //todo：将界面可见
        {
            frame.setVisible(true);
        }
    }

    /**
     * 清空游玩窗口
     */
    public void clear() {
        GameSystem.shapes.clear();
        GameSystem.shapeMap = new Shape[22][22];
        window.removeAll();
        window.repaint();
        for (int i = 0; i < 22; i++) {
            GameSystem.shapeMap[0][i] = new AirWall(0, i, 3);
            GameSystem.shapeMap[i][0] = new AirWall(0, i, 1);
            GameSystem.shapeMap[21][i] = new AirWall(0, i, 4);
            GameSystem.shapeMap[i][21] = new AirWall(0, i, 2);
        }
    }

    /**
     * 将监听事件变为每个键盘的监听事件（平台移动）
     *
     * @param event
     */
    @Override
    public void eventDispatched(AWTEvent event) {
        KeyEvent e = (KeyEvent) event;
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_A -> new Thread(new Move(-1, true)).start();
                case KeyEvent.VK_D -> new Thread(new Move(1, true)).start();
                case KeyEvent.VK_LEFT -> new Thread(new Move(-1, false)).start();
                case KeyEvent.VK_RIGHT -> new Thread(new Move(1, false)).start();
            }
        }
    }

    /**
     * 对平台进行移动的调用
     */
    public class Move implements Runnable {

        //平台和移动的具体信息
        public String name;
        public int e;

        /**
         * 初始化平台移动信息
         *
         * @param towards
         * @param isLeft
         */
        public Move(int towards, boolean isLeft) {
            this.e = towards;
            GameSystem.towards = e;
            this.name = isLeft ? "left" : "right";
        }

        /**
         * 遍历数组，让相应的平台移动，但多线程不安全
         */
        @Override
        public void run() {

            //正序遍历
            if (e < 0) {
                GameSystem.shapes.forEach(shapePanel -> {
                    if (shapePanel.name.equals(name)) {
                        if (GameSystem.shapeMap[shapePanel.location.x + e][shapePanel.location.y] == null) {
                            shapePanel.move();//调用平台的移动方法
                        }
                    }
                });
            }

            //倒序遍历
            else {
                for (int i = GameSystem.shapes.size() - 1; i >= 0; i--) {
                    Shape shapePanel = GameSystem.shapes.get(i);
                    if (shapePanel.name.equals(name)) {
                        if (GameSystem.shapeMap[shapePanel.location.x + e][shapePanel.location.y] == null) {
                            shapePanel.move();//调用平台的移动方法
                        }
                    }
                }
            }
            window.repaint();
        }
    }

    /**
     * 返回当前单选按钮的选择项
     *
     * @return
     */
    public int selectedWhich() {
        if (group.isSelected(radioButton1.getModel()))
            return 1;
        if (group.isSelected(radioButton2.getModel()))
            return 2;
        if (group.isSelected(radioButton3.getModel()))
            return 3;
        if (group.isSelected(radioButton4.getModel()))
            return 4;
        if (group.isSelected(radioButton5.getModel()))
            return 5;
        if (group.isSelected(radioButton6.getModel()))
            return 6;
        if (group.isSelected(radioButton7.getModel()))
            return 7;
        if (group.isSelected(radioButton8.getModel()))
            return 8;
        if (group.isSelected(radioButton9.getModel()))
            return 9;
        if (group.isSelected(radioButton10.getModel()))
            return 10;
        return 0;
    }

    /**
     * 用来转发改变组件的形状的类
     */
    public class ChangeComponent implements MouseListener {

        //组件需要改变方式
        int condition;

        /**
         * 初始化
         *
         * @param num
         */
        public ChangeComponent(int num) {
            condition = num;
        }

        /**
         * 转发鼠标监听，根据点击的工具内容完成相应功能（增大删除减小旋转）
         *
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (GameSystem.play != null)
                return;
            if (GameSystem.mousePoint != null && selectedWhich() == 1)
                switch (condition) {
                    case 1 -> new Thread(new PlusComponent()).start();
                    case 2 -> new Thread(new MinusComponent()).start();
                    case 3 -> new Thread(new DeleteComponent()).start();
                    case 4 -> new Thread(new SpinComponent()).start();
                }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    /**
     * 用来增大组件的形状的类
     */
    public class PlusComponent implements Runnable {

        //形状对象
        Shape shape;

        /**
         * 增大形状方法
         */
        @Override
        public void run() {
            try {

                //找到点的形状
                shape = GameSystem.getShape();

                //非空判断
                if (shape == null)
                    return;

                //重设鼠标指针至形状上
                GameSystem.mousePoint = shape.location.point;

                //形状不能增大则返回
                if (!shape.canSize())
                    return;

                //判断下一行有没有其他东西，有则返回
                for (int i = 0; i < shape.size.x / GameSystem.cell; i++) {
                    if (GameSystem.shapeMap[shape.location.left / GameSystem.cell + 1 + i][shape.location.bottom / GameSystem.cell + 1] != null)
                        return;
                }

                //判断右一行有没有其他东西，有则返回
                for (int j = 0; j < shape.size.y / GameSystem.cell; j++) {
                    if (GameSystem.shapeMap[shape.location.right / GameSystem.cell + 1][shape.location.top / GameSystem.cell + 1 + j] != null)
                        return;
                }

                //判断右下角有没有其他东西，有则返回
                if (GameSystem.shapeMap[shape.location.right / GameSystem.cell + 1][shape.location.bottom / GameSystem.cell + 1] != null)
                    return;

                //添加下一行占位碰撞体
                for (int i = 0; i < shape.size.x / GameSystem.cell; i++) {
                    GameSystem.shapeMap[shape.location.left / GameSystem.cell + 1 + i][shape.location.bottom / GameSystem.cell + 1] = shape;
                }

                //添加右一行占位碰撞体
                for (int j = 0; j < shape.size.y / GameSystem.cell; j++) {
                    GameSystem.shapeMap[shape.location.right / GameSystem.cell + 1][shape.location.top / GameSystem.cell + 1 + j] = shape;
                }

                //添加右下角占位碰撞体
                GameSystem.shapeMap[shape.location.right / GameSystem.cell + 1][shape.location.bottom / GameSystem.cell + 1] = shape;

                //将形状改为加一个单位的大小
                shape.setSize(shape.size.x + GameSystem.cell, shape.size.y + GameSystem.cell);

                //如果超出界面则返回
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用来减小组件的形状的类
     */
    public class MinusComponent implements Runnable {

        //形状对象
        Shape shape;

        /**
         * 减小形状方法
         */
        @Override
        public void run() {

            //找到点的形状
            shape = GameSystem.getShape();

            //非空判断
            if (shape == null)
                return;

            //重设鼠标指针至形状上
            GameSystem.mousePoint = shape.location.point;

            //形状不能减小则返回
            if (!shape.canSize())
                return;

            //形状为一个单位则返回
            if (shape.size.x <= GameSystem.cell || shape.size.y <= GameSystem.cell)
                return;

            //去除底部一行占位碰撞体
            for (int i = 0; i < shape.size.x / GameSystem.cell - 1; i++) {
                GameSystem.shapeMap[shape.location.left / GameSystem.cell + 1 + i][shape.location.bottom / GameSystem.cell] = null;
            }

            //去除右边一行占位碰撞体
            for (int j = 0; j < shape.size.y / GameSystem.cell - 1; j++) {
                GameSystem.shapeMap[shape.location.right / GameSystem.cell][shape.location.top / GameSystem.cell + 1 + j] = null;
            }

            //去除右下角占位碰撞体
            GameSystem.shapeMap[shape.location.right / GameSystem.cell][shape.location.bottom / GameSystem.cell] = null;

            //将形状改为减一个单位的大小
            shape.setSize(shape.size.x - GameSystem.cell, shape.size.y - GameSystem.cell);
        }
    }

    /**
     * 用来删除组件的形状的类
     */
    public class DeleteComponent implements Runnable {

        //形状对象
        Shape shape;

        /**
         * 删除形状方法
         */
        @Override
        public void run() {

            //找到点的形状
            shape = GameSystem.getShape();

            //非空判断
            if (shape == null)
                return;

            //重设鼠标指针至形状上
            GameSystem.mousePoint = shape.location.point;

            //去除该形状的占位碰撞体
            for (int i = 0; i < shape.size.x / GameSystem.cell; i++) {
                for (int j = 0; j < shape.size.y / GameSystem.cell; j++) {
                    GameSystem.shapeMap[shape.location.x + i][shape.location.y + j] = null;
                }
            }

            //去除该形状在游戏数组中的存储
            GameSystem.shapes.remove(shape);

            //在游玩界面去除该形状
            window.remove(shape);

            //刷新界面
            window.repaint();

            //去除鼠标位置记录
            GameSystem.mousePoint = null;
        }
    }

    /**
     * 用来旋转组件的形状的类
     */
    public class SpinComponent implements Runnable {

        //形状对象
        Shape shape;

        /**
         * 旋转形状方法
         */
        @Override
        public void run() {

            //找到点的形状
            shape = GameSystem.getShape();

            //非空判断
            if (shape == null)
                return;

            //重设鼠标指针至形状上
            GameSystem.mousePoint = shape.location.point;

            //形状不能旋转则返回
            if (!shape.canSpin())
                return;

            //将形状改为旋转90度的状态
            shape.spin();
        }
    }

    /**
     * 用来转发添加组件的类
     */
    public class AddComponent implements MouseListener {

        /**
         * 转发鼠标监听，根据点击的组件以及界面的位置完成形状的添加
         *
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {

            //判断不在游玩模式
            if (GameSystem.play != null)
                return;

            //判断该位置没有其他物体（通过占位碰撞体）
            if (GameSystem.shapeMap[e.getX() / GameSystem.cell + 1][e.getY() / GameSystem.cell + 1] != null) {

                //判断是否选到手指
                if (selectedWhich() == 1)

                    //设置当前鼠标位置为点击窗格
                    GameSystem.mousePoint = new Pair<>(e.getX(), e.getY());

                //如果没选到手指则返回
                return;
            }

            //添加组件
            new Thread(new PutComponent(e.getX(), e.getY())).start();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    /**
     * 用来添加对应组件的类
     */
    public class PutComponent implements Runnable {

        //鼠标指针位置
        int x;
        int y;

        /**
         * 初始化，得到指针位置
         *
         * @param x
         * @param y
         */
        public PutComponent(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 添加组件的方法
         */
        @Override
        public void run() {

            //根据单选框得到要添加组件名
            switch (selectedWhich()) {

                //小球
                case 2 -> {
                    for (Shape shape : GameSystem.shapes) {
                        if (shape.name.equals("ball")) {
                            JOptionPane.showMessageDialog(frame, "无法放置第二个小球。", "错误", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }//如果已经有小球就不添加
                    Ball thePanel = new Ball(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel, 0);
                    window.repaint();
                }

                //黑洞
                case 3 -> {
                    Hole thePanel = new Hole(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel);
                    window.repaint();
                }

                //三角
                case 4 -> {
                    Triangle thePanel = new Triangle(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel);
                    window.repaint();
                }

                //钻石
                case 5 -> {
                    Circle thePanel = new Circle(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel);
                    window.repaint();
                }

                //立方石
                case 6 -> {
                    Cube thePanel = new Cube(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel);
                    window.repaint();
                }

                //直轨道
                case 7 -> {
                    StraightWay thePanel = new StraightWay(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel);
                    window.repaint();
                }

                //弯轨道
                case 8 -> {
                    BendWay thePanel = new BendWay(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel);
                    window.repaint();
                }

                //左平台
                case 9 -> {
                    LeftBoard thePanel = new LeftBoard(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel);
                    window.repaint();
                }

                //右平台
                case 10 -> {
                    RightBoard thePanel = new RightBoard(x / GameSystem.cell + 1, y / GameSystem.cell + 1);
                    GameSystem.shapeMap[x / GameSystem.cell + 1][y / GameSystem.cell + 1] = thePanel;
                    GameSystem.shapes.add(thePanel);
                    window.add(thePanel);
                    window.repaint();
                }
            }
        }
    }

    /**
     * 用来保存文件的类
     */
    public class SaveFile implements Runnable {

        /**
         * 保存文件的方法
         */
        @Override
        public void run() {

            //得到保存的文件位置
            File file;
            saveDia.setVisible(true);//显示保存文件对话框
            String dirpath = saveDia.getDirectory();//获取保存文件路径并保存到字符串中。
            String fileName = saveDia.getFile();////获取打保存文件名称并保存到字符串中

            if (dirpath == null || fileName == null)//判断路径和文件是否为空
                return;//空操作
            else
                file = new File(dirpath, fileName);//文件不为空，新建一个路径和名称

            //将游戏的占位碰撞体和游戏的组件数组序列化后写入文件
            try {
                FileOutputStream fs = new FileOutputStream(file);//写文件流
                ObjectOutputStream os = new ObjectOutputStream(fs);//写对象流
                os.writeObject(GameSystem.shapes);//写组件数组
                os.writeObject(GameSystem.shapeMap);//写地图信息
                os.close();

                //抛出IO异常
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 用来打开文件的类
     */
    public class OpenFile implements Runnable {

        /**
         * 打开文件的方法
         */
        @Override
        public void run() {

            //得到打开的文件位置
            openDia.setVisible(true);//显示打开文件对话框
            String dirpath = openDia.getDirectory();//获取打开文件路径并保存到字符串中。
            String fileName = openDia.getFile();//获取打开文件名称并保存到字符串中

            if (dirpath == null || fileName == null)//判断路径和文件是否为空
                return;

            File file = new File(dirpath, fileName);//创建新的路径和名称

            //将游戏的占位碰撞体和游戏的组件数组反序列化后生成对象
            try {

                //得到对象
                FileInputStream fi = new FileInputStream(file);//读文件流
                ObjectInputStream oi = new ObjectInputStream(fi);//读对象流
                Object o1 = oi.readObject();//得到第一个对象
                Object o2 = oi.readObject();//得到第二个对象

                //强转对象
                ArrayList<Shape> panels = (ArrayList<Shape>) o1;
                Shape[][] shapes = (Shape[][]) o2;
                oi.close();

                //将对象指向游戏
                GameSystem.shapes = panels;
                GameSystem.shapeMap = shapes;

                //回复窗体状态
                window.removeAll();
                panels.forEach((shape -> {
                    if (shape.name.equals("ball"))
                        window.add(shape, 0);
                    else
                        window.add(shape);
                }));//顺序添加每个组件，如果是球，则放在最上层
                window.repaint();


                //抛出IO异常、抛出文件路径找不到异常
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof StreamCorruptedException)
                    JOptionPane.showMessageDialog(frame, "此文件类型未知，应用无法读取", "错误", JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "系统找不到指定的文件。", "错误", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
