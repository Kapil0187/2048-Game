package java2048;
import java.util.*;
public class Game {
    private int[][] gameBoard;
    private Random r = new Random();
    //private boolean gameOver;
    private GameState state;
    public Game()
    {
        gameBoard  = new int[4][4];
        addNewNumber();
        addNewNumber();
        state = GameState.CONTINUE;
    }
    
    public int[][] getGameBoard()
    {
        return gameBoard;
    }
    
    public GameState getState()
    {
        return state;
    }
    public void printArray()
    {
        for(int[] x:gameBoard)
        {
            System.out.println(x[0]+" "+x[1]+" "+x[2]+" "+x[3]);
        }
        System.out.println();
    }
    
    public void addNewNumber()
    {
       
        ArrayList<Integer>  emptySpacesX = new ArrayList<>();
        ArrayList<Integer>  emptySpacesY = new ArrayList<>();
        for(int x = 0 ; x < 4 ; x++)
        {
            for(int y = 0 ; y < 4 ; y++)
            {
                if(gameBoard[x][y]==0)
                {
                    emptySpacesX.add(x);
                    emptySpacesY.add(y);
                }
            }
        }
        int choice = r.nextInt(emptySpacesX.size());
        int numberChooser = r.nextInt(10); // value 0-9
        int newNumber = 2;
        if(numberChooser ==0)
        {
            newNumber = 2;
        }
        int X = emptySpacesX.get(choice);
        int Y = emptySpacesY.get(choice);
        gameBoard[X][Y] = newNumber;
    }
    public void pushUp()
    {
        System.out.println("Pushing Up...");
        for(int y = 0; y < 4 ;y++)
        {
            boolean[] alreadyCombined = new boolean[]{false,false,false,false};
            for(int x = 1 ; x < 4 ; x++)
            {
                if(gameBoard[x][y] != 0)
                {
                   int value = gameBoard[x][y];
                   int X = x-1;
                   while(X>=0 && gameBoard[X][y] ==0 )
                   {
                       X--;
                   }
                   if(X==-1)
                   {
                       gameBoard[0][y] = value;
                       gameBoard[x][y] = 0;
                   }
                   else if(gameBoard[X][y] != value)
                   {
                       gameBoard[x][y] = 0;
                       gameBoard[X+1][y] = value;
                   }
                   else
                   {
                    if(alreadyCombined[X])
                    {
                       gameBoard[x][y] = 0;
                       gameBoard[X+1][y] = value;
                       
                    }
                    else
                    {
                       gameBoard[x][y] = 0;
                       gameBoard[X][y] *= 2;
                       alreadyCombined[X] = true;
                       
                    }
                   }
                }
            }
        }
    }
    public void pushDown()
    {
        System.out.println("Pushing down...");
        
        for(int y = 0; y < 4 ;y++)
        {
            boolean[] alreadyCombined = new boolean[]{false,false,false,false};
            for(int x = 2 ; x >-1 ; x--)
            {
                if(gameBoard[x][y] != 0)
                {
                   int value = gameBoard[x][y];
                   int X = x+1;
                   while(X<=3 && gameBoard[X][y] ==0 )
                   {
                       X++;
                   }
                   if(X==4)
                   {
                       gameBoard[3][y] = value;
                       gameBoard[x][y] = 0;
                   }
                   else if(gameBoard[X][y] != value)
                   {
                       gameBoard[x][y] = 0;
                       gameBoard[X-1][y] = value;
                   }
                   else
                   {
                    if(alreadyCombined[X])
                    {
                       gameBoard[x][y] = 0;
                       gameBoard[X-1][y] = value;
                       
                    }
                    else
                    {
                       gameBoard[x][y] = 0;
                       gameBoard[X][y] *= 2;
                       alreadyCombined[X] = true;
                      
                    }
                   }
                }
            }
        }
    }
    public void pushLeft()
    {
        System.out.println("Pushing Left...");
        
        for(int x = 0; x < 4 ;x++)
        {
            boolean[] alreadyCombined = new boolean[]{false,false,false,false};
            for(int y = 1 ; y < 4 ; y++)
            {
                if(gameBoard[x][y] != 0)
                {
                   int value = gameBoard[x][y];
                   int Y = y-1;
                   while(Y>= 0 && gameBoard[x][Y] ==0 )
                   {
                       Y--;
                   }
                   if(Y==-1)
                   {
                       gameBoard[x][0] = value;
                       gameBoard[x][y] = 0;
                   }
                   else if(gameBoard[x][Y] != value)
                   {
                        gameBoard[x][y] = 0;
                       gameBoard[x][Y+1] = value;
                       
                   }
                   else
                   {
                    if(alreadyCombined[Y])
                    {
                       gameBoard[x][y] = 0;
                       gameBoard[x][Y+1] = value;
                       
                    }
                    else
                    {
                       gameBoard[x][y] = 0;
                       gameBoard[x][Y] *= 2;
                       alreadyCombined[Y] = true;
                       
                    }
                   }
                }
            }
        }
    }
    public void pushRight()
    {
        System.out.println("Pushing Right...");
        
        for(int x = 0; x < 4 ;x++)
        {
            boolean[] alreadyCombined = new boolean[]{false,false,false,false};
            for(int y = 2 ; y >-1 ; y--)
            {
                if(gameBoard[x][y] != 0)
                {
                   int value = gameBoard[x][y];
                   int Y = y+1;
                   while(Y <= 3 && gameBoard[x][Y]==0 )
                   {
                       Y++;
                   }
                   if(Y==4)
                   {
                       gameBoard[x][3] = value;
                       gameBoard[x][y] = 0;
                   }
                   else if(gameBoard[x][Y] != value)
                   {
                        gameBoard[x][y] = 0;
                       gameBoard[x][Y-1] = value;
                      
                   }
                   else
                   {
                    if(alreadyCombined[Y])
                    {
                        gameBoard[x][y] = 0;
                       gameBoard[x][Y-1] = value;
                    }
                    else
                    {
                        gameBoard[x][y] = 0;
                       gameBoard[x][Y] *= 2;
                       alreadyCombined[Y] = true;
                      
                    }
                   }
                }
            }
        }
    }
   // return true if a 2048 is on the board
   public boolean checkFor2048(){
       for(int x=0;x<4;x++)
       {
           for(int y=0;y<4;y++)
           {
             if(gameBoard[x][y] == 2048)
                   return true;
           }    
       }
       return false;
   }
   // return true if the board is full of tiles
   public boolean checkBoardFull(){
       for(int x=0; x<4; x++)
       {
           for(int y=0; y<4; y++)
           {
             if(gameBoard[x][y] == 0)
                   return false;
           }    
       }
       return true;
   }
   //return true if there are any adjacent number
   public boolean checkHasMoves(){
       for(int x=0; x<4; x++)
       {
           for(int y=0; y<4; y++)
           {
               if(x==0)
               {
                   if(y!=0)
                   {
                       if(gameBoard[x][y]==gameBoard[x][y-1])
                       {
                           return true;
                       }
                   }
               }
               else
               {
                   if(y!=0)
                   {
                       if(gameBoard[x][y]==gameBoard[x][y-1])
                       {
                           return true;
                       }
                   }
                   if(gameBoard[x][y]==gameBoard[x-1][y])
                   {
                       return true;
                   }
               }
           }
       }
       return false;
   }
   public void checkState(){
       // determine game state
       if(checkFor2048())
       {
           state = GameState.WIN;
       }
       else if(checkBoardFull())
       {
           if(checkHasMoves())
           {
               state = GameState.CONTINUE;
           }
           else
           {
               state = GameState.LOSE;
           }
       }
       else
       {
         state = GameState.CONTINUE;
       }
   }
}
 