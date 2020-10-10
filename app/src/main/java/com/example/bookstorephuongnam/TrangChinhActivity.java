package com.example.bookstorephuongnam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstorephuongnam.Adapter.Adapter_thongkethangtruoc;
import com.example.bookstorephuongnam.Modal.thongkethangtruoc_class;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TrangChinhActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rcv_thongkethangtruoc_khoanthu;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3;
    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView img_menuicon, img_add;
    LinearLayout contentView;
    static final float END_SCALE = 0.7f;
    String strUserName, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_trang_chinh);
        //Hooks
        rcv_thongkethangtruoc_khoanthu = findViewById(R.id.rcv_thongkethangtruoc_khoanthu);
        img_add = findViewById(R.id.imgAddNav);
        //Menu Hooks
        drawerLayout = findViewById(R.id.drawable_layout);
        navigationView = findViewById(R.id.myNavigationView);
        img_menuicon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        //Check Login
        if (checkLoginShap()<0){
            Intent i = new Intent(TrangChinhActivity.this, LoginActivity.class);
            startActivity(i);
        }

        //vô hiệu hóa màu cho item khi chọn trong item navigation view
        navigationView.setItemIconTintList(null);

        //Gọi các chức năng
        navigationDrawer();
        rcv_thongkethangtruoc_khoanthu();
    }

    public int checkLoginShap(){
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        boolean chk = pref.getBoolean("REMEMBER", false);
        if (chk) {
            strUserName = pref.getString("USERNAME", "");
            strPassword = pref.getString("PASSWORD", "");
            return 1;
        }
        return -1;
    }
    private void navigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(TrangChinhActivity.this);
        navigationView.setCheckedItem(R.id.nav_home);

        img_menuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.transparent));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), TrangChinhActivity.class));
                break;
            case R.id.nav_theloai:
                startActivity(new Intent(getApplicationContext(), TheLoaiActivity.class));
                break;
            case R.id.nav_sach:
                startActivity(new Intent(getApplicationContext(), ListBookActivity.class));
                break;
            case R.id.nav_hoadon:
                startActivity(new Intent(getApplicationContext(), ListHoaDonActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), InfAccountActivity.class));
                break;
        }
        return true;
    }

    private void rcv_thongkethangtruoc_khoanthu(){
        rcv_thongkethangtruoc_khoanthu.setHasFixedSize(true);
        rcv_thongkethangtruoc_khoanthu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<thongkethangtruoc_class> featuredLocations = new ArrayList<>();
        featuredLocations.add(new thongkethangtruoc_class(R.drawable.ic_analytics, "22/04/2020", "2.234.423 VNĐ"));
        featuredLocations.add(new thongkethangtruoc_class(R.drawable.ic_analytics, "22/04/2020", "2.234.423 VNĐ"));
        featuredLocations.add(new thongkethangtruoc_class(R.drawable.ic_analytics, "22/04/2020", "2.234.423 VNĐ"));

        adapter = new Adapter_thongkethangtruoc(featuredLocations);
        rcv_thongkethangtruoc_khoanthu.setAdapter(adapter);


        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }


}