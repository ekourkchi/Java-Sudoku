import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.applet.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SudokuGUI extends JFrame implements  ActionListener, KeyListener, MouseListener {


JPanel toppanel = new JPanel(new FlowLayout());  
JPanel infopanel = new JPanel();
JPanel playpanel = new JPanel();
JPanel allkeys = new JPanel(new GridLayout(2, 1)); // two row at the bottom
JPanel keypanel = new JPanel();
JPanel blockopanel = new JPanel();
JPanel [] rowpanel = new JPanel[9];
JPanel butPanel = new JPanel();
JTextField [] cell = new JTextField[81];
JButton exit = new JButton("Exit");;
JButton clear = new JButton("Clear");
JButton solve = new JButton("Solve");
JButton play = new JButton("Easy");
JButton medium = new JButton("Medium");
JButton hard = new JButton("Hard");
JLabel emhStatus = new JLabel("");
JLabel emptylabel = new JLabel("");
JButton playAgame = new JButton("Play a game");
JButton tryAgain = new JButton("Play again");
JLabel infolabel = new JLabel("Ehsan Kourkchi (C) 2013");
JLabel timelabel = new JLabel("");


        // All menu controls
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenu menuNew = new JMenu("New Game");
        JMenuItem menueasy = new JMenuItem("Easy");
        JMenuItem menumedium = new JMenuItem("Medium");
        JMenuItem menuhard = new JMenuItem("Hard");
        JMenuItem menuTry = new JMenuItem("Play again");
        JMenuItem menuSolve = new JMenuItem("Solve");
        JMenuItem menuClear = new JMenuItem("Clear all");
        JMenuItem menuExit = new JMenuItem("Exit");

String title = new String("Ehsan Kourkchi (C) 2013");
boolean Newgame = false;
boolean PlayMode = false;


     int [] [] sudokuUser = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
                            
     int [] [] sudokuTmp = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
                            
          int delay = 1000; //milliseconds
          int time=0;
          ActionListener taskPerformer = new ActionListener() {
              public void actionPerformed(ActionEvent evt) {
              timelabel.setText("Elapsed Time: "+ calculateTime(++time));
              }
         };
         
         Timer myTimer = new Timer(delay, taskPerformer);
  
  
  
  
  
     /****************************************/
     /****************************************/
     /****************************************/
     /****************************************/
     /****************************************/
     /****************************************/     
     /****************************************/
     /****************************************/    
     /****************************************/
     /****************************************/ 
     /****************************************/
     /****************************************/
     /****************************************/
     /****************************************/     
     /****************************************/
     /****************************************/    
     /****************************************/
     /****************************************/ 
   /** constructor */
   
   public SudokuGUI() {
      myTimer.stop();
      int i=0, j=0, n;
    

      JPanel content = new JPanel();
      content.setLayout(new BorderLayout());


      
      
      keypanel.add(solve);
      keypanel.add(clear);
      keypanel.add(exit);
      keypanel.add(emhStatus);
      emhStatus.setForeground(Color.BLUE.darker().darker());

      
      butPanel = new JPanel();
      emhStatus.setPreferredSize(new Dimension(100, 25));
      emhStatus.setFont(new Font("SansSerif", Font.PLAIN, 24));
      butPanel.add(emhStatus);
      
      butPanel.add(keypanel);

      
      
      playpanel.add(playAgame);
      playpanel.add(play);
      playpanel.add(medium);
      playpanel.add(hard);
      playpanel.add(tryAgain);
      
      allkeys.add(playpanel);
      allkeys.add(butPanel);
      
   
      infopanel = new JPanel(new GridLayout(1, 2, 2, 2));
      
      infolabel.setPreferredSize(new Dimension(200, 25));
      timelabel.setPreferredSize(new Dimension(200,25));
      infopanel.add(infolabel);
      infopanel.add(timelabel);
      infolabel.setHorizontalAlignment(JLabel.CENTER);
      timelabel.setHorizontalAlignment(JLabel.CENTER);
      
      
      
      toppanel.add(infopanel);
      
      
      
      
      content.add(toppanel, BorderLayout.NORTH);
   
	blockopanel = new JPanel(new GridLayout(3, 3, 3, 3));
	for (i=0; i<9; i++) rowpanel[i] = new JPanel(new GridLayout(3, 3));
	for (i=0; i<81; i++) cell[i] = new JTextField(2);
	for (i=0; i<9; i++)  blockopanel.add(rowpanel[i]);
	
	Font font1 = new Font("SansSerif", Font.PLAIN, 24);
	
	for (n=0; n < 81; n++) {
	
		j = n%9;
		i = (n-j)/9; // row number
		
		cell[n].setHorizontalAlignment(JTextField.CENTER);
		cell[n].setFont(font1);
		cell[n].setForeground(new Color(0,0,204));
		cell[n].addKeyListener(this);
		cell[n].addActionListener(this);
		
		int p = 3*(i/3)+(j/3);
		rowpanel[p].add(cell[n]);
		
		if (p%2 == 0)
		  cell[n].setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(95,100,200)));
		else
		  cell[n].setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(150,100,37)));
		
		
		cell[n].setPreferredSize(new Dimension(40,40));
		//System.out.println(3*(i/3)+(j/3));
		//cell[n].setText(Integer.toString(3*(i/3)+(j/3)));
	}
    


	
	content.add(blockopanel, BorderLayout.CENTER);
	content.add(allkeys, BorderLayout.SOUTH);

	exit.addActionListener(this);
	clear.addActionListener(this);
	solve.addActionListener(this);
	play.addActionListener(this);
	medium.addActionListener(this);
	hard.addActionListener(this);
	playAgame.addActionListener(this);
	tryAgain.addActionListener(this);
	tryAgain.setEnabled(false);
	play.setEnabled(false);
	medium.setEnabled(false);
	hard.setEnabled(false);
	this.getContentPane().add(content);
	

        
        menuBar.add(menu);
        menuBar.add(menuNew);
        menuExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        menuNew.addActionListener(this);
        menuTry.addActionListener(this);
        menuSolve.addActionListener(this);
        menuClear.addActionListener(this);
        
        menueasy.addActionListener(this);
        menumedium.addActionListener(this);
        menuhard.addActionListener(this);
        
        menuNew.add(menueasy);
        menuNew.add(menumedium);
        menuNew.add(menuhard);
        menu.add(menuNew);
        menu.add(menuTry);
        menu.add(menuSolve);
        menu.add(menuClear);
        menu.add(menuExit);
        this.setJMenuBar(menuBar); 

     } /** The end of constructor */
     /****************************************/
     /****************************************/
     /****************************************/
     /****************************************/     
     /****************************************/
     /****************************************/    
     /****************************************/
     /****************************************/    
     
     
     
     
     
     
     /** This method change the initial position of the main frame on the screen*/
   
      private void setCenter() {
      
	// Get the size of the screen
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
	// Determine the new location of the window
	int w = this.getSize().width;
	int h = this.getSize().height;
	int x = (dim.width-w)*1/3;
	int y = (dim.height-h)*1/3;
    
	// Move the window
	this.setLocation(x, y);

      }

  /****************************************/
  /****************************************/   
  /** All the mouse events */
  
   public void mouseClicked(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mousePressed(MouseEvent e) {}

  /****************************************/
  /****************************************/      
   
   /** All the KeyEvents  */
   
   public void keyPressed(KeyEvent event) {}
   public void keyTyped(KeyEvent event) {}
   
   /** Method effective when a key is released  */
   public void keyReleased(KeyEvent event) {
      
      int n;
      int key = event.getKeyCode();
      int [] xy = new int[1000];
        for (int i = 0; i<1000; i++)  xy[i] = 100;
      
      
      int [] [] sudoku = {{1, 0, 0, 0, 0, 7, 0, 9, 0},
                          {0, 3, 0, 0, 2, 0, 0, 0, 8},
                          {0, 0, 9, 6, 0, 0, 5, 0, 0},
                          {0, 0, 5, 3, 0, 0, 9, 0, 0},
                          {0, 1, 0, 0, 8, 0, 0, 0, 2},
                          {6, 0, 0, 0, 0, 4, 0, 0, 0},
                          {3, 0, 0, 0, 0, 0, 0, 1, 0},
                          {0, 4, 0, 0, 0, 0, 0, 0, 7},
                          {0, 0, 7, 0, 0, 0, 3, 0, 0}};	     
                          
     for (n=0; n < 81; n++) {
     
       if (event.getSource() == cell[n]) {
           
           String outString = "";
           
           if (cell[n].getText().length() > 1)  {
           
             String s = cell[n].getText();
             outString += s.charAt(1);
             cell[n].setText(outString);
             outString = "" + s.charAt(0);
           
           }   
           
           if (cell[n].getText().equals("1"))       cell[n].setText("1");
           else if (cell[n].getText().equals("2"))  cell[n].setText("2");
           else if (cell[n].getText().equals("3"))  cell[n].setText("3");
           else if (cell[n].getText().equals("4"))  cell[n].setText("4");
           else if (cell[n].getText().equals("5"))  cell[n].setText("5");
           else if (cell[n].getText().equals("6"))  cell[n].setText("6");
           else if (cell[n].getText().equals("7"))  cell[n].setText("7");
           else if (cell[n].getText().equals("8"))  cell[n].setText("8");
           else if (cell[n].getText().equals("9"))  cell[n].setText("9");
           else cell[n].setText(outString);
           
           cell[n].setForeground(new Color(0,0,204));

           infolabel.setForeground(Color.BLUE.darker());
           infolabel.setText(title);  
           Newgame = false;
           playAgame.setEnabled(true);
           play.setEnabled(false);
	   medium.setEnabled(false);
	   hard.setEnabled(false);
       } // endif
     } // endfor
     

                          
        for (n=0; n < 81; n++) {
         
	  int j = n%9;        // Column number
	  int i = (n-j)/9;   // row number
  

	  if (!cell[n].getText().equals("")) {
	      sudoku[i][j] = Integer.parseInt(cell[n].getText());
	      cell[n].setForeground(new Color(0,0,204));
	  } else {
	      sudoku[i][j] = 0;
	      cell[n].setForeground(Color.BLACK);
	    }
         
     }
     
         if (!ExtSudoku.checkSudoku(sudoku, false, xy)) {
                
                int counter = 0;
                while (xy[counter] != 100 ) {
                    cell[9*xy[counter]+xy[counter+1]].setForeground(Color.RED);
                    counter+=2;
                    }
                
           
           
           
           infolabel.setText("Sudoku has illegal values");
           infolabel.setForeground(Color.RED);
              
              if (!PlayMode) solve.setEnabled(false);
           
         } else solve.setEnabled(true);
     
   } // method ends
   
  /****************************************/
  /****************************************/  
  
  /** Action Listener Events ... */

   public void actionPerformed(ActionEvent event) {
        
     
     /** play A game buttom */
     if (event.getSource() == playAgame) {
        play.setEnabled(true);
	medium.setEnabled(true);
	hard.setEnabled(true);
          
        playAgame.setEnabled(false);
        PlayMode = true;
        
        }
     
     /** Exit buttom */
     if (event.getSource() == exit)  
       System.exit(0);
       
     /** Clear buttom */
     if (event.getSource() == clear  || event.getSource() == menuClear)  {
     
        myTimer.stop();
        time = 0;
	  timelabel.setText("");
        
        for (int n=0; n < 81; n++) {
             cell[n].setText("");
             cell[n].setForeground(new Color(0,0,204));
             cell[n].setBackground(Color.WHITE);
             cell[n].setEnabled(true);

        }
     
        infolabel.setForeground(Color.BLACK);
        title = "Ehsan Kourkchi (C) 2013";
        infolabel.setText(title);
        PlayMode = false;
        solve.setEnabled(true);
        play.setEnabled(false);
	medium.setEnabled(false);
	hard.setEnabled(false);
	playAgame.setEnabled(true);
     }

     /** Solve buttom */
     if (event.getSource() == solve || event.getSource() == menuSolve) { 
     
     myTimer.stop();
     time = 0;
     
     int i,j;  // looping indices
     int [] xx = {-1,-1,-1,-1};
     // false soduku indices
     
     /* a hard sudoku known as AI Escargot */
     int [] [] sudoku = {{1, 0, 0, 0, 0, 7, 0, 9, 0},
                          {0, 3, 0, 0, 2, 0, 0, 0, 8},
                          {0, 0, 9, 6, 0, 0, 5, 0, 0},
                          {0, 0, 5, 3, 0, 0, 9, 0, 0},
                          {0, 1, 0, 0, 8, 0, 0, 0, 2},
                          {6, 0, 0, 0, 0, 4, 0, 0, 0},
                          {3, 0, 0, 0, 0, 0, 0, 1, 0},
                          {0, 4, 0, 0, 0, 0, 0, 0, 7},
                          {0, 0, 7, 0, 0, 0, 3, 0, 0}};
                          
     if (!PlayMode) {  
     
     for (int n=0; n < 81; n++) {
         
	  j = n%9;        // Column number
	  i = (n-j)/9;   // row number
	  
	  
	    if (!cell[n].getText().equals("")) {
	        sudoku[i][j] = Integer.parseInt(cell[n].getText());
	    } else {
	        sudoku[i][j] = 0;
	        cell[n].setForeground(Color.BLACK);
	      }
	 
         
     }
     
     if (ExtSudoku.checkSudoku(sudoku, false, xx)) {
     
	  // Calling the solving algorithm
	  if (Sudoku.fillSudoku (sudoku)) {
	     infolabel.setForeground(Color.GREEN.darker().darker());
             infolabel.setText("This is the solution");
             System.out.println ("\n\nSudoku Solution\n" + Sudoku.toString (sudoku, false));
           } else {
             infolabel.setText("No solution for this Soduku");
             infolabel.setForeground(Color.RED);
             }
     }
     else {
         //System.out.println(ii+ "  " +  jj+ "  " + mm + "  " + nn);
         cell[9*xx[0]+xx[1]].setForeground(Color.RED);
         cell[9*xx[2]+xx[3]].setForeground(Color.RED);
         infolabel.setText("Sudoku has illegal values");
         infolabel.setForeground(Color.RED);
     }
     
     } else {
              for(int row = 0; row < 9; row++) {
                 for(int col = 0; col < 9; col++) {
                    
                    sudoku[row][col] = sudokuTmp[row][col];   // sudokuTmp is the solution
                 }
              }
          System.out.println ("\n\nSudoku Solution\n" + Sudoku.toString (sudoku, false));    
        }
              
     
     
     
     for (int n=0; n < 81; n++) {
         
         j = n%9;
         i = (n-j)/9; // row number
         
         if (sudoku[i][j] != 0) {
           // setting the GUI values
           cell[n].setText(Integer.toString(sudoku[i][j]));
         } else {
           cell[n].setText("");
         }
         
     }

     if (!PlayMode) solve.setEnabled(false);
     
   }
   
   
  
   /** Play buttom */
   if (((event.getSource() == play || event.getSource() == medium || event.getSource() == hard) && Newgame == true) || 
          event.getSource() == menueasy || event.getSource() == menumedium || event.getSource() == menuhard ) {  
        
        // clear all
        for (int n=0; n < 81; n++) {
             cell[n].setText("");
             cell[n].setForeground(new Color(0,0,204));
             cell[n].setBackground(Color.WHITE);
             cell[n].setEnabled(true);
        }
        
        
        
        
        int [] [] sudoku = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        
                         
        //cell[0].setBackground(Color.GRAY);
        //cell[0].setEnabled(false);
        //cell[0].setText("4");
        
        do {
	      
	      for(int i=0; i<9; i++)
	      for(int j=0; j<0; j++) {
	          sudoku[i][j] = 0;
	          sudokuUser[i][j] = 0;
	      }
	      
	      
		
	      Random whichCell = new Random((long) (Math.random()*10000));
	      int min = 6;
	      int max = 7;
	      int noClearcells = min + whichCell.nextInt(max-min+1);
	      //System.out.println(noClearcells);
	      
	      
	      whichCell.setSeed((long) (Math.random()*10000));
	      
	      
	      //Random cellValueRand = new Random((long) (Math.random()*100000));
	      
	      int counter = 1;
	      while (counter < noClearcells+1) {
		
		int n;
		if (counter < 3) 
		     n = whichCell.nextInt(9);
		else
		     n = whichCell.nextInt(80);
		
		//System.out.println(n);
		
		int j = n%9;  // column number
		int i = (n-j)/9; // row number 
		int tryNumber = 0;
		    
		    do {
		    
		      Random cellValueRand = new Random((long) (Math.random()*100));
		      sudoku[i][j] =  1 + cellValueRand.nextInt(9);
		      sudokuUser[i][j] = sudoku[i][j];
		         
		      tryNumber++;
		      
		    } while(!Sudoku.checkSudoku(sudoku, false));
		    

	      counter++;
	      }
	      
	}
	while (!Sudoku.fillSudoku (sudoku));
	
/***  Shuffling Unit ***************/
	Random shuffle = new Random((long) (Math.random()*100));
	
	
	for(int level=0; level< 3; level++) {
	  
	  
	  int p2=-1, p1 = shuffle.nextInt(3);   // shuffle = 0,1,2
	  
	    while ((p2 = shuffle.nextInt(3)) == p1);
	    int p3 = 3 - (p1+p2);	  
	    
	  //System.out.println("ROW p1p2p3:" + p1 + p2 + p3);
	
	  for(int col = 0; col < 9; col++) {
	  
	      sudokuTmp[level*3+p1][col] = sudoku[level*3][col];
	      sudokuTmp[level*3+p2][col] = sudoku[level*3+1][col];
	      sudokuTmp[level*3+p3][col] = sudoku[level*3+2][col];
	      
	  }
	   
	   
	}
	
	for(int level=0; level< 3; level++) {
	  
	  int p2=-1, p1 = shuffle.nextInt(3);   // shuffle = 0,1,2
	  
	    while ((p2 = shuffle.nextInt(3)) == p1);
	    int p3 = 3 - (p1+p2);	  
	    
	  //System.out.println("COL p1p2p3:" + p1 + p2 + p3);
	
	  for(int row = 0; row < 9; row++) {
	  
	      sudokuUser[row][level*3+p1] = sudokuTmp[row][level*3];
	      sudokuUser[row][level*3+p2] = sudokuTmp[row][level*3+1];
	      sudokuUser[row][level*3+p3] = sudokuTmp[row][level*3+2];
	      
	  }
	   
	}	
	
/***  END Shuffling Unit ***************/
/***************************************/
/***  USER Solvable Unit ***************/

              for(int row = 0; row < 9; row++) {
                 for(int col = 0; col < 9; col++) {
                    
                    sudokuTmp[row][col] = sudokuUser[row][col];   // sudokuTmp is the solution
                 }
              }
              

	      Random whichCell = new Random((long) (Math.random()*10000));
	      //int min = 34; // 20
	      //int max = 35; // 27
	      //int noClearcells = 81 - (min + whichCell.nextInt(max-min+1));
	      int noClearcells = 0;
	      if (event.getSource() == hard || event.getSource() == menuhard) {
	          noClearcells = 81 - (int) Math.round(22.+whichCell.nextGaussian());
	          emhStatus.setText("Hard");
	          emhStatus.setForeground(new Color(165,42,42));
	          }
	      if ((event.getSource() == medium) || event.getSource() == menumedium ) {
	          noClearcells = 81 - (int) Math.round(27.+whichCell.nextGaussian());
	          emhStatus.setText("Medium");
	          emhStatus.setForeground(new Color(255,69,0));
	          }
	      if ((event.getSource() == play) || event.getSource() == menueasy ) {
	          noClearcells = 81 - (int) Math.round(33.+1.5*whichCell.nextGaussian());
	          emhStatus.setText("Easy");
	          emhStatus.setForeground(new Color(25,51,0));
	          }
	      
	      whichCell.setSeed((long) (Math.random()*10000));
	      
	      
	      //System.out.println(81-noClearcells);
	      
	      int counter = 1;
	      while (counter < noClearcells+1) {
		
		
		int n = whichCell.nextInt(80);

		int j = n%9;  // column number
		int i = (n-j)/9; // row number 
		
		
		   if (sudokuUser[i][j] != 0) {
		        sudokuUser[i][j] = 0;
		        counter++;
		   }
              }
/***  END of USER Solvable Unit ***************/

	      
	      for (int n = 0; n < 81; n++) {
	      
		int j = n%9;  // column number
		int i = (n-j)/9; // row number 
		if (sudokuUser[i][j] != 0) {
		    cell[n].setText(Integer.toString(sudokuUser[i][j]));
		    cell[n].setBackground(Color.GRAY);
		    cell[n].setDisabledTextColor(Color.ORANGE);
		    cell[n].setEnabled(false);
		}
        
              }

           System.out.println ("\n\nNew Sudoku\n" + Sudoku.toString (sudokuUser, false));
           
        play.setEnabled(false);
	medium.setEnabled(false);
	hard.setEnabled(false);
           
           
           title = "Valid Sudoku";
           infolabel.setText(title);
           infolabel.setForeground(Color.BLUE.darker());  
           tryAgain.setEnabled(true);
           playAgame.setEnabled(true);
           time = 0;
           timelabel.setText("Start ... Enjoy it");
           myTimer.start();
           myTimer.setInitialDelay(0);
           solve.setEnabled(true);
   } // end of play buttom
   
   
      /** Try buttom */
   if (event.getSource() == tryAgain  || event.getSource() == menuTry) {
      
      
        // clear all
        for (int n=0; n < 81; n++) {
             cell[n].setText("");
             cell[n].setForeground(new Color(0,0,204));
             cell[n].setBackground(Color.WHITE);
             cell[n].setEnabled(true);
        }   
        
	      for (int n = 0; n < 81; n++) {
	      
		int j = n%9;  // column number
		int i = (n-j)/9; // row number 
		if (sudokuUser[i][j] != 0) {
		    cell[n].setText(Integer.toString(sudokuUser[i][j]));
		    cell[n].setBackground(Color.GRAY);
		    cell[n].setEnabled(false);
		}
        
         }
       
           title = "Valid Sudoku";
           infolabel.setText(title);
           infolabel.setForeground(Color.BLUE.darker()); 
           PlayMode = true;
           time = 0;
             
           timelabel.setText("Timer Reset");
             
           myTimer.start();
           myTimer.setInitialDelay(0);
           solve.setEnabled(true);
           playAgame.setEnabled(true);
           play.setEnabled(false);
	   medium.setEnabled(false);
	   hard.setEnabled(false);
   
   }  // end of Try again method
   
   
   // Always at the end of this method
   if (event.getSource() == playAgame) {
   
     Newgame = true;
       
   } else {
     Newgame = false;
	playAgame.setEnabled(true);
     	medium.setEnabled(false);
	hard.setEnabled(false);
	play.setEnabled(false);

	playAgame.setEnabled(true);
     
     }
   

} // end of action listening

   /** Converting time to string
   
       This method gets the time in second as an integer number
       and returns a string formatted like hh:mm:dd
       
       @source: http://stackoverflow.com/questions/11357945/
        java-convert-seconds-into-day-hour-minute-and-seconds-using-timeunit
   */
   public String calculateTime(int fsec) {

    
        int d,h,m,s,temp=0,i;
        String st;
        //fsec=3600;
        //For Days
        if(fsec>=86400)
        {
            temp=fsec/86400;
            d=temp;
            for(i=1;i<=temp;i++)
            {
                fsec-=86400;
            }
        }
        else
        {
            d=0;
        }
        //For Hours
        if(fsec>=3600)
        {
            temp=fsec/3600;
            h=temp;
            for(i=1;i<=temp;i++)
            {
                fsec-=3600;
            }            
        }
        else
        {
            h=0;
        }
        //For Minutes
        if(fsec>=60)
        {
            temp=fsec/60;
            m=temp;
            for(i=1;i<=temp;i++)
            {
                fsec-=60;
            }            
        }
        else
        {
            m=0;
        }
        //For Seconds
        if(fsec>=1)
        {
            s=fsec;            
        }
        else
        {
            s=0;
        }
        //st = "Days:"+Integer.toString(d)+" Hours:"+Integer.toString(h)+" Minutes:"+Integer.toString(m)+" Seconds:"+Integer.toString(s);
        
        String second, minute, hour;
        
        if (s < 10) second = "0" + Integer.toString(s); else second = Integer.toString(s);
        if (m < 10) minute = "0" + Integer.toString(m); else minute = Integer.toString(m);
        if (h < 10) hour = "0" + Integer.toString(h); else hour = Integer.toString(h);
        
        st = hour + ":" + minute + ":" + second;
         
      return st;

      }
   
  /****************************************/
  /****************************************/ 

   
   public static void main(String [] args) {
   
   SudokuGUI myWindow = new SudokuGUI();
   myWindow.setTitle("Ehsan's Sudoku");

   myWindow.setSize(700,200);
   
   myWindow.setCenter();
   myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   myWindow.pack();
   
   
   myWindow.setResizable(false);
   myWindow.setVisible(true);
   myWindow.toFront();
   }


  /****************************************/
  /****************************************/


} /** End of this GUI class */
