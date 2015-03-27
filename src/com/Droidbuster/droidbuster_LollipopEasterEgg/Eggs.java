package com.Droidbuster.droidbuster_LollipopEasterEgg;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

@SuppressLint("WorldReadableFiles")
public class Eggs extends Activity {

	SharedPreferences pref;
	SharedPreferences.Editor edit;
	Boolean enabled;
	String current_egg;
	ImageView  lpImg;
	ImageView openLayout;
	RelativeLayout logLayout;

	@SuppressLint("CommitPrefEdits")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		pref = getSharedPreferences("easter_preference",
				Context.MODE_WORLD_READABLE);
		edit = pref.edit();

		enabled = pref.getBoolean("enabled", false);
		current_egg = pref.getString("egg_name", "");
		
		colorize();

		if (enabled == false) {

			decolorize();

		} 
			
	
	
		
	
		
	}
	
	 
	
	
	
	public void colorize() {

		setColorFilter(lpImg, 255, 58, 118);

	}
	
	public void decolorize() {

		setColorFilter(lpImg, 100, 100, 100);
		
	}
	
	public void lollipop(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Eggs .this,MainActivity.class);
		startActivity(intent);
		
	}
	
	public static void setColorFilter(ImageView iv, float redValue, float greenValue,
			float blueValue) {

		redValue = redValue / 255;
		blueValue = blueValue / 255;
		greenValue = greenValue / 255;

		/*
		 * However I have learned about different positions of matrix by hit and
		 * trial, here I'll explain what little I got from it.
		 * 
		 * Color Matrix is a 4x5 matrix (4 rows and 5 columns) with 20 elements.
		 * Every element has a role play, but I'll only discuss elements that
		 * I've used, because I'm not sure about others, and I'm little unsure
		 * about these too.
		 * 
		 * The below results are purely based on my observations by hit and
		 * trial and may be entirely wrong for any other image. Image here used
		 * was kitkat easter egg with black bg and white and grey fg.
		 * 
		 * a(1,1) = RED value of white foreground a(2,2) = GREEN value of white
		 * foreground a(3,3) = BLUE value of white foreground
		 * 
		 * (According to my input, they should've given grey color, but they
		 * give translucent white (more opaque when you're nearer to one),
		 * however I wanted that only :p
		 * 
		 * a(1,4) = RED value of black background a(2,4) = GREEN value of black
		 * background a(3,4) = BLUE value of black background
		 * 
		 * (They somehow also control alpha(opacity) of image)
		 * 
		 * a(4,4) = Overall alpha of image
		 * 
		 * The elements of matrix require scale values in RGB. Meaning value
		 * should be between 0 and 1 in float. This should explain my need to
		 * divide values by 255 as RGB values range from 0 to 255
		 * 
		 * So, matrix should look like this (F = Foreground, B = Background)
		 * 
		 * | RF, 0 , 0 , RB, 0, | | 0 , GF, 0 , GB, 0, | | 0 , 0 , BF, BB, 0, |
		 * | 0 , 0 , 0 , AA, 0 |
		 */

		float[] colorMatrix = { 0.5f, 0, 0, redValue, 0, 0, 0.5f, 0,
				greenValue, 0, 0, 0, 0.5f, blueValue, 0, 0, 0, 0, 1, 0 };

		ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);

		iv.setColorFilter(colorFilter);

	}

	

}
