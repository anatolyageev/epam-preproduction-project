package com.epam.anatolii.ageev.eshop.utils;

import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class SerializeUtils {
    public static final String FILE_FOR_SERIALIZATION = "task04\\src\\main\\resources\\ItemsDB.txt";

    public void serialize (ItemsService itemsService, String file){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            ArrayList<Item> itemArrayList = new ArrayList<>(itemsService.findAll());
            outputStream.writeObject(itemArrayList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deSerialize(ItemsService itemsService, String file){
        List<Item> itemList = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
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

    public void serializeSeveralTimes(ItemsService itemsService, int times, String file){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            for(int i = 0; i<times; i++) {
                outputStream.writeObject(new ArrayList<>(itemsService.findAll()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serializeSeveralTimesZip(ItemsService itemsService, int times, String file){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)))){
            for(int i = 0; i<times; i++) {
                outputStream.writeObject(new ArrayList<>(itemsService.findAll()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
