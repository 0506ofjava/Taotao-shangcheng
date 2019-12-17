package com.taotao.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemCatService;
import com.taotao.utils.ExceptionUtil;
import com.taotao.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yq
 * @description
 * @create 2019-15-22-49
 **/
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;




    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //设置查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        //分类列表转换成EasyUITreeNode的列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode(tbItemCat.getId(), tbItemCat.getName(),
                    tbItemCat.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }

        return resultList;

    }



}
