package com.ncode.mukhyamantriamrutum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ncode.mukhyamantriamrutum.infiniteindicator.InfiniteIndicatorLayout;
import com.ncode.mukhyamantriamrutum.infiniteindicator.slideview.BaseSliderView;
import com.ncode.mukhyamantriamrutum.infiniteindicator.slideview.DefaultSliderView;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {
    InfiniteIndicatorLayout viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("IMAGENAME"));
        int pos = Integer.parseInt(getIntent().getStringExtra("POSITION"));

        viewPager = (InfiniteIndicatorLayout) findViewById(R.id.vpHomeSlider);

        ArrayList<PageInfo> viewInfos = new ArrayList<PageInfo>();
        if (pos == 0) {
            viewInfos.add(new PageInfo("" + 1, R.drawable.a1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.a2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.a3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.a4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.a5));
        }else if(pos==1){
            viewInfos.add(new PageInfo("" + 1, R.drawable.b1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.b2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.b3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.b4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.b5));
        }else if(pos==2){
            viewInfos.add(new PageInfo("" + 1, R.drawable.c1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.c2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.c3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.c4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.c5));
        }else if(pos==3){
            viewInfos.add(new PageInfo("" + 1, R.drawable.d1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.d2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.d3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.d4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.d5));
        }else if(pos==4){
            viewInfos.add(new PageInfo("" + 1, R.drawable.e1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.e2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.e3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.e4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.e5));
        }else if(pos==5){
            viewInfos.add(new PageInfo("" + 1, R.drawable.f1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.f2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.f3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.f4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.f5));
        }else if(pos==6){
            viewInfos.add(new PageInfo("" + 1, R.drawable.g1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.g2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.g3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.g4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.g5));
        }else if(pos==7){
            viewInfos.add(new PageInfo("" + 1, R.drawable.h1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.h2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.h3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.h4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.h5));
        }else if(pos==8){
            viewInfos.add(new PageInfo("" + 1, R.drawable.i1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.i2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.i3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.i4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.i5));
        }else if(pos==9){
            viewInfos.add(new PageInfo("" + 1, R.drawable.j1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.j2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.j3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.j4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.j5));
        }else if(pos==10){
            viewInfos.add(new PageInfo("" + 1, R.drawable.k1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.k2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.k3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.k4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.k5));
        }else if(pos==11){
            viewInfos.add(new PageInfo("" + 1, R.drawable.l1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.l2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.l3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.l4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.l5));
        }else if(pos==12){
            viewInfos.add(new PageInfo("" + 1, R.drawable.m1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.m2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.m3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.m4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.m5));
        }else if(pos==13){
            viewInfos.add(new PageInfo("" + 1, R.drawable.n1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.n2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.n3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.n4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.n5));
        }else if(pos==14){
            viewInfos.add(new PageInfo("" + 1, R.drawable.o1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.o2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.o3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.o4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.o5));
        }else if(pos==15){
            viewInfos.add(new PageInfo("" + 1, R.drawable.p1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.p2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.p3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.p4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.p5));
        }else if(pos==16){
            viewInfos.add(new PageInfo("" + 1, R.drawable.q1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.q2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.q3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.q4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.q5));
        }else if(pos==17){
            viewInfos.add(new PageInfo("" + 1, R.drawable.r1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.r2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.r3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.r4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.r5));
        }else if(pos==18){
            viewInfos.add(new PageInfo("" + 1, R.drawable.s1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.s2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.s3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.s4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.s5));
        }else if(pos==19){
            viewInfos.add(new PageInfo("" + 1, R.drawable.t1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.t2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.t3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.t4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.t5));
        }else if(pos==20){
            viewInfos.add(new PageInfo("" + 1, R.drawable.u1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.u2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.u3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.u4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.u5));
        }else if(pos==21){
            viewInfos.add(new PageInfo("" + 1, R.drawable.v1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.v2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.v3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.v4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.v5));
        }else if(pos==22){
            viewInfos.add(new PageInfo("" + 1, R.drawable.w1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.w2));
        }else if(pos==23){
            viewInfos.add(new PageInfo("" + 1, R.drawable.x1));
            viewInfos.add(new PageInfo("" + 1, R.drawable.x2));
            viewInfos.add(new PageInfo("" + 1, R.drawable.x3));
            viewInfos.add(new PageInfo("" + 1, R.drawable.x4));
            viewInfos.add(new PageInfo("" + 1, R.drawable.x5));
        }
        for (PageInfo name : viewInfos) {
            DefaultSliderView textSliderView = new DefaultSliderView(PhotoActivity.this);

            try {
                textSliderView.image(name.getDrawableRes()).setScaleType(BaseSliderView.ScaleType.CenterInside);
                textSliderView.getBundle()
                        .putString("extra", name.getData());
                viewPager.addSlider(textSliderView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        viewPager.setIndicatorPosition(InfiniteIndicatorLayout.IndicatorPosition.Center_Bottom);
        viewPager.startAutoScroll();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
