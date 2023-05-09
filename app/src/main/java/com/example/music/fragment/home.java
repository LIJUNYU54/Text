package com.example.music.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.music.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class home extends Fragment {

    private TabLayout tablayout;
    ViewPager viewpager;
    FragmentManager manager;
    private FragmentTransaction transtion;
    private ArrayList<String> titles;
    private List<Fragment> list;
    private MyFragmentPageAdapter adapter;

    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
//    FragmentThree fragmentThree;
//    FragmentFour fragmentFour;
//    FragmentFive fragmentFive;
//    FragmentSix fragmentSix;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.home,container,false);
        tablayout= (TabLayout) view.findViewById(R.id.tabLayout);
        viewpager= (ViewPager) view.findViewById(R.id.viewpager);
        initData();//初始化数据
        setListener();//点击监听
     return view;
     }

    private void setListener() {

    }
    private void initData() {

        //将各个标题装在titles里面
        titles = new ArrayList<String>();
        titles.add("本机音乐");
        titles.add("预存音乐");
//        titles.add("欧美");
//        titles.add("日韩");
//        titles.add("清新");
//        titles.add("其他");

        //将两个Fragment装进集合中
        list = new ArrayList<Fragment>();
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
//        fragmentThree=new FragmentThree();
//        fragmentFour=new FragmentFour();
//        fragmentFive=new FragmentFive();
//        fragmentSix=new FragmentSix();
        list.add(fragmentOne);
        list.add(fragmentTwo);
//        list.add(fragmentThree);
//        list.add(fragmentFour);
//        list.add(fragmentFive);
//        list.add(fragmentSix);

        manager = getChildFragmentManager();
        adapter = new MyFragmentPageAdapter(manager);
        transtion = manager.beginTransaction();
        transtion.commit();
        viewpager.setAdapter(adapter);

        // tablayout.addTab可以将标题添加进Tab里面，true表示默认选中
        tablayout.addTab(tablayout.newTab().setText(titles.get(0)), true);
        tablayout.addTab(tablayout.newTab().setText(titles.get(1)), false);
//        tablayout.addTab(tablayout.newTab().setText(titles.get(2)), false);
//        tablayout.addTab(tablayout.newTab().setText(titles.get(3)), false);
//        tablayout.addTab(tablayout.newTab().setText(titles.get(4)), false);
//        tablayout.addTab(tablayout.newTab().setText(titles.get(5)), false);

        //这两个方法是将Tablayout和Viewpager联合起来
        tablayout.setupWithViewPager(viewpager);
        //tablayout.setTabsFromPagerAdapter(adapter);

    }
    private class MyFragmentPageAdapter extends FragmentPagerAdapter{
        public MyFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
