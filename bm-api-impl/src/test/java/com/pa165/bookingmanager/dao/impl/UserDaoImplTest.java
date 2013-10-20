package com.pa165.bookingmanager.dao.impl;

import com.pa165.bookingmanager.TestSetup;
import com.pa165.bookingmanager.dao.UserDao;
import com.pa165.bookingmanager.entity.UserEntity;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import java.util.List;

/**
 * @author Josef Stribny
 */
public class UserDaoImplTest extends TestSetup
{
    @Autowired
    UserDao userDao;

    @Test
    public void testFindAll(){
        List<UserEntity> UserEntities = userDao.findAll();
        Assert.assertEquals(2, UserEntities.size());
    }

    @Test
    public void testFind(){
        Long id = 1L;

        UserEntity UserEntity = userDao.find(id);
        Assert.assertEquals(id, UserEntity.getId());
        Assert.assertEquals("admin@bm.com", UserEntity.getEmail());

        // Check associated role entity
        Assert.assertEquals("ROLE_ADMIN", UserEntity.getRoleByRoleId().getName());
    }

    @Test
    public void testFindByCriteria(){
        List<UserEntity> UserEntitiesByProperty = userDao.findByCriteria(Restrictions.like("email", "%@bm.com%"));
        Assert.assertEquals(2, UserEntitiesByProperty.size());

        List<UserEntity> UserEntitiesByRestrictions = userDao.findByCriteria(Restrictions.lt("id", (long) 3));
        Assert.assertEquals(2, UserEntitiesByRestrictions.size());
    }

    @Test
    public void testCreate(){
        UserEntity user = new UserEntity();
        user.setEmail("bla@bla.cz");

        userDao.create(user);

        UserEntity UserEntity = userDao.find(user.getId());
        Assert.assertNotNull(UserEntity);
    }

    @Test
    public void testUpdate(){
        Long id = 2L;

        UserEntity userEntity = userDao.find(id);
        Assert.assertEquals("receptionist@bm.com", userEntity.getEmail());

        userEntity.setEmail("no-reply@bm.com");
        userEntity.setPassword("hfhalkdf56ds6adkdda6ajdks6a6s");

        userDao.update(userEntity);

        UserEntity userEntityUpdated = userDao.find(id);

        Assert.assertEquals("no-reply@bm.com", userEntityUpdated.getEmail());
        Assert.assertEquals("hfhalkdf56ds6adkdda6ajdks6a6s", userEntityUpdated.getPassword());
    }

    @Test
    public void testDelete(){
        Long id = 1L;

        UserEntity userEntity = userDao.find(id);

        userDao.delete(userEntity);

        UserEntity userEntityDeleted = userDao.find(id);
        Assert.assertEquals(userEntityDeleted, null);
    }
}