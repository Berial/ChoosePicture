package com.ycl.chooseavatar.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;



/**
 * 选择拍照上传头像还是从相册选择图片上传的对话框
 * 作者：yaochangliang on 2016/3/14 11:19
 * 邮箱：yaochangliang159@sina.com
 */
public class UpLoadHeadImageDialog extends Dialog {
	TextView tv_take_picture;
	TextView tv_choose_gallery;

    Activity activity;
	Fragment fragment;

	public UpLoadHeadImageDialog(Context context) {
		super(context,R.style.AlertDialogStyle);
		activity=(Activity) context;
	}
	public UpLoadHeadImageDialog(Context context, Fragment fragment) {
		super(context,R.style.AlertDialogStyle);
		activity=(Activity) context;
		this.fragment=fragment;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_headimg);

		tv_take_picture= (TextView) findViewById(R.id.tv_take_picture);
		tv_choose_gallery= (TextView) findViewById(R.id.tv_choose_gallery);
		tv_take_picture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(fragment==null){
					YCLTools.getInstance().startChoose(activity, 0);
					UpLoadHeadImageDialog.this.dismiss();
				}else{
					YCLTools.getInstance().startChoose(fragment, 0);
					UpLoadHeadImageDialog.this.dismiss();
				}

			}
		});
		tv_choose_gallery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(fragment==null){
					YCLTools.getInstance().startChoose(activity, 1);
					UpLoadHeadImageDialog.this.dismiss();
				}else{
					YCLTools.getInstance().startChoose(fragment, 1);
					UpLoadHeadImageDialog.this.dismiss();
				}

			}
		});

		setCancelable(true);
		setCanceledOnTouchOutside(true);
		Window w=this.getWindow();
		WindowManager.LayoutParams p=getWindow().getAttributes();
		//p.height=T.getHeightPixel(activity)*1;

		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;     // 屏幕宽度（像素）
		//int height = metric.heightPixels;   // 屏幕高度（像素）
		p.width=(int) (width*0.8);
		getWindow().setAttributes(p);
	}


	

}
