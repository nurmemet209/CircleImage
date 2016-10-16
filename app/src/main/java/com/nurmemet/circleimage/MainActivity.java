package com.nurmemet.circleimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mXmodeView;
    private ImageView mShaderView;
    private ImageView mClipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mXmodeView = (ImageView) findViewById(R.id.xormode_);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.baby);
        CircleImageXorMode drawable = new CircleImageXorMode(bmp);
        mXmodeView.setBackground(drawable);

        mShaderView= (ImageView) findViewById(R.id.shader_);
        CircleImageShader shader=new CircleImageShader(bmp);
        mShaderView.setBackground(shader);

        mClipView= (ImageView) findViewById(R.id.clip_);
        CircleImageClip clip=new CircleImageClip(bmp);
        mClipView.setBackground(clip);
    }
}
