package com.ljn.demo.acl.helper;


import com.ljn.demo.acl.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建菜单数据
 * </p>
 *
 * @author qy
 * @since 2019-11-11
 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<Permission> bulid(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission parentNode, List<Permission> treeNodes) {
        parentNode.setChildren(new ArrayList<>());
        for (Permission treeNode : treeNodes) {
            if(parentNode.getId().equals(treeNode.getPid())) {
                int level = parentNode.getLevel() + 1;
                treeNode.setLevel(level);
//                if (parentNode.getChildren() == null) {
//                    parentNode.setChildren(new ArrayList<>());
//                }
                parentNode.getChildren().add(findChildren(treeNode,treeNodes));
            }
        }
        return parentNode;
    }
}
