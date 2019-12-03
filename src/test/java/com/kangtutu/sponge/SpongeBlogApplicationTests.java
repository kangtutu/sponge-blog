package com.kangtutu.sponge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpongeBlogApplicationTests {
    public static void main(String[] args) {


      BigDecimal num = new BigDecimal(17).divide(new BigDecimal(10),0,BigDecimal.ROUND_UP);
        System.out.println(num);
    }

    //@Test
    public void contextLoads() {
    }

}
