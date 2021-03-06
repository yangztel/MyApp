package com.chimu.myapp.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Longwj on 2018/3/30 0030.
 */
public class ShopHelperTest{
    private ShopHelper helper;
    private List<GoodsInfo> goods1;
    private List<GoodsInfo> goods2;

    @Before
    public void start(){
        goods1 = new ArrayList<>();
        goods1.add(new GoodsInfo("1",3));
        goods1.add(new GoodsInfo("2",2));
        goods1.add(new GoodsInfo("3",3));
        goods1.add(new GoodsInfo("4",1));

        goods2 = new ArrayList<>();
        goods2.add(new GoodsInfo("1",1));
        goods2.add(new GoodsInfo("2",2));
        goods2.add(new GoodsInfo("3",2));
        goods2.add(new GoodsInfo("4",1));
        goods2.add(new GoodsInfo("5",1));
    }
    @Test
    public void addGoods() throws Exception {
        List<GoodsInfo> list =  ShopHelper.addGoods(goods2,goods1);
        for(GoodsInfo info:list){
            System.out.println(info);
        }
    }

    @Test
    public void buyGoods() throws Exception {
        List<GoodsInfo> list = ShopHelper.buyGoods(goods1,goods2);
        for(GoodsInfo info:list){
            System.out.println(info);
        }
    }

}