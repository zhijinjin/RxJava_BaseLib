package com.zjj.baselibrary.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhijinjin (951507056@qq.com)
 * on 2018/3/29.
 */

public class ListUtils {
    /** default join separator **/
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }


    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[] {separator}));
    }

    public static String join(List<String> list, String separator) {
        if (isEmpty(list)) {
            return "";
        }
        if (separator == null) {
            separator = DEFAULT_JOIN_SEPARATOR;
        }

        StringBuilder joinStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            joinStr.append(list.get(i));
            if (i != list.size() - 1) {
                joinStr.append(separator);
            }
        }
        return joinStr.toString();
    }

    /**
     * add distinct entry to list
     *
     * @param <V>
     * @param sourceList
     * @param entry
     * @return if entry already exist in sourceList, return false, else add it and return true.
     */
    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
        return (sourceList != null && !sourceList.contains(entry)) ? sourceList.add(entry) : false;
    }

    /**
     * add all distinct entry to list1 from list2
     *
     * @param <V>
     * @param sourceList
     * @param entryList
     * @return the count of entries be added
     */
    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if (sourceList == null || isEmpty(entryList)) {
            return 0;
        }

        int sourceCount = sourceList.size();
        for (V entry : entryList) {
            if (!sourceList.contains(entry)) {
                sourceList.add(entry);
            }
        }
        return sourceList.size() - sourceCount;
    }

    /**
     * remove duplicate entries in list
     * @param <V>
     * @param sourceList
     * @return the count of entries be removed
     */
    public static <V> int distinctList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return 0;
        }

        int sourceCount = sourceList.size();
        int sourceListSize = sourceList.size();
        for (int i = 0; i < sourceListSize; i++) {
            for (int j = (i + 1); j < sourceListSize; j++) {
                if (sourceList.get(i).equals(sourceList.get(j))) {
                    sourceList.remove(j);
                    sourceListSize = sourceList.size();
                    j--;
                }
            }
        }
        return sourceCount - sourceList.size();
    }

    /**
     * add not null entry to list
     *
     * @param sourceList
     * @param value
     * @return <ul>
     *         <li>if sourceList is null, return false</li>
     *         <li>if value is null, return false</li>
     *         <li>return {@link List#add(Object)}</li>
     *         </ul>
     */
    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
        return (sourceList != null && value != null) ? sourceList.add(value) : false;
    }



    /**
     * invert list
     *
     * @param <V>
     * @param sourceList
     * @return
     */
    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }

        List<V> invertList = new ArrayList<V>(sourceList.size());
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            invertList.add(sourceList.get(i));
        }
        return invertList;
    }
}