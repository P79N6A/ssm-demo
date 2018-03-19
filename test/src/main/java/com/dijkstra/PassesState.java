package com.dijkstra;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 节点访问状态类
 *
 * @author fangcong on 2018/3/13.
 */
@Setter@Getter
public class PassesState {

    /**
     * 节点
     */
    private EdgeNode edgeNode;

    /**
     * 是否被访问过
     */
    private boolean visited;

    /**
     * 累计的权值
     */
    private Integer totalWeight;

    /**
     * 路径
     */
    private List<String> passedIds;

    PassesState(){
        this.edgeNode = null;
        this.totalWeight = Integer.MAX_VALUE;
        this.passedIds = new ArrayList<>();
        this.visited = false;
    }

    PassesState(EdgeNode edgeNode){
        this.edgeNode = edgeNode;
        this.totalWeight = Integer.MAX_VALUE;
        this.passedIds = new ArrayList<>();
        this.visited = false;
    }

}
