package shawn.c4q.nyc.customviewswithdrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawnspeaks on 1/9/17.
 */

public class MSPaintView extends ImageView {

    private int paintColor = Color.BLACK;

    private Paint drawPaint;

    private List<Point> circlePoints;

    int position = 0;

    private int[] paintBrush = new int[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE} ;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));


        drawPaint.setColor(paintBrush[position]);

        // indicate view should be redrawn
        postInvalidate();
        return true;
    }


    public MSPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createPaint();
        circlePoints = new ArrayList<Point>();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        for (Point p : circlePoints) {
            if(position < 3 ) {
                position++;
            }else
                position = 0;
            drawPaint.setColor(paintBrush[position]);
            canvas.drawCircle(p.x, p.y, 20, drawPaint);
        }
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



}
