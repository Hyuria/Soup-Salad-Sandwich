package com.revature.services;

import com.revature.beans.Status;

import java.util.Set;

public interface StatusService {
    // Create
    public Status addStatus(Status s);

    // Read
    public Status getStatusById(Integer id);
    public Set<Status> getAll();

    // Update
    public void updateStatus(Status s);

    // Delete
    public void deleteStatus(Status s);
}
