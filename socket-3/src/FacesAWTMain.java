import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FacesAWTMain {

    public static void main(String[] args) {
        new FacesAWTMain();
    }

    FacesAWTMain() {
        FaceFrame f = new FaceFrame();
        f.setSize(800, 800);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    class FaceFrame extends Frame {
        private FaceObj[] fobjs;

        FaceFrame() {
            fobjs = new FaceObj[9];
            int w = 200;
            int h = 200;

            // すべての組み合わせをリストに保存しシャッフルする
            List<int[]> combinations = new ArrayList<>();
            for (int expression = 0; expression < 3; expression++) {
                for (int eyeSize = 20; eyeSize <= 40; eyeSize += 10) {
                    for (int eyebrowShape = 0; eyebrowShape < 3; eyebrowShape++) {
                        combinations.add(new int[]{eyeSize, expression, eyebrowShape});
                    }
                }
            }
            Collections.shuffle(combinations);

            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    int[] combination = combinations.remove(0);
                    fobjs[i + 3 * j] = new FaceObj(200 * i + 50, 200 * j + 50, w, h, combination[0], combination[1], combination[2]);
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            for (FaceObj fobj : fobjs) {
                fobj.drawFace(g);
            }
        }
    }

    private class FaceObj {
        private int x;
        private int y;
        private int w;
        private int h;
        private int eyeSize;
        private int expression; // 0: normal, 1: happy, 2: sad
        private int eyebrowShape; // 0: straight, 1: angled, 2: curved

        FaceObj(int x, int y, int w, int h, int eyeSize, int expression, int eyebrowShape) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.eyeSize = eyeSize;
            this.expression = expression;
            this.eyebrowShape = eyebrowShape;
        }

        public void drawFace(Graphics g) {
            // 顔の輪郭を描画
            g.setColor(Color.YELLOW); // 顔の色
            g.fillOval(x, y, w, h);
            g.setColor(Color.BLACK); // 輪郭とパーツの色
            g.drawOval(x, y, w, h);

            // 目を描画
            g.fillOval(x + 45, y + 65, eyeSize, eyeSize);
            g.fillOval(x + 120, y + 65, eyeSize, eyeSize);

            // 鼻を描画
            g.fillOval(x + 90, y + 100, 20, 20);

            // 口を描画
            switch (expression) {
                case 0: // normal
                    g.drawLine(x + 70, y + 150, x + 130, y + 150);
                    break;
                case 1: // happy
                    g.drawArc(x + 70, y + 140, 60, 20, 0, -180);
                    break;
                case 2: // sad
                    g.drawArc(x + 70, y + 160, 60, 20, 0, 180);
                    break;
            }

            // 眉毛を描画
            switch (eyebrowShape) {
                case 0: // straight
                    g.drawLine(x + 50, y + 50, x + 80, y + 50);
                    g.drawLine(x + 120, y + 50, x + 150, y + 50);
                    break;
                case 1: // angled
                    g.drawLine(x + 50, y + 50, x + 70, y + 40);
                    g.drawLine(x + 130, y + 40, x + 150, y + 50);
                    break;
                case 2: // curved
                    g.drawArc(x + 45, y + 40, 40, 20, 0, -180);
                    g.drawArc(x + 115, y + 40, 40, 20, 0, -180);
                    break;
            }

            // 耳を描画
            g.setColor(Color.ORANGE);
            g.fillOval(x + 20, y + 20, 40, 60);
            g.fillOval(x + 140, y + 20, 40, 60);
        }
    }
}
