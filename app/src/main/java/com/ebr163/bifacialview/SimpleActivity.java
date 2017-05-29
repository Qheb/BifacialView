package com.ebr163.bifacialview;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ebr163.bifacialview.view.BifacialView;

public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        final BifacialView bifacialView = (BifacialView) findViewById(R.id.view);
        Glide.with(this)
                .load("https://files4.adme.ru/files/news/part_149/1494765/29530665-262592-3-0-1491487588-1491487592-650-bf845cd25e-1491558012.jpg")
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        bifacialView.setDrawableRight(resource);
                        return false;
                    }
                }).preload();

        Glide.with(this)
                .load("https://files3.adme.ru/files/news/part_149/1494765/29530265-262592-33-0-1491489159-1491489163-650-8edb1df01d-1491558012.jpg")
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        bifacialView.setDrawableLeft(resource);
                        return false;
                    }
                }).preload();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final BifacialView bifacialView = (BifacialView) findViewById(R.id.view);
        final ObjectAnimator objectAnimator = ObjectAnimator.ofObject(bifacialView, "delimiterRatio", new FloatEvaluator(), 0.5f, 0.8f, 0.5f);

        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.setDuration(1000);
        bifacialView.postDelayed(new Runnable() {
            @Override
            public void run() {
                objectAnimator.start();
            }
        },100);
    }
}
