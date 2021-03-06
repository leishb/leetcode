package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2019/7/1.
 */
public class _399_Evaluate_Division {

    /*
    Example:
        Given a / b = 2.0, b / c = 3.0.
        queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
        return [6.0, 0.5, -1.0, 1.0, -1.0 ].
     */
    /**
     * Accepted
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];
        int k = 0;
        Set<String> set = new HashSet<>();
        for(List<String> list : equations){
            set.addAll(list);
        }
        for (List<String> list : queries){
            if (list.get(0).equals(list.get(1)) && set.contains(list.get(0))){
                result[k++] = 1.0;
            }else if (!set.contains(list.get(0)) || !set.contains(list.get(1))){
                result[k++] = -1.0;
            } else {
                boolean[] visited = new boolean[equations.size()];
                result[k++] = helper(equations, values, list.get(0), list.get(1), visited);
            }
        }
        return result;
    }


    private double helper(List<List<String>> equations, double[] values, String first, String second, boolean[] visited){
        if (first.equals(second)){
            return 1.0;
        }
        for (int i=0;i<equations.size();i++){
            if (visited[i]){
                continue;
            }
            List<String> equation = equations.get(i);
            if (equation.get(0).equals(first)){
                visited[i] = true;
                double value =  helper(equations, values, equation.get(1), second, visited);
                visited[i] = false;
                if (value!=-1.0){
                    return value * values[i];
                }
            }
            if (equation.get(1).equals(first)){
                visited[i] = true;
                double value =  helper(equations, values, equation.get(0), second, visited);
                visited[i] = false;
                if (value!=-1.0){
                    return value / values[i];
                }
            }
        }
        return -1.0;
    }


    /**
     * Accepted
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];
        Map<String, Map<String, Double>> graph = buidGraph(equations, values);
        for (int i=0;i<queries.size();i++){
            result[i] = calEquation(queries.get(i).get(0), queries.get(i).get(1), graph, new HashSet<>());
        }
        return result;
    }


    private double calEquation(String first, String second, Map<String, Map<String, Double>> graph, Set<String> visited){
        if (!graph.containsKey(first)){
            return -1.0;
        }
        if (first.equals(second)){
            return 1.0;
        }
        if (graph.get(first).containsKey(second)){
            return graph.get(first).get(second);
        }
        visited.add(first);
        for (Map.Entry<String, Double> neighbour : graph.get(first).entrySet()){
            if (!visited.contains(neighbour.getKey())){
                double value = calEquation(neighbour.getKey(), second, graph, visited);
                if (value!=-1.0){
                    return value * neighbour.getValue();
                }
            }
        }
        return -1.0;
    }


    private Map<String, Map<String, Double>> buidGraph(List<List<String>> equations, double[] values){
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i=0;i<equations.size();i++){
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            if (!graph.containsKey(u)){
                graph.put(u, new HashMap<>());
            }
            graph.get(u).put(v, values[i]);
            if (!graph.containsKey(v)){
                graph.put(u, new HashMap<>());
            }
            graph.get(v).put(u, 1/values[i]);
        }
        return graph;
    }
}
