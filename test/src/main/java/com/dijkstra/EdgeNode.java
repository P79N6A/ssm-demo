package com.dijkstra;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 节点类，需要一个名字以及可达的边
 *
 * @author fangcong on 2018/3/13.
 */
@Setter
@Getter
public class EdgeNode {

    /**
     * 节点标识id
     */
    private String nodeId;

    /**
     * 可达边的集合
     */
    private List<Edge> edges;
}
