package com.hackerchai.openingsource.util;

import android.widget.ImageView;

public class ImageLoader {
    public void showImageByAsyncTask(ImageView imageView, String url){
        new PicAsyncTask(imageView,url).execute(url);
    }
}
