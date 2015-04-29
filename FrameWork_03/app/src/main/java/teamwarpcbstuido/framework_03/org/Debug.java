package teamwarpcbstuido.framework_03.org;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowManager;

public class Debug {

	//#deifne
	int X = 0;
	int Y = 1;
	
	int STANDSTATE = 0;
	int WALKSTATE = 1;
	int JUMPSTATE= 2;
	
	int REDCOLOR = 0;
	int BLUECOLOR = 1;
	int BLACKCOLOR = 2;
	//
	String text;
	int width,height;
	int DPI[] = new int[2];
	
	//paint
	Paint paint[] = new Paint[4];
	 public Debug(Context context){
		Display dispaly = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); //((WindowManager)context.getSystemService(Context.WIFI_SERVICE)).getDefaultDisplay();
		width = dispaly.getWidth();
		height = dispaly.getHeight();	
		DPI[X] = width / 18;
		DPI[Y] = height / 32;	
		InitPaint();

	 }
	 public Debug(){
         InitPaint();
     }
	 void InitPaint(){			
			paint[0] = new Paint();
			paint[1] = new Paint();
			paint[2] = new Paint();
			paint[3] = new Paint();
			
			paint[0].setStyle(Style.STROKE);
			paint[0].setColor(Color.RED); 
		
			paint[1].setStyle(Style.STROKE);
			paint[1].setColor(Color.BLUE); 
			
			paint[2].setStrokeCap(Paint.Cap.ROUND);
			paint[2].setColor(Color.BLACK);
	
			paint[3].setColor(Color.WHITE);
	 }


    public static void drawText(Canvas canvas,int data,int x,int y,int size, int color){
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(color);
        paint.setTextSize(size);
        String text;
        text = Integer.toString(data);
        canvas.drawText(text, x, y, paint);
    }
    public static void drawText(Canvas canvas,float data,int x,int y,int size, int color){
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(color);
        paint.setTextSize(size);
        String text;
        text = Float.toString(data);
        canvas.drawText(text, x, y, paint);
    }
    public static void drawText(Canvas canvas, String data,int x,int y,int size, int color){
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(color);
        paint.setTextSize(size);
        canvas.drawText(data, x, y, paint);
    }


	 void intRED(Canvas canvas, int data, int posX, int posY,int size){
		 paint[REDCOLOR].setTextSize(size);
		 text = Integer.toString(data);
		 canvas.drawText(text, posX, posY, paint[REDCOLOR]);
		 paint[REDCOLOR].setTextSize(15);
		 
	 }
	 void intBLUE(Canvas canvas, int data, int posX, int posY,int size){
		 paint[BLUECOLOR].setTextSize(size);
		 text = Integer.toString(data);
		 canvas.drawText(text, posX, posY, paint[BLUECOLOR]);
	 }
	 void intBLACK(Canvas canvas, int data, int posX, int posY,int size){
		 paint[BLACKCOLOR].setTextSize(size);
		 text = Integer.toString(data);
		 canvas.drawText(text, posX, posY, paint[BLACKCOLOR]);
	 }
	 void intBLACK(Canvas canvas, String str,int data, int posX, int posY,int size){
		 paint[BLACKCOLOR].setTextSize(size);
		 text = Integer.toString(data);
		 canvas.drawText(str + text, posX, posY, paint[BLACKCOLOR]);
	 }	 
	 void floatRED(Canvas canvas, float data, int posX, int posY,int size){
		 paint[REDCOLOR].setTextSize(size);
		 text = Float.toString(data);
		 canvas.drawText(text, posX, posY, paint[REDCOLOR]);
	 }
	 void floatBLUE(Canvas canvas, float data, int posX, int posY,int size){
		 paint[BLUECOLOR].setTextSize(size);
		 text = Float.toString(data);
		 canvas.drawText(text, posX, posY, paint[BLUECOLOR]);
	 }
	 void flaotBLACK(Canvas canvas, float data, int posX, int posY,int size){
		 paint[BLACKCOLOR].setTextSize(size);
		 text = Float.toString(data);
		 canvas.drawText(text, posX, posY, paint[BLACKCOLOR]);
	 }
	 
	 void textRED(Canvas canvas, String text, int posX, int posY,int size){
		 paint[REDCOLOR].setTextSize(size);
		 canvas.drawText(text, posX, posY, paint[REDCOLOR]);
	 }  
	 void booleanRED(Canvas canvas, boolean flag, int posX, int posY,int size){
	       paint[REDCOLOR].setTextSize(size);
	       if (flag)canvas.drawText("true", posX, posY, paint[REDCOLOR]);
	       else  canvas.drawText("false", posX, posY, paint[REDCOLOR]);
	       paint[REDCOLOR].setTextSize(15);
			 
	     }
	  void booleanBLUE(Canvas canvas, boolean flag, int posX, int posY,int size){
		  paint[BLUECOLOR].setTextSize(size);
	       if (flag)  canvas.drawText("true", posX, posY, paint[BLUECOLOR]);
	       else canvas.drawText("false", posX, posY, paint[BLUECOLOR]);
	       paint[BLUECOLOR].setTextSize(15); 
	     }
	  
	  //
	  
	void rectAlpah(Canvas canvas,String color,Rect rect,int alpha){
		 int type = 0;
		 if(color == "RED")type = REDCOLOR;
		 if(color == "BLUE")type = BLUECOLOR;
		 if(color == "BLACK")type = BLACKCOLOR;
		 int tmp = paint[type].getAlpha();
		 paint[type].setAlpha(alpha);
		 canvas.drawRect(rect, paint[type]);
		 
		 paint[type].setAlpha(tmp);
	 }
	
	 void WHITEBackground(Canvas canvas){
		 Rect r;
		 r = new Rect(0, 0, width, height);
		 canvas.drawRect(r,paint[3]);
		 Line(canvas);
	 }
	 public void drawDebug(Canvas canvas,Rect rect){
		 Paint paint = new Paint();
		 paint.setColor(Color.RED);
		 paint.setStyle(Style.STROKE);
		 canvas.drawRect(rect, paint);	
	}
	  void Line(Canvas canvas){	
		  int num  = 0;
		  paint[REDCOLOR].setTextSize(20);
		  for(int i = 0; i < width; i += DPI[X]){
				String text = Integer.toString(num); 
				canvas.drawText(text, i, 15, paint[REDCOLOR]);
				canvas.drawLine(i, 1, i, height, paint[BLACKCOLOR]);
				num++;
			}
		  num = 0;
		 for(int j = 0; j < height; j+= DPI[Y]){
				String text = Integer.toString(num); 
				canvas.drawText(text, 15, j, paint[REDCOLOR]);
				canvas.drawLine(1, j, width, j, paint[BLACKCOLOR]);			
				num++;
		 }

		  paint[REDCOLOR].setTextSize(15);
		}//Line


}


















