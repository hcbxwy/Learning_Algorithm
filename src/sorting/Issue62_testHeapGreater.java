package sorting;

import java.util.Comparator;

/**
 * 手写一个加强堆
 */
public class Issue62_testHeapGreater {

    public static void main(String[] args) {
        Student s1 = new Student("张三", 18);
        Student s2 = new Student("李四", 16);
        Student s3 = new Student("王五", 20);
        Student s4 = new Student("赵六", 19);
        Student s5 = new Student("周七", 15);
        HeapGreater<Student> heap = new HeapGreater<>(Comparator.comparingInt(o -> o.age));
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        while (!heap.isEmpty()) {
            Student student = heap.poll();
            System.out.println(student.name + ", age=" + student.age);
        }
    }

    static class Student {
        private final String name;
        private final int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
