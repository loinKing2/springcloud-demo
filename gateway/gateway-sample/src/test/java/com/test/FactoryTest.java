package com.test;

public class FactoryTest {


    public static void main(String[] args) {
        Demo demo = new Demo("test13");
        demo.handle();
    }


    static class Demo {
        private Integer index;
        private String name;


        public Demo(String name) {
            this.index = 0;
            this.name = name;
        }

        public Demo(Demo parent, Integer index){
            this.index = index;
            this.name = parent.name;
        }

        public Integer getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        private void handle(){
            if (this.index >= 3){
                return;
            }else{
                System.out.println("被执行一次了");
                System.out.println(index);
                Demo demo = new Demo(this, this.index + 1);
                demo.testAbc();
            }

        }

        private void testAbc(){
            //执行功能逻辑
            this.handle();
        }

    }


}
