package cn.javass.spring.chapter3.bean;

/**
* 说明：
*
* @author tom
* @version 创建时间： 2015/1/19  20:40
*/
import java.util.Collection;
public class CollectionTestBean {
   private Collection<String> values;
   public void setValues(Collection<String> values) {
       this.values = values;
   }
   public Collection<String> getValues() {
       return values;
    }
}
