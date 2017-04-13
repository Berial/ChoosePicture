package com.ycl.chooseavatar.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.isseiaoki.simplecropview.CropImageView;

import java.io.File;


public class CropImageViewActivity extends Activity {
    public static final String TAG = "YCL_CHOOSE_PICTURE";

    private static String YCL_FOLDER_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + "ycl/";

    private String TEMP_PIC_NAME = "temp_headImg";

    CropImageView cropImageView;
    ImageView iv_cancel;
    ImageView iv_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_imageview);

        init();
        String path = this.getIntent().getStringExtra("photo_path");
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Bitmap bitmap = ImageTools.rotateBitmap(path, width, height);
        cropImageView.setImageBitmap(bitmap);
        cropImageView.setCropMode(CropImageView.CropMode.FREE);

    }

    private void init() {
        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        iv_cancel = (ImageView) findViewById(R.id.iv_cancel);
        iv_ok = (ImageView) findViewById(R.id.iv_ok);
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YCLTools.listener == null) {
                    Log.e(TAG, "you should use the medthod YCLTools.getInstance.setOnChoosePictureListener() in your activity");
                } else {
                    YCLTools.listener.OnCancel();
                }

                CropImageViewActivity.this.finish();
            }
        });
        iv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap1 = cropImageView.getCroppedBitmap();
                File file = ImageTools.savePhotoToSDCard(bitmap1, YCL_FOLDER_PATH, TEMP_PIC_NAME);
                bitmap1.recycle();
                if (YCLTools.listener == null) {
                    Log.e(TAG, "you should use the medthod YCLTools.getInstance.setOnChoosePictureListener() in your activity");
                } else {
                    YCLTools.listener.OnChoose(file.getAbsolutePath());
                }

                Log.e("test", file.getAbsolutePath());
                CropImageViewActivity.this.finish();
            }
        });
    }


}
