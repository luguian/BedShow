package com.nova.bedshow.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nova.bedshow.R;
import com.nova.bedshow.fragment.DynamicFragment;
import com.nova.bedshow.fragment.FigureFragment;
import com.nova.bedshow.fragment.MainFragment;
import com.nova.bedshow.fragment.MineFragment;
import com.nova.bedshow.fragment.VideoFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.iv_main)
    ImageView iv_main;
    @Bind(R.id.text_main)
    TextView text_main;
    @Bind(R.id.iv_photo)
    ImageView iv_photo;
    @Bind(R.id.text_photo)
    TextView text_photo;
    @Bind(R.id.iv_video)
    ImageView iv_video;
    @Bind(R.id.text_video)
    TextView text_video;
    @Bind(R.id.iv_dynamic)
    ImageView iv_dynamic;
    @Bind(R.id.text_dynamic)
    TextView text_dynamic;
    @Bind(R.id.iv_mine)
    ImageView iv_mine;
    @Bind(R.id.text_mine)
    TextView text_mine;

    //首页
    private MainFragment mainFragment;
    //图辑
    private FigureFragment figureFragment;
    //视频页
    private VideoFragment videoFragment;
    //动态页
    private DynamicFragment dynamicFragment;
    //我的页面
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void initview() {
        fragmentManager = getSupportFragmentManager();
        showFragment(0);
    }

    @Override
    protected void initdata() {

    }


    //视频页 主页 图专辑 我的页面
   @OnClick({R.id.ll_main,R.id.ll_photo,R.id.ll_video,R.id.ll_dynamic,R.id.ll_mine})
    public void Onclick(View v){
       switch(v.getId()){
           case R.id.ll_main:
               showFragment(0);
               break;
           case R.id.ll_photo:
               showFragment(1);
               break;
           case R.id.ll_video:
               showFragment(2);
               break;
           case R.id.ll_dynamic:
               showFragment(3);
               break;
           case R.id.ll_mine:
               showFragment(4);
               break;
       }

   }


    private void showFragment(int page){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragment(ft);
        switch (page){
            case 0:
                if(mainFragment!=null){
                    ft.show(mainFragment);
                }else{
                    mainFragment =new MainFragment();
                    ft.add(R.id.ll_main_fragment,mainFragment);
                }
                changestatus(0);
                break;
            case 1:
                if(figureFragment != null){
                    ft.show(figureFragment);
                }else{
                    figureFragment = new FigureFragment();
                    ft.add(R.id.ll_main_fragment,figureFragment);
                }
                changestatus(1);
                break;
            case 2:
                if(videoFragment!=null){
                    ft.show(videoFragment);

                }else{
                    videoFragment = new VideoFragment();
                    ft.add(R.id.ll_main_fragment,videoFragment);
                }
                changestatus(2);
                break;
            case 3:
                if(dynamicFragment!=null){
                    ft.show(dynamicFragment);

                }else{
                    dynamicFragment = new DynamicFragment();
                    ft.add(R.id.ll_main_fragment,dynamicFragment);
                }
                changestatus(3);
                break;
            case 4:
                if(mineFragment!=null){
                    ft.show(mineFragment);

                }else{
                    mineFragment = new MineFragment();
                    ft.add(R.id.ll_main_fragment,mineFragment);
                }
                changestatus(4);
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft){
        if(mainFragment != null){
            ft.hide(mainFragment);
        }
        if(figureFragment !=null){
            ft.hide(figureFragment);
        }
        if(videoFragment!=null){
            ft.hide(videoFragment);
        }
        if(dynamicFragment!=null){
            ft.hide(dynamicFragment);
        }
        if(mineFragment != null){
            ft.hide(mineFragment);
        }

    }


    private void changestatus(int flag){
        iv_main.setBackgroundResource(flag == 0 ? R.drawable.tab_main_pressed : R.drawable.tab_main_default);
        text_main.setTextColor(flag == 0 ? getResources().getColor(R.color.theme_p) :getResources().getColor(R.color.text_default_9));
        iv_photo.setBackgroundResource(flag == 1 ? R.drawable.tab_figure_pressed : R.drawable.tab_figure_default);
        text_photo.setTextColor(flag == 1 ? getResources().getColor(R.color.theme_p) :getResources().getColor(R.color.text_default_9));
        iv_video.setBackgroundResource(flag == 2 ? R.drawable.tab_video_pressed : R.drawable.tab_video_default);
        text_video.setTextColor(flag == 2 ? getResources().getColor(R.color.theme_p) :getResources().getColor(R.color.text_default_9));
        iv_dynamic.setBackgroundResource(flag == 3 ? R.drawable.tab_dynamic_pressed : R.drawable.tab_dynamic_default);
        text_dynamic.setTextColor(flag == 3 ? getResources().getColor(R.color.theme_p) :getResources().getColor(R.color.text_default_9));
        iv_mine.setBackgroundResource(flag == 4 ? R.drawable.tab_mine_pressed : R.drawable.tab_mine_default);
        text_mine.setTextColor(flag == 4 ? getResources().getColor(R.color.theme_p) :getResources().getColor(R.color.text_default_9));

    }
}
