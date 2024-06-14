package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MovingBallFacesAWT2 {

    public static void main(String[] args) {
        new MovingBallFacesAWT2();
    }

    MovingBallFacesAWT2() {
        MovingInnerFFrame f = new MovingInnerFFrame();
        Thread th = new Thread(f);
        th.start();

        f.setSize(500, 500);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);

        int MAXTime = 20;
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("残り" + (MAXTime - i) + "秒です");
        }
        System.out.println("終了しました");
        f.setTh_enable(false);
        System.out.println("終了しました");
    }

    class MovingInnerFFrame extends Frame implements Runnable {

        int ballnum = 5;
        BallRim[] myBalls = new BallRim[ballnum];
        private boolean th_enable = true;

        public boolean isTh_enable() {
            return th_enable;
        }

        public void setTh_enable(boolean th_enable) {
            this.th_enable = th_enable;
        }

        private int th_counter = 0;
        int w = 500;
        int h = 500;

        public void run() {
            for (int i = 0; i < myBalls.length; i++) {
                myBalls[i] = new BallRim();
            }

            int timer = 0;
            while (th_enable) {
                try {
                    Thread.sleep(100);
                    th_counter++;
                    if (th_counter >= 500)
                        th_enable = false;
                } catch (InterruptedException e) {
                }

                for (int i = 0; i < myBalls.length; i++) {
                    myBalls[i].move();
                }

                repaint();
            }
        }

        public void paint(Graphics g) {
            for (int i = 0; i < myBalls.length; i++) {
                myBalls[i].draw(g);
            }
        }
    }

    class BallRim {
        FaceObj fobj;
        Random rdn;
        int w = 500;
        int h = 500;
        int x;
        int y;
        int radius;
        Color color = Color.RED;
        double xDir = -1;
        double yDir = 1;

        BallRim() {
            rdn = new Random();
            xDir = rdn.nextDouble() * 2 - 1;
            yDir = rdn.nextDouble() * 2 - 1;
            setPosition(rdn.nextInt(w), rdn.nextInt(h));
            setRadius(rdn.nextInt(30) + 30);

            Color c = new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255));
            setColor(c);

            int eyeSize = (rdn.nextInt(3) + 2) * 10; // 20, 30, 40のいずれか
            int expression = rdn.nextInt(3); // 0, 1, 2のいずれか
            int eyebrowShape = rdn.nextInt(3); // 0, 1, 2のいずれか

            fobj = new FaceObj(x, y, 2 * radius, 2 * radius, eyeSize, expression, eyebrowShape);
        }

        void setColor(Color c) {
            this.color = c;
        }

        void move() {
            if ((xDir > 0) && (x + this.radius * 2 >= w)) {
                xDir = -1 * xDir;
            }
            if ((xDir < 0) && (x <= 0)) {
                xDir = -1 * xDir;
            }

            if (xDir > 0) {
                x = x + 10;
            } else {
                x = x - 10;
            }

            if ((yDir > 0) && (y + this.radius * 2 >= h)) {
                yDir = -1 * yDir;
            }
            if ((yDir < 0) && (y <= 25)) {
                yDir = -1 * yDir;
            }

            if (yDir > 0) {
                y = y + 10;
            } else {
                y = y - 10;
            }
        }

        void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void setRadius(int r) {
            this.radius = r;
        }

        void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, 2 * radius, 2 * radius);
            fobj.setXY(x, y);
            fobj.setSize(2 * radius, 2 * radius);
            fobj.drawFace(g);
        }
    }

    class FaceObj {
        private int x;
        private int y;
        private int w;
        private int h;
        private int eyeSize;
        private int expression;
        private int eyebrowShape;

        FaceObj(int x, int y, int w, int h, int eyeSize, int expression, int eyebrowShape) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.eyeSize = eyeSize;
            this.expression = expression;
            this.eyebrowShape = eyebrowShape;
        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setSize(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public void drawFace(Graphics g) {
            // 顔の輪郭を描画
            g.setColor(Color.YELLOW); // 顔の色
            g.fillOval(x, y, w, h);
            g.setColor(Color.BLACK); // 輪郭とパーツの色
            g.drawOval(x, y, w, h);

            // 目を描画
        
g.fillOval(x + w / 4, y + h / 4, eyeSize / 2, eyeSize / 2);
g.fillOval(x + w * 3 / 5, y + h / 4, eyeSize / 2, eyeSize / 2);



            // 鼻を描画
            g.fillOval(x + w / 2 - 10, y + h / 2 - 10, 20, 20);

            // 口を描画
            switch (expression) {
                case 0: // normal
                    g.drawLine(x + w / 4, y + 3 * h / 4, x + 3 * w / 4, y + 3 * h / 4);
                    break;
                case 1: // happy
                    g.drawArc(x + w / 4, y + 3 * h / 4 - 10, w / 2, 20, 0, -180);
                    break;
                case 2: // sad
                    g.drawArc(x + w / 4, y + 3 * h / 4 - 10, w / 2, 20, 0, 180);
                    break;
            }

            // 眉毛を描画
switch (eyebrowShape) {
    case 0: // straight
        g.drawLine(x + w / 4, y + h / 4 - 10, x + w / 4 + 20, y + h / 4 - 10);
        g.drawLine(x + w * 3 / 5, y + h / 4 - 10, x + w * 3 / 5 + 20, y + h / 4 - 10);
        break;
    case 1: // angled
        g.drawLine(x + w / 4, y + h / 4 - 10, x + w / 4 + 30, y + h / 4 - 20);
        g.drawLine(x + w * 3 / 5, y + h / 4 - 20, x + w * 3 / 5 + 30, y + h / 4 - 10);
        break;
    case 2: // curved
        g.drawArc(x + w / 4 - 10, y + h / 4 - 20, 30, 20, 0, -180);
        g.drawArc(x + w * 3 / 5 - 10, y + h / 4 - 20, 30, 20, 0, -180);
        break;
}


            // 耳を描画
            g.setColor(Color.ORANGE);
            g.fillOval(x + w / 10, y + h / 8, w / 5, h / 5);
            g.fillOval(x + w * 7 / 10, y + h / 8, w / 5, h / 5);
        }
    }
}
