package com.wugod.wg_imitationzone;

import java.util.ArrayList;
import java.util.Iterator;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * ��QQ�ռ���Ƭ�ռ�
 * 
 * @author hanhuizhong
 * 
 */
public class QzonePicturesView extends ViewGroup {
	private final static String TAG = "QzonePicturesView";

	private int mHorizontalSpacing = 0;
	private int mVerticalSpacing = 0;
	private int childCurrentWidth = 0;
	private ArrayList<String> mImgArrayList;
	private int mResource;
	private int mDefaultImgId;

	public QzonePicturesView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public QzonePicturesView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public QzonePicturesView(Context context, AttributeSet attrs, int defStyle) {
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

		Log.i(TAG, "sizeWidth��" + sizeWidth + "��sizeHeight��" + sizeHeight);

		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int width = sizeWidth;
		int height = sizeHeight;

		int childCount = getChildCount();
		Log.i(TAG, "childCount��" + childCount);

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

		Log.i(TAG, "childCurrentWidth��" + childCurrentWidth);
		Log.i(TAG, "width��" + width + "��height��" + height);
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onLayout");

		int childCount = getChildCount();
		Log.i(TAG, "childCount��" + childCount);

		for (int i = 0; i < childCount; i++) {

			View childView = getChildAt(i);
			childView.setLayoutParams(new LayoutParams(childCurrentWidth,
					childCurrentWidth));

			int cl = 0, ct = 0, cr = 0, cb = 0;

			cl = i % 3 * (childCurrentWidth + mHorizontalSpacing);
			ct = (i + 1) / 3 * (childCurrentWidth + mVerticalSpacing);
			cr = cl + childCurrentWidth;
			cb = childCurrentWidth + ct;

			Log.i(TAG, "childView_index��" + i + "{cl��" + cl + "��ct��" + ct
					+ "��cr��" + cr + "��cb��" + cb + "}");
			childView.layout(cl, ct, cr, cb);
		}
	}

	public void initDataAndView(ArrayList<String> imgList, int defaultImgId,
			int resource) {
		// TODO Auto-generated method stub
		Log.i(TAG, "initDataAndView");
		mImgArrayList = imgList;
		mDefaultImgId = defaultImgId;
		mResource = resource;
		notifyChangeView();
	}

	public void notifyChangeView() {
		// TODO Auto-generated method stub
		Log.i(TAG, "notifyChangeView");
		removeAllViews();
		for (String imgUrl : mImgArrayList) {

			ImageView childView = (ImageView) LayoutInflater.from(getContext())
					.inflate(mResource, this, false);

			Glide.with(getContext()).load(imgUrl).placeholder(mDefaultImgId)
					.into(childView);
			addView(childView);
		}

		invalidate();
		//postInvalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.i(TAG, "onDraw");
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		// TODO Auto-generated method stub
		Log.i(TAG, "drawChild");
		return super.drawChild(canvas, child, drawingTime);
	}
}
