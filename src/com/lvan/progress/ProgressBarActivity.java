package com.lvan.progress;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class ProgressBarActivity extends Activity implements Runnable {

	private ProgressBar mColor = null;
	private Thread thread;
	private int mCount = 0;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			finish();

			super.handleMessage(msg);
		}
	};
	private boolean stateChange;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mypage_color);

		showProgress();
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		while (true) {
			int current = mColor.getProgress();// �õ���ǰ����ֵ
			int currentMax = mColor.getMax();// �õ���������������ֵ
			// int secCurrent = bar.getSecondaryProgress();// �õ��ײ㵱ǰ����ֵ
			// ���´���ʵ�ֽ���ֵԽ��Խ��Խ��ԽС��һ����̬Ч��
			if (stateChange == false) {
				if (current >= currentMax) {
					stateChange = true;
				} else {
					// ���ý���ֵ
					mColor.setProgress(current + 1);
					// ���õײ����ֵ
					mColor.setSecondaryProgress(current + 1);
				}
			} else {
				if (current <= 0) {
					stateChange = false;
				} else {
					mColor.setProgress(current - 1);
					mColor.setSecondaryProgress(current - 1);
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showProgress() {
		mCount = 0;
		mColor = (ProgressBar) findViewById(R.id.progress_horizontal_color);
		mColor.setProgress(0);
		mColor.setIndeterminate(false);
		/*
		 * ��Ҫע�����setIndeterminate�Ĳ���Ϊtrue ��false��
		 * true��ʾ��ȷ��ģʽ���������ĵ�ǰֵ�Զ�����С�����ֵ֮�������ƶ�
		 * ���γ�����һ������Ч�������ֻ�Ǹ��߱��ˡ������ڹ���������������ʾ�������ȵ��ĸ��׶Ρ���Ҫ���ڽ���һЩ�޷�ȷ������ʱ�������ʱ��Ϊ��ʾ
		 *false��ʾȷ��ģʽ������ʵ�ʵĽ������ý���ֵ*/
		
	}
}
