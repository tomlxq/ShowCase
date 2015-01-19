package cn.javass.spring.chapter3.bean;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/19  20:49
 */
public class ArrayTestBean {
    private String[] array;
    private String[] [] array2;

    public void setArray(String[] array) {
        this.array = array;
    }

    public void setArray2(String[][] array2) {
        this.array2 = array2;
    }
}
