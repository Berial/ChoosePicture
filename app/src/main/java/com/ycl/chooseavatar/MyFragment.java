package com.ycl.chooseavatar;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ycl.chooseavatar.library.OnChoosePictureListener;
import com.ycl.chooseavatar.library.UpLoadHeadImageDialog;
import com.ycl.chooseavatar.library.YCLTools;

/**
 * 作者：yaochangliang on 2016/3/14 14:31
 * 邮箱：yaochangliang159@sina.com
 */
public class MyFragment extends Fragment implements OnChoosePictureListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.my_fragment,container,false);
        YCLTools.getInstance().setOnChoosePictureListener(this);
        v.findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //attention
                new UpLoadHeadImageDialog(getActivity(),MyFragment.this).show();
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        YCLTools.getInstance().upLoadImage(requestCode, resultCode, data);
    }

    @Override
    public void OnChoose(String filePath) {
        Toast.makeText(getActivity(),"filePath:"+filePath,Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnCancel() {


    }
}
