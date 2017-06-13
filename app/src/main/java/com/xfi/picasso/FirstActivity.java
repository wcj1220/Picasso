package com.xfi.picasso;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

public class FirstActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    private String url = "http://i.imgur.com/DvR.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);
        imageView3 = (ImageView) findViewById(R.id.image3);

        //普通的一张图片
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView1);

        //url加载失败或错误
        Picasso.with(this)
                .load(url)
                .placeholder(R.mipmap.picasso) //未加载完成
                .error(R.mipmap.ic_launcher)  //加载发生错误
                .into(imageView2);

        //加载本地文件
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                loadPicture();
            }
        } else {
            loadPicture();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadPicture();
            } else {
                // Permission Denied
                Toast.makeText(this, "请打开位置权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadPicture() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "pic.jpg");
        Picasso.with(getBaseContext()).load(file).error(R.mipmap.ic_launcher).into(imageView3);
    }
}
