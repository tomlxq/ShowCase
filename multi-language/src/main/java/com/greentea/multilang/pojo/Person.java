package com.greentea.multilang.pojo;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/5  16:19
 */



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class Person {

    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return "ID: "+id+" - Name: "+name;
    }

}


