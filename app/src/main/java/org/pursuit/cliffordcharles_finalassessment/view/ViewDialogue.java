package org.pursuit.cliffordcharles_finalassessment.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.pursuit.cliffordcharles_finalassessment.R;

public class ViewDialogue {

    private Activity activity;
    private Dialog dialog;

    public ViewDialogue(Activity act) {

        this.activity = act;


    }


    public void showDialogue() {

        dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogue_layout);

        ImageView loadingGif = dialog.findViewById(R.id.loading_image);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(loadingGif);

        Glide.with(activity)
                .load(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .centerCrop()
                .crossFade()
                .into(imageViewTarget);
        dialog.show();


    }

    public void hideDialogue() {

        dialog.dismiss();
    }

}
