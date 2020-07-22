package test;


import com.sun.javafx.util.Utils;

import java.util.*;

/**
 * @author:jzz
 * @date:2020/7/16
 */
public class TestList {

    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
        //多态的体现
//        List<String> list1 = Collections.synchronizedList(list);
        HashMap<String, String> map = new HashMap<>();
        
//        Map<String, String> map1 = Collections.synchronizedMap(map);

//        LinkedList<Object> links = new LinkedList<>();

        map.put("1","1");
        String put = map.put("1", "2");
        System.out.println(put);


    }
}
