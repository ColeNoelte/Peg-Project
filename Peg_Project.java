import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.*;
//import java.util.*;
import javafx.geometry.*;

class Dots extends Canvas
{
   public Dots()
   {
      setWidth(80);
      setHeight(80);
   }
   
   //draw method takes in the x number and draws that number of lines with slightly different colors
   public void draw()
   {
      GraphicsContext gc = getGraphicsContext2D();
      
      
      //sets background
      gc.setFill(Color.GREEN);
      gc.fillOval(0,0,80,80);
      
   }
   
   public void clear()
   {
       GraphicsContext gc = getGraphicsContext2D();
       
       gc.clearRect(0,0,80,80);
   }
}




public class Peg_Project extends Application
{   
   public void start(Stage stage)
   {
    ButtonListener myListener = new ButtonListener();
    label.setPrefSize(520,20);
    label.setAlignment(Pos.BOTTOM_CENTER);
    root.setTop(label);
    fp.setPrefSize(520,580);
    filler.setPrefSize(40,600);
    fp.setHgap(10);
    fp.setVgap(10);
    
    for(int i=0;i<4;i++)
      {
         for(int j=0;j<4;j++)
         {
            buttonRightArray[i][j]=new Button();
            buttonLeftArray[i][j]=new Button();
            buttonUpArray[i][j]=new Button();
            buttonDownArray[i][j]=new Button();
            
            
            panes[i][j]= new GamePane(i,j);
            if((i==2)&&(j==0))
               {
                panes[i][j].falseLeft();
                panes[i][j].falseRight();
                panes[i][j].falseUp();
                panes[i][j].falseDown();
                panes[i][j].falseBall();
                panes[i][j].draw(i,j);
               }
            else
               {
                panes[i][j].trueLeft();
                panes[i][j].trueRight();
                panes[i][j].trueUp();
                panes[i][j].trueDown();
                panes[i][j].trueBall();
               }
            
            fp.getChildren().add(panes[i][j]);
         }
      }
      
      check();
      root.setLeft(filler);
      root.setCenter(fp);
      //scene setup
      Scene scene = new Scene(root,600,600);
      stage.setScene(scene);
      stage.setTitle("Ball Game");
      stage.show();
   }
   
   public void check()
   {
    moves=0;
    balls=0;
    for(int a=0;a<4;a++)
      {
         for(int b=0;b<4;b++)
         {
          if(panes[a][b].returnBall())
             {
              balls++;
             }
          if((a==0)||(a==1))
             {
              panes[a][b].falseDown();
             }
          if((a==3)||(a==2))
             {
              panes[a][b].falseUp();
             }
          if((b==3)||(b==2))
             {
              panes[a][b].falseLeft();
             }
          if((b==0)||(b==1))
             {
              panes[a][b].falseRight();
             }
          
          if((panes[a][b].returnUp())&&!(panes[a+2][b].returnBall())&&(panes[a+1][b].returnBall())&&(panes[a][b].returnBall()))
             {
              moves++;
             }
          else
             {
              panes[a][b].falseUp();
             }
          if((panes[a][b].returnDown())&&!(panes[a-2][b].returnBall())&&(panes[a-1][b].returnBall())&&(panes[a][b].returnBall()))
             {
              moves++;
             }
          else
             {
              panes[a][b].falseDown();
             }
          if((panes[a][b].returnLeft())&&!(panes[a][b+2].returnBall())&&(panes[a][b+1].returnBall())&&(panes[a][b].returnBall()))
             {
              moves++;
             }
          else
             {
              panes[a][b].falseLeft();
             }
          if((panes[a][b].returnRight())&&!(panes[a][b-2].returnBall())&&(panes[a][b-1].returnBall())&&(panes[a][b].returnBall()))
             {
              moves++;
             }
          else
             {
              panes[a][b].falseRight();
             }
          panes[a][b].draw(a,b);
          panes[a][b].trueRight();
          panes[a][b].trueLeft();
          panes[a][b].trueUp();
          panes[a][b].trueDown();
         }
      }
    if((moves==0)&&(balls==1))
       {
        label.setText("You won!!!");
       }
    else if(moves==0)
       {
        label.setText("You lost!");
       }
    else
       {
        label.setText("Balls Left: "+balls+"  Possible Moves: "+moves);
       }
    
   }
   
   public void click(String pressed, int a, int b)
   {
    panes[a][b].falseLeft();
    panes[a][b].falseRight();
    panes[a][b].falseUp();
    panes[a][b].falseDown();
    panes[a][b].falseBall();
    panes[a][b].draw(a,b);
    if(pressed.equals("up"))
       {
        panes[a+1][b].falseLeft();
        panes[a+1][b].falseRight();
        panes[a+1][b].falseUp();
        panes[a+1][b].falseDown();
        panes[a+1][b].falseBall();
        panes[a+1][b].draw(a+1,b);
        
        panes[a+2][b].trueLeft();
        panes[a+2][b].trueRight();
        panes[a+2][b].trueUp();
        panes[a+2][b].trueDown();
        panes[a+2][b].trueBall();
        panes[a+2][b].draw(a+2,b);
       }
    else if(pressed.equals("down"))
       {
        panes[a-1][b].falseLeft();
        panes[a-1][b].falseRight();
        panes[a-1][b].falseUp();
        panes[a-1][b].falseDown();
        panes[a-1][b].falseBall();
        panes[a-1][b].draw(a-1,b);
        
        panes[a-2][b].trueLeft();
        panes[a-2][b].trueRight();
        panes[a-2][b].trueUp();
        panes[a-2][b].trueDown();
        panes[a-2][b].trueBall();
        panes[a-2][b].draw(a-2,b);
       }
    else if(pressed.equals("left"))
       {
        panes[a][b+1].falseLeft();
        panes[a][b+1].falseRight();
        panes[a][b+1].falseUp();
        panes[a][b+1].falseDown();
        panes[a][b+1].falseBall();
        panes[a][b+1].draw(a,b+1);
        
        panes[a][b+2].trueLeft();
        panes[a][b+2].trueRight();
        panes[a][b+2].trueUp();
        panes[a][b+2].trueDown();
        panes[a][b+2].trueBall();
        panes[a][b+2].draw(a,b+2);
       }
    else if(pressed.equals("right"))
       {
        panes[a][b-1].falseLeft();
        panes[a][b-1].falseRight();
        panes[a][b-1].falseUp();
        panes[a][b-1].falseDown();
        panes[a][b-1].falseBall();
        panes[a][b-1].draw(a,b-1);
        
        panes[a][b-2].trueLeft();
        panes[a][b-2].trueRight();
        panes[a][b-2].trueUp();
        panes[a][b-2].trueDown();
        panes[a][b-2].trueBall();
        panes[a][b-2].draw(a,b-2);
       }
   }
   
   //private variable setup
   private FlowPane fp = new FlowPane();
   private FlowPane filler = new FlowPane();
   private BorderPane root = new BorderPane();
   private ButtonListener myListener = new ButtonListener();
   private Button[][] buttonRightArray= new Button[4][4];
   private Button[][] buttonLeftArray= new Button[4][4];
   private Button[][] buttonUpArray= new Button[4][4];
   private Button[][] buttonDownArray= new Button[4][4];
   private GamePane[][] panes = new GamePane[4][4];
   private int a;
   private int b;
   private String pressed;
   private int moves;
   private int balls;
   private Label label = new Label("Balls: "+balls+"  Moves: "+moves);
   
   public class ButtonListener implements EventHandler<ActionEvent>  
   {
      public void handle(ActionEvent e) 
      {
       
       for(int i=0;i<4;i++)
          {
           for(int j=0;j<4;j++)
              {
               if(e.getSource()== buttonUpArray[i][j])
                  {
                   pressed="up";
                   a=i;
                   b=j;
                  }
               else if(e.getSource()== buttonDownArray[i][j])
                  {
                   pressed="down";
                   a=i;
                   b=j;
                  }
               else if(e.getSource()== buttonLeftArray[i][j])
                  {
                   pressed="left";
                   a=i;
                   b=j;
                  }
               else if(e.getSource()== buttonRightArray[i][j])
                  {
                   pressed="right";
                   a=i;
                   b=j;
                  }
              }
          }
       click(pressed,a,b);
       check();
      }
   }

  class GamePane extends GridPane
  {
    Dots cc = new Dots();
    boolean ball= true;
    boolean up= true;
    boolean down= true;
    boolean right= true;
    boolean left= true;
        
    
    public GamePane(int x, int y)
    {     
          buttonRightArray[x][y].setPrefSize(20,80);
          buttonLeftArray[x][y].setPrefSize(20,80);
          buttonUpArray[x][y].setPrefSize(80,20);
          buttonDownArray[x][y].setPrefSize(80,20);
          
          buttonRightArray[x][y].setOnAction(myListener);
          buttonLeftArray[x][y].setOnAction(myListener);
          buttonUpArray[x][y].setOnAction(myListener);
          buttonDownArray[x][y].setOnAction(myListener);
      
      
          draw(x,y);    
          
          add(buttonUpArray[x][y],1,0);
          add(buttonRightArray[x][y],2,1);
          add(buttonLeftArray[x][y],0,1);
          add(buttonDownArray[x][y],1,2);
          add(cc,1,1);
 
   }
   
      
   public void draw(int i, int j)
   {
    
        buttonRightArray[i][j].setVisible(false);
        buttonLeftArray[i][j].setVisible(false);
        buttonUpArray[i][j].setVisible(false);
        buttonDownArray[i][j].setVisible(false);
       
    
        if(ball)
           {
            cc.draw();
           }
        else
           {
            cc.clear();
           }
        buttonRightArray[i][j].setVisible(right);
        buttonLeftArray[i][j].setVisible(left);
        buttonUpArray[i][j].setVisible(up);
        buttonDownArray[i][j].setVisible(down);
       
    
   }
   
   public Boolean returnBall()
   {
    return ball;
   }
   
   public Boolean returnUp()
   {
    return up;
   }
   
   public Boolean returnDown()
   {
    return down;
   }
   
   public Boolean returnRight()
   {
    return right;
   }
   
   public Boolean returnLeft()
   {
    return left;
   }
   
   public void falseUp()
   {
    up=false;
   }
   
   public void falseDown()
   {
    down=false;
   }
   
   public void falseRight()
   {
    right=false;
   }
   
   public void falseLeft()
   {
    left=false;
   }
   
   public void falseBall()
   {
    ball=false;
   }
   
   public void trueBall()
   {
    ball=true;
   }
   
   public void trueUp()
   {
    up=true;
   }
   
   public void trueDown()
   {
    down=true;
   }
   
   public void trueRight()
   {
    right=true;
   }
   
   public void trueLeft()
   {
    left=true;
   }
  }

   public static void main(String[] args)
   {
      launch(args);
   }

}