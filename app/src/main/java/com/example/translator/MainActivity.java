package com.example.translator;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private TextView mTextMessage;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private BottomNavigationView mbuttomNavView;
    private static int REQUEST_PERMISSION_CODE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public String CREATE_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
        CREATE_PATH = getApplicationContext().getFilesDir().getAbsolutePath() + "/data.txt";

        //初始化控件
        initViews();
        // 初始化导航
        initBottonNavView();
        //初始化PageAdapter
        initAdapter();

    }

    // 在Fragment中调用这个方法
    public String getPath(){
        return CREATE_PATH;
    }
    //初始化控件
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.main_container);
        mbuttomNavView = findViewById(R.id.navigation);
    }

    //初始化PageAdapter
    private void initAdapter() {

    }

    //初始化BottomNavigation
    private void initBottonNavView() {

    }

    // 翻译界面点击按钮
    public void onclick(View v) {

    }

    public static void bufferSave(String filename, String msg) {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(filename, true));
            bfw.write(msg);
            bfw.newLine();
            bfw.flush();
            bfw.close();
            System.out.println("写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 申请文件读取权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }
}
