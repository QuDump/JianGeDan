package com.qudump.jiangedan.ui;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.image.ImageInfo;
import com.qudump.jiangedan.R;
import com.qudump.jiangedan.ui.widget.CircleProgressBar;
import com.qudump.jiangedan.ui.widget.WrapContentDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/9.
 */
public class PicViewerActivity extends Activity {

    @Bind(R.id.img)
    WrapContentDraweeView img;
    public static final String EXT_KEY_IMG_URL = "ext.img_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);

        if(null != getIntent()){
            showImg(getIntent().getStringExtra(EXT_KEY_IMG_URL));
        }

    }

    private void showImg(String url) {
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setProgressBarImage(new CircleProgressBar())
                .build();
        img.setHierarchy(hierarchy);
        if(url.endsWith("gif")) {
            DraweeController draweeController = Fresco
                    .newDraweeControllerBuilder()
                    .setControllerListener(listener)
                    .setUri(url)
                    .setAutoPlayAnimations(true)
                    .build();
            img.setController(draweeController);
        } else {
           img.setImageURI(Uri.parse(url));
        }
    }

    private final ControllerListener listener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
            updateViewSize(imageInfo);
        }

        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            updateViewSize(imageInfo);
        }
    };

    void updateViewSize(ImageInfo imageInfo) {
        if (imageInfo != null) {
            img.setAspectRatio((float) imageInfo.getWidth() / imageInfo.getHeight());
        }
    }
}
