package com.dijkstra;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象出边
 *
 * @author fangcong on 2018/3/13.
 */
@Getter@Setter
public class Edge {

    /**
     * 起点
     */
    private String startNodeId;
    /**
     * 终点
     */
    private String endNodeId;
    /**
     * 权重
     */
    private Integer weight;
}
