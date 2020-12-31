package com.revature.data;

import com.revature.beans.Image;
import com.revature.beans.User;
import com.revature.exception.NonUniqueUsernameException;

public interface ImageDAO extends GenericDAO<Image>{
	public Image findByName(Image i);

	public Image findByUserId(Integer userId);
	
}

