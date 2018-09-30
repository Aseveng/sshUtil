package DBUtil.com.util.hibernate;

import DBUtil.com.util.pojo.Hero;

/**
 * Created by xp110 on 2018/9/28.
 */
public class ParseHibernateAnnotation {
    public static void main(String[] args) {
        Class<Hero> clazz = Hero.class;
        MyEntity myEntity = (MyEntity) clazz.getAnnotation(MyTable.class);
        if (myEntity == null) {
            System.out.println("Hero类不是实体类");
        } else {
            System.out.println("Hero类是实体类");
        }
    }

}
