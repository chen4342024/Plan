package com.cc.view;

import android.view.View;

public interface WallPagerListener {
  public boolean deleteWallPaper(final int id,View view);
  public boolean updatePosition(int id,int x,int y);
}

