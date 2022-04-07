package com.example.wallbreaker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Brick {
    private int coordX, coordY;
    private  int width = 200, height= 100;
    private boolean isBroken= false;

    public Brick(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.WHITE);
        canvas.drawRect(coordX, coordY, coordX+width, coordY+height, paint);
    }

    public boolean isColision(int coordBallX, int coordBallY, int radiusBall){
        for(int i=coordBallX-radiusBall; i<=coordBallX+radiusBall; i++) {
            for(int j=coordBallY-radiusBall; j<=coordBallY+radiusBall;j++){
                if(i >= coordX && i <=coordX+width
                        && j >= coordY && j<= coordY+height){
                    return  true;
                }
            }
        }
        return false;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }
}
