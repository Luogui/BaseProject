/*
 * Copyright (c) 2018.
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

import com.android.luogui.baselibrary.MyApplication;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by QingMei on 2017/7/10.
 * desc:
 */

public class CacheProviders {

    private static Providers userCacheProviders;

    public synchronized static Providers getCache() {
        if (userCacheProviders == null) {
            userCacheProviders = new RxCache.Builder()
                    .persistence(MyApplication.getInstance().getExternalCacheDir(), new GsonSpeaker())
                    .using(Providers.class);
        }
        return userCacheProviders;
    }
}
