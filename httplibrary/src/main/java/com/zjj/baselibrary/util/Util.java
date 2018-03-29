package com.zjj.baselibrary.util;

import java.util.Objects;

/**
 * Created by zhijinjin on 2018/3/28.
 */

public class Util {
    public static String join(CharSequence delimiter, CharSequence... elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        // Number of elements not likely worth Arrays.stream overhead.
        StringBuilder builder = new StringBuilder();
        for (CharSequence cs: elements) {
            if (cs == elements[0]){
                builder.append(cs);
            }else {
                builder.append(delimiter);
                builder.append(cs);
            }
        }
        return builder.toString();
    }

    public static String join(CharSequence delimiter,
                              Iterable<? extends CharSequence> elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        StringBuilder builder = new StringBuilder();
        for (CharSequence cs: elements) {
            if (elements.iterator().hasNext()){
                builder.append(cs);
                builder.append(delimiter);
            }else {
                builder.append(cs);
            }
        }
        return builder.toString();
    }
}
