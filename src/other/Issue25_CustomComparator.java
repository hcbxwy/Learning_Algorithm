package other;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 自定义比较器
 */
public class Issue25_CustomComparator {

    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        list.add(new People(1, "张三", 18));
        list.add(new People(2, "李四", 16));
        list.add(new People(3, "王五", 20));
        list.add(new People(4, "赵六", 18));
        System.out.println("根据年龄自定义排序器进行排序");
        List<People> list1 = new ArrayList<>(list);
        list1.sort(new AgeComparator());
        printPeople(list1);
        System.out.println("使用lambada对年龄降序排序");
        List<People> list2 = new ArrayList<>(list);
        list2.sort((o1, o2) -> {
            // 年龄相等时，id倒序
            if (o1.age == o2.age) {
                return o2.id - o1.id;
            }
            return o2.age - o1.age;
        });
        printPeople(list2);
    }

    private static void printPeople(List<People> list) {
        for (People p : list) {
            System.out.println(p.id + ", " + p.name + ", " + p.age);
        }
    }

    private static class People {
        private final int id;
        private final String name;
        private final int age;

        private People(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

    private static class AgeComparator implements Comparator<People> {

        @Override
        public int compare(People o1, People o2) {
            return o1.age - o2.age;
        }
    }
}
