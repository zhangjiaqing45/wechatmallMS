package com.fante.project.business.bizDescription.utils;


import com.fante.project.business.bizDescription.domain.BizDescription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 权限数据处理
 *
 * @author fante
 */
public class TreeUtils {
    List<BizDescription> returnList = new ArrayList<BizDescription>();

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<BizDescription> getChildPerms(List<BizDescription> list, int parentId) {
        List<BizDescription> returnList = new ArrayList<BizDescription>();
        for (Iterator<BizDescription> iterator = list.iterator(); iterator.hasNext(); ) {
            BizDescription t = (BizDescription) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static void recursionFn(List<BizDescription> list, BizDescription t) {
        // 得到子节点列表
        List<BizDescription> childList = getChildList(list, t);
        t.setChildren(childList);
        for (BizDescription tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<BizDescription> it = childList.iterator();
                while (it.hasNext()) {
                    BizDescription n = (BizDescription) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<BizDescription> getChildList(List<BizDescription> list, BizDescription t) {

        List<BizDescription> tlist = new ArrayList<BizDescription>();
        Iterator<BizDescription> it = list.iterator();
        while (it.hasNext()) {
            BizDescription n = (BizDescription) it.next();
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<BizDescription> list, BizDescription t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list   分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<BizDescription> getChildPerms(List<BizDescription> list, int typeId, String prefix) {
        if (list == null) {
            return null;
        }
        for (Iterator<BizDescription> iterator = list.iterator(); iterator.hasNext(); ) {
            BizDescription node = (BizDescription) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId) {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<BizDescription> list, BizDescription node, String p) {
        // 得到子节点列表
        List<BizDescription> childList = getChildList(list, node);
        if (hasChild(list, node)) {
            // 判断是否有子节点
            returnList.add(node);
            Iterator<BizDescription> it = childList.iterator();
            while (it.hasNext()) {
                BizDescription n = (BizDescription) it.next();
                n.setDescTitle(p + n.getDescTitle());
                recursionFn(list, n, p + p);
            }
        } else {
            returnList.add(node);
        }
    }
}
