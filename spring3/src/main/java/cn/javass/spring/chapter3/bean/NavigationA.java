package cn.javass.spring.chapter3.bean;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/23  21:34
 */

 public class NavigationA {
 private NavigationB navigationB;
 public void setNavigationB(NavigationB navigationB) {
 this.navigationB = navigationB;
 }
 public NavigationB getNavigationB() {
 return navigationB;
 }
}
