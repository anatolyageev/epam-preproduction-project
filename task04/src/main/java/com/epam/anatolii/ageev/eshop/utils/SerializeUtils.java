package com.epam.anatolii.ageev.eshop.utils;

import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeUtils {
    private static final String FILE_FOR_SERIALIZATION = "task04\\src\\main\\resources\\ItemsDB.txt";

    public void serialize (ItemsService itemsService){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_FOR_SERIALIZATION))){
            ArrayList<Item> itemArrayList = new ArrayList<>(itemsService.findAll());
            outputStream.writeObject(itemArrayList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deSerialize(ItemsService itemsService){
        List<Item> itemList = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_FOR_SERIALIZATION))) {
            itemList = (List<Item>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(itemList.size()>0){
            itemList.forEach(itemsService::insert);
        }
    }
}
