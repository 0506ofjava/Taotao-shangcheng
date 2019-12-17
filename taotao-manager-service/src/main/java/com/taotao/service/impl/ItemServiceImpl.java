package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import com.taotao.utils.ExceptionUtil;
import com.taotao.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author yq
 * @description
 * @create 2019-15-19-12
 */
@Service
public class ItemServiceImpl implements ItemService {

  @Autowired private TbItemMapper itemMapper;

  @Autowired
  private TbItemDescMapper itemDescMapper;


  @Override
  public TbItem getItemById(long itemId) {
    // 添加查询条件
    TbItemExample example = new TbItemExample();
    TbItemExample.Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(itemId);

    List<TbItem> list = itemMapper.selectByExample(example);
    if (list != null && list.size() > 0) {
      TbItem item = list.get(0);
      return item;
    }
    return null;
  }

  @Override
  public EasyUIDataGridResult getItemList(int page, int rows) {
    //分页处理,获得记录
    PageHelper.startPage(page, rows);
    TbItemExample example = new TbItemExample();
    List<TbItem> list = itemMapper.selectByExample(example);

    //获得记录的总条数
    PageInfo<TbItem> pageInfo = new PageInfo<>(list);
    long total = pageInfo.getTotal();

    EasyUIDataGridResult result = new EasyUIDataGridResult();
    result.setRows(list);
    result.setTotal(total);
    return result;
  }

  public TaotaoResult addItem(TbItem item, TbItemDesc itemDesc) {
    try {
      //生成商品id，使用时间+随机数策略生成
      Long itemId = IDUtils.genItemId();

      //补全商品信息
      item.setId(itemId);
      item.setStatus((byte) 1);
      Date date = new Date();
      item.setCreated(date);
      item.setUpdated(date);
      //把数据插入到商品表
      itemMapper.insert(item);

      //补全商品描述信息
      itemDesc.setItemId(itemId);
      itemDesc.setCreated(date);
      itemDesc.setUpdated(date);
      //把数据插入到商品描述表
      itemDescMapper.insert(itemDesc);
    } catch (Exception e) {
      e.printStackTrace();
      return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
    }

    return TaotaoResult.ok();

  }
}
