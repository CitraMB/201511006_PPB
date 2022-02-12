package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggeButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggeButton = (ToggleButton) findViewById(R.id.OffOn);
        //R read ID togglebutton yang namanya OffOn
        imageView = (ImageView) findViewById(R.id.BalingBaling);
        switchButton = (Switch) findViewById(R.id.Lights);
        seekBar = (SeekBar) findViewById(R.id.SpeedSeek);

        rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        toggeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateOn();
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LigthOn();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //rotate the fan based on progress parameter
                RotateOn();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    protected void RotateOn (){
        if(toggeButton.isChecked()){
            rotateAnimator.setDuration(SPEED[seekBar.getProgress()]);
            rotateAnimator.start();
        }else{
            rotateAnimator.end();
        }
    }

    protected void LigthOn (){
        if (switchButton.isChecked()){
            gd.setColors(new int[]{ Color.YELLOW , Color.TRANSPARENT });
            imageView.setBackground(gd);
        }else{
            imageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}