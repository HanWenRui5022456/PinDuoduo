package me.songning.mvp.ui;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import me.songning.mvp.R;
import me.songning.mvp.base.BaseActivity;

import me.songning.mvp.bean.Gank;

import me.songning.mvp.fragment.FiveFragment;
import me.songning.mvp.fragment.ForeFragment;
import me.songning.mvp.fragment.HomeFragment;
import me.songning.mvp.fragment.ThreeFragment;
import me.songning.mvp.fragment.TwoFragment;
import me.songning.mvp.mvp.contract.MainContract;
import me.songning.mvp.mvp.presenter.MainPresenter;


public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog mDialog;
    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private TextView mTextView;







    private HomeFragment homeFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private ForeFragment foreFragment;
    private FiveFragment fiveFragment;
    //
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButOne;
    private RadioButton mRadioButTwo;
    private RadioButton mRadioButThree;
    private RadioButton mRadioButFore;
    private RadioButton mRadioButFive;


    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mToolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(mToolbar);

     /*   mTextView = (TextView) findViewById(R.id.tv);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setCancelable(false);
        mDialog.setMessage("正在加载...");

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


        monitor();
        Loding();
        //DaggerMianComponent.builder().mainsModules(new MainsModules(this,"")).build().inject(this);
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);

    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void onSucceed(Gank data) {

        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();

        List<Gank.Result> results = data.getResults();

        mTextView.setText(results.get(new Random().nextInt(10)).toString());

        for (Gank.Result result : results) {
            Log.e(TAG, result.toString());
        }
    }

    @Override
    public void onFail(String err) {
        Log.e(TAG, err);
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideDialog() {
        mDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
























    private void monitor() {
//
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioButOne = (RadioButton) findViewById(R.id.Radio_but_one);
        mRadioButTwo = (RadioButton) findViewById(R.id.Radio_but_two);
        mRadioButThree = (RadioButton) findViewById(R.id.Radio_but_three);
        mRadioButFore = (RadioButton) findViewById(R.id.Radio_but_fore);
        mRadioButFive = (RadioButton) findViewById(R.id.Radio_but_five);

    }

    @Override
    protected void onStart() {
        setDefaultFragment();//写在onCreateView里面，当页面跑到其他Fragment再回来就不会生效
        super.onStart();
    }
    private void Loding() {


        homeFragment = new HomeFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        foreFragment = new ForeFragment();
        fiveFragment = new FiveFragment();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                FragmentTransaction transaction =getFragmentManager().beginTransaction();



                switch (checkedId){

                    case  R.id.Radio_but_one:
                        if(homeFragment==null){


                            homeFragment =HomeFragment.newInstance(getString(R.string.item_home));

                        }
                        //homeFragment =HomeFragment.newInstance(getString(R.string.item_home));
                        transaction.replace(R.id.sub_content,homeFragment);
                        break;
                    case  R.id.Radio_but_two:
                        if(twoFragment==null){




                            twoFragment=TwoFragment.newInstance(getString(R.string.item_Newproduct));

                        }

                        twoFragment=TwoFragment.newInstance(getString(R.string.item_Newproduct));
                        transaction.replace(R.id.sub_content,twoFragment);
                        break;




                    case  R.id.Radio_but_three:
                        if(threeFragment==null){
                            threeFragment=ThreeFragment.newInstance(getString(R.string.item_iherb));

                        }

                        transaction.replace(R.id.sub_content,threeFragment);

                        break;

                    case  R.id.Radio_but_fore:
                        if(foreFragment==null){



                            foreFragment=ForeFragment.newInstance(getString(R.string.item_search));

                        }
                        transaction.replace(R.id.sub_content,foreFragment);

                        break;

                    case  R.id.Radio_but_five:
                        if(fiveFragment==null){


                            fiveFragment=FiveFragment.newInstance(getString(R.string.item_personal));

                        }
                        transaction.replace(R.id.sub_content,fiveFragment);
                        break;
                }
                setTabState();
                transaction.commit();
            }
        });
    }

    private void setDefaultFragment() {
        mRadioButOne.setChecked(true);
        mRadioButTwo.setChecked(false);
        mRadioButThree.setChecked(false);
        mRadioButFore.setChecked(false);
        mRadioButFive.setChecked(false);
        if (mRadioButOne.isChecked()) {
            setTabState();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            homeFragment = homeFragment.newInstance(getString(R.string.item_home));
            transaction.replace(R.id.sub_content, homeFragment).commit();
        }
    }


    private void setTabState() {//设置状态
        setHomeState();
        setNewProduct();
        setIherbState();
        setSerachState();
        setPersonal();

    }

    private void setPersonal() {

        if (mRadioButFive.isChecked()) {
            mRadioButFive.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

        } else {
            mRadioButFive.setTextColor(ContextCompat.getColor(this, R.color.red));

        }
    }

    private void setSerachState() {

        if (mRadioButFore.isChecked()) {
            mRadioButFore.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            mRadioButFore.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }

    private void setIherbState() {

        if (mRadioButThree.isChecked()) {
            mRadioButThree.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            mRadioButThree.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }

    private void setNewProduct() {

        if (mRadioButTwo.isChecked()) {
            mRadioButTwo.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            mRadioButTwo.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }

    private void setHomeState() {
        if (mRadioButOne.isChecked()) {
            mRadioButOne.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            mRadioButOne.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }


}
