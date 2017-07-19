package gocar.jeno.com.tablayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_myCustom;
    private GridLayoutManager layoutManager;
    ArrayList<TabLayouuBean> tagBeanList;
    private TadAdapter tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        setView();
        setRecyclerView();
    }

    private void setData() {
        tagBeanList = new ArrayList<>();
        tagBeanList.add(new TabLayouuBean("1", "准时"));
        tagBeanList.add(new TabLayouuBean("2", "非常绅士"));
        tagBeanList.add(new TabLayouuBean("3", "非常有礼貌"));
        tagBeanList.add(new TabLayouuBean("4", "很会照顾女生"));
        tagBeanList.add(new TabLayouuBean("5", "我的男神是个大暖男哦"));
        tagBeanList.add(new TabLayouuBean("6", "谈吐优雅"));
        tagBeanList.add(new TabLayouuBean("7", "送我到楼下"));
        tagBeanList.add(new TabLayouuBean("9", "迟到"));
        tagBeanList.add(new TabLayouuBean("10", "态度恶劣"));
        tagBeanList.add(new TabLayouuBean("11", "有不礼貌行为"));
        tagBeanList.add(new TabLayouuBean("12", "有侮辱性语言有暴力倾向"));
        tagBeanList.add(new TabLayouuBean("13", "人身攻击"));
        tagBeanList.add(new TabLayouuBean("14", "临时改变行程"));
        tagBeanList.add(new TabLayouuBean("15", "客户迟到并无理要求延长约会时间"));
    }

    private void setRecyclerView() {

        layoutManager = new GridLayoutManager(this, 2);
        rv_myCustom.setLayoutManager(layoutManager);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                String name = tagBeanList.get(position).getTagName();
                if (name.length() > 9) {
                    return 2;//每个item项占据多少个单元格      每行有两个item 默认是
                }
                return 1;  //每个item项占据多少个单元格

            }
        });
        tagAdapter = new TadAdapter(tagBeanList);
        rv_myCustom.setAdapter(tagAdapter);
    }

    private void setView() {
        rv_myCustom = (RecyclerView) findViewById(R.id.rv_myCustom);
    }
}
