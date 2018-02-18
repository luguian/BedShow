package com.nova.bedshow.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/6.
 */

public abstract class BaseFragment extends Fragment {
    protected View view;//当前界面的根
    private int layoutId;//当前界面对应的布局

    public BaseFragment(int layoutId){
        super();
        this.layoutId = layoutId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = View.inflate(getActivity(),layoutId,null);
        ButterKnife.bind(this, view);
        initView();//初始化当前界面的主要内容
        initData();//初始化空间位置
        return view;

    }

    /**
     * 显示和隐藏
     *
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        //可见
        if (isVisibleToUser) {
            isShow();
            //不可见
        } else {
            isGone();
        }

    }
    /**
     *
     * 初始化当前界面的主要内容，即除了头部以外的其他部分
     */
    protected abstract void initView();

    /**
     *fragment可见
     *
     */
    protected abstract void isShow();

    /**
     * 初始化控件位置
     *
     */
    protected abstract void initData();

    /**
     * fragment不可见
     */
    protected abstract void isGone();


    @Override
    public void onDestroy(){
        super.onDestroy();
        //检测内存泄漏的代码
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Field childFragmentManager;
        try {
            childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
