package com.ljn.demo.rabbitmq.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {
    /*
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "seckillQueue")
    public void receiveSeckillMsg(String msg) {
        log.info("seckillQueue: " + msg);
        SeckillMsgVo seckillMsgVo = JsonUtil.jsonStr2Object(msg, SeckillMsgVo.class);
        User user = seckillMsgVo.getUser();
        Long goodsId = seckillMsgVo.getGoodsId();
        GoodsVO goodsVO = goodsService.findGoodsVOByGoodsId(goodsId);
        orderService.toSeckill(user, goodsVO);
    }
    */

    // 监听队列
    @RabbitListener(queues = "queue")
    public void receive(Object msg) {
        log.info("queue: " + msg);
    }

    @RabbitListener(queues = "queue_fanout01")
    public void receive01(Object msg) {
        log.info("queue_fanout01: " + msg);
    }

    @RabbitListener(queues = "queue_fanout02")
    public void receive02(Object msg) {
        log.info("queue_fanout02: " + msg);
    }

    @RabbitListener(queues = "queue_direct01")
    public void receive03(Object msg) {
        log.info("queue_direct01" + msg);
    }

    @RabbitListener(queues = "queue_direct02")
    public void receive04(Object msg) {
        log.info("queue_direct02" + msg);
    }

    @RabbitListener(queues = "queue_topic01")
    public void receive05(Object msg) {
        log.info("queue_topic01" + msg);
    }

    @RabbitListener(queues = "queue_topic02")
    public void receive06(Object msg) {
        log.info("queue_topic02" + msg);
    }
}
