package com.leishb.leetcode.design;

import java.util.*;

/**
 * Accepted
 * Created by me on 2019/11/7.
 */
public class _588_Design_In_Memory_File_System {

    Dir root ;

    public _588_Design_In_Memory_File_System() {
        root= new Dir("/");
    }

    public List<String> ls(String path) {
        if ("/".equals(path)) {
            List<String> ans = new ArrayList<>(root.children.keySet());
            Collections.sort(ans);
            return ans;
        }
        String[] paths = path.split("\\/");
        Dir cur = root;
        for (String s : paths){
            if (s.equals(""))continue;
            if (!cur.children.containsKey(s)){
                cur.children.put(s, new Dir(s));
            }
            cur = cur.children.get(s);
        }
        if (cur.isFile)return Arrays.asList(cur.name);
        List<String> ans = new ArrayList<>(cur.children.keySet());
        Collections.sort(ans);
        return ans;
    }

    public void mkdir(String path) {
        String[] paths = path.split("\\/");
        Dir cur = root;
        for (String s : paths){
            if (s.equals(""))continue;
            if (!cur.children.containsKey(s)){
                cur.children.put(s, new Dir(s));
            }
            cur = cur.children.get(s);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] paths = filePath.split("\\/");
        Dir cur = root;
        for (String s : paths){
            if (s.equals(""))continue;
            if (!cur.children.containsKey(s)){
                cur.children.put(s, new Dir(s));
            }
            cur = cur.children.get(s);
        }
        cur.isFile = true;
        cur.content += content;
    }

    public String readContentFromFile(String filePath) {
        String[] paths = filePath.split("\\/");
        Dir cur = root;
        for (String s : paths){
            if (s.equals(""))continue;
            cur = cur.children.get(s);
        }
        return cur.content;
    }


    class Dir {
        String name;
        Map<String, Dir> children = new HashMap<>();
        String content = "";
        boolean isFile;

        Dir(String name){
            this.name = name;
        }
    }
}
