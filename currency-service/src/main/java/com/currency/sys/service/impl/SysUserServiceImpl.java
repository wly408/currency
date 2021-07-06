package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.constrants.CommonConstrants;
import com.currency.sys.dto.SysUserDTO;
import com.currency.sys.entity.SysUser;
import com.currency.sys.mapper.SysUserMapper;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.BaseResult;
import com.currency.utils.ObjectUtil;
import com.currency.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public BaseResult addUser(SysUserDTO sysUserDTO) {
        SysUser user = ObjectUtil.copy(sysUserDTO,SysUser.class);
        this.save(user);
        return ResultUtil.suc();
    }

    @Override
    public SysUserDTO getSysUserByUserCodeAndUserType(String userCode,String userType) {
        Wrapper<SysUser> query = new QueryWrapper<>();
        SysUser queryUser = new SysUser();
        queryUser.setUserCode(userCode);
        queryUser.setUserType(userType);
        queryUser.setStatusCd(CommonConstrants.COMMON_YES);
        ((QueryWrapper<SysUser>) query).setEntity(queryUser);

        List<SysUser> list = this.baseMapper.selectList(query);
        if(!CollectionUtils.isEmpty(list)){
            return ObjectUtil.copy(list.get(0),SysUserDTO.class);
        }
        return null;
    }

}
