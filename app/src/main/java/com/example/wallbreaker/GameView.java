package com.example.wallbreaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View {
    private int xMax, yMax;
    private int coordX=500, coordY=500;
    private int radius=50;
    private int speedX= 20, speedY=20;

    private int playerX, playerY;
    private int playerWidth= 300, playerHeight=100;
    private ArrayList<Brick> wall= new ArrayList<Brick>();

    public GameView(Context context) {
        super(context);
        int startX= 20, startY=20, nbrLine= 3;
        for(int i=0; i<5; i++){
            for(int level=0; level<nbrLine; level++){
                wall.add(new Brick(startX + 205 * i, startY + 105 * level));
            }
        }
    }

    public void restardGame(){
        coordX= 500;
        coordY= 500;
        speedX= 20;
        speedY= 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF ballBounds = new RectF();
        ballBounds.set(coordX-radius, coordY-radius, coordX+radius, coordY+radius);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawOval(ballBounds, paint);

        paint.setColor(Color.WHITE);
        canvas.drawRect(playerX, playerY, playerX+playerWidth, playerY+playerHeight, paint);

        for(Brick brick: wall){
            if(!brick.isBroken()) {
                brick.draw(canvas, paint);
            }
        }

        update();
        collision();
        // Delay
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) { }
        invalidate();
    }

    public void update(){
        coordX+= speedX;
        coordY+= speedY;
    }

    public void collision(){
        //Collision mur
        if((coordX+radius >= xMax) || (coordX - radius <= 0)){
            speedX= -speedX;
            //Collision plafond
        }else if(coordY - radius <= 0){
            speedY= -speedY;
            //collision player
        }else if(coordY+radius>= playerY &&
                coordX+radius>= playerX &&
                coordX-radius <= playerX + playerWidth){
            speedY= -speedY;
        }

        for(Brick brick: wall){
            if(!brick.isBroken() && brick.isColision(coordX, coordY, radius)){
                brick.setBroken(true);
                speedY= -speedY;
            }
        }

        //if lose
        if(coordY-radius>=yMax){
            restardGame();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        xMax= w-1;
        yMax= h-1;

        playerX= w/2;
        playerY= h-100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        if (eventX < playerX) {
            playerX -= 100;
        }else if(eventX >  playerX + playerWidth){
            playerX += 100;
        }
        return true;
    }
}
