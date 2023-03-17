import java.awt.*;

public class Paddle extends Rectangle {

    int id;
    Paddle(int x,int y, int PADDLE_WIDTH,int PADDLE_HEIGHT,int id)
    {
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }
    public void draw(Graphics g)
    {
        if(id==1)
          g.setColor(Color.red);

        if(id==2)
            g.setColor(Color.blue);

        g.fillRect(x,y,width,height);
    }


}
