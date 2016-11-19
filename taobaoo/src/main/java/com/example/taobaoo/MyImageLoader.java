package com.example.taobaoo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2016/11/11.
 */

public class MyImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.get(context).with(context).load(path.toString()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}
