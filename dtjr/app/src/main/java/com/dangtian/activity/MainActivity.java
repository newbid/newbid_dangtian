package com.dangtian.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.dangtian.fragment.AccountFragment;
import com.dangtian.fragment.CapitalFragment;
import com.dangtian.fragment.InvestmentFragment;
import com.dangtian.fragment.InviteFragment;
import com.dangtian.fragment.MoreFragment;
import com.dangtian.fragment.SigininFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity  extends FragmentActivity {
    private static final int[] tabStrings = {R.string.investment, R.string.account,R.string.capital,
            R.string.sigin_in,R.string.invitation,R.string.more};
    private FragmentTabHost mTabHost;
    private Bundle mBundles[] = new Bundle[tabStrings.length];
    private Map<String, Bundle> mBundleMap = new HashMap<>();
    private int savedNumber = 0;
    private boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        bindFragment();

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {/*
                if (tabId == getResources().getString(tabStrings[2])) {
                    if (!UserModel.hasLogin()) {
                        startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), 1);
                    }
                } else {
                    savedNumber = mTabHost.getCurrentTab();
                }*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
/*        if (!UserModel.hasLogin() && mTabHost.getCurrentTab() == 2){
            if (savedNumber == 2){
                savedNumber = 0;
            }
            mTabHost.setCurrentTab(savedNumber);
        }*/
    }

    private void bindFragment() {
        Class[] clazzes = new Class[]{
                InvestmentFragment.class,
                AccountFragment.class,
                CapitalFragment.class,
                SigininFragment.class,
                InviteFragment.class,
                MoreFragment.class
        };

        int[] drawableIds = new int[]{
                R.drawable.home_page_push_investment,
                R.drawable.home_page_account,
                R.drawable.home_page_push_capital,
                R.drawable.home_page_push_sign,
                R.drawable.home_page_push_invitation,
                R.drawable.home_page_push_more
        };

        int[] ViewsID = new int[]{
                R.id.id_item1,
                R.id.id_item2,
                R.id.id_item3,
                R.id.id_item4,
                R.id.id_item5,
                R.id.id_item6
        };

        findViewById(R.id.id_item1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTabHost.setCurrentTab(0);
                    }
                }
        );

        findViewById(R.id.id_item2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTabHost.setCurrentTab(1);
                    }
                }
        );
        findViewById(R.id.id_item3).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTabHost.setCurrentTab(2);
                    }
                }
        );
        findViewById(R.id.id_item4).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTabHost.setCurrentTab(3);
                    }
                }
        );
        findViewById(R.id.id_item5).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTabHost.setCurrentTab(4);
                    }
                }
        );
        findViewById(R.id.id_item6).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTabHost.setCurrentTab(5);
                    }
                }
        );

        for (int i = 0; i < tabStrings.length; i++) {
            String s = getResources().getString(tabStrings[i]);
            View view =  getLayoutInflater().inflate(R.layout.item_tab, null);
            ((TextView) view.findViewById(R.id.text)).setText(s);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(drawableIds[i]);
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(s);
            tabSpec.setIndicator(view);
            mTabHost.addTab(tabSpec, clazzes[i], new Bundle());

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data == null) {
                    return;
                }

                Class<?> cls = (Class<?>) data.getSerializableExtra("class");
                if (cls == null) {
                    return;
                }

                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent(this, cls);
                    Bundle bundle = mBundleMap.get(cls.getName());
                    if (bundle != null) {
                        intent.putExtras(bundle);
                    }

                    startActivity(intent);
                }

                mBundleMap.remove(cls.getName());
                return;
            case 1:
                if (resultCode == RESULT_OK){
                    savedNumber = 2;
                }
                return;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit(){
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            isExit = false;
        }
    };
}
