package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.util.*;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    private Queue<JigsawNode> exploreList;    // 用以保存已发现但未访问的节点
    private Set<JigsawNode> visitedList;    // 用以保存已发现的节点

    private List<JigsawNode> solutionPath;// 解路径：用以保存从起始状态到达目标状态的移动路径中的每一个状态节点
    private int searchedNodesNum;

    /**
     * 拼图构造函数
     */
    public Solution() {

    }

    /**
     * 拼图构造函数
     *
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }


    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        exploreList = new LinkedList<>();
        visitedList = new HashSet<>();
        beginJNode = new JigsawNode(bNode);
        endJNode = new JigsawNode(eNode);
        currentJNode = null;

        final int DIR = 4;
        searchedNodesNum = 0;
        solutionPath = null;

        exploreList.add(beginJNode);

        while (!exploreList.isEmpty()) {
            searchedNodesNum++;

            currentJNode = exploreList.poll();

            if (currentJNode.equals(endJNode)) {
                getPath();
                break;
            }

            JigsawNode[] nextNodes = new JigsawNode[]{
                    new JigsawNode(currentJNode), new JigsawNode(currentJNode),
                    new JigsawNode(currentJNode), new JigsawNode(currentJNode)
            };

            for (int i = 0; i < DIR; i++) {
                if (nextNodes[i].move(i) && !visitedList.contains(nextNodes[i])) {
                    exploreList.add(nextNodes[i]);
                    visitedList.add(nextNodes[i]);
                }
            }
        }

        return this.isCompleted();
    }


    public void estimateValue(JigsawNode jNode) {
        int s = 0; // s for later not correct number counter
        int distance = 0; //distance for the correct position number and its correct distance
        int difference = 0;
        int wrongPos = 0;
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
            if (jNode.getNodesState()[index] != 0 &&
                    jNode.getNodesState()[index] != endJNode.getNodesState()[index]) {
                wrongPos++;
                int endIndex = 1;
                for (int i = 1; i < endJNode.getNodesState().length; i++) {
                    if (endJNode.getNodesState()[i] == jNode.getNodesState()[index]) {
                        endIndex = i;
                        break;
                    }
                }

                int x1 = (endIndex - 1) / dimension;
                int y1 = (endIndex - 1) % dimension;
                int x2 = (index - 1) / dimension;
                int y2 = (index - 1) % dimension;

                difference += Math.abs(x1 - x2) + Math.abs(y1 - y2);
                distance += (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
            }
        }
        jNode.setEstimatedValue(difference);
    }
}
