package com.epam.anatolii.ageev.eshop.utils;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.Mode;
import com.epam.anatolii.ageev.eshop.services.ItemsService;
import com.epam.anatolii.ageev.eshop.view.Menu;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

public class SerializeUtilsTest {
    private final int SERIALIZE_TIMES = 10;

    private ComputerShop computerShop;
    private SerializeUtils serializeUtils;
    private final String FILE_FOR_SEVERAL_SERIALIZATION = "D:\\Clouds\\OneDrive\\000_EPAM\\IntelIDE\\Laboratory_Project\\epam-preproduction-project\\task04\\src\\test\\resources\\SeveralTimesItems.txt";
    private final String FILE_FOR_SEVERAL_SERIALIZATION_ZIP = "D:\\Clouds\\OneDrive\\000_EPAM\\IntelIDE\\Laboratory_Project\\epam-preproduction-project\\task04\\src\\test\\resources\\SeveralTimesItems.gz";
    private final String FILE_FOR_SERIALIZATION = "D:\\Clouds\\OneDrive\\000_EPAM\\IntelIDE\\Laboratory_Project\\epam-preproduction-project\\task04\\src\\test\\resources\\ItemsDB.txt";
    private File fileSerialize;
    private File fileSeveralSerialize;
    private File fileSeveralSerializeZip;

    @Before
    public void before() {
        Menu menu = new Menu();
        computerShop = new ComputerShop(Mode.AUTOMATIC);
        serializeUtils = new SerializeUtils();
        FillInitData.initShopFromDB(computerShop,FILE_FOR_SERIALIZATION);
        fileSerialize = new File(FILE_FOR_SERIALIZATION);
        fileSeveralSerialize = new File(FILE_FOR_SEVERAL_SERIALIZATION);
        fileSeveralSerializeZip = new File(FILE_FOR_SEVERAL_SERIALIZATION_ZIP);
    }

    @Test
    public void serializeSeveralTimesTest() {
        serializeSeveralTimes(computerShop.getItemsService(), SERIALIZE_TIMES, FILE_FOR_SEVERAL_SERIALIZATION);

        System.out.println("Length one time serialize: " + fileSerialize.length());
        System.out.println("Length several times: " + fileSeveralSerialize.length());
    }

    @Test
    public void serializeSeveralTimesZipTest() {
        serializeSeveralTimesZip(computerShop.getItemsService(), SERIALIZE_TIMES,FILE_FOR_SEVERAL_SERIALIZATION_ZIP);

        System.out.println("Length one time serialize: " + fileSerialize.length());
        System.out.println("Length several times: " + fileSeveralSerialize.length());
        System.out.println("Length several times: " + fileSeveralSerializeZip.length());
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