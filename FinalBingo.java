/*
 * NAME: HAROLD DANE C. BAGUINON
 * DATE: 8/17/2014
 * PURPOSE: This program is the eighth and final assignment.
 *          It will utilize a GUI for a Bingo Card Game.
 *          
 */

// Commentos.

/* The following are the requirements:

1) Design and implement a "BINGO" game with a NICE GUI . Details about the game are in the attached final project
   document.

2) LOADS of comments! I want to know what almost every block of code is doing and not have to guess at what it is
   doing.

3) Not that any of you would think of doing this BUT...DO NOT PLAGIARIZE THIS! I actually can tell if you just
   copied some code off the web. You can research different implementations of this program and use similar 
   algorithms but do NOT just copy the code! You will receive a 0 for your grade if I find code on the web
   that looks remarkably similar to yours!!

4) Your grade is not an automatic 100. If you gave me a great app (one that I say "wow, I want to download that"),
   then I'll give you a great grade. If you didn't put much effort into it, you will not do so well.

NOTE: In the requirements document, I mention that you need to "present" your project to me.  This does NOT pertain
   to this class since this is an online section.
*/

/*
I am SO PROUD OF THIS PROGRAM! I wrote everything myself using only the code we learned in class! It took hours
and hours, but I am just so happy I managed to finish, just minutes before the deadline.

This program utilizes nearly everything we covered, including arrays, GUI, loops, fonts, and even a menu.
Also, I successfully managed to fix ALL of my bugs. I had so many, and tried everything I could think of to solve
them. I figured out how to create Bingo cards with no repeating numbers. When calling a new number, there is again,
no repeated numbers. And every time a new number is called, the game checks to see if you got a Bingo!

I chose gray colors because that's the Bingo cards I played with as a kid were
always gray. Haha. Anyway, I hope you enjoy!

*/

import javax.swing.*;            // For Swing classes
import java.awt.*;               // For BorderLayout class and color class
import java.awt.event.*;         // For event listener interface
import java.util.*;              // For the Random class and any other utilities.

public class FinalBingo extends JFrame
{
   private final int WINDOW_WIDTH = 700;     // Window width
   private final int WINDOW_HEIGHT = 659;    // Window height, set to make the borders look nicer.

   private JPanel BingoCardPanel;    // The Bingo Card, Center.
   private JPanel BingoWordPanel;    // B-I-N-G-O Panel, North.
   private JPanel NewNumberPanel;    // Newest number called, East.
   private JPanel ButtonPanel;       // Panel for buttons, South.
   
   private JLabel BingoGame;         // To show the title.
   private JLabel BLabel1;           // To show B number 1.
   private JLabel BLabel2;           // To show B number 2.
   private JLabel BLabel3;           // To show B number 3.
   private JLabel BLabel4;           // To show B number 4.
   private JLabel BLabel5;           // To show B number 5.
   private JLabel ILabel1;           // To show I number 1.
   private JLabel ILabel2;           // To show I number 2.
   private JLabel ILabel3;           // To show I number 3.
   private JLabel ILabel4;           // To show I number 4.
   private JLabel ILabel5;           // To show I number 5.
   private JLabel NLabel1;           // To show N number 1.
   private JLabel NLabel2;           // To show N number 2.
   private JLabel NLabel3;           // To show N number 3.
   private JLabel NLabel4;           // To show N number 4.
   private JLabel NLabel5;           // To show N number 5.
   private JLabel GLabel1;           // To show G number 1.
   private JLabel GLabel2;           // To show G number 2.
   private JLabel GLabel3;           // To show G number 3.
   private JLabel GLabel4;           // To show G number 4.
   private JLabel GLabel5;           // To show G number 5.         
   private JLabel OLabel1;           // To show O number 1.
   private JLabel OLabel2;           // To show O number 2.
   private JLabel OLabel3;           // To show O number 3.
   private JLabel OLabel4;           // To show O number 4.
   private JLabel OLabel5;           // To show O number 5.
   
   private JLabel BLabel;        // To show the letter B.
   private JLabel ILabel;        // To show the letter I.
   private JLabel NLabel;        // To show the letter N.
   private JLabel GLabel;        // To show the letter G.
   private JLabel OLabel;        // To show the letter O.
   
   private JLabel CalledNumber;      // To show the text "Called Number."
   private JLabel NextNumberLabel;   // To show the new number called.
   private int NewNumber;            // To hold the value of the number called.
   private ArrayList<Integer> NextNumberPool;      // Empty Number Pool
      
   private JButton NextNumber;       // Displays next number called.
   private JButton NewGame;          // Clears board for a new game.
   private JButton ExitButton;       // Exit Button.
   
   private JMenuBar MenuBar;         // The menu barrrrrr
   private JMenu FileMenu;           // File menu
   private JMenu GameMenu;           // Game menu
   private JMenuItem NewGameItem;    // To start a new game
   private JMenuItem ExitItem;       // To exit the game
   private JMenuItem NextNumberItem; // To call the next number.

   
   /**
      Constructionator ;)
   */
   
   public FinalBingo()
   {
      // Title.
      setTitle("B-I-N-G-O!");
            
      // Close button.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Add BorderLayout manager to content pane.
      setLayout(new BorderLayout());
      
      // Build menu bar
      BuildMenuBar();
      
      // BUILD PANELS
      BuildBingoCardPanel();
      BuildBingoWordPanel();
      BuildNewNumberPanel();
      BuildButtonPanel();
      
      // Add panels
      add(BingoCardPanel, BorderLayout.CENTER);
      add(BingoWordPanel, BorderLayout.NORTH);
      add(NewNumberPanel, BorderLayout.EAST);
      add(ButtonPanel, BorderLayout.SOUTH);
      
      // Set size of the window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Display the window, but do not pack.
      // pack();               // DON'T PACK!
      setVisible(true);
   }
   
   
   
   /**
      This method builds the menu bar.
   */
   
   private void BuildMenuBar()
   {
      // Create the menu bar.
      MenuBar = new JMenuBar();
      
      // Create File and Game menus.
      BuildFileMenu();
      BuildGameMenu();
      
      // Add File and Game menus to the Menu Bar.
      MenuBar.add(FileMenu);
      MenuBar.add(GameMenu);
      
      // Set the Menu Bar.
      setJMenuBar(MenuBar);
   }
   
   
   /**
      This method builds the File Menu.
   */
   
   private void BuildFileMenu()
   {
      // Create the New Game menu item.
      NewGameItem = new JMenuItem("New Game");
      NewGameItem.setMnemonic(KeyEvent.VK_G);
      NewGameItem.addActionListener(new NewGameButtonListener());
      
      // Create the Exit menu item.
      ExitItem = new JMenuItem("Exit");
      ExitItem.setMnemonic(KeyEvent.VK_X);
      ExitItem.addActionListener(new ExitButtonListener());
      
      // Create JMenu object for the File menu.
      FileMenu = new JMenu("File");
      FileMenu.setMnemonic(KeyEvent.VK_F);
      
      // Add the menu items to the File menu.
      FileMenu.add(NewGameItem);
      FileMenu.add(ExitItem);
   }
   
   
   /**
      This method builds the Game Menu.
   */
   
   private void BuildGameMenu()
   {
      // Create the Next Number menu item.
      NextNumberItem = new JMenuItem("Next Number");
      NextNumberItem.setMnemonic(KeyEvent.VK_N);
      NextNumberItem.addActionListener(new NextNumberButtonListener());
            
      // Create JMenu object for the File menu.
      GameMenu = new JMenu("Game");
      GameMenu.setMnemonic(KeyEvent.VK_G);
      
      // Add the menu items to the File menu.
      GameMenu.add(NextNumberItem);
   }
   
   
   
   /**
      This method builds the north panel.
   */
   
   public void BuildBingoWordPanel()
   {
      // Build title panel. Make it LIGHT_GRAY.
      BingoWordPanel = new JPanel();
      BingoWordPanel.setBackground(Color.LIGHT_GRAY);

      // Display the title.
      BingoGame = new JLabel("Bingo!", SwingConstants.CENTER);
      BingoGame.setFont(new Font("Serif", Font.BOLD, 64));
      
      // Add the label to the panel.
      BingoWordPanel.add(BingoGame);

   }
   
   
   /**
      This is the method that builds the central panel.
   */
   
   public void BuildBingoCardPanel()
   {
      // Build Card panel. Make it LIGHT_GRAY.
      BingoCardPanel = new JPanel();
     //BingoCardPanel.setBackground(Color.LIGHT_GRAY);
      
      // Set layout and border for the panel.
      BingoCardPanel.setLayout(new GridLayout(6, 5));
      BingoCardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

      // Create title label for the letters of B I N G O.
      BLabel = new JLabel("B", SwingConstants.CENTER);
      BLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
      BLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      ILabel = new JLabel("I", SwingConstants.CENTER);
      ILabel.setFont(new Font("SansSerif", Font.BOLD, 48));
      ILabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      NLabel = new JLabel("N", SwingConstants.CENTER);
      NLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
      NLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      GLabel = new JLabel("G", SwingConstants.CENTER);
      GLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
      GLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      OLabel = new JLabel("O", SwingConstants.CENTER);
      OLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
      OLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      // Add labels to first row of card panel.
      BingoCardPanel.add(BLabel);
      BingoCardPanel.add(ILabel);
      BingoCardPanel.add(NLabel);
      BingoCardPanel.add(GLabel);
      BingoCardPanel.add(OLabel);

      // Create five labels for each letter of B I N G O. Add empty space: " ".
      // Set font (SansSerif, Bold) and border (2).
      BLabel1 = new JLabel(" ", SwingConstants.CENTER);
      BLabel1.setFont(new Font("SansSerif", Font.BOLD, 36));
      BLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      BLabel2 = new JLabel(" ", SwingConstants.CENTER);
      BLabel2.setFont(new Font("SansSerif", Font.BOLD, 36));
      BLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      BLabel3 = new JLabel(" ", SwingConstants.CENTER);
      BLabel3.setFont(new Font("SansSerif", Font.BOLD, 36));
      BLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      BLabel4 = new JLabel(" ", SwingConstants.CENTER);
      BLabel4.setFont(new Font("SansSerif", Font.BOLD, 36));
      BLabel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      BLabel5 = new JLabel(" ", SwingConstants.CENTER);
      BLabel5.setFont(new Font("SansSerif", Font.BOLD, 36));
      BLabel5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      // I labels.
      ILabel1 = new JLabel(" ", SwingConstants.CENTER);
      ILabel1.setFont(new Font("SansSerif", Font.BOLD, 36));
      ILabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      ILabel2 = new JLabel(" ", SwingConstants.CENTER);
      ILabel2.setFont(new Font("SansSerif", Font.BOLD, 36));
      ILabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      ILabel3 = new JLabel(" ", SwingConstants.CENTER);
      ILabel3.setFont(new Font("SansSerif", Font.BOLD, 36));
      ILabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      ILabel4 = new JLabel(" ", SwingConstants.CENTER);
      ILabel4.setFont(new Font("SansSerif", Font.BOLD, 36));
      ILabel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      ILabel5 = new JLabel(" ", SwingConstants.CENTER);
      ILabel5.setFont(new Font("SansSerif", Font.BOLD, 36));
      ILabel5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      // N labels.
      NLabel1 = new JLabel(" ", SwingConstants.CENTER);
      NLabel1.setFont(new Font("SansSerif", Font.BOLD, 36));
      NLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      NLabel2 = new JLabel(" ", SwingConstants.CENTER);
      NLabel2.setFont(new Font("SansSerif", Font.BOLD, 36));
      NLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      NLabel3 = new JLabel("FREE", SwingConstants.CENTER);
      NLabel3.setFont(new Font("SansSerif", Font.BOLD, 18));
      NLabel3.setForeground(Color.BLUE);
      NLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      NLabel4 = new JLabel(" ", SwingConstants.CENTER);
      NLabel4.setFont(new Font("SansSerif", Font.BOLD, 36));
      NLabel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      NLabel5 = new JLabel(" ", SwingConstants.CENTER);      
      NLabel5.setFont(new Font("SansSerif", Font.BOLD, 36));
      NLabel5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      // G labels.
      GLabel1 = new JLabel(" ", SwingConstants.CENTER);
      GLabel1.setFont(new Font("SansSerif", Font.BOLD, 36));
      GLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      GLabel2 = new JLabel(" ", SwingConstants.CENTER);
      GLabel2.setFont(new Font("SansSerif", Font.BOLD, 36));
      GLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      GLabel3 = new JLabel(" ", SwingConstants.CENTER);
      GLabel3.setFont(new Font("SansSerif", Font.BOLD, 36));
      GLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      GLabel4 = new JLabel(" ", SwingConstants.CENTER);
      GLabel4.setFont(new Font("SansSerif", Font.BOLD, 36));
      GLabel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      GLabel5 = new JLabel(" ", SwingConstants.CENTER);
      GLabel5.setFont(new Font("SansSerif", Font.BOLD, 36));
      GLabel5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      // O labels.
      OLabel1 = new JLabel(" ", SwingConstants.CENTER);
      OLabel1.setFont(new Font("SansSerif", Font.BOLD, 36));
      OLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      OLabel2 = new JLabel(" ", SwingConstants.CENTER);
      OLabel2.setFont(new Font("SansSerif", Font.BOLD, 36));
      OLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      OLabel3 = new JLabel(" ", SwingConstants.CENTER);
      OLabel3.setFont(new Font("SansSerif", Font.BOLD, 36));
      OLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      OLabel4 = new JLabel(" ", SwingConstants.CENTER);
      OLabel4.setFont(new Font("SansSerif", Font.BOLD, 36));
      OLabel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      OLabel5 = new JLabel(" ", SwingConstants.CENTER);    
      OLabel5.setFont(new Font("SansSerif", Font.BOLD, 36));
      OLabel5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      // Call the number generation method to fill cells with random numbers.
      NumberGenerator();
      
      // Add labels to card panel, row 1.
      BingoCardPanel.add(BLabel1);
      BingoCardPanel.add(ILabel1);
      BingoCardPanel.add(NLabel1);
      BingoCardPanel.add(GLabel1);
      BingoCardPanel.add(OLabel1);
      
      // Add labels to card panel, row 2.
      BingoCardPanel.add(BLabel2);
      BingoCardPanel.add(ILabel2);
      BingoCardPanel.add(NLabel2);
      BingoCardPanel.add(GLabel2);
      BingoCardPanel.add(OLabel2);
      
      // Add labels to card panel, row 3.
      BingoCardPanel.add(BLabel3);
      BingoCardPanel.add(ILabel3);
      BingoCardPanel.add(NLabel3);
      BingoCardPanel.add(GLabel3);
      BingoCardPanel.add(OLabel3);
      
      // Add labels to card panel, row 4.
      BingoCardPanel.add(BLabel4);
      BingoCardPanel.add(ILabel4);
      BingoCardPanel.add(NLabel4);
      BingoCardPanel.add(GLabel4);
      BingoCardPanel.add(OLabel4);
      
      // Add labels to card panel, row 5.
      BingoCardPanel.add(BLabel5);
      BingoCardPanel.add(ILabel5);
      BingoCardPanel.add(NLabel5);
      BingoCardPanel.add(GLabel5);
      BingoCardPanel.add(OLabel5);
      
      // Build the number pool!
      BuildNumberPool();
      
   }
   
   
   /**
      The BuildNumberPool method creates the number pool for the new Bingo numbers.
   */
   
   public void BuildNumberPool()
   {
      NextNumberPool = new ArrayList<Integer>(75);   // Creates the array for the pool.
      
      // Create a Random object.
      Random randomNumber = new Random();
      
      for (int index = 1; index <= 75; index++)
      {
         NextNumberPool.add(index);
      }

   }
   
   
   /**
      The NumberGenerator method fills cells with random numbers.
   */
   
   public void NumberGenerator()
   {
      // Declare an int number array which will be used to number the B, I, N, G, and O numbers.
      int[] BNumbers = new int[5];      // Array of 5 B numbers.
      int[] INumbers = new int[5];      // Array of 5 I numbers.
      int[] NNumbers = new int[5];      // Array of 5 N numbers.
      int[] GNumbers = new int[5];      // Array of 5 G numbers.
      int[] ONumbers = new int[5];      // Array of 5 O numbers.
      
      // Create a Random object.
      Random randomNumber = new Random();
      
      // Assign random numbers to B number array and try to remove duplicates via do-while loop.
      do
      {
      /* This For loop will enter a random number into each array element while the above loop
      continuously checks against duplicate values. The loops run until there are no duplicate
      values, and the values may be assigned to the cells below.
      */   
         for (int index = 0; index < 5; index++)
         {
            BNumbers[index] = randomNumber.nextInt(15) + 1;
         }
      }
      while (BNumbers[0] == BNumbers[1] || BNumbers[0] == BNumbers[2] || BNumbers[0] == BNumbers[3] ||
            BNumbers[0] == BNumbers[4] || BNumbers[1] == BNumbers[2] || BNumbers[1] == BNumbers[3] ||
            BNumbers[1] == BNumbers[4] || BNumbers[2] == BNumbers[3] || BNumbers[2] == BNumbers[4] ||
            BNumbers[3] == BNumbers[4]);
                     
      // Set the text of the labels to the value of the randomly generated numbers as a string.
      BLabel1.setText(String.valueOf(BNumbers[0]));
      BLabel2.setText(String.valueOf(BNumbers[1]));
      BLabel3.setText(String.valueOf(BNumbers[2]));
      BLabel4.setText(String.valueOf(BNumbers[3]));
      BLabel5.setText(String.valueOf(BNumbers[4]));
      
      
      // Assign random numbers to I number array and try to remove duplicates via do-while loop.
      do
      {
         for (int index = 0; index < 5; index++)
         {
            INumbers[index] = randomNumber.nextInt(15) + 1 + 15;
         }
      }
      while (INumbers[0] == INumbers[1] || INumbers[0] == INumbers[2] || INumbers[0] == INumbers[3] ||
            INumbers[0] == INumbers[4] || INumbers[1] == INumbers[2] || INumbers[1] == INumbers[3] ||
            INumbers[1] == INumbers[4] || INumbers[2] == INumbers[3] || INumbers[2] == INumbers[4] ||
            INumbers[3] == INumbers[4]);
                     
      // Set the text of the labels to the value of the randomly generated numbers as a string.
      ILabel1.setText(String.valueOf(INumbers[0]));
      ILabel2.setText(String.valueOf(INumbers[1]));
      ILabel3.setText(String.valueOf(INumbers[2]));
      ILabel4.setText(String.valueOf(INumbers[3]));
      ILabel5.setText(String.valueOf(INumbers[4]));
      
      
      // Assign random numbers to N number array and try to remove duplicates via do-while loop.
      do
      {
         for (int index = 0; index < 5; index++)
         {
            NNumbers[index] = randomNumber.nextInt(15) + 1 + 30;
         }
      }
      while (NNumbers[0] == NNumbers[1] || NNumbers[0] == NNumbers[2] || NNumbers[0] == NNumbers[3] ||
            NNumbers[0] == NNumbers[4] || NNumbers[1] == NNumbers[2] || NNumbers[1] == NNumbers[3] ||
            NNumbers[1] == NNumbers[4] || NNumbers[2] == NNumbers[3] || NNumbers[2] == NNumbers[4] ||
            NNumbers[3] == NNumbers[4]);
                     
      // Set the text of the labels to the value of the randomly generated numbers as a string.
      // Don't bother with N3, as it is a FREE SPACE.
      NLabel1.setText(String.valueOf(NNumbers[0]));
      NLabel2.setText(String.valueOf(NNumbers[1]));
      NLabel4.setText(String.valueOf(NNumbers[3]));
      NLabel5.setText(String.valueOf(NNumbers[4]));
      
      
      // Assign random numbers to G number array and try to remove duplicates via do-while loop.
      do
      {
         for (int index = 0; index < 5; index++)
         {
            GNumbers[index] = randomNumber.nextInt(15) + 1 + 45;
         }
      }
      while (GNumbers[0] == GNumbers[1] || GNumbers[0] == GNumbers[2] || GNumbers[0] == GNumbers[3] ||
            GNumbers[0] == GNumbers[4] || GNumbers[1] == GNumbers[2] || GNumbers[1] == GNumbers[3] ||
            GNumbers[1] == GNumbers[4] || GNumbers[2] == GNumbers[3] || GNumbers[2] == GNumbers[4] ||
            GNumbers[3] == GNumbers[4]);
                     
      // Set the text of the labels to the value of the randomly generated numbers as a string.
      GLabel1.setText(String.valueOf(GNumbers[0]));
      GLabel2.setText(String.valueOf(GNumbers[1]));
      GLabel3.setText(String.valueOf(GNumbers[2]));
      GLabel4.setText(String.valueOf(GNumbers[3]));
      GLabel5.setText(String.valueOf(GNumbers[4]));
      
      
      // Assign random numbers to O number array and try to remove duplicates via do-while loop.
      do
      {
         for (int index = 0; index < 5; index++)
         {
            ONumbers[index] = randomNumber.nextInt(15) + 1 + 60;
         }
      }
      while (ONumbers[0] == ONumbers[1] || ONumbers[0] == ONumbers[2] || ONumbers[0] == ONumbers[3] ||
            ONumbers[0] == ONumbers[4] || ONumbers[1] == ONumbers[2] || ONumbers[1] == ONumbers[3] ||
            ONumbers[1] == ONumbers[4] || ONumbers[2] == ONumbers[3] || ONumbers[2] == ONumbers[4] ||
            ONumbers[3] == ONumbers[4]);
                     
      // Set the text of the labels to the value of the randomly generated numbers as a string.
      OLabel1.setText(String.valueOf(ONumbers[0]));
      OLabel2.setText(String.valueOf(ONumbers[1]));
      OLabel3.setText(String.valueOf(ONumbers[2]));
      OLabel4.setText(String.valueOf(ONumbers[3]));
      OLabel5.setText(String.valueOf(ONumbers[4]));
   }
   
   
   /**
      The BuildNewNumberPanel method builds the panel to the right.
   */
   
   public void BuildNewNumberPanel()
   {
      // Build title panel. Make it LIGHT_GRAY.
      NewNumberPanel = new JPanel();
      NewNumberPanel.setBackground(Color.LIGHT_GRAY);
      
      // Set layout for the panel.
      NewNumberPanel.setLayout(new GridLayout(3, 1));
      
      // Create two labels for the text and number.
      CalledNumber = new JLabel("Called Number:", SwingConstants.CENTER);
      CalledNumber.setFont(new Font("SansSerif", Font.BOLD, 12));
      CalledNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
      
      NextNumberLabel = new JLabel(" ", SwingConstants.CENTER);
      NextNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
      NextNumberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
      
      
      // Add labels to card panel.
      NewNumberPanel.add(CalledNumber);
      NewNumberPanel.add(NextNumberLabel);
      
      // Create button and add it to panel.
      NextNumber = new JButton("Next Number");
      NextNumber.setMnemonic(KeyEvent.VK_N);
      NextNumber.setToolTipText("Click here to call the next number.");
      NextNumber.setFont(new Font("SansSerif", Font.BOLD, 12));

      // Add an action listener to the button.
      NextNumber.addActionListener(new NextNumberButtonListener());
      
      // Add button to panel.
      NewNumberPanel.add(NextNumber);
      
   }
   
      
   /**
      The NextNumberButtonListener is an action listener class for Next Number button.
   */
   
   private class NextNumberButtonListener implements ActionListener
   {
      /**
         The actionPerformed method executes when the user clicks on the
         Next Number button.
         @param action The event object.
      */
      
      public void actionPerformed(ActionEvent action)
      {
         Collections.shuffle(NextNumberPool);
         NewNumber = NextNumberPool.get(0);
         
         NextNumberLabel.setText(String.valueOf(NewNumber));
         
         Highlight();
         
         NextNumberPool.remove(0);
         
         if (NextNumberPool.size() == 0)
            JOptionPane.showMessageDialog(null, "No more numbers!");
         
         WinCheck();
      }
   }
    
      
   /**
      This method checks the new number called with the numbers on the card.
   */

   public void Highlight()
   {
      // Check if the new number equals any B numbers.
      if (BLabel1.getText().equals(String.valueOf(NewNumber)))
         BLabel1.setForeground(Color.BLUE);
      else if (BLabel2.getText().equals(String.valueOf(NewNumber)))
         BLabel2.setForeground(Color.BLUE);
      else if (BLabel3.getText().equals(String.valueOf(NewNumber)))
         BLabel3.setForeground(Color.BLUE);
      else if (BLabel4.getText().equals(String.valueOf(NewNumber)))
         BLabel4.setForeground(Color.BLUE);
      else if (BLabel5.getText().equals(String.valueOf(NewNumber)))
         BLabel5.setForeground(Color.BLUE);
      else if (ILabel1.getText().equals(String.valueOf(NewNumber)))
         ILabel1.setForeground(Color.BLUE);
      else if (ILabel2.getText().equals(String.valueOf(NewNumber)))
         ILabel2.setForeground(Color.BLUE);
      else if (ILabel3.getText().equals(String.valueOf(NewNumber)))
         ILabel3.setForeground(Color.BLUE);
      else if (ILabel4.getText().equals(String.valueOf(NewNumber)))
         ILabel4.setForeground(Color.BLUE);
      else if (ILabel5.getText().equals(String.valueOf(NewNumber)))
         ILabel5.setForeground(Color.BLUE);
      else if (NLabel1.getText().equals(String.valueOf(NewNumber)))
         NLabel1.setForeground(Color.BLUE);
      else if (NLabel2.getText().equals(String.valueOf(NewNumber)))
         NLabel2.setForeground(Color.BLUE);
      else if (NLabel3.getText().equals(String.valueOf(NewNumber)))
         NLabel3.setForeground(Color.BLUE);
      else if (NLabel4.getText().equals(String.valueOf(NewNumber)))
         NLabel4.setForeground(Color.BLUE);
      else if (NLabel5.getText().equals(String.valueOf(NewNumber)))
         NLabel5.setForeground(Color.BLUE);
      else if (GLabel1.getText().equals(String.valueOf(NewNumber)))
         GLabel1.setForeground(Color.BLUE);
      else if (GLabel2.getText().equals(String.valueOf(NewNumber)))
         GLabel2.setForeground(Color.BLUE);
      else if (GLabel3.getText().equals(String.valueOf(NewNumber)))
         GLabel3.setForeground(Color.BLUE);
      else if (GLabel4.getText().equals(String.valueOf(NewNumber)))
         GLabel4.setForeground(Color.BLUE);
      else if (GLabel5.getText().equals(String.valueOf(NewNumber)))
         GLabel5.setForeground(Color.BLUE);
      else if (OLabel1.getText().equals(String.valueOf(NewNumber)))
         OLabel1.setForeground(Color.BLUE);
      else if (OLabel2.getText().equals(String.valueOf(NewNumber)))
         OLabel2.setForeground(Color.BLUE);
      else if (OLabel3.getText().equals(String.valueOf(NewNumber)))
         OLabel3.setForeground(Color.BLUE);
      else if (OLabel4.getText().equals(String.valueOf(NewNumber)))
         OLabel4.setForeground(Color.BLUE);
      else if (OLabel5.getText().equals(String.valueOf(NewNumber)))
         OLabel5.setForeground(Color.BLUE);
   }
  
  
   /**
      This method checks if the card has 5 highlighted numbers in a row.
   */

   public void WinCheck()
   {
      // Check each column, each row, and two diagonals for highlights.
      if (BLabel1.getForeground().equals(Color.BLUE) && 
          BLabel2.getForeground().equals(Color.BLUE) &&
          BLabel3.getForeground().equals(Color.BLUE) && 
          BLabel4.getForeground().equals(Color.BLUE) && 
          BLabel5.getForeground().equals(Color.BLUE))       // Checks B column
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (ILabel1.getForeground().equals(Color.BLUE) && 
          ILabel2.getForeground().equals(Color.BLUE) &&
          ILabel3.getForeground().equals(Color.BLUE) && 
          ILabel4.getForeground().equals(Color.BLUE) && 
          ILabel5.getForeground().equals(Color.BLUE))       // Checks I column
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
          else if (NLabel1.getForeground().equals(Color.BLUE) && 
          NLabel2.getForeground().equals(Color.BLUE) &&
          NLabel3.getForeground().equals(Color.BLUE) && 
          NLabel4.getForeground().equals(Color.BLUE) && 
          NLabel5.getForeground().equals(Color.BLUE))       // Checks N column
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (GLabel1.getForeground().equals(Color.BLUE) && 
          GLabel2.getForeground().equals(Color.BLUE) &&
          GLabel3.getForeground().equals(Color.BLUE) && 
          GLabel4.getForeground().equals(Color.BLUE) && 
          GLabel5.getForeground().equals(Color.BLUE))       // Checks G column
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (OLabel1.getForeground().equals(Color.BLUE) && 
          OLabel2.getForeground().equals(Color.BLUE) &&
          OLabel3.getForeground().equals(Color.BLUE) && 
          OLabel4.getForeground().equals(Color.BLUE) && 
          OLabel5.getForeground().equals(Color.BLUE))       // Checks O column
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (BLabel1.getForeground().equals(Color.BLUE) && 
          ILabel1.getForeground().equals(Color.BLUE) &&
          NLabel1.getForeground().equals(Color.BLUE) && 
          GLabel1.getForeground().equals(Color.BLUE) && 
          OLabel1.getForeground().equals(Color.BLUE))       // Checks first row
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (BLabel2.getForeground().equals(Color.BLUE) && 
          ILabel2.getForeground().equals(Color.BLUE) &&
          NLabel2.getForeground().equals(Color.BLUE) && 
          GLabel2.getForeground().equals(Color.BLUE) && 
          OLabel2.getForeground().equals(Color.BLUE))       // Checks second row
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (BLabel3.getForeground().equals(Color.BLUE) && 
          ILabel3.getForeground().equals(Color.BLUE) &&
          NLabel3.getForeground().equals(Color.BLUE) && 
          GLabel3.getForeground().equals(Color.BLUE) && 
          OLabel3.getForeground().equals(Color.BLUE))       // Checks third row
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (BLabel4.getForeground().equals(Color.BLUE) && 
          ILabel4.getForeground().equals(Color.BLUE) &&
          NLabel4.getForeground().equals(Color.BLUE) && 
          GLabel4.getForeground().equals(Color.BLUE) && 
          OLabel4.getForeground().equals(Color.BLUE))       // Checks fourth row
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (BLabel5.getForeground().equals(Color.BLUE) && 
          ILabel5.getForeground().equals(Color.BLUE) &&
          NLabel5.getForeground().equals(Color.BLUE) && 
          GLabel5.getForeground().equals(Color.BLUE) && 
          OLabel5.getForeground().equals(Color.BLUE))       // Checks fifth row
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (BLabel1.getForeground().equals(Color.BLUE) && 
          ILabel2.getForeground().equals(Color.BLUE) &&
          NLabel3.getForeground().equals(Color.BLUE) && 
          GLabel4.getForeground().equals(Color.BLUE) && 
          OLabel5.getForeground().equals(Color.BLUE))       // Checks \ diagonal
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
      else if (BLabel5.getForeground().equals(Color.BLUE) && 
          ILabel4.getForeground().equals(Color.BLUE) &&
          NLabel3.getForeground().equals(Color.BLUE) && 
          GLabel2.getForeground().equals(Color.BLUE) && 
          OLabel1.getForeground().equals(Color.BLUE))       // Checks / diagonal
       {
          JOptionPane.showMessageDialog(null, "BINGO!");
          JOptionPane.showMessageDialog(null, "Please click Exit or start a New Game!");
       }
          
   }
   
   
   /**
      This method builds the south (button) panel.
   */
   
   public void BuildButtonPanel()
   {
      // Build title panel. Make it LIGHT_GRAY.
      ButtonPanel = new JPanel();
      ButtonPanel.setBackground(Color.LIGHT_GRAY);
      
      // Set layout for the panel.
      ButtonPanel.setLayout(new GridLayout(1, 2));
      
      // Create buttons and add mnemonics and tool tips.
      NewGame = new JButton("New Game");
      NewGame.setMnemonic(KeyEvent.VK_G);
      NewGame.setToolTipText("Click here to start a new game.");
      ExitButton = new JButton("Exit");
      ExitButton.setMnemonic(KeyEvent.VK_X);
      ExitButton.setToolTipText("Click here to exit the game.");
      
      // Add an action listener to the buttons.
      NewGame.addActionListener(new NewGameButtonListener());
      ExitButton.addActionListener(new ExitButtonListener());
      
      // Add buttons to panel.
      ButtonPanel.add(NewGame);
      ButtonPanel.add(ExitButton);
      
   }
  
  
   /**
      The NewGameButtonListener is an action listener class for New Game button.
   */
   
   private class NewGameButtonListener implements ActionListener
   {
      /**
         The actionPerformed method executes when the user clicks on the
         New Game button.
         @param action The event object.
      */
      
      public void actionPerformed(ActionEvent action)
      {
         // Call the Number Generator to set new numbers into the cells of the card.
         NumberGenerator();
         
         // Clear the Next Number Label.
         NextNumberLabel.setText(null);
         
         // Rebuild the Next Number Pool.
         BuildNumberPool();
         
         // Reset all of the highlighting of the labels. Start with B's.
         BLabel1.setForeground(Color.BLACK);
         BLabel2.setForeground(Color.BLACK);
         BLabel3.setForeground(Color.BLACK);
         BLabel4.setForeground(Color.BLACK);
         BLabel5.setForeground(Color.BLACK);
         
         // Reset I highlights.
         ILabel1.setForeground(Color.BLACK);
         ILabel2.setForeground(Color.BLACK);
         ILabel3.setForeground(Color.BLACK);
         ILabel4.setForeground(Color.BLACK);
         ILabel5.setForeground(Color.BLACK);
         
         // Reset N highlights. Skip Free.
         NLabel1.setForeground(Color.BLACK);
         NLabel2.setForeground(Color.BLACK);
         NLabel4.setForeground(Color.BLACK);
         NLabel5.setForeground(Color.BLACK);
         
         // Reset G highlights.
         GLabel1.setForeground(Color.BLACK);
         GLabel2.setForeground(Color.BLACK);
         GLabel3.setForeground(Color.BLACK);
         GLabel4.setForeground(Color.BLACK);
         GLabel5.setForeground(Color.BLACK);
         
         // Reset O highlights.
         OLabel1.setForeground(Color.BLACK);
         OLabel2.setForeground(Color.BLACK);
         OLabel3.setForeground(Color.BLACK);
         OLabel4.setForeground(Color.BLACK);
         OLabel5.setForeground(Color.BLACK);
         
      }
   }
   
   
   /**
      The ExitButtonListener is an action listener class for Exit button.
   */
   
   private class ExitButtonListener implements ActionListener
   {
      /**
         The actionPerformed method executes when the user clicks on the
         Exit button.
         @param action The event object.
      */
      
      public void actionPerformed(ActionEvent action)
      {
         // Close the game.
         System.exit(0);
      }
   }
   
  
   /**
      The main method creates an instance of the FinalBingo class, which causes
      the Bingo game to appear in its window.
   */
   
   public static void main(String[] args)
   {
      new FinalBingo();
   }
      
}