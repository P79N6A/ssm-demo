package com.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 计算最短距离
 *
 * @author fangcong on 2018/3/13.
 */
public class CalcMinDistance {

    private static Set<String> PASSIDS = new HashSet<>();

    private static Integer route = 0;

    private static final Map<String, EdgeNode> NODES = new HashMap<>(16);

    static {
        {
            EdgeNode edgeNodeA = new EdgeNode();
            edgeNodeA.setNodeId("A");

            List<Edge> listA = new ArrayList<>();
            listA.add(initNode("A", "B", 5));
            listA.add(initNode("A", "D", 5));
            listA.add(initNode("A", "E", 5));
            edgeNodeA.setEdges(listA);
            NODES.put("A", edgeNodeA);
        }

        {
            EdgeNode edgeNodeB = new EdgeNode();
            edgeNodeB.setNodeId("B");

            List<Edge> listA = new ArrayList<>();
            listA.add(initNode("B", "C", 4));
            edgeNodeB.setEdges(listA);
            NODES.put("B", edgeNodeB);
        }

        {
            EdgeNode edgeNodeC = new EdgeNode();
            edgeNodeC.setNodeId("C");

            List<Edge> listC = new ArrayList<>();
            listC.add(initNode("C", "D", 8));
            listC.add(initNode("C", "E", 2));
            edgeNodeC.setEdges(listC);
            NODES.put("C", edgeNodeC);
        }

        {
            EdgeNode edgeNodeD = new EdgeNode();
            edgeNodeD.setNodeId("D");

            List<Edge> listD = new ArrayList<>();
            listD.add(initNode("D", "C", 8));
            listD.add(initNode("D", "E", 6));
            edgeNodeD.setEdges(listD);
            NODES.put("D", edgeNodeD);
        }

        {
            EdgeNode edgeNodeE = new EdgeNode();
            edgeNodeE.setNodeId("E");

            List<Edge> listE = new ArrayList<>();
            listE.add(initNode("E", "B", 3));
            edgeNodeE.setEdges(listE);
            NODES.put("E", edgeNodeE);
        }
    }

    /**
     * 有向图初始化
     *
     * @param startNodeId
     * @param endNodeId
     * @param weight
     * @return
     */
    public static Edge initNode(String startNodeId, String endNodeId, Integer weight) {
        Edge edge = new Edge();
        edge.setStartNodeId(startNodeId);
        edge.setEndNodeId(endNodeId);
        edge.setWeight(weight);

        return edge;
    }

    /**
     * 初始化路径
     *
     * @param nodeId
     */
    public static List<PassesState> initPath(String nodeId) {
        List<PassesState> passedPaths = new ArrayList<>();
        /*PassesState path;
        for (EdgeNode node : NODES) {
            //已nodeId为起点的路径
            if (node.getNodeId().equals(nodeId)) {
                path = new PassesState(node);
                path.setTotalWeight(0);
                List<String> tmp = new ArrayList<>();
                tmp.add(nodeId);
                path.setPassedIds(tmp);
            } else {
                path = new PassesState(node);
            }
            passedPaths.add(path);
        }*/
        return passedPaths;
    }

    /**
     * 运用迪杰斯特拉算法得出V到所有节点的最短路径
     *
     * @param nodes
     * @param paths
     */
    public static void run(List<EdgeNode> nodes, List<PassesState> paths) {
        PassesState min = new PassesState();
        int flag = 0;
        for (PassesState state : paths) {
            if (!state.isVisited()) {
                if (state.getTotalWeight() < min.getTotalWeight()) {
                    min = state;
                }
                flag++;
            }
        }
        if (flag == 0) { return; }
        //用min去更新可达节点的path
        for (Edge edge : min.getEdgeNode().getEdges()) {
            //根据目标节点名找到目标节点的passedPath:to
            EdgeNode tmp = null;
            for (EdgeNode d : nodes) {
                if (d.getNodeId().equals(edge.getEndNodeId())) {
                    tmp = d;
                }
            }
            PassesState to = null;
            for (PassesState e : paths) {
                if (e.getEdgeNode().equals(tmp)) {
                    to = e;
                }
            }
            //判断是否需要更新
            if (null != to && to.getTotalWeight() > edge.getWeight() + min.getTotalWeight()) {
                List<String> tmpList = new ArrayList<>(min.getPassedIds());
                tmpList.add(to.getEdgeNode().getNodeId());
                //更新路径列表
                to.setPassedIds(tmpList);
                //更新累积权值
                to.setTotalWeight(edge.getWeight() + min.getTotalWeight());
            }
        }
        min.setVisited(true);
        run(nodes, paths);
    }

    /**
     * 求两个节点之间的权重
     *
     * @param startNodeId
     * @param endNodeId
     * @return
     */
    public static int getWeight(String startNodeId, String endNodeId) {
        for (Edge edge : NODES.get(startNodeId).getEdges()) {
            if (edge.getEndNodeId().equals(endNodeId)) {
                return edge.getWeight();
            }
        }
        return 0;
    }

    /**
     * 求指定路径的权重
     *
     * @param nodeIds
     * @return
     */
    public static int getWeight(String... nodeIds) {
        if (null == nodeIds || 0 == nodeIds.length) {
            return 0;
        }
        int totalWeight = 0;
        for (int i = 0; i < nodeIds.length - 1; i++) {
            int tmpWeight = getWeight(nodeIds[i], nodeIds[i + 1]);
            if (tmpWeight == 0) {
                totalWeight = 0;
                break;
            }
            totalWeight += tmpWeight;
        }
        return totalWeight;
    }


    public static void main(String[] args) {
        //A-B-C
        int abcDistance = getWeight("A", "B", "C");
        System.out.println("#1:" + (abcDistance == 0 ? "no such source" : abcDistance));
        //A-D
        int adDistance = getWeight("A", "D");
        System.out.println("#2:" + (adDistance == 0 ? "no such source" : adDistance));
        //A-D-C
        int adcDistance = getWeight("A", "D", "C");
        System.out.println("#3:" + (adcDistance == 0 ? "no such source" : adcDistance));
        //A-E-B-C-D
        int aebcd = getWeight("A", "E", "B", "C", "D");
        System.out.println("#4:" + (aebcd == 0 ? "no such source" : aebcd));
        //A-E-D
        int aed = getWeight("A", "E", "D");
        System.out.println("#5:" + (aed == 0 ? "no such source" : aed));

        System.out.println("#6:" + route);
        //initPath("A");
        /*run(nodes);
        node tmp=null;
        for(node d:nodes){
            if(d.getId().equals(end)){
                tmp = d;
            }
        }
        passedPath to=null;
        for(passedPath e:paths){
            if(e.getCurNode().equals(tmp)){
                to = e;
            }
        }
        for(String f:to.getPassedIDList()){
            System.out.print(f+" ");
        }
        System.out.print(":"+to.getWeight());*/
    }
}
