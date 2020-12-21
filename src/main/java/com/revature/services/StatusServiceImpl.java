package com.revature.services;

import com.revature.beans.Status;
import com.revature.data.StatusDAO;
import com.revature.data.StatusHibernate;

import java.util.Set;

public class StatusServiceImpl implements StatusService{
    private StatusDAO statusDAO;

    public StatusServiceImpl(){
        statusDAO = new StatusHibernate();
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
