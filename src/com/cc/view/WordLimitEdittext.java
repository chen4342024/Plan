package com.cc.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cc.activity.R;

public class WordLimitEdittext extends FrameLayout {
  private Context context;
  private EditText editText;
  private TextView textView;
  private final int MAX_NUM = 20;
  private final int FACE_STR_LENGTH = 4;
  private final int FACE_IMG_LENGTH = 1;
  private int oldImageSpanNum = 0;
  public WordLimitEdittext(Context context) {
    this(context, null);
  }

  public WordLimitEdittext(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    LayoutInflater.from(context).inflate(R.layout.word_limit_edit, this, true);
    editText = (EditText) findViewById(R.id.word_limit_edit);
    editText.addTextChangedListener(ediTextWatcher);

    textView = (TextView) findViewById(R.id.word_limit_text);
    textView.setText(0 + "/" + MAX_NUM);
  }

  public EditText getEditText() {
    return this.editText;
  }

  // 自定义计算长度方法
  private int caluEditText(Editable editable) {
	 
    int faceCount = editable.getSpans(0, editable.length(), ImageSpan.class).length;
    
    int number = editable.length() - faceCount * (FACE_STR_LENGTH - FACE_IMG_LENGTH);
    return number;
  }
  //判断当前是否添加的是表情
  private boolean isImageSpanNumChange(Editable editable){
    int faceCount = editable.getSpans(0, editable.length(), ImageSpan.class).length;
    return !(faceCount == oldImageSpanNum);
  }
  //重置表情的数量，以便下次判断
  private void resetImageSpanNum(Editable editable){
    oldImageSpanNum = editable.getSpans(0, editable.length(), ImageSpan.class).length;
    Log.i("oldImageSpanNum", "oldImageSpanNum--->"+oldImageSpanNum);
  }

  private TextWatcher ediTextWatcher = new TextWatcher() {
    private CharSequence temp;
    private int selectionStart;
    private int selectionEnd;
 
    @Override
    public void afterTextChanged(Editable editable) {
      
      selectionStart = editText.getSelectionStart();
      selectionEnd = editText.getSelectionEnd();
      
      if (caluEditText(editable) > MAX_NUM) {
        Toast.makeText(context, "超出长度了", Toast.LENGTH_SHORT).show();
        if (isImageSpanNumChange(editable)) {
          editable.delete(selectionStart - FACE_STR_LENGTH, selectionEnd);
        }else {
          editable.delete(selectionStart - 1, selectionEnd);
        }
        int tempSelection = selectionStart;
        editText.setText(editable);
        editText.setSelection(tempSelection);
      }
      resetImageSpanNum(editable);
      textView.setText(caluEditText(editable) + "/" + MAX_NUM);
    }

    @Override
    public void beforeTextChanged(CharSequence charsequence, int start, int before, int count) {
      temp = charsequence;
      
    }

    @Override
    public void onTextChanged(CharSequence charsequence, int start, int before, int count) {

    }

  };
}
