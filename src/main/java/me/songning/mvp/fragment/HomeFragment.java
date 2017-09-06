package me.songning.mvp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.songning.mvp.R;
import me.songning.mvp.adapter.MyRecyclerViewAdapter;
import me.songning.mvp.adapter.NetworkImageHolderView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    protected ImageLoader imageLoader;
    private View view;
    private RecyclerView recy_view;
    private MyRecyclerViewAdapter adapter;


    public static HomeFragment newInstance(String s){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();

        homeFragment.setArguments(bundle);
        return homeFragment;
}

    private List<String> list=new ArrayList<>();
    private List<String> networkImages;
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_home, container, false);


        ConvenientBanner banner  = (ConvenientBanner) view.findViewById(R.id.fragment_home_bunner);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));


        //网络加载例子
        networkImages = Arrays.asList(images);
        banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },networkImages)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(1000)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置点击监听事件
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(),"这是第："+position,Toast.LENGTH_LONG).show();
                    }
                })
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);

        //initHTTP();
        initView();
        initData();

        return view;
    }

    private void initHTTP() {

        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://apiv3.yangkeduo.com/v4/goods?page=1&size=20&list_id=0738400526&pdduid=")
                .build();


    }

    private void initView() {

        recy_view = (RecyclerView) view.findViewById(R.id.recy_view);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MyRecyclerViewAdapter(getActivity(),list);
        recy_view.setAdapter(adapter);
    }



    private void initData()
    {

        for (int i = 0; i < 20; i++)
        {
            list.add("这是第" +i);
        }
    }


}