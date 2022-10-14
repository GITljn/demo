package com.ljn.demo.spring_security.temp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author ljn
 * @since 2022-05-07
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> getPermissionsByUserId(Long userId);
}
