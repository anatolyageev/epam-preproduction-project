package com.epam.anatolii.ageev.eshop.servers.server_impl.tcp;

import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.domain.Server;
import com.epam.anatolii.ageev.eshop.servers.hendler.ShopTcpHandler;
import com.epam.anatolii.ageev.eshop.services.ItemsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.GET_COUNT_COMMAND;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TcpServerTest {
    private final long ITEM_ID = 1L;
    private final int FIRST_INDEX = 0;
    private final String REQUEST_FOR_GET_ITEM = "get item=1";

    @Mock
    private ItemsService itemsService;
    @Mock
    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream = spy(new ByteArrayOutputStream());
    private List<Item> itemList;

    @Before
    public void before() {
        itemList = dummyDb();
        when(itemsService.getOne(ITEM_ID)).thenReturn(itemList.get(FIRST_INDEX));
        when(itemsService.findAll()).thenReturn(itemList);
    }

    @Test
    public void getItemInfoTest_ShouldReturnCorrectPrice() throws IOException {
        inputStream = spy(new ByteArrayInputStream(REQUEST_FOR_GET_ITEM.getBytes()));

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);

        new Thread(new ShopTcpHandler(socket, itemsService)).run();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        String resp = String.valueOf(socket.getOutputStream()).trim();

        assertTrue(resp.endsWith(itemList.get(FIRST_INDEX).getPrice().toString()));
    }

    @Test
    public void getItemNumberTest_ShouldReturnCorrectNumber() throws IOException {
        inputStream = spy(new ByteArrayInputStream(GET_COUNT_COMMAND.getBytes()));
        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);

        new Thread(new ShopTcpHandler(socket, itemsService)).run();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        String resp = String.valueOf(socket.getOutputStream()).trim();

        assertTrue(resp.endsWith(String.valueOf(itemList.size())));
    }

    private List<Item> dummyDb() {
        List itemList = new ArrayList();
        itemList.add(new Server(1L, new BigDecimal("1000.00"), "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(2L, new BigDecimal("1000.00"), "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(3L, new BigDecimal("1003.00"), "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(4L, new BigDecimal("1000.00"), "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(5L, new BigDecimal("1003.00"), "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(6L, new BigDecimal("1000.00"), "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(7L, new BigDecimal("1000.00"), "Intel core 9", 4.5, 48, "FX", 2, true));
        return itemList;
    }
}