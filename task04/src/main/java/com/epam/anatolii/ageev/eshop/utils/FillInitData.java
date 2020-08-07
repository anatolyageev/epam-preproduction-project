package com.epam.anatolii.ageev.eshop.utils;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.domain.Desktop;
import com.epam.anatolii.ageev.eshop.domain.Laptop;
import com.epam.anatolii.ageev.eshop.domain.Server;
import java.math.BigDecimal;

public class FillInitData {
    public static void initShoprWhithItems(ComputerShop computerShop){
        computerShop.getItemsService().insert(new Server(1L, new BigDecimal(3000.00), "Intel core 9", 4.5, 48, "FX", 2, true));
        computerShop.getItemsService().insert(new Server(4L, new BigDecimal(4000.00), "Intel core 9", 4.5, 48, "FX", 2, true));
        computerShop.getItemsService().insert(new Server(6L, new BigDecimal(5000.00), "Intel core 9", 4.5, 48, "FX", 2, true));
        computerShop.getItemsService().insert(new Server(7L, new BigDecimal(2000.00), "Intel core 9", 4.5, 48, "FX", 2, true));
        computerShop.getItemsService().insert(new Laptop(44L, new BigDecimal(750),"Intel core i5",2.6,8,13.2));
        computerShop.getItemsService().insert(new Desktop(7L, new BigDecimal(1500.00), "Intel core 9", 4.5, 48, "DX"));
    }

    public static void initShopFromDB(ComputerShop computerShop, String file){
        SerializeUtils serializeUtils = new SerializeUtils();
        serializeUtils.deSerialize(computerShop.getItemsService(), file);
    }
}
