/*
 * Copyright (c) 2017.
 * Create by LuoGui.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.luogui.baseproject;

import com.android.luogui.baselibrary.base.BaseListActivity;
import com.android.luogui.baselibrary.base.adapter.BaseViewHolder;
import com.android.luogui.baselibrary.base.adapter.SingleAdapter;
import com.android.luogui.baselibrary.netWork.retrofit.ApiClint;
import com.android.luogui.baselibrary.netWork.retrofit.HttpParse;
import com.android.luogui.baselibrary.netWork.retrofit.ResultCallBack;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baseproject.adapter.XAdapter;
import com.android.luogui.baseproject.bean.NewsBean;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwipeActivity extends BaseListActivity<NewsBean> {


    @Override
    protected void setAdapter() {
        initPage = 1;
        adapter = new SingleAdapter<NewsBean>(this, R.layout.adapter_item_text, mList) {
            @Override
            protected void convert(BaseViewHolder holder, NewsBean newsBean, int position) {

            }
        };
        adapter.setItemClick((position, item) -> {
            LogUtil.toast(item.toString());
        });
    }

    @Override
    protected void getDataList(int page) {
        Call<String> call = ApiClint.createApiString(ApiService.class).getString(page, "性感");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                List<NewsBean> newsBeanList = HttpParse
                        .parseArrayObject(response.body(), "res", NewsBean.class);
                dispatch(newsBeanList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

//        call.enqueue(new ResultCallBack<NewsBean>("a") {
//            @Override
//            public void onSuccess(NewsBean newsBean) {
//
//            }
//
//            @Override
//            public void onFailed(int code, String s) {
//
//            }
//        });
    }
}
