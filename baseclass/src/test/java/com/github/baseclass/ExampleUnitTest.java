package com.github.baseclass;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    List<String> list;
    @Test
    public void ad() throws Exception {

        List<String> list2=null;
//        list2.add("1");
//        list2.add("2");
        if(list==null){
            list=new ArrayList<>();
            list.add("1");
//            list.addAll( new ArrayList<String>());
        }
    }
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//        getStr();
//        getStr(null);
        getStr(null,null);
    }

    private void getStr(String... str) {
        if(str!=null){
            System.out.println("kong1-"+str.length);
        }else{
            System.out.println("kong2-"+str.length);
        }

    }
}