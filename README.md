# ChoosePicture
android 拍照裁剪照片，从相册选择照片裁剪,android take picture,choose picture from gallery
![image](https://github.com/yaochangliang159/ChoosePicture/raw/master/ScreenShots/screenshot01.gif)
Gradle:
###build.gradle里面，添加：
```Java
compile 'com.ycl.utils:ycllibrary:1.0.0'
```
#####集成这个选择照片上传库需要四步
###第一步：
####将以下代码加入onCreate()方法中：
```Java
 YCLTools.getInstance().setOnChoosePictureListener(this);
```
###第二步：
####调用以下代码显示选取图片方式的dialog:
```Java
  new UpLoadHeadImageDialog(MainActivity.this).show();
```
###第三步：
####重写Activity的onActivityResult方法，并在该方法中加入以下语句:
```Java
YCLTools.getInstance().upLoadImage(requestCode, resultCode, data);
``` 
###第四步：
####在你项目的Manifest.xml中，注册该Activity：
```Java
<activity android:name="com.ycl.chooseavatar.library.CropImageViewActivity"></activity>
```
#####加入以下权限：
```Java
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"></uses-permission>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
```
####以下是一个简单的例子：
```Java
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
    
}
```
###注意，在Fragment中，应该这样进行第二步：
```Java
 new UpLoadHeadImageDialog(getActivity(),MyFragment.this).show();
```
####感谢：
  [IsseiAoki/SimpleCropView](https://github.com/IsseiAoki/SimpleCropView)
