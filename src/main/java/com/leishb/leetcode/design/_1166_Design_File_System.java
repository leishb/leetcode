package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/12/2.
 */
public class _1166_Design_File_System {


    File root ;

    public _1166_Design_File_System() {
        root = new File("R", 0);
    }

    public boolean createPath(String path, int value) {
        String[] fs = path.split("/");
        File cur = root;
        for (int i=0;i<fs.length-1;i++){
            if (fs[i].length()==0) continue;
            if (!cur.children.containsKey(fs[i])) return false;
            cur = cur.children.get(fs[i]);
        }
        if (cur.children.containsKey(fs[fs.length-1])) return false;
        cur.children.put(fs[fs.length-1], new File(fs[fs.length-1], value));
        return true;
    }

    public int get(String path) {
        String[] fs = path.split("/");
        File cur = root;
        for (String f : fs){
            if (f.length()==0) continue;
            if (!cur.children.containsKey(f)){
                return -1;
            }
            cur = cur.children.get(f);
        }
        return cur.data;
    }




    class File {
        String path;
        int data;
        Map<String, File> children = new HashMap<>();
        public File(String path, int data){
            this.path = path;
            this.data = data;
        }
    }
}
