package shawn.c4q.nyc.customviewswithdrawing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by shawnspeaks on 1/9/17.
 */

public class BouncingBallCusomView extends View {

    private int radius;
    private int centerBallX;
    private int centerBallY;
    private int xSpeed;
    private int ySpeed;
    private int directionX = 1;
    private int directionY = 1;
    private int paintColor;
    private Paint drawPaint;

    public BouncingBallCusomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BouncingBallCusomView);
        int arraySz = array.getIndexCount();
        for(int i = 0; i < arraySz; i++){
            int attr = array.getIndex(i);
            switch(attr){
                case R.styleable.BouncingBallCusomView_radius:
                    radius = (int) array.getDimension(attr, 0);
                    centerBallX = getLeft() + radius;
                    centerBallY = getTop() + radius;
                    break;
                case R.styleable.BouncingBallCusomView_xSpeed:
                    xSpeed = (int) array.getDimension(attr, 0);
                    break;
                case R.styleable.BouncingBallCusomView_ySpeed:
                    ySpeed = (int) array.getDimension(attr, 0);
                    break;
                case R.styleable.BouncingBallCusomView_ballColor:
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
        canvas.drawCircle(centerBallX, centerBallY, radius, drawPaint);

            if(centerBallX >= getWidth()-radius){
                directionX *= -1;
                paintColor = randomPaintColor();
            }
            if(centerBallX < radius){
                directionX *= -1;
                paintColor = randomPaintColor();
            }
            if(centerBallY >= getBottom()-radius){
                directionY *= -1;
                paintColor = randomPaintColor();
            }
            if(centerBallY < getTop()) {
                directionY *= -1;
                paintColor = randomPaintColor();
            }
        drawPaint.setColor(paintColor);
        centerBallX += directionX * xSpeed;
        centerBallY += directionY * ySpeed;
    }


    public int randomPaintColor(){
        int[] colorArray = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        Random random = new Random();
        return colorArray[random.nextInt(4)];
    }
}