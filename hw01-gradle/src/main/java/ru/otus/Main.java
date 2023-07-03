package ru.otus;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * To start the application:
 * ./gradlew clean build
 * java -jar ./hw01-gradle/build/libs/shadowJar_hw01_gradle-SNAPSHOT-0.0.1.jar
 * <p>
 * To unzip the jar:
 * tar -xf shadowJar_hw01_gradle-SNAPSHOT-0.0.1.jar // for windows
 */
public class Main {
    public static void main(String[] args) {
        String hello = "hello";
        int intgr = (int) (Math.random() * 1001);

        HelloOtus<String> helloOtus1 = new HelloOtus<>(hello, intgr);
        Multiset<String> mlts = HashMultiset.create();
        mlts.add("string1");
        mlts.add("string2");
        mlts.add("string3");

        helloOtus1.setCounts(mlts);

        HelloOtus<String> helloOtus2 = new HelloOtus<>(hello, intgr);
        helloOtus2.setCounts(mlts);

        HelloOtus<String> helloOtus3 = new HelloOtus<>(hello, intgr);
        mlts.add("string4");
        helloOtus2.setCounts(mlts);


        System.out.println(helloOtus1.compareTo(helloOtus2));
        System.out.println(helloOtus1.compareTo(helloOtus3));
    }
}