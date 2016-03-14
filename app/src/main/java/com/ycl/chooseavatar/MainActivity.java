package com.ycl.chooseavatar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ycl.chooseavatar.library.OnChoosePictureListener;
import com.ycl.chooseavatar.library.UpLoadHeadImageDialog;
import com.ycl.chooseavatar.library.YCLTools;

public class MainActivity extends AppCompatActivity implements OnChoosePictureListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //must set a listener
        YCLTools.getInstance().setOnChoosePictureListener(this);
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show dialog
             new UpLoadHeadImageDialog(MainActivity.this).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //must set like this
        YCLTools.getInstance().upLoadImage(requestCode, resultCode, data);
    }

    @Override
    public void OnChoose(String filePath) {
        Toast.makeText(getApplicationContext(),"filePath:"+filePath,Toast.LENGTH_LONG).show();

    }

    @Override
    public void OnCancel() {
        Toast.makeText(getApplicationContext(),"OnCancel",Toast.LENGTH_LONG).show();

    }
    public void  bt_fragment(View v)
    {
        Intent intent=new Intent(this,FragmentsActivity.class);
        startActivity(intent);
    }
}
