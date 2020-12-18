package com.revature.data;

import com.revature.beans.Like;

public interface LikeDAO extends GenericDAO<Like> {
    public Like add(Like l);
}
