package com.example.demo.prototype;

import java.io.*;
import java.util.Date;

public class TheGreatestSage extends Monkey implements Cloneable, Serializable {
    //金箍棒
    GoldRingedStaff goldRingedStaff;

    public TheGreatestSage() {
        this.goldRingedStaff = new GoldRingedStaff();
        this.weight = 30;
        this.height = 150;
        this.birthday = new Date();
        System.out.println("克隆是不走构造方法的");
    }

    public Object clone() {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //深度克隆
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);

            TheGreatestSage theGreatestSage = (TheGreatestSage) ois.readObject();
            theGreatestSage.setBirthday(new Date());
            return theGreatestSage;

            //浅克隆，只克隆基本类型和String
            //return super.clone();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                oos.close();
                bos.close();
                ois.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void change() {
        TheGreatestSage clone = (TheGreatestSage) clone();
        System.out.println("本尊的生日：" + this.getBirthday().getTime());
        System.out.println("克隆的生日：" + clone.getBirthday().getTime());
        System.out.println("本尊和克隆是否为同一对象:" + (this == clone));
        System.out.println("本尊金箍棒和克隆金箍棒是否为同一对象:" + (this.goldRingedStaff == clone.goldRingedStaff));
    }
}
