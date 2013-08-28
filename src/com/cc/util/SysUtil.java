package com.cc.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SysUtil {
  /**
   * 隐藏输入法
   */
  public static void hideInputMethod(Activity ac) {
    InputMethodManager inputMethodManager = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(((Activity) ac).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
  }

  /**
   * 获取输入法状态
   */
  public static boolean isInputMethodOpen(Activity ac) {
    InputMethodManager imm = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
    boolean isOpen = imm.isActive();
    return isOpen;
  }

  /**
   * 打开输入法
   */
  public static void openInputMethod(Activity ac) {
    View v = ac.getWindow().peekDecorView();
    if (v != null && v.getWindowToken() != null) {
      InputMethodManager imm = (InputMethodManager) ac.getSystemService(ac.INPUT_METHOD_SERVICE);
      // 如果输入法打开则关闭，如果没打开则打开
      imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
      imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }
  }

  /**
   * 缩放图片大小
   */
  public static Bitmap changeBitmapSize(Bitmap bitmap,int newWidth,int newHeight) {
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();
    // 计算缩放比例
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;
    // 取得想要缩放的matrix参数
    Matrix matrix = new Matrix();
    matrix.postScale(scaleWidth, scaleHeight);
    // 得到新的图片
    bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
    return bitmap;
  }
}
