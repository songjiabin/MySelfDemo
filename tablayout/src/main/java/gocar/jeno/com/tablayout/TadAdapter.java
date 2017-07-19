package gocar.jeno.com.tablayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2017/07/12
 * desc   :
 * version: 1.0.0
 */

public class TadAdapter extends RecyclerView.Adapter<TadAdapter.ViewHolder> {


    private List<TabLayouuBean> tagList;

    private boolean isSelected = false;

    private List<TabLayouuBean> selectList;

    public TadAdapter(List<TabLayouuBean> tagList) {
        this.tagList = tagList;
        selectList = new ArrayList<>();
    }

    @Override
    public TadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final TadAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText(tagList.get(position).getTagName());
        holder.itemView.setTag(tagList.get(position));

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected = !holder.mTextView.isSelected();
                if (isSelected) {
                    holder.mTextView.setSelected(true);
                    holder.mTextView.setBackgroundResource(R.drawable.tag_checked_bg);
//                    selectList.add(tagList.get(position)); //当你要得到 选中的效果的话 那么可以 进行 add();   和 removoe();
                } else {
                    holder.mTextView.setSelected(false);
                    holder.mTextView.setBackgroundResource(R.drawable.tag_checked_normal);
//                    selectList.remove(tagList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tag_tv);
        }
    }

    public List<TabLayouuBean> getSelectData() {
        return selectList;  //左后得到选中的数据
    }
}
