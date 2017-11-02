package com.example.mypopwindowdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private Button pop_under_button;
    private Button bottom_popwindow;
    private Button left, right;
    private View buttonPopView;
    private MyPopWindowBottomShow myPopWindowBottomShow;//��Ļ�ײ�popwindow
    private MyButtonPopwindow myButtonPopwindow;//��ť��popwindow

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pop_under_button = (Button) findViewById(R.id.pop_under_button);
        bottom_popwindow = (Button) findViewById(R.id.bottom_popwindow);
        pop_under_button.setOnClickListener(this);
        bottom_popwindow.setOnClickListener(this);
        myPopWindowBottomShow = new MyPopWindowBottomShow(this, new BottomPopWindow());
        myButtonPopwindow = new MyButtonPopwindow(this, R.layout.under_button_pop_window, new PoPUnderListener());

        buttonPopView = myButtonPopwindow.getPopWindowView();
        left = (Button) buttonPopView.findViewById(R.id.left);
        right = (Button) buttonPopView.findViewById(R.id.right);

        left.setOnClickListener(new OnClickListener() {//�����ⲿ��Ӽ����¼�

            @Override
            public void onClick(View arg0) {
                myButtonPopwindow.dismiss();
                Toast.makeText(MainActivity.this, "��߲���", Toast.LENGTH_SHORT).show();
            }
        });
        right.setOnClickListener(new OnClickListener() {//�����ⲿ��Ӽ����¼�

            @Override
            public void onClick(View arg0) {
                myButtonPopwindow.dismiss();
                Toast.makeText(MainActivity.this, "�ұ߲���", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.pop_under_button:
                if (myButtonPopwindow.isShowing()) {
                    myButtonPopwindow.dismiss();
                } else {
                    myButtonPopwindow.showAsDropDown(pop_under_button);//popwindow����pop_under_button����
                }
                break;

            case R.id.bottom_popwindow:
                if (myPopWindowBottomShow.isShowing()) {
                    myPopWindowBottomShow.dismiss();
                } else {
//				myPopWindowBottomShow.showAsDropDown(bottom_popwindow);//popwindow�����ӿؼ�bottom_popwindow����Ps������ֻ������ⰴ���Ļ�popwindow����ʾ����ȫ��������������˲����ܾúܾã�ϣ���㲻Ҫ����·��
                    myPopWindowBottomShow.showAtLocation(MainActivity.this.findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);//���ַ�ʽ���������ⰴ������û�ж�����ȫ��ʾ����Ϊ����ʾ����������������
                }
                break;
        }
    }

    class PoPUnderListener implements MyPopWindowListener {//ʵ�ְ�ť�µĽӿ�

        @Override
        public void firstItem() {

            Toast.makeText(MainActivity.this, "��߰�ť", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void secondItem() {

            Toast.makeText(MainActivity.this, "�ұ߰�ť", Toast.LENGTH_SHORT).show();
        }

    }

    class BottomPopWindow implements MyPopWindowListener {//ʵ�ֵײ������Ľӿ�

        @Override
        public void firstItem() {
            Toast.makeText(MainActivity.this, "�ϱ߰�ť", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void secondItem() {
            Toast.makeText(MainActivity.this, "�±߰�ť", Toast.LENGTH_SHORT).show();
        }
    }

}
