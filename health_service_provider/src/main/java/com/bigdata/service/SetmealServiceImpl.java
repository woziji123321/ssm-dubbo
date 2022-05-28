package com.bigdata.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.constant.RedisConstant;
import com.bigdata.dao.SetmealDao;
import com.bigdata.entity.PageResult;
import com.bigdata.pojo.Setmeal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SetMaleServiceImpl
 * @Description
 * @Author:我自己
 * @Date: 2022/5/26  9:46
 * @Version 1.0
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        this.setmealDao.add(setmeal);
        if(checkgroupIds != null && checkgroupIds.length > 0){
            setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
        }

        //将图片名称保存到Redis
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    @Override
    public void delete(Integer id) {
        long count = setmealDao.findCountBySetmealId(id);
        if (count>0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        this.setmealDao.deleteById(id);
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdsBySetmeal(Integer id) {
        return setmealDao.findCheckGroupIdsBySetmeal(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        //根据检查组id删除中间表数据（清理原有关联关系）
        setmealDao.deleteAssociation(setmeal.getId());
        //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
        setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
        //更新检查组基本信息
        setmealDao.edit(setmeal);
    }


    private void setSetmealAndCheckGroup(Integer setmealId, Integer[] checkgroupIds) {
        if(checkgroupIds != null&&checkgroupIds.length >0){
            for (Integer checkgroupId : checkgroupIds){
                Map<String, Integer> map = new HashMap<>();
                map.put("setmeal_id",setmealId);
                map.put("checkgroup_id",checkgroupId);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }
}
