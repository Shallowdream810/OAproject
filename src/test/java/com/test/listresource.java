package com.test;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;

public class listresource {

    @Test
    public void  test01(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);
    }
}
