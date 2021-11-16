# gizmo

该项目实现了一个简单的弹球游戏，使用`java swing`界面工具库以及相关的事件监听器控制游戏的进行，通过每10微秒刷新一次界面实现游玩界面的动态演进过程。

![avatar](./untitled/src/logo.JPG)

## 游戏界面

![avatar](./untitled/img/panel.JPG)

### 导航栏

![avatar](./untitled/img/guide.JPG)

组件栏可控制以下功能

|  导航   | 功能  |
|  ----  | ----  |
| 打开  | 读取保存的文件 |
| 保存  | 将界面元素保存至文件 |
| 清空  | 清空界面 |
| 退出  | 关闭游戏 |

### 组件栏

![avatar](./untitled/img/component.JPG)

组件栏可控制以下功能

|  组件   | 功能  |
|  ----  | ----  |
| ![avatar](./untitled/src/hand.JPG)  | 获取对象，用于改变组件 |
| ![avatar](./untitled/src/ball.JPG)  | 游戏所用的弹球 |
| ![avatar](./untitled/src/hole.JPG)  | 弹球掉入黑洞则游戏结束 |
| ![avatar](./untitled/src/triangle.JPG)  | 三角形组件，碰撞会反弹 |
| ![avatar](./untitled/src/diamond.JPG)  | 圆形组件，碰撞会反弹 |
| ![avatar](./untitled/src/cube.JPG)  | 正方形组件，碰撞会反弹 |
| ![avatar](./untitled/src/straight.JPG)  | 直轨道，进入后会以不变的速度驶出 |
| ![avatar](./untitled/src/bend.JPG)  | 弯轨道，改变小球方向，但不改变速度大小 |
| ![avatar](./untitled/src/left.JPG)  | 左托盘，可以受到`A`和`D`键控制 |
| ![avatar](./untitled/src/right.JPG)  | 右托盘，可以受到`←`和`→`键控制 |

### 工具栏

![avatar](./untitled/img/tool.JPG)

工具栏可控制以下功能

|  组件   | 功能  |
|  ----  | ----  |
| ![avatar](./untitled/src/spin.JPG)  | 旋转组件 |
| ![avatar](./untitled/src/delete.JPG)  | 删除组件 |
| ![avatar](./untitled/src/add.JPG)  | 放大组件 |
| ![avatar](./untitled/src/remove.JPG)  | 缩小组件 |

### 模式栏

![avatar](./untitled/img/model.JPG)

模式栏可控制以下功能

|  组件   | 功能  |
|  ----  | ----  |
| create mode  | 进入设计模式 |
| play mode  | 进入游玩模式 |

### 游戏区域

![avatar](./untitled/img/area.JPG)

## 设计模式

### 添加组件

![avatar](./untitled/img/add%2000_00_00-00_00_30.gif)

### 改变组件

![avatar](./untitled/img/change%2000_00_00-00_00_30.gif)

### 读取清空与保存

![avatar](./untitled/img/file%2000_00_00-00_00_30.gif)

## 游玩模式

### 游戏开始

![avatar](./untitled/img/start%2000_00_00-00_00_30.gif)

### 游戏结束

![avatar](./untitled/img/end%2000_00_00-00_00_30.gif)
