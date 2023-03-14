package com.ljn.demo.middleware.elasticsearch.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljn.demo.middleware.elasticsearch.community.entity.DiscussPost;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljn
 * @since 2022-09-29
 */
public interface DiscussPostService extends IService<DiscussPost> {
    List<DiscussPost> queryDiscussPosts(int userId, int page, int size);

    int queryDiscussPostRows(int userId);

    void addDiscussPost(DiscussPost post);
}
