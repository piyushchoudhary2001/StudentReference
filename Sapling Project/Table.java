import javax.swing.*;
import java.awt.*;

public class Table extends JPanel implements  Runnable{
    int GAME_WIDTH=1000;
    int GAME_HEIGHT= (int) (1000*(0.555));
    Dimension SCREEN_SIZE= new Dimension(GAME_WIDTH,GAME_HEIGHT);

    int BALL_DIAMETER=20;

    int PADDLE_HEIGHT=100;
    int PADDLE_WIDTH=25;

    Image image;

    Ball ball;
    Graphics graphics;

    Thread gameThread;

    Paddle paddle1;
    Paddle paddle2;

    Table()
    {
        this.setPreferredSize(SCREEN_SIZE);
        gameThread=new Thread(this);
        gameThread.start();
        newBall();
        newPaddle();
    }

    private void newPaddle() {
        paddle1=new Paddle(0,GAME_HEIGHT/2-PADDLE_HEIGHT/2,PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2=new Paddle(GAME_WIDTH-PADDLE_WIDTH,GAME_HEIGHT/2-PADDLE_HEIGHT/2,PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    private void newBall() {
       ball= new Ball(0,0,BALL_DIAMETER,BALL_DIAMETER);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }

    private void draw(Graphics graphics) {
        ball.draw(graphics);
        paddle1.draw(graphics);
        paddle2.draw(graphics);
    }
    public void move()
    {
        ball.move();
    }

    public void Collision()
    {
        if(ball.y<=0)
        {
            ball.yVelocity=-ball.yVelocity;
        }
        if(ball.y>=GAME_HEIGHT-BALL_DIAMETER)
        {
            ball.yVelocity=-ball.yVelocity;
        }

        if(ball.x<=0)
        {
            ball.xVelocity=-ball.xVelocity;
        }
        if(ball.x>=GAME_WIDTH-BALL_DIAMETER)
        {
            ball.xVelocity=-ball.xVelocity;
        }


    }

    @Override
    public void run(){
        long LastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0;
        while(true)
        {
            long now=System.nanoTime();
            delta+=(now-LastTime)/ns;
            LastTime=now;
            if(delta>=1)
            {
                move();
                repaint();
                Collision();
                delta--;
            }

        }
    }




}
