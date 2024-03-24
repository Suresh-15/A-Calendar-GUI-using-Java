import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.time.*;

public class MyCalendar extends JFrame implements ActionListener{

    protected JTextField month,year;
    protected JTextField[] days = new JTextField[7];
    protected JButton[][] dates = new SquareButton[5][7];
    protected JButton prev,next,prevYear,nextYear;

    protected Font font1 = new Font("Lucida Calligraphy",Font.BOLD,13);
    protected Font font2 = new Font("DM Serif", Font.BOLD,35);
    protected Font font3 = new Font("Lucida Handwriting", Font.BOLD,13);
    protected Font font4 = new Font("Lucida Handwriting", Font.BOLD,16);
    protected Font font5 = new Font("Arial", Font.BOLD,10);
    Border whiteborder = BorderFactory.createLineBorder(Color.white,1);
    protected LocalDate currentDate = LocalDate.now();
    protected LocalDate startDate = currentDate.withDayOfMonth(1);

    /*
        private int r[] = {255,000,000,005,200,200,255};
        private int g[] = {000,255,000,200,005,200,120};
        private int b[] = {000,000,255,200,200,005,005};
    */

    public MyCalendar() {
		
        this.setTitle("Calender");
        this.setSize(765,640);	this.setLayout( null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        Toolkit t = Toolkit.getDefaultToolkit();
        this.setIconImage(t.getImage("Icon101.png"));

        for(int i=0;i<7;i++){
            days[i] = new JTextField();
            days[i].setBorder(whiteborder);
            days[i].setBackground(Color.decode("#073987"));
            days[i].setForeground(new Color(255,255,255));
        }
		
        for(int i=0;i<5;i++){
			for(int j=0;j<7;j++){
                dates[i][j] = new SquareButton();
                dates[i][j].setFont(font2);	
				dates[i][j].setEnabled(true);
                dates[i][j].addActionListener(this);
            }
		}
        
		for(int i=0; i<7; i++){
			days[i].setFont(font1);
            days[i].setEditable(false);	
            days[i].setHorizontalAlignment(JTextField.CENTER);
        }

        days[0].setText("Sunday");
        days[1].setText("Monday");
        days[2].setText("Tuesday");
        days[3].setText("Wednesday");
        days[4].setText("Thursday");
        days[5].setText("Friday");
        days[6].setText("Saturday");

        days[1].setBounds(151,70,89,30);
        days[0].setBounds( 60,70,89,30);
        days[2].setBounds(241,70,89,30);
        days[3].setBounds(331,70,89,30);
        days[4].setBounds(421,70,89,30);
        days[5].setBounds(511,70,89,30);
        days[6].setBounds(601,70,89,30);

        dates[0][0].setBounds( 60,110,90,90);
        dates[0][1].setBounds(150,110,90,90);
        dates[0][2].setBounds(240,110,90,90);
        dates[0][3].setBounds(330,110,90,90);
        dates[0][4].setBounds(420,110,90,90);
        dates[0][5].setBounds(510,110,90,90);
        dates[0][6].setBounds(600,110,90,90);

        dates[1][0].setBounds( 60,200,90,90);
        dates[1][1].setBounds(150,200,90,90);
        dates[1][2].setBounds(240,200,90,90);
        dates[1][3].setBounds(330,200,90,90);
        dates[1][4].setBounds(420,200,90,90);
        dates[1][5].setBounds(510,200,90,90);
        dates[1][6].setBounds(600,200,90,90);

        dates[2][0].setBounds( 60,290,90,90);
        dates[2][1].setBounds(150,290,90,90);
        dates[2][2].setBounds(240,290,90,90);
        dates[2][3].setBounds(330,290,90,90);
        dates[2][4].setBounds(420,290,90,90);
        dates[2][5].setBounds(510,290,90,90);
        dates[2][6].setBounds(600,290,90,90);

        dates[3][0].setBounds( 60,380,90,90);
        dates[3][1].setBounds(150,380,90,90);
        dates[3][2].setBounds(240,380,90,90);
        dates[3][3].setBounds(330,380,90,90);
        dates[3][4].setBounds(420,380,90,90);
        dates[3][5].setBounds(510,380,90,90);
        dates[3][6].setBounds(600,380,90,90);

        dates[4][0].setBounds( 60,470,90,90);
        dates[4][1].setBounds(150,470,90,90);
        dates[4][2].setBounds(240,470,90,90);
        dates[4][3].setBounds(330,470,90,90);
        dates[4][4].setBounds(420,470,90,90);
        dates[4][5].setBounds(510,470,90,90);
        dates[4][6].setBounds(600,470,90,90);

        for(int i=0; i<5; i++)
            for(int j=0; j<7; j++){
                dates[i][j].setForeground(new Color(255,255,255));
                dates[i][j].setBackground(Color.decode("#cdb380"));
            }

        for(int i=0;i<7;i++)
            this.add(days[i]);
        for(int i=0;i<5;i++)
            for(int j=0;j<7;j++)
                this.add(dates[i][j]);

        fillDates();	//To initially fill dates of the month.

        month = new JTextField(null);
        month.setEditable(false);	
		month.setFont(font3);
        month.setText(String.valueOf(currentDate.getMonth()));
        month.setHorizontalAlignment( JTextField.CENTER );
        month.setBackground(Color.decode("#052a65"));
        month.setForeground(new Color(255,255,255));
        month.setBounds(60,40,630,29);
        month.setBorder(whiteborder);    
		this.add(month);

        year = new JTextField(null);
        year.setEditable(false);	
		year.setFont(font4);
        year.setText(String.valueOf(currentDate.getYear()));
        year.setHorizontalAlignment( JTextField.CENTER );
        year.setBackground(Color.decode("#031c43"));
        year.setForeground(new Color(255,255,255));
        year.setBounds(101,10,548,29);
        year.setBorder(whiteborder);    
		this.add(year);

        selectToday();	//	Represent the current day with different background

        ImageIcon prevImage = new ImageIcon(t.getImage("Icon157.png"));
        ImageIcon nextImage = new ImageIcon(t.getImage("Icon158.png"));

        prev = new JButton();
        prev.setIcon(prevImage);
        prev.setContentAreaFilled(false);
        prev.setBorderPainted(false);
        prev.setBounds(0,300,50,75);
        prev.setFocusable(false);   
		prev.addActionListener(this);

        next = new JButton();
        next.setIcon(nextImage);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.setBounds(698,300,50,75);
        next.setFocusable(false);   
		next.addActionListener(this);

        prevYear = new JButton("<");
        prevYear.setBounds(60,10,40,29);
        prevYear.setFocusable(false);
        prevYear.addActionListener(this);
        prevYear.setFont(font5);    
		prevYear.setBorder(whiteborder);
        prevYear.setBackground(Color.decode("#031c43"));
        prevYear.setForeground(new Color(255,255,255));
        prevYear.setHorizontalAlignment(JTextField.CENTER);

        nextYear = new JButton(">");
        nextYear.setBounds(650,10,40,29);
        nextYear.setFocusable(false);
        nextYear.addActionListener(this);
        nextYear.setFont(font5);    
		nextYear.setBorder(whiteborder);
        nextYear.setBackground(Color.decode("#031c43"));
        nextYear.setForeground(new Color(255,255,255));
        nextYear.setHorizontalAlignment(JTextField.CENTER);

        this.add(prev);	this.add(next);	this.add(prevYear);	this.add(nextYear);
        this.getContentPane().setBackground(Color.lightGray);
        this.setVisible(true);
    }

    protected void fillFrame(){
        for(int i=0;i<5;i++)
            for(int j=0;j<7;j++){
				dates[i][j].setText("");
                dates[i][j].setFont(font2);	
                dates[i][j].setEnabled(true);
                dates[i][j].setForeground(new Color(255,255,255));
                dates[i][j].setBackground(Color.decode("#cdb380"));
            }
        month.setText(String.valueOf(startDate.getMonth()));
        year.setText(String.valueOf(startDate.getYear()));
    }
	
    protected void fillDates(){

        String dayOfWeek = String.valueOf(startDate.getDayOfWeek());
        //	To Know the position of current date in the calendar.
        int columnNo = getColumnNumber(dayOfWeek);   
		int start=1;
        for(int i=0;i<6;i++){
            for(int j=columnNo; j<7; j++){
                if(start>startDate.lengthOfMonth()){   break;  }
                if(i==5)
                    dates[0][j].setText("<html> <font color=#000000>"+start+++"</font> </html>");
                else
                    dates[i][j].setText("<html> <font color=#000000>"+start+++"</font> </html>");
            }
            columnNo = 0;
        }
        for(int i=0; i<5; i++){
			for(int j=0; j<7; j++){
				if(dates[i][j].getText().equals("")){
                    dates[i][j].setEnabled(false);
                    dates[i][j].setBackground(Color.lightGray);
                    dates[i][j].setForeground(Color.lightGray);
                }
			}
		}
    }
    
	protected void selectToday(){
        int dayOfMonth = currentDate.getDayOfMonth();
        String dayOfWeek = String.valueOf(currentDate.getDayOfWeek());
        int y = currentDate.getYear();	
		int m = currentDate.getMonthValue();
        int rowNo = getRowNumber(dayOfMonth);
        int columnNo = getColumnNumber(dayOfWeek);
        if((m==startDate.getMonthValue()) && (y==startDate.getYear())){
            dates[rowNo][columnNo].setForeground(Color.black);
            dates[rowNo][columnNo].setBackground(Color.decode("#e8ddcb"));
        }
    }
    
	protected int getRowNumber(int dayOfMonth){
        int rowNo;
        LocalDate firstDate = currentDate.withDayOfMonth(1);
        String firstDay = String.valueOf(firstDate.getDayOfWeek());
        int columnNo = getColumnNumber(firstDay);
        if(dayOfMonth <= (7-columnNo))
            rowNo = 0;
        else if(dayOfMonth > (7-columnNo) && dayOfMonth <= (14-columnNo))
            rowNo = 1;
        else if(dayOfMonth > (14-columnNo) && dayOfMonth <= (21-columnNo))
            rowNo = 2;
        else if(dayOfMonth > (21-columnNo) && dayOfMonth <= (28-columnNo))
            rowNo = 3;
        else if(dayOfMonth > (28-columnNo) && dayOfMonth <= (35-columnNo))
            rowNo = 4;
        else	
			rowNo = 5;
        return rowNo;
    }
    
	protected int getColumnNumber(String dayOfWeek){
        int columnNo=0;
        for(int i=0;i<7;i++){
            if(dayOfWeek.equalsIgnoreCase(days[i].getText()))
                columnNo = i;
        }
        return columnNo;
    }
    
	protected void goToPreviousMonth(){
        if(startDate.getMonthValue()==1)
            startDate = startDate.withDayOfMonth(1).withMonth(12).withYear(startDate.getYear()-1);
        else	
			startDate = startDate.withDayOfMonth(1).withMonth(startDate.getMonthValue()-1);
        fillFrame();	
		fillDates();	
		selectToday();
        this.setVisible(true);
    }
    
	protected void goToNextMonth(){
        if(startDate.getMonthValue()==12)
            startDate = startDate.withDayOfMonth(1).withMonth(1).withYear(startDate.getYear()+1);
        else	
			startDate = startDate.withDayOfMonth(1).withMonth(startDate.getMonthValue()+1);
        fillFrame();	
		fillDates();	
		selectToday();
        this.setVisible(true);
    }
    
	protected void gotoPreviousYear(){
        startDate=startDate.withYear(startDate.getYear()-1);
        fillFrame();    
		fillDates();    
		selectToday();
        this.setVisible(true);
    }
    
	protected void gotoNextYear(){
        startDate=startDate.withYear(startDate.getYear()+1);
        fillFrame();    
		fillDates();    
		selectToday();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==prev)
            goToPreviousMonth();
        if(ae.getSource()==next)
            goToNextMonth();
        if(ae.getSource()==prevYear)
            gotoPreviousYear();
        if(ae.getSource()==nextYear)
            gotoNextYear();
    }
    
	public static void main(String[] args){
        new MyCalendar();
    }
}