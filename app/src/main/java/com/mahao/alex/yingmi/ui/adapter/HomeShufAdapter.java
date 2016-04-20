package com.mahao.alex.yingmi.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mahao.alex.yingmi.utils.DimenUtils;

import java.util.List;

/**
 * 首页轮播图
 * Created by mdw on 2016/4/20.
 */
public class HomeShufAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private int padding;

    private List<ImageView> mImageViewList;

    private OnPageSelectListener listener;

    public HomeShufAdapter(List<ImageView> mImageViewList) {
        this.mImageViewList = mImageViewList;
        padding = DimenUtils.dp2px(40);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(mImageViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgView = mImageViewList.get(position);


        container.addView(imgView);

        return imgView;
    }

    @Override
    public int getCount() {
        return mImageViewList == null ? 0 : mImageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        if (mImageViewList.size()>0&&position<mImageViewList.size()) {
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            int outPadding = (int) (positionOffset * padding);
            mImageViewList.get(position).setPadding(outPadding, outPadding, outPadding, outPadding);

            if (position < mImageViewList.size() - 1) {
                int inPadding = (int) ((1 - positionOffset) * padding);
                mImageViewList.get(position + 1).setPadding(inPadding, inPadding, inPadding, inPadding);
            }
        }

    }

    @Override
    public void onPageSelected(int position) {
        if (listener != null) {
            listener.select(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.listener = listener;
    }


    public interface OnPageSelectListener {
        void select(int position);
    }
}
