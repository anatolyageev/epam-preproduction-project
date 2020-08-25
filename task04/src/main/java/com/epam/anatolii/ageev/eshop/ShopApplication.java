package com.epam.anatolii.ageev.eshop;

import com.epam.anatolii.ageev.eshop.constants.FileNameConstants;
import com.epam.anatolii.ageev.eshop.utils.FillInitData;
import com.epam.anatolii.ageev.eshop.view.Menu;

import java.io.File;

public class ShopApplication {

    public static void main(String[] args) {
        Menu menu = new Menu();
        ComputerShop computerShop = new ComputerShop(menu.initMode());
        computerShop.serverStart();
        if(new File(FileNameConstants.FILE_FOR_SERIALIZATION).exists()) {
            FillInitData.initShopFromDB(computerShop, FileNameConstants.FILE_FOR_SERIALIZATION);
        }else{
            FillInitData.initShopWithItems(computerShop);
        }
        menu.menuRunner(computerShop);
    }
}
