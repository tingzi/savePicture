package com.quick.savepicture;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.quick.savepicture.bean.Picture;
import com.quick.savepicture.contract.PictureContract;
import com.quick.savepicture.presenter.PicturePresenter;
import com.quick.savepicture.view.PictureAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PictureContract.View{

    private PictureContract.Presenter presenter;
    private RecyclerView recyclerView;
    private PictureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPermission();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(manager);
        adapter = new PictureAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    private void initPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if(granted) {
                        Logger.d("grant success");
                        presenter = new PicturePresenter();
                        presenter.load(this);
                    } else {
                        Logger.d("grant failure");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != presenter) {
            presenter.onDestroy();
        }
    }

    @Override
    public void show(List<Picture> pictures) {
        adapter.loadData(pictures);
    }
}
