package com.Shiroha.coronavirus.service.iml;

import com.Shiroha.coronavirus.dao.InspectDao;
import com.Shiroha.coronavirus.entity.Inspect;
import com.Shiroha.coronavirus.service.InspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InspectServiceImp implements InspectService {
    @Autowired
    InspectDao dao;
    @Override
    public List<Inspect> find(int baseId) {
        return dao.findById( baseId);
    }

    @Override
    public void add(Inspect inspect) {
        dao.add(inspect);
    }
}
