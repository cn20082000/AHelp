package com.cn.ahelp3.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cn.ahelp3.core.action.Returnable;
import com.cn.ahelp3.ui.parent.ParentActivity;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        List<String> list = new ArrayList<>();
//
//
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("NEW_PHONE_NUMBER", new Returnable() {
//            @Override
//            public void execute(Object o) {
//                list.add((String)o);
//                notifyDataSetHasChanged();
//            }
//        });

        Intent intent = new Intent(this, ParentActivity.class);
//        intent.putExtra("bundle", bundle);
        startActivity(intent);
        finish();
    }
}