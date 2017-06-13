package com.xfi.picasso;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter = new DemoAdapter());
        adapter.replaceAll(getData());
    }

    public ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        for (String url : ImageUtil.imageUrls) {
            list.add(url);
        }
        return list;
    }

    public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.BaseViewHolder> {
        private ArrayList<String> dataList = new ArrayList<>();

        public void replaceAll(ArrayList<String> list) {
            dataList.clear();
            if (list != null && list.size() > 0) {
                dataList.addAll(list);
            }
            notifyDataSetChanged();
        }

        @Override
        public DemoAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new OneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_demo, parent, false));
        }

        @Override
        public void onBindViewHolder(DemoAdapter.BaseViewHolder holder, int position) {
            holder.setData(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList != null ? dataList.size() : 0;
        }

        public class BaseViewHolder extends RecyclerView.ViewHolder {
            public BaseViewHolder(View itemView) {
                super(itemView);
            }

            void setData(Object data) {
            }
        }

        private class OneViewHolder extends BaseViewHolder {
            private ImageView ivImage;

            public OneViewHolder(View view) {
                super(view);
                ivImage = (ImageView) view.findViewById(R.id.ivImage);
                int width = ((Activity) ivImage.getContext()).getWindowManager().getDefaultDisplay().getWidth();
                ViewGroup.LayoutParams params = ivImage.getLayoutParams();
                //设置图片的相对于屏幕的宽高比
                params.width = width / 3;
                params.height = (int) (200 + Math.random() * 400);
                ivImage.setLayoutParams(params);
            }

            @Override
            void setData(Object data) {
                if (data != null) {
                    String text = (String) data;
                    Glide.with(itemView.getContext())
                            .load(text)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.mipmap.picasso)
                            .crossFade()
                            .into(ivImage);
                }
            }
        }
    }
}
