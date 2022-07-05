package com.ljn.demo.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用递归构建多级目录
 */
public class PermissionHelper {

    /**
     * 先找到所有的一级目录，根据一级目录递归构建多级目录
     * @param permissions 所有的权限
     * @return
     */
    public static List<Permission> bulid(List<Permission> permissions) {
        List<Permission> trees = new ArrayList<>();
        for (Permission permission : permissions) {
            if ("0".equals(permission.getPid())) {
                permission.setLevel(1);
                trees.add(findChildren(permission,permissions));
            }
        }
        return trees;
    }

    /**
     * 根据一级目录递归构建多级目录
     * @param permissions
     * @return
     */
    public static Permission findChildren(Permission parentPermission, List<Permission> permissions) {
        parentPermission.setChildren(new ArrayList<>());
        for (Permission permission : permissions) {
            if(parentPermission.getId().equals(permission.getPid())) {
                int level = parentPermission.getLevel() + 1;
                permission.setLevel(level);
//                if (parentPermission.getChildren() == null) {
//                    parentPermission.setChildren(new ArrayList<>());
//                }
                parentPermission.getChildren().add(findChildren(permission,permissions));
            }
        }
        return parentPermission;
    }

    /**
     * 递归查找当前节点及其子节点，权限列表只需查询一次
     * @param id 当前节点
     * @param permissionList 所有权限列表
     * @param ids 当前节点及其子节点
     */
    public static void selectChildRecursion(String id, List<Permission> permissionList, List<String> ids) {
        ids.add(id);
        for (Permission permission : permissionList) {
            if (id != null && id.equals(permission.getPid())) {
                selectChildRecursion(permission.getId(), permissionList, ids);
            }
        }
    }

    /**
     * 递归查找当前节点及其子节点，每次查询当前节点的子节点
     * @param id 当前节点
     * @param ids 当前节点及其子节点
     */
    public static void selectChildListById(String id, List<String> ids) {
//        ids.add(id);
//        List<Permission> childList = permissionMapper.selectList(new QueryWrapper<Permission>().eq("pid", id).select("id"));
//        childList.stream().forEach(item -> {
//            selectChildListById(item.getId(), ids);
//        });
    }
}
