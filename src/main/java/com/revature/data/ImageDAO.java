package com.revature.data;

import com.revature.beans.Image;

public interface ImageDAO extends GenericDAO<Image>{
	public Image findByName(Image i);
}
