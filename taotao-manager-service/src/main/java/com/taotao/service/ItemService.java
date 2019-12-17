package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface ItemService {
    TbItem getItemById(long itemId);

    EasyUIDataGridResult getItemList(int page,int rows);

    public TaotaoResult addItem(TbItem item, TbItemDesc itemDesc);
}
