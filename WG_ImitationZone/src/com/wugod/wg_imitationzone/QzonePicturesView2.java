package com.wugod.wg_imitationzone;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * ·ÂQQ¿Õ¼äÕÕÆ¬¿Õ¼ä
 * 
 * @author hanhuizhong
 * 
 */
public class QzonePicturesView2 extends ViewGroup {
	private final static String TAG = "QzonePicturesView";

	private int mHorizontalSpacing = 0;
	private int mVerticalSpacing = 0;
	private int childCurrentWidth = 0;

	public QzonePicturesView2(Context context) {
		super(context, null);
		// TODO Auto-generated constructor stub
	}

	public QzonePicturesView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public QzonePicturesView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.QzonePictureView, defStyle, 0);
		mHorizontalSpacing = a.getDimensionPixelOffset(
				R.styleable.QzonePictureView_horizontalSpacing, 0);
		mVerticalSpacing = a.getDimensionPixelOffset(
				R.styleable.QzonePictureView_verticalSpacing, 0);
		a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.i(TAG, "onMeasure");

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

		Log.i(TAG, "sizeWidth£º" + sizeWidth + "£¬sizeHeight£º" + sizeHeight);

		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int width = sizeWidth;
		int height = sizeHeight;

		int childCount = getChildCount();
		Log.i(TAG, "childCount£º" + childCount);

		int childWidth = 0;
		int childHeight = 0;

		if (childCount == 1 || childCount == 2) {
			View childView = getChildAt(0);

			childWidth = childView.getMeasuredWidth();
			childHeight = childView.getMeasuredHeight();

			// width = childWidth * 2 + childParams.leftMargin
			// + childParams.rightMargin + mHorizontalSpacing;

			childCurrentWidth = (width - -mHorizontalSpacing) / 2;
			height = childCurrentWidth;

		} else if (childCount > 2) {
			View childView = getChildAt(0);

			childWidth = childView.getMeasuredWidth();
			childHeight = childView.getMeasuredHeight();

			// width = childWidth * 3 + childParams.leftMargin
			// + childParams.rightMargin + mHorizontalSpacing * 2;

			// height = childHeight * (childCount / 3 + 1) +
			// childParams.topMargin
			// + childParams.bottomMargin + mVerticalSpacing
			// * (childCount / 3);

			childCurrentWidth = (width - -mHorizontalSpacing * 2) / 3;
			height = childCurrentWidth * (childCount / 3 + 1)
					+ mVerticalSpacing * (childCount / 3);
		}

		Log.i(TAG, "childCurrentWidth£º" + childCurrentWidth);
		Log.i(TAG, "width£º" + width + "£¬height£º" + height);
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onLayout");

		int childCount = getChildCount();
		Log.i(TAG, "childCount£º" + childCount);

		for (int i = 0; i < childCount; i++) {

			View childView = getChildAt(i);
			childView.setLayoutParams(new LayoutParams(childCurrentWidth,
					childCurrentWidth));

			int cl = 0, ct = 0, cr = 0, cb = 0;

			cl = i % 3 * (childCurrentWidth + mHorizontalSpacing);
			ct = (i + 1) / 3 * (childCurrentWidth + mVerticalSpacing);
			cr = cl + childCurrentWidth;
			cb = childCurrentWidth + ct;

			Log.i(TAG, "childView_index£º" + i + "{cl£º" + cl + "£¬ct£º" + ct + "£¬cr£º"
					+ cr + "£¬cb£º" + cb + "}");
			childView.layout(cl, ct, cr, cb);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.i(TAG, "onDraw");
	}
}
