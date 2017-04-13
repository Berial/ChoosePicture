package com.ycl.chooseavatar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * 作者：yaochangliang on 2016/3/14 14:27
 * 邮箱：yaochangliang159@sina.com
 */
public class FragmentsActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_activity);
        MyFragment myFragment=new MyFragment();

        getFragmentManager().beginTransaction().add(R.id.container,myFragment).commit();

    }


}
