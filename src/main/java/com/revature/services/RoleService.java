package com.revature.services;

import com.revature.beans.Role;

import java.util.Set;

public interface RoleService {
    // Create
    public Role addRole(Role r);

    // Read
    public Role getRoleById(Integer id);
    public Set<Role> getAll();

    // Update
    public void updateRole(Role r);

    // Delete
    public void deleteRole(Role r);
}
