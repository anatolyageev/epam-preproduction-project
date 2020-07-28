package com.epam.anatolii.ageev.task02;

import com.epam.anatolii.ageev.entity.Item;
import com.epam.anatolii.ageev.entity.Server;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class HashFunctionTest {

    public static void main(String[] args) {
        HashMap<HashFunctionString, Item> hashFunctionStringItemHashMap = new HashMap<>();
        HashMap<HashFunctionLength, Item> hashFunctionLengthItemHashMap = new HashMap<>();
        LinkedHashMap<HashFunctionString, Item> hashFunctionStringItemLinkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<HashFunctionLength, Item> hashFunctionLengthItemLinkedHashMap = new LinkedHashMap<>();

        Item item1 = new Server(1L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true);
        Item item2 = new Server(2L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true);
        Item item3 = new Server(3L, 1003.00, "Intel core 9", 4.5, 48, "FX", 2, true);
        Item item4 = new Server(4L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true);

        HashFunctionString hashFunctionString1 = new HashFunctionString("item1test");
        HashFunctionString hashFunctionString2 = new HashFunctionString("item2");
        HashFunctionString hashFunctionString3 = new HashFunctionString("ite");
        HashFunctionString hashFunctionString4 = new HashFunctionString("item4");

        HashFunctionLength hashFunctionLength1 = new HashFunctionLength("aa");
        HashFunctionLength hashFunctionLength2 = new HashFunctionLength("bb");
        HashFunctionLength hashFunctionLength3 = new HashFunctionLength("cccTest");
        HashFunctionLength hashFunctionLength4 = new HashFunctionLength("d");

        hashFunctionStringItemHashMap.put(hashFunctionString1, item1);
        hashFunctionStringItemHashMap.put(hashFunctionString2, item2);
        hashFunctionStringItemHashMap.put(hashFunctionString3, item3);
        hashFunctionStringItemHashMap.put(hashFunctionString4, item4);

        System.out.println("hashFunctionStringItemHashMap: ");

        hashFunctionStringItemHashMap.forEach((k,v)-> {
            System.out.print(k.hashCode() + " ");
            System.out.println(v);
        });

        System.out.println("----------------");

        hashFunctionLengthItemHashMap.put(hashFunctionLength1, item1);
        hashFunctionLengthItemHashMap.put(hashFunctionLength2, item2);
        hashFunctionLengthItemHashMap.put(hashFunctionLength3, item3);
        hashFunctionLengthItemHashMap.put(hashFunctionLength4, item4);

        System.out.println("hashFunctionLengthItemHashMap: ");

        hashFunctionLengthItemHashMap.forEach((k,v)-> {
            System.out.print(k.hashCode() + " ");
            System.out.println(v);
        });

        System.out.println("----------------");

        hashFunctionStringItemLinkedHashMap.put(hashFunctionString1, item1);
        hashFunctionStringItemLinkedHashMap.put(hashFunctionString2, item2);
        hashFunctionStringItemLinkedHashMap.put(hashFunctionString3, item3);
        hashFunctionStringItemLinkedHashMap.put(hashFunctionString4, item4);

        System.out.println("hashFunctionStringItemLinkedHashMap: ");

        hashFunctionStringItemLinkedHashMap.forEach((k,v)-> {
            System.out.print(k.hashCode() + " ");
            System.out.println(v);
        });

        System.out.println("----------------");


        hashFunctionLengthItemLinkedHashMap.put(hashFunctionLength1, item1);
        hashFunctionLengthItemLinkedHashMap.put(hashFunctionLength2, item2);
        hashFunctionLengthItemLinkedHashMap.put(hashFunctionLength3, item3);
        hashFunctionLengthItemLinkedHashMap.put(hashFunctionLength4, item4);

        System.out.println("hashFunctionLengthItemLinkedHashMap: ");

        hashFunctionLengthItemLinkedHashMap.forEach((k,v)-> {
            System.out.print(k.hashCode() + " ");
            System.out.println(v);
        });

        System.out.println("----------------");

    }
}
