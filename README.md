# ChoosePicture
android 拍照裁剪照片，从相册选择照片裁剪,android take picture,choose picture from gallery
![image](https://github.com/yaochangliang159/ChoosePicture/raw/master/ScreenShots/screenshot01.gif)
Gradle:
build.gradle里面，添加：
compile 'com.ycl.utils:ycllibrary:1.0.0'

集成这个选择照片上传库需要四步！！！

第一步：
将以下代码加入onCreate()方法中：
 YCLTools.getInstance().setOnChoosePictureListener(this);
 
 第二步：
 调用以下代码显示选取图片方式的dialog:
new UpLoadHeadImageDialog(MainActivity.this).show();

第三步：
重写Activity的onActivityResult方法，并在该方法中加入以下语句:
 YCLTools.getInstance().upLoadImage(requestCode, resultCode, data);
 
 第四步：
 在你项目的Manifest.xml中，注册该Activity：
  <activity android:name="com.ycl.chooseavatar.library.CropImageViewActivity"></activity>
  在Manifest.xml中，加入以下权限：
   <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"></uses-permission>
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
