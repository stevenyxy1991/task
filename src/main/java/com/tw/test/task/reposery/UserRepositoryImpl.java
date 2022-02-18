package com.tw.test.task.reposery;

import com.tw.test.task.dao.IUserDao;
import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.model.Context;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private TaskRepositoryImpl taskRepositoryImpl;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private Context context;

    @Override
    public OperationType operationDef(){
        return OperationType.JPA;
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
        taskRepositoryImpl.deleteByUserId(id);
    }

    @Override
    public void save(UserModel userModel) {
        userDao.save(userModel.getUserEntity());
        if (null != userModel.getTaskEntitys()) {
            userModel.getTaskEntitys().stream().forEach(t -> {
                StringBuilder sb = new StringBuilder(SYMBOL);
                sb.append(userModel.getUserEntity().getId()).append("#false");
                t.setRelation(sb.toString());
            });
            taskRepositoryImpl.saveAll(userModel.getTaskEntitys());
        }
    }

    @Override
    public void update(UserModel userEntityPo) {
        userDao.save(userEntityPo.getUserEntity());
    }


    @Override
    public List<UserEntityPo> queryAll() {
        Optional pageable = Optional.ofNullable(context.getContext().get("pageable"));
        if (pageable.isPresent()) {
            return userDao.findAll((Pageable) pageable.get()).getContent();
        }
        return userDao.findAll();
    }

    @Override
    public UserOutModel getByIdInfo(Long id) {
        UserOutModel userOutModel = new UserOutModel();
        userOutModel.setUserEntity(userDao.getById(id));
        userOutModel.setTaskEntitys(taskRepositoryImpl.findAllByRelation(id));
        return userOutModel;
    }
}
