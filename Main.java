import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
//    int num=750;
//    int LE[] = new int[num];
//    int RE[] = new int[num];
    public Main() {
        DemoScanline demoScanline = new DemoScanline();
        add(demoScanline); // Add DemoScanline to the content pane
        setSize(700, 700);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

class DemoScanline extends JPanel {
    int num=750;
    int LE[] = new int[num];
    int RE[] = new int[num];
    public DemoScanline() {
        setSize(700, 700);
        setLayout(new FlowLayout()); // Use FlowLayout for automatic positioning
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call super's paint to handle background
        g.setColor(Color.BLACK); // Set drawing color
        fill(g);
        fillt(g);
    }

    void fillt(Graphics g){
        int x1 = 50, y1 = 150,
                x2 = 150, y2 = 50,
                x3 = 250, y3 = 150;

        DDALINE(g, x1, y1, x2, y2);
        DDALINE(g, x2, y2, x3, y3);
        DDALINE(g, x3, y3, x1, y1);

        for (int i = 0; i < num ; i++) {
            LE[i] = num;
            RE[i] = 0;
        }


        fillscanline(x1,y1,x2,y2);
        fillscanline(x2,y2,x3,y3);
        fillscanline(x3, y3, x1, y1);


        for (int y=0; y<num  ; y++) {
            for (int x=LE[y]; x<RE[y] ; x++) {
                g.fillOval(x,y,2,2);
            }
        }

    }

    void fill(Graphics g) {
        int tempadd = 75;
        int x1 = 50+tempadd, y1 = 150+tempadd,
                x2 = 100+tempadd, y2 = 150+tempadd,
                x3 = 100+tempadd, y3 = 50+tempadd,
                x4 = 50+tempadd, y4 = 50+tempadd;

        DDALINE(g, x1, y1, x2, y2);
        DDALINE(g, x2, y2, x3, y3);
        DDALINE(g, x3, y3, x4, y4);
        DDALINE(g, x4, y4, x1, y1);

        for (int i = 0; i < num ; i++) {
            LE[i] = num;
            RE[i] = 0;
        }

        fillscanline(x1,y1,x2,y2);
        fillscanline(x2,y2,x3,y3);
        fillscanline(x3, y3, x4, y4);
        fillscanline( x4, y4, x1, y1);

        for (int y=0; y<num  ; y++) {
            for (int x=LE[y]; x<RE[y] ; x++) {
                g.fillOval(x,y,2,2);
            }
        }
    }
    void fillscanline(int x1,int y1,int x2,int y2){
        float x,M;
        int t;
        if(y1>y2){
            t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }
       if(y2-y1==0){
           M=(x2-x1);
       }else{
           M= (float) (x2-x1) / (float) (y2-y1);
       }
       x=x1;
        for (int y=y1; y<y2 ; y++) {
            if(x < LE[y]){
                LE[y] = (int)x;
            }
            if(x>RE[y]){
                RE[y] = (int)x;
            }
            x+=M;
        }
    }

    void DDALINE(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        double steps = Math.max(Math.abs(dx), Math.abs(dy));

        // Calculate step sizes
        double xStep = dx / steps;
        double yStep = dy / steps;

        double x = x1;
        double y = y1;

        // Draw each point using integer casting
        for (int i = 0; i < steps; i++) {
            g.drawLine((int) Math.round(x), (int) Math.round(y), (int) Math.round(x), (int) Math.round(y));
            x += xStep;
            y += yStep;
        }
    }
}
