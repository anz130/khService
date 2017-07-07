package com.dyb.dybc.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.dyb.dybc.R;


public class LoadingDialog {

    private Activity activity;
    private View contentView;
    private ImageView loadingImage;
    private Dialog dialog;
    private Animation animation;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
        initView();
        initDialog();
        setListener();
    }

    private void initView() {
        contentView = View.inflate(activity, R.layout.dialog_loading, null);
        loadingImage = (ImageView) contentView.findViewById(R.id.loadingImage);
    }

    private void initDialog() {
        dialog = new Dialog(activity, R.style.LoadingDialogStyle);
        dialog.setContentView(contentView);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.alpha = 0.7f;
        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);
    }

    private void setListener() {
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (null != loadingImage) {
                    loadingImage.clearAnimation();
                }
            }
        });
    }

    private void startAnimation() {
        animation = AnimationUtils.loadAnimation(activity, R.anim.refresh);
        loadingImage.startAnimation(animation);
    }

    public void show() {
        if (null != dialog && !dialog.isShowing()) {
            dialog.show();
            startAnimation();
        }
    }

    public boolean isShowing() {
        if (null != dialog) {
            return dialog.isShowing();
        }
        return false;
    }

    public void dismiss() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public Dialog getDialog(){
        return dialog;
    }
}