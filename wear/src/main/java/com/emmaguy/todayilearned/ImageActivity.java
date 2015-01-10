package com.emmaguy.todayilearned;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ImageView;

import com.emmaguy.todayilearned.sharedlib.Constants;

public class ImageActivity extends Activity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);

        final String imageId = getIntent().getStringExtra(Constants.KEY_HIGHRES_IMAGE_ID);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                Bitmap bm = NotificationListenerService.mBitmaps.get(imageId);

                Logger.Log("imageactivitybm " + bm);

                mImageView = (ImageView) stub.findViewById(R.id.fullscreen_imageview);
                mImageView.setImageBitmap(bm);
            }
        });
    }
}
