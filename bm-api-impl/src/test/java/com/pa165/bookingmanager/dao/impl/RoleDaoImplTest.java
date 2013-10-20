package com.pa165.bookingmanager.dao.impl;

import com.pa165.bookingmanager.TestSetup;
import com.pa165.bookingmanager.dao.RoleDao;
import com.pa165.bookingmanager.entity.RoleEntity;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import java.util.List;

/**
 * @author Josef Stribny
 */
public class RoleDaoImplTest extends TestSetup
{
    @Autowired
    RoleDao roleDao;

    @Test
    public void testFindAll(){
        List<RoleEntity> roleEntities = roleDao.findAll();
        Assert.assertEquals(3, roleEntities.size());
    }

    @Test
    public void testFind(){
        Long id = 1L;
        RoleEntity roleEntity = roleDao.find(id);

        Assert.assertEquals(id, roleEntity.getId());
        Assert.assertEquals("ROLE_ADMIN", roleEntity.getName());
        Assert.assertEquals(1, roleEntity.getUsersById().size());
    }

    @Test
    public void testFindByCriteria(){
        List<RoleEntity> roleEntitiesByProperty = roleDao.findByCriteria(Property.forName("name").like("%I%"));
        Assert.assertEquals(2, roleEntitiesByProperty.size());

        List<RoleEntity> roleEntitiesByRestrictions = roleDao.findByCriteria(Restrictions.eq("name", "ROLE_RECEPTIONIST"));
        Assert.assertEquals(1, roleEntitiesByRestrictions.size());
    }

    @Test
    public void testCreate(){
        RoleEntity role = new RoleEntity();
        role.setName("EDITOR");

        roleDao.create(role);

        RoleEntity roleEntity = roleDao.find(role.getId());
        Assert.assertNotNull(roleEntity);
    }

    @Test
    public void testUpdate(){
        Long id = 1L;

        RoleEntity roleEntity = roleDao.find(id);
        Assert.assertEquals("ROLE_ADMIN", roleEntity.getName());

        roleEntity.setName("ROLE_EDITOR");

        roleDao.update(roleEntity);

        RoleEntity roleEntityUpdated = roleDao.find(id);
        Assert.assertEquals("ROLE_EDITOR", roleEntityUpdated.getName());
    }

    @Test
    public void testDelete(){
        Long id = 1L;

        RoleEntity roleEntity = roleDao.find(id);

        roleDao.delete(roleEntity);

        RoleEntity roleEntityDeleted = roleDao.find(id);
        Assert.assertEquals(null, roleEntityDeleted);
    }
}