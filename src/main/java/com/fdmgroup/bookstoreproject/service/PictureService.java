package com.fdmgroup.bookstoreproject.service;

import com.fdmgroup.bookstoreproject.model.Picture;

public interface PictureService {
	
	Picture getPictureById(int id);
	void savePicture(Picture picture);

}
