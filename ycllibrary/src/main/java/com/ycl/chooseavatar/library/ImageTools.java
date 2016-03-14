package com.ycl.chooseavatar.library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public final class ImageTools {

	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}


	public static boolean checkSDCardAvailable(){
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}
	

	public static File savePhotoToSDCard(Bitmap photoBitmap,String path,String photoName){
		if (checkSDCardAvailable()) {
			File dir = new File(path);
			if (!dir.exists()){
				dir.mkdirs();
			}
			
			File photoFile = new File(path , photoName + ".jpg");
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null) {
					if (photoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)) {
						fileOutputStream.flush();
						return photoFile;
//						fileOutputStream.close();
					}
				}
			} catch (FileNotFoundException e) {
				photoFile.delete();
				e.printStackTrace();
			} catch (IOException e) {
				photoFile.delete();
				e.printStackTrace();
			} finally{
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null; 
	}
	
	private static int readPictureDegree(String path){
		int degree=0;
		try {
			ExifInterface exifInterface=new ExifInterface(path);
			int orientation=exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch(orientation){
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree=90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree=180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree=270;
				break;
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return degree;
		
	}
	public static Bitmap rotateBitmap(Bitmap bitmap,String path){
		Bitmap new_bitmap=null;
		if(bitmap!=null){
			Matrix matrix=new Matrix();
			matrix.postRotate(readPictureDegree(path));
			Bitmap small_bitmap=getSmallBitmap(path);
			 new_bitmap=Bitmap.createBitmap(small_bitmap, 0, 0, small_bitmap.getWidth(), small_bitmap.getHeight(),matrix,true);
			if (null != bitmap && bitmap.isRecycled()) {
				bitmap.recycle();
				bitmap = null;
			}
			if (null != small_bitmap && small_bitmap.isRecycled()) {
				small_bitmap.recycle();
				small_bitmap = null;
			}

		}
		return new_bitmap;
		
	}

	public static Bitmap getSmallBitmap(String path){
		//new 出来一个bitmap的参数
		BitmapFactory.Options options=new BitmapFactory.Options();
		//设置为true，不会生成bitmao对象，只是读取尺寸和类型信息
		options.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path, options);
		//得到这个比例   并赋予option里面的inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 320, 480);
		//设置为false，即将要生成bitmap对象啦
		options.inJustDecodeBounds = false;
		//有了这个option，我们可以生成bitmap对象了
		Bitmap bitmap=BitmapFactory.decodeFile(path, options);

		return bitmap;

	}
	public static int calculateInSampleSize(BitmapFactory.Options options,int reqHeight,int reqWidth){
		//得到原始图片宽高
		int height=options.outHeight;
		int width=options.outWidth;
		//默认设置为1，即不缩放
		int inSampleSize=1;
		//如果图片原始的高大于我们期望的高，或者图片的原始宽大于我们期望的宽，换句话意思就是，我们想让它变小一点
		if (height > reqHeight || width > reqWidth) {
			//原始的高除以期望的高，得到一个比例
			final int heightRatio = Math.round((float) height/ (float) reqHeight);
			//原始的宽除以期望的宽，得到一个比例
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			//取上面两个比例中小的一个，返回这个比例
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;

	}
	
}
