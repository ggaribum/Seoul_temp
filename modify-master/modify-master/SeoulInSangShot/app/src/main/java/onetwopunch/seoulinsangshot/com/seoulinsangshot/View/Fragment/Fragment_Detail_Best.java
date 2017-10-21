package onetwopunch.seoulinsangshot.com.seoulinsangshot.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.Controller.Adapter_Best;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Best_DataManager;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Data.BestVO;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Remote.RetrofitClient;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Remote.RetrofitService;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Best;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.DetailActivity;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.PostActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kwakgee on 2017. 9. 17..
 */

public class Fragment_Detail_Best extends Fragment {


    public ArrayList<Model_Best> adapterBestList;
    public static ArrayList<Model_Best> bestList;
    private BestVO repo;
    private ArrayList<Model_Best> tempList;

    //리사이클러뷰를 위한 변수
    RecyclerView rv_best;
    LinearLayoutManager manager;
    Adapter_Best adapter_best;

    String initials;

    FloatingActionButton bestFAB;

    Intent uploadP;


    public static Fragment_Detail_Best newInstance() {
        Fragment_Detail_Best fragment = new Fragment_Detail_Best();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_best, container, false);

        uploadP = new Intent(getContext(), PostActivity.class);

        initials = getArguments().getString("initials");

        bestFAB = (FloatingActionButton) rootView.findViewById(R.id.bestFAB);
        rv_best = (RecyclerView) rootView.findViewById(R.id.best_RecyclerView);

        //area랑 initials랑 같은 리스트만을 다시 저장해서 연결.
        adapterBestList = new ArrayList<Model_Best>();
       /* bestList = new ArrayList<Model_Best>();


        RetrofitService retrofitService = RetrofitClient.retrofit.create(RetrofitService.class);
        Call<BestVO> call = retrofitService.getBestData();
        call.enqueue(new Callback<BestVO>() {
            @Override
            public void onResponse(Call<BestVO> call, Response<BestVO> response) {

                repo = response.body();
                tempList = repo.getList();

                for (int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i).getArea().equals(initials)) {
                        int likecount = tempList.get(i).getLikecount();
                        int view = tempList.get(i).getView();
                        String id = tempList.get(i).getId();
                        String area = tempList.get(i).getArea();
                        String url = tempList.get(i).getUrl();
                        String phoneType = tempList.get(i).getPhoneType();
                        String phoneApp = tempList.get(i).getPhoneApp();
                        String season = tempList.get(i).getSeason();
                        String time = tempList.get(i).getTime();
                        String tip = tempList.get(i).getTip();

                        adapterBestList.add(new Model_Best(id, area, url, phoneType, phoneApp, season, time, tip, likecount, view));
                    }
                }
                manager = new LinearLayoutManager(rootView.getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                adapter_best = new Adapter_Best(getContext(), adapterBestList);
                rv_best.setLayoutManager(manager);
                rv_best.setAdapter(adapter_best);

            }

            @Override
            public void onFailure(Call<BestVO> call, Throwable t) {

            }
        });*/

        for (int i = 0; i < DetailActivity.bestList.size(); i++) {
            if (DetailActivity.bestList.get(i).getArea().equals(initials)) {
                int likecount = DetailActivity.bestList.get(i).getLikecount();
                int view = DetailActivity.bestList.get(i).getView();
                String id = DetailActivity.bestList.get(i).getId();
                String area = DetailActivity.bestList.get(i).getArea();
                String url = DetailActivity.bestList.get(i).getUrl();
                String phoneType = DetailActivity.bestList.get(i).getPhoneType();
                String phoneApp = DetailActivity.bestList.get(i).getPhoneApp();
                String season = DetailActivity.bestList.get(i).getSeason();
                String time = DetailActivity.bestList.get(i).getTime();
                String tip = DetailActivity.bestList.get(i).getTip();
                adapterBestList.add(new Model_Best(id, area, url, phoneType, phoneApp, season, time, tip, likecount, view));
            }

        }

        //리사이클러뷰 어뎁터에 연결.
        manager = new LinearLayoutManager(rootView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter_best = new Adapter_Best(getContext(), adapterBestList);
        rv_best.setLayoutManager(manager);
        rv_best.setAdapter(adapter_best);

        bestFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setIntentFlag(uploadP);
                startActivity(uploadP);

            }
        });


        return rootView;

    }


    public void setIntentFlag(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }

}
