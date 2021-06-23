package com.example.dam;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton centerButton;
    private FloatingActionButton outlineButton;
    private RelativeLayout layout;
    private TextView piValueTextView;

    private boolean initialized = false;
    private float centerX, centerY, touchX, touchY;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.relativeLayout);
        // text = findViewById(R.id.text);
        centerButton = findViewById(R.id.centerButton);
        outlineButton = findViewById(R.id.outlineButton);
        piValueTextView = findViewById(R.id.piValueTextView);

        // final Handler h = new Handler();

        // setTitle("Sleep Timer");
        // layout.setOnTouchListener(handleTouch);

        centerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                setTimer(30);
            }
        });

        /*h.postDelayed(new Runnable()
        {
            public void run()
            {
                updateUI();

                h.postDelayed(this, 50);
            }
        }, 100);*/
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
}