package com.revature.services;

import com.revature.beans.Status;
import com.revature.data.StatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StatusServiceImpl implements StatusService{
    private StatusDAO statusDAO;

    @Autowired
    public StatusServiceImpl(StatusDAO s){
        statusDAO = s;
    }

    @Override
    public Status addStatus(Status s) {
        return statusDAO.add(s);
    }

    @Override
    public Status getStatusById(Integer id) {
        return statusDAO.getById(id);
    }

    @Override
    public Set<Status> getAll() {
        return statusDAO.getAll();
    }

    @Override
    public void updateStatus(Status s) {
        statusDAO.update(s);
    }

    @Override
    public void deleteStatus(Status s) {
        statusDAO.delete(s);
    }
}
