package shawn.c4q.nyc.customviewswithdrawing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shawnspeaks on 1/9/17.
 */

public class DrawingView extends View {

    int radius = 50;
    int rightBound = getWidth()-radius;
    int leftBound = radius;
    int centerx = radius;
    int centery = radius;
    int xSpeed;
    int ySpeed;



    int directionx = 1;
    int directiony = 1;

    private int paintColor;

    private Paint drawPaint;


    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DrawingView);
        final int arraySz = array.getIndexCount();
        for(int i = 0; i < arraySz; i++){
            int attr = array.getIndex(i);
            switch(attr){
                case R.styleable.DrawingView_radius:
                    radius = array.getInteger(attr, 0);
                    break;
                case R.styleable.DrawingView_xSpeed:
                    xSpeed = (int) array.getDimension(attr, 0);
                    break;
                case R.styleable.DrawingView_ySpeed:
                    ySpeed = (int) array.getDimension(attr, 0);
                    break;
                case R.styleable.DrawingView_ballColor:
                    paintColor = array.getColor(attr, Color.BLUE);
                    break;
            }
        }
        array.recycle();

        setFocusable(true);
        setFocusableInTouchMode(true);
        createPaint();
    }


    private void createPaint(){
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(centerx, centery, radius, drawPaint);

            if(centerx >= getWidth()-radius){
                directionx*= -1;
                radius+=1;
            }
            if(centerx < radius){
                directionx*= -1;
                radius+=1;
            }

            if(centery >= getBottom()){
                directiony*= -1;
                radius+=1;

            }
            if(centery < getTop()){
                directiony*= -1;
                radius+=1;
            }




            centerx += directionx * xSpeed;
            centery += directiony * ySpeed*2;

    }


}