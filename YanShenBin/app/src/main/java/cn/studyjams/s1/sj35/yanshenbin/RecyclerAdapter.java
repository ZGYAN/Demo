/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package cn.studyjams.s1.sj35.yanshenbin;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * User: Yan
 * Date: 2016-04-24
 * Time: 11:42
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<String> datas;


    public RecyclerAdapter(Context context,List<String> datas) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.datas = datas;
    }


    @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        view.setBackgroundResource(mBackground);
        return new MyViewHolder(view);
    }


    @Override public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mBoundString = datas.get(position);
        String data = datas.get(position);
        if (data!=null){
            holder.tv.setText(data);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_NAME, holder.mBoundString);

                    context.startActivity(intent);
                }
            });
        }
        Glide.with(holder.img.getContext())
             .load(R.drawable.a)
             .fitCenter()
             .into(holder.img);
    }


    @Override public int getItemCount() {
        return datas.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
        public String mBoundString;
        private View view;
        private TextView tv;
        private ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tv = (TextView) itemView.findViewById(R.id.tv);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
