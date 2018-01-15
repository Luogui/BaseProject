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

package com.android.luogui.baselibrary.util;

import android.os.Build;
import android.support.annotation.NonNull;
import android.webkit.WebSettings;

import com.android.luogui.baselibrary.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Horrarndoo on 2017/9/18.
 * <p>
 * HttpUtils 主要用于获取UserAgent
 */

public class HttpUtils {
    /**
     * 获取UserAgent
     *
     * @return UserAgent
     */
    @NonNull
    public static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(MyApplication.getInstance());
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String makeUA() {
        final String ua = Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
        return ua;
    }

    public static String[] returnImageUrlsFromHtml(String html) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\" +
                ".jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\" +
                ".jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern
                .CASE_INSENSITIVE);
        Matcher m = p.matcher(html);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m
                    .group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList.size() == 0) {
            LogUtil.e("资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }
}
