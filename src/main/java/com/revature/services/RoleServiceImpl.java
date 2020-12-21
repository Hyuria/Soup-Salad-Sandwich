package com.revature.services;

import com.revature.beans.Role;
import com.revature.data.RoleDAO;
import com.revature.data.RoleHibernate;

import java.util.Set;

public class RoleServiceImpl implements RoleService{
    private RoleDAO roleDAO;

    public RoleServiceImpl(){
        roleDAO = new RoleHibernate();
    }

    @Override
    public Role addRole(Role r) {
        return roleDAO.add(r);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleDAO.getById(id);
    }

    @Override
    public Set<Role> getAll() {
        return roleDAO.getAll();
    }

    @Override
    public void updateRole(Role r) {
        roleDAO.update(r);
    }

    @Override
    public void deleteRole(Role r) {
        roleDAO.delete(r);
    }
}
