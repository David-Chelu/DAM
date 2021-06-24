package com.example.dam;

import android.graphics.Point;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton centerButton;
    private FloatingActionButton outlineButton;
    private RelativeLayout layout;
    private TextView piValueTextView;
    private Point plottedButton;

    private boolean initialized = false;
    private float centerX, centerY, touchX, touchY;
    private double piValue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.relativeLayout);
        centerButton = findViewById(R.id.centerButton);
        outlineButton = findViewById(R.id.outlineButton);
        piValueTextView = findViewById(R.id.piValueTextView);

        final Handler handler = new Handler();

        setTitle("PI Values");
        layout.setOnTouchListener(tapHandle);

        centerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                setTimer(30);
            }
        });

        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                updateGUI();

                handler.postDelayed(this, 50);
            }
        }, 100);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
//            updateValues();
            initialize();
            layout.requestFocus();
        }
    }

    private void initialize()
    {
        if (!initialized)
        {
            centerX = centerButton.getX() + centerButton.getWidth()  / 2;
            centerY = centerButton.getY() + centerButton.getHeight() / 2;

            piValueTextView.setX(10);
            piValueTextView.setY(10);

            initialized = true;
        }
    }

    private void updateGUI()
    {
        new Thread()
        {
            public void run()
            {
                if (layout.isFocused())
                {
                    try
                    {
                        runOnUiThread(() -> {
                            moveOutlineButton();
                        });
                        Thread.sleep(100);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private View.OnTouchListener tapHandle = new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent e)
        {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchX = e.getX();
                    touchY = e.getY();
                    piValue = CircleFunctions.pointsToAngle(centerX, centerY, touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }

            return false;
        }
    };

    private void moveOutlineButton()
    {
//        piValueTextView.setText(Double.toString(-piValue) + " rad");
        piValueTextView.setText("Rads\n" + String.format("%.02f", -piValue));

        plottedButton = CircleFunctions.getPointOnCircle(centerX, centerY, piValue, centerButton.getWidth() / 2);

        outlineButton.setX(plottedButton.x - outlineButton.getWidth() / 2);
        outlineButton.setY(plottedButton.y - outlineButton.getHeight() / 2);
    }
}