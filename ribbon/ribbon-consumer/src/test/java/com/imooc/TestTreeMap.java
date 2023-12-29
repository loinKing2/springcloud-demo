package com.imooc;

import org.junit.Test;

import java.util.TreeMap;

public class TestTreeMap {

    @Test
    public void testTreeMap(){
        TreeMap treeMap = new  TreeMap<String,Object>();
        treeMap.put("aab","x1312321");
        treeMap.put("cb","321312542");
        treeMap.put("add","hello");
        treeMap.put("baa","hello");
        treeMap.put("aca","hello");
        System.out.println(treeMap);
        System.out.println(treeMap.tailMap("ada",false));;

    }


}
