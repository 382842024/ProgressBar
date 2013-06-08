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
			int current = mColor.getProgress();// 得到当前进度值
			int currentMax = mColor.getMax();// 得到进度条的最大进度值
			// int secCurrent = bar.getSecondaryProgress();// 得到底层当前进度值
			// 以下代码实现进度值越来越大，越来越小的一个动态效果
			if (stateChange == false) {
				if (current >= currentMax) {
					stateChange = true;
				} else {
					// 设置进度值
					mColor.setProgress(current + 1);
					// 设置底层进度值
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
		 * 需要注意的是setIndeterminate的参数为true 或false：
		 * true表示不确定模式：滚动条的当前值自动在最小到最大值之间来回移动
		 * ，形成这样一个动画效果，这个只是告诉别人“我正在工作”，但不能提示工作进度到哪个阶段。主要是在进行一些无法确定操作时间的任务时作为提示
		 *false表示确定模式根据你实际的进度设置进度值*/
		
	}
}
