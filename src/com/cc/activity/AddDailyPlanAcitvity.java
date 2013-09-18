package com.cc.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cc.db.DailyPlan;
import com.cc.db.DailyPlanDao;
import com.cc.util.DateService;
import com.cc.util.ExpressionUtil;
import com.cc.util.SysUtil;
import com.cc.view.CustomerViewpager;
import com.cc.view.MyWallPaper;
import com.cc.view.WallPagerListener;
import com.cc.view.WordLimitEdittext;
import com.clj.service.PlanService;

public class AddDailyPlanAcitvity extends Activity implements OnClickListener {
  private static int FACE_NUM_EACH_PAGE = 36;
  private int[] imageIds = new int[107];// 存放表情的数组
  private RelativeLayout wallLayout;
  private LinearLayout faceLayout;
//test
  private Button submitPlanBtn; 
  private WordLimitEdittext planContent;
  private ImageButton faceBtn;

  private Context context;
  private int _xDelta = 0;
  private int _yDelta = 0;
  private boolean isFace;
  private boolean hasMeasured;

  private List<DailyPlan> allDailyPlans;// 保存所有日计划
  

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.daily_plan);
    context = this;

    wallLayout = (RelativeLayout) findViewById(R.id.plan_layout);
    ViewTreeObserver vto = wallLayout.getViewTreeObserver();
    vto.addOnPreDrawListener(preDrawListener);

    initFace();

    faceBtn = (ImageButton) findViewById(R.id.faceButton);
    submitPlanBtn = (Button) findViewById(R.id.submit_plan);
    planContent = (WordLimitEdittext) findViewById(R.id.plan_content);

    wallLayout.setOnClickListener(this);
    faceBtn.setOnClickListener(this);
    submitPlanBtn.setOnClickListener(this);
    planContent.getEditText().setOnClickListener(this);

  }
  
  @Override
  protected void onPause() {
    super.onPause();
    DailyPlanDao.updatePagerPosition(context, this.allDailyPlans);
  }



  private android.view.ViewTreeObserver.OnPreDrawListener preDrawListener = new OnPreDrawListener() {
    @Override
    public boolean onPreDraw() {
      if (hasMeasured == false) {
        
        initAllWallPage();
        
        hasMeasured = true;
        return false;
      }
      return true;
    }
  };
  
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
        if (!isFace) {
          SysUtil.hideInputMethod(AddDailyPlanAcitvity.this);

          faceLayout.setVisibility(View.VISIBLE);
          isFace = true;
        } else {
          faceLayout.setVisibility(View.GONE);
          SysUtil.hideInputMethod(AddDailyPlanAcitvity.this);
          isFace = false;
        }
        break;
      case R.id.plan_layout:
        faceLayout.setVisibility(View.GONE);
        SysUtil.hideInputMethod(AddDailyPlanAcitvity.this);
        if (isFace)
          isFace = false;
        break;
      case R.id.word_limit_edit:

        faceLayout.setVisibility(View.GONE);

        break;
      default:
        break;
    }
  }

  /**
   * 发送计划
   */
  private void submitPlan() {
    if (planContent.getEditText().getText().length() == 0) {
      Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
      return;
    }
    int maxPlanCount = Integer.parseInt(PlanService.instance().ReadVal("plan_count", "5").substring(0, 1));
    // if (this.allDailyPlans.size() + 1 > maxPlanCount) {
    // Toast.makeText(context, "您的计划数目已经达到最大数目"+maxPlanCount+"，如果需要修改，请在设置里修改", Toast.LENGTH_SHORT).show();
    // return;
    // }
    SysUtil.hideInputMethod(AddDailyPlanAcitvity.this);
    DailyPlan dailyPlan = saveDailyPlan();
    if (dailyPlan.getId() != 0) {
      this.allDailyPlans.add(dailyPlan);
      Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
      addWallPage(dailyPlan);
    } else {
      Toast.makeText(context, "保存出错", Toast.LENGTH_SHORT).show();
    }
  };

  private void initAllWallPage() {
    allDailyPlans = DailyPlanDao.readTodayPlan(context);
    for (DailyPlan dailyPlan : allDailyPlans) {
      addWallPage(dailyPlan);
    }
  }

  private void addWallPage(DailyPlan dailyPlan) {
    MyWallPaper wallPaper = new MyWallPaper(context, wallLayout);
    wallPaper.setImageResource(R.drawable.wallpaper_x2);
    wallPaper.setText(ExpressionUtil.convertStrToFace(context,dailyPlan.getContent()));
    wallPaper.setUniqueId(dailyPlan.getId());
    wallPaper.setListener(wallPagerListener);
    wallPaper.setPosition(dailyPlan.getX(), dailyPlan.getY());
    wallLayout.addView(wallPaper);
  }
  
  private void removeWallPage(View view){
    wallLayout.removeView(view);
  }

  private DailyPlan saveDailyPlan() {
    DailyPlan dailyPlan = new DailyPlan();
    dailyPlan.setInitTime(DateService.getInstance().now());
    dailyPlan.setContent(planContent.getEditText().getText().toString());
    dailyPlan.setFinish(false);
    dailyPlan.setX(50);
    dailyPlan.setY(100);
    return DailyPlanDao.addDailyPlan(context, dailyPlan);
  }
  
  private WallPagerListener wallPagerListener = new WallPagerListener() {
    
    @Override
    public boolean updatePosition(int id,int x, int y) {
      DailyPlan dailyPlan = getDailyPlanById(id);
      if (dailyPlan == null) {
        return false;
      }
      dailyPlan.setX(x);
      dailyPlan.setY(y);
      return true;
    }
    
    @Override
    public boolean deleteWallPaper(final int id,final View view) {
      
      new AlertDialog.Builder(context).setTitle("是否确定删除此计划")// 提示信息
      .setNegativeButton("取消", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          
        }
      }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          if (DailyPlanDao.deleteDailyplan(context, id)) {
            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
            removeWallPage(view);
            removeDailyPlanById(id);
          }
        }
      }).show();
      return false;
    }
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
        // 以view的ID作为每个gridview的开始
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
    // 生成表情的id，目前资源里只有107个，要控制end在107以内
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
  
  
  private DailyPlan getDailyPlanById(int id){
    for (DailyPlan dailyPlan : this.allDailyPlans) {
      if (dailyPlan.getId() == id) {
        return dailyPlan;
      }
    }
    return null;
  }
  
  private void removeDailyPlanById(int id){
    DailyPlan dailyPlan = getDailyPlanById(id);
    if (dailyPlan != null) {
      this.allDailyPlans.remove(dailyPlan);
    }
  }
}
