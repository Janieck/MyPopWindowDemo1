package com.example.mypopwindowdemo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MyPopWindowBottomShow extends PopupWindow implements OnClickListener{
	private LayoutInflater layoutInflater;
	private MyPopWindowListener myPopWindowListener;
	private View popView;
	private Button firstButton;
	private Button secondButton;
	
	public MyPopWindowBottomShow(Context context,MyPopWindowListener myPopWindowListener){
		this.myPopWindowListener = myPopWindowListener;
		this.layoutInflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		init();
	}
	
	private void init(){
		popView = layoutInflater.inflate(R.layout.bottom_pop_window, null);
		firstButton = (Button) popView.findViewById(R.id.on);
		secondButton = (Button) popView.findViewById(R.id.under);
		firstButton.setOnClickListener(this);
		secondButton.setOnClickListener(this);
		
		//把View添加到PopWindow中
		this.setContentView(popView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.BottomPopWindowAnimation);
        
//        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//实例化一个ColorDrawable颜色为透明
        
      //实例化一个ColorDrawable颜色为半透明
      		ColorDrawable dw = new ColorDrawable(0xb0000000);
      		//设置SelectPicPopupWindow弹出窗体的背景
      		this.setBackgroundDrawable(dw);
        popView.setOnTouchListener(new OnTouchListener() {//设置背景区域外为点击消失popwindow
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = popView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.on:
			myPopWindowListener.firstItem();
			break;

		case R.id.under:
			dismiss();
			myPopWindowListener.secondItem();
			break;
		}
	}
}
