package com.example.aac.data.fragment.list.defaults;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.aac.expansion.list.AacListFragment;
import com.aac.module.pres.RequiresPresenter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
@RequiresPresenter(TestFragmentPresenter.class)
public class TestListFragment extends AacListFragment<TestFragmentPresenter, String> {

    @Override
    public int getItemLayout() {
        return android.R.layout.simple_list_item_2;
    }

    /***
     * 等于0 list 大于0 GridS
     * **/
    @Override
    public int setGridSpanCount() {
        return 3;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setStartLoadMore(true);
    }

    @Override
    public void convertViewHolder(BaseViewHolder helper, String item) {
       // helper.setText(android.R.id.text1, item);
       // helper.setText(android.R.id.text2, item);
    }

    @Override
    protected boolean setOpenLazyLoad() {
        return true;
    }
}
