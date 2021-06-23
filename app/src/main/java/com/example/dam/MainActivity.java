package com.example.dam;

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

    private boolean initialized = false;
    private float centerX, centerY, touchX, touchY, piValue;

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
                piValueTextView.setText(Float.toString(centerX));
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

            outlineButton.setX(centerX + centerButton.getWidth() / 2 - outlineButton.getWidth() / 2);
            outlineButton.setY(centerY - outlineButton.getHeight() / 2);

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
                            piValueTextView.setText(Float.toString(touchX));
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
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }

            return false;
        }
    };

    private void moveOutlineButton()
    {

    }
}