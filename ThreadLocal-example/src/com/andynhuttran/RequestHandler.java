package com.andynhuttran;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class Service1 {
    public Service1(User user){
        UserContextHolder.holder.set(user);
    }

    public void process(){
        User user = UserContextHolder.holder.get();
        user.name += ";Service1;";
    }
}

class Service2 {
    public void process(){
        User user = UserContextHolder.holder.get();
        user.name += ";Service2;";
    }
}

class Service3 {
    public void process(){
        User user = UserContextHolder.holder.get();
        user.name += ";Service3";
        System.out.println(user.toString());

        if (user.age == 1) { //remove in require 1
            UserContextHolder.holder.remove();
        }

        User user1 = UserContextHolder.holder.get();
        if (user1 == null){
            System.out.println("User is removed");
        }
        else {
            System.out.println(user1);
        }
    }
}


class ProcessUser {

    public void run(User user) {
        Service1 s1 = new Service1(user);
        s1.process(); //front

        Service2 s2 = new Service2();
        s2.process(); //middleware

        Service3 s3 = new Service3();
        s3.process(); //terminate
    }
}

public class RequestHandler {

    public static void main(String[] args) {

        ProcessUser processUser = new ProcessUser();

        Thread request1 = new Thread(() -> {
            User user = new User("Request 1", 1);
            processUser.run(user);
        });
        request1.start();


        Thread request2 = new Thread(() -> {
            User user = new User("Request 2", 2);
            processUser.run(user);
        });
        request2.start();

    }

}
