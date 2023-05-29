package java2048;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.*;
import java.net.URL;
public class GUI {
    Game game;
    
    int frameHeight = 394;
    int frameWidth = 328;
    int gameBoardSize = 296;
    int marginSize = 16;
    Color backgroundColor = new Color(255,225,120);
    
    Font largeFeedbackFont = new Font("SanSerif",0,40);
    Font smallFeedbackFont = new Font("SanSerif",0,20);
    
    Map numberTilles;
    GameBoard gb;
    
    MyFrame frame;
    public GUI()
    {
        game = new Game();
        frame = new MyFrame();
        frame.setFocusable(true);
        frame.addKeyListener(new MyFrame());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loadNumberTiles();
        
         gb = new GameBoard();
        
        //North Panal
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout());
        northPanel.setPreferredSize(new Dimension(frameWidth,82));
        
        JLabel gameLable = new JLabel("2048",SwingConstants.CENTER);
        gameLable.setFont(new Font("serif",Font.BOLD,20));
        northPanel.add(gameLable);
        northPanel.add(new JLabel("<html>Score:<br>524</html>",SwingConstants.CENTER));
        northPanel.add(new JLabel("<html>High Score:<br> 22600</html>",SwingConstants.CENTER));
        northPanel.setBackground(backgroundColor);
        
        JPanel westBuffer = new JPanel();
        westBuffer.setPreferredSize(new Dimension(marginSize,gameBoardSize));
        westBuffer.setBackground(backgroundColor);
        
        JPanel eastBuffer = new JPanel();
        eastBuffer.setPreferredSize(new Dimension(marginSize,gameBoardSize));
        eastBuffer.setBackground(backgroundColor);
        
        JPanel southBuffer = new JPanel();
        southBuffer.setPreferredSize(new Dimension(frameWidth,marginSize));
        southBuffer.setBackground(backgroundColor);
        
        //  add panals to frame
        frame.getContentPane().add(northPanel,BorderLayout.NORTH);
        frame.getContentPane().add(westBuffer,BorderLayout.WEST);
        frame.getContentPane().add(eastBuffer,BorderLayout.EAST);
        frame.getContentPane().add(southBuffer,BorderLayout.SOUTH);
        frame.getContentPane().add(gb,BorderLayout.CENTER);
       
        frame.getContentPane().setPreferredSize(new Dimension(frameWidth,frameHeight));
        frame.pack();
        frame.setVisible(true);
    }
    
    private void loadNumberTiles()
    {
        numberTilles = new Hashtable();
        ClassLoader cldr = this.getClass().getClassLoader();
        URL url0000 = cldr.getResource("image/tile00.png");
       // URL url0001 = cldr.getResource("image/1_64.png");
        URL url0002 = cldr.getResource("image/2_64.png");
        URL url0004 = cldr.getResource("image/4_64.png");
        URL url0008 = cldr.getResource("image/8_64.png");
        URL url0016 = cldr.getResource("image/16_64.png");
        URL url0032 = cldr.getResource("image/32_64.png");
        URL url0064 = cldr.getResource("image/64_64.png");
        URL url0128 = cldr.getResource("image/128_64.png");
        URL url0256 = cldr.getResource("image/256_64.png");
        URL url0512 = cldr.getResource("image/512_64.png");
        URL url1024 = cldr.getResource("image/1024_64.png");
        URL ur12048 = cldr.getResource("image/2048_64.png");
        numberTilles.put(0,new ImageIcon(url0000));
       // numberTilles.put(1,new ImageIcon(url0001));
        numberTilles.put(2,new ImageIcon(url0002));
        numberTilles.put(4,new ImageIcon(url0004));
        numberTilles.put(8,new ImageIcon(url0008));
        numberTilles.put(16,new ImageIcon(url0016));
        numberTilles.put(32,new ImageIcon(url0032));
        numberTilles.put(64,new ImageIcon(url0064));
        numberTilles.put(128,new ImageIcon(url0128));
        numberTilles.put(256,new ImageIcon(url0256));
        numberTilles.put(512,new ImageIcon(url0512));
        numberTilles.put(1024,new ImageIcon(url1024));
        numberTilles.put(2048,new ImageIcon(ur12048));
    }
    class GameBoard extends JPanel
    {
 
        protected void paintComponent(Graphics g)
        {
            g.setColor(new Color(20,20,20));
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            int[][] board = game.getGameBoard();
            for(int y = 1;y <5 ; y++)
            {
                for(int x = 1;x < 5; x++)
                {
                    int X = (8*x)+(64*(x-1));
                    int Y = (8*y)+(64*(y-1));
                    int thisNumber = board[y-1][x-1];
                    if(numberTilles.containsKey(thisNumber))
                    {
                        ImageIcon thisTile = (ImageIcon) numberTilles.get(thisNumber);
                        thisTile.paintIcon(this, g, X, Y);
                    }
                }
            }
        }
    }
    class WinBoard extends JPanel{
        protected void paintComponent(Graphics g)
        {
            g.setColor(new Color(20,20,20));
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setFont(largeFeedbackFont);
            g.setColor(new Color(0,80,0));
            g.drawString("You Win!", 20, 10);
            g.setFont(smallFeedbackFont);
            g.setColor(new Color(255,255,255));
            g.drawString("Press Enter to play again..",20,30);
        }
    }
    class LoseBoard extends JPanel{
        protected void paintComponent(Graphics g)
        {
            g.setColor(new Color(20,20,20));
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setFont(largeFeedbackFont);
            g.setColor(new Color(80,0,0));
            g.drawString("You Lose!", 20, 10);
            g.setFont(smallFeedbackFont);
            g.setColor(new Color(255,255,255));
            g.drawString("Press Enter to try again..",20,30);
        }
    }
    class MyFrame extends JFrame implements KeyListener
    {
        public void keyPressed(KeyEvent e)
        {
            
        }
        public void keyReleased(KeyEvent e)
        {
            int key = e.getKeyCode();
            if(game.getState() == GameState.CONTINUE)
            {
                if(key== KeyEvent.VK_UP){
                    System.out.print("Up key pressed...");
                    game.pushUp();
                    game.addNewNumber();
                    game.checkState();
                    game.printArray();
                    gb.repaint();
                }
                else if(key == KeyEvent.VK_DOWN)
                {
                    System.out.print("Down Key Pressed");
                    game.pushDown();
                    game.addNewNumber();
                    game.checkState();
                    game.printArray();
                    gb.repaint();
                }
                else if(key ==KeyEvent.VK_LEFT)
                {
                    System.out.print("Left Key Pressed");
                    game.pushLeft();
                    game.addNewNumber();
                    game.checkState();
                    game.printArray();
                    gb.repaint();
                }
                else if(key == KeyEvent.VK_RIGHT)
                {
                    System.out.print("right Key Pressed");
                    game.pushRight();
                    game.addNewNumber();
                    game.checkState();
                    game.printArray();
                    gb.repaint();
                }
                GameState gs = game.getState();
                if(gs == GameState.LOSE)
                {
                    frame.getContentPane().remove(gb);
                    frame.getContentPane().add(new LoseBoard(),BorderLayout.CENTER);
                }
                else if(gs == GameState.WIN)
                {
                    frame.getContentPane().remove(gb);
                    frame.getContentPane().add(new WinBoard(),BorderLayout.CENTER);
                }
            }
        }
        public void keyTyped(KeyEvent e)
        {
            
        }
    }
}
