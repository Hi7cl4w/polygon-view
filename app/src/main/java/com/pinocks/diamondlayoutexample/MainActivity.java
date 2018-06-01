package com.pinocks.diamondlayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pinocks.polygonview.PolygonView;
import com.pinocks.polygonview.PolygonViewEventListener;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    PolygonView polygonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        polygonView = findViewById(R.id.polygonview);
        polygonView.setPolygonViewEventListener(new PolygonViewEventListener() {
            @Override
            public void onClickCenterButton() {
                Toast.makeText(getApplicationContext(),"onClickCenterButton",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickTopLeftButton() {
                Toast.makeText(getApplicationContext(),"onClickTopLeftButton",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickTopRightButton() {
                Toast.makeText(getApplicationContext(),"onClickTopRightButton",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickBottomLeftButton() {
                Toast.makeText(getApplicationContext(),"onClickBottomLeftButton",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickBottomRightButton() {
                Toast.makeText(getApplicationContext(),"onClickBottomRightButton",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
