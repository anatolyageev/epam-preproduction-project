package com.epam.anatolii.ageev.eshop;

import com.epam.anatolii.ageev.eshop.utils.FillInitData;
import com.epam.anatolii.ageev.eshop.utils.SerializeUtils;
import com.epam.anatolii.ageev.eshop.view.Menu;

public class ShopApplication {

    public static void main(String[] args) {
        Menu menu = new Menu();

        ComputerShop computerShop = new ComputerShop(menu.initMode());

        FillInitData.initShopFromDB(computerShop, new SerializeUtils().FILE_FOR_SERIALIZATION);
        menu.menuRunner(computerShop);
    }
}
