import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
public class SquareButton extends JButton {
    public SquareButton(){
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width,size.height);
        setPreferredSize(size);	setContentAreaFilled(false);
        setFocusPainted(false);	setFocusable(false);
    }

    public SquareButton(String label){
        super(label);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width,size.height);
        setPreferredSize(size);	setContentAreaFilled(false);
        setFocusPainted(false);	setFocusable(false);
    }

    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(getBackground());
        if(getModel().isArmed())
            g2D.setColor(new Color(255,238,3));
        if(getModel().isSelected())
            g2D.setColor(new Color(245,30,75));
        g2D.fillRect( 0, 0, getSize().width-1, getSize().height-1);

        int red,green,blue;
        red=getForeground().getRed();	green=getForeground().getGreen();	blue=getForeground().getBlue();
        if(red>200 && green>200 && blue>200)
            setText("<html><font color=#000000>"+getText()+"</font></html>");
        else
            setText("<html><font color=#ffffff>"+getText()+"</font></html>");

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(getForeground());
        if(getModel().isArmed())
            g2D.setColor(Color.white);
        g2D.drawRect( 0, 0, getSize().width-2, getSize().height-2);
    }

    Shape shape;
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

    public static void main(String[] args){
        JButton button = new SquareButton("Hi");
        button.setPreferredSize(new Dimension(60,60));
        button.setBackground(new Color(243,141,51));
        button.setForeground(new Color(255,163,81));
        button.setFont(new Font("Lucid Handwriting",Font.PLAIN,20));

        JFrame frame = new JFrame("Square Button");
        frame.setLayout(new FlowLayout());
        frame.setSize(200,200);	frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
