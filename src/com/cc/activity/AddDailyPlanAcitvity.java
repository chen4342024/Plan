package com.cc.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.cc.util.SysUtil;
import com.cc.view.CustomerViewpager;
import com.cc.view.MyWallPaper;
import com.cc.view.WordLimitEdittext;

public class AddDailyPlanAcitvity extends Activity implements OnClickListener {
  private static int FACE_NUM_EACH_PAGE = 36;

  private RelativeLayout wallLayout;
  private LinearLayout faceLayout;

  private Button submitPlanBtn;
  private WordLimitEdittext planContent;
  private ImageButton faceBtn;

  private Context context;
  private int[] imageIds = new int[107];// 存放表情的数组
  private int _xDelta = 0;
  private int _yDelta = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.daily_plan);
    context = this;

    wallLayout = (RelativeLayout) findViewById(R.id.plan_layout);
    
    initFace();
    
    faceBtn = (ImageButton) findViewById(R.id.faceButton);
    submitPlanBtn = (Button) findViewById(R.id.submit_plan);
    planContent = (WordLimitEdittext) findViewById(R.id.plan_content);

    wallLayout.setOnClickListener(this);
    faceBtn.setOnClickListener(this);
    submitPlanBtn.setOnClickListener(this);
  }

  // 初始化表情界面
  private void initFace() {
    faceLayout = (LinearLayout) findViewById(R.id.faceLayout);
    CustomerViewpager viewpager = new CustomerViewpager(context, createExpressionGridView());
    faceLayout.addView(viewpager);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.submit_plan:
        submitPlan();
        faceLayout.setVisibility(View.GONE);
        break;
      case R.id.faceButton:
        SysUtil.hideInputMethod(AddDailyPlanAcitvity.this);
        faceLayout.setVisibility(View.VISIBLE);
        break;
      case R.id.plan_layout:
        faceLayout.setVisibility(View.GONE);
        SysUtil.hideInputMethod(AddDailyPlanAcitvity.this);
      default:
        break;
    }

  }

  /**
   * 发送计划
   */
  private void submitPlan() {
    SysUtil.hideInputMethod(AddDailyPlanAcitvity.this);
    MyWallPaper wallPaper = new MyWallPaper(context, wallLayout);
    wallPaper.setImageResource(R.drawable.wallpaper_x2);
    wallPaper.setText(planContent.getEditText().getText());// 20
    wallLayout.addView(wallPaper);
  };

  /**
   * 创建一个表情选择对话框
   */
  private List<View> createExpressionGridView() {
    List<View> gridViews = new ArrayList<View>();
    GridView gridView1 = createGridView(0, FACE_NUM_EACH_PAGE);
    GridView gridView2 = createGridView(FACE_NUM_EACH_PAGE * 1, FACE_NUM_EACH_PAGE * 2);
    GridView gridView3 = createGridView(FACE_NUM_EACH_PAGE * 2, 107);

    gridViews.add(gridView1);
    gridViews.add(gridView2);
    gridViews.add(gridView3);

    return gridViews;
  }

  /**
   * 生成一个表情对话框中的gridview
   * 
   * @return
   */
  private GridView createGridView(final int start, int end) {
    final GridView view = new GridView(this);
    List<Map<String, Object>> listItems = getFaceResMap(start, end);
    
    SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.team_layout_single_expression_cell, new String[] { "image" },
        new int[] { R.id.image });
    view.setAdapter(simpleAdapter);
    view.setNumColumns(6);
    view.setHorizontalSpacing(1);
    view.setVerticalSpacing(1);
    view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    view.setGravity(Gravity.CENTER);
    view.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
        // 以view的ID作为每个gridview的开始值
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeResource(getResources(), imageIds[start + (arg2 % imageIds.length)]);
        bitmap = SysUtil.changeBitmapSize(bitmap, 30, 30);
        ImageSpan imageSpan = new ImageSpan(AddDailyPlanAcitvity.this, bitmap);

        String str = "000" + arg2;
        str = "/" + str.substring(str.length() - 3, str.length());
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        planContent.getEditText().append(spannableString);
      }
    });
    return view;
  }

  private List<Map<String, Object>> getFaceResMap(int start, int end) {
    List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
    // 生成表情的id，目前资源里只有107个，要控制end在107以内。
    for (int i = start; i < end; i++) {
      try {
        Field field = null;
        if (i < 10) {
          field = R.drawable.class.getDeclaredField("f00" + i);
        } else if (i < 100) {
          field = R.drawable.class.getDeclaredField("f0" + i);
        } else {
          field = R.drawable.class.getDeclaredField("f" + i);
        }
        int resourceId = Integer.parseInt(field.get(null).toString());
        imageIds[i] = resourceId;
      } catch (Exception e) {
        e.printStackTrace();
      }
      Map<String, Object> listItem = new HashMap<String, Object>();
      listItem.put("image", imageIds[i]);
      listItems.add(listItem);
    }
    return listItems;
  }

}
