package com.fdmgroup.bookstoreproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.verify;

import com.fdmgroup.bookstoreproject.model.Picture;
import com.fdmgroup.bookstoreproject.repository.PictureRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class PictureServiceImplTest {

	@InjectMocks
	PictureServiceImpl pictureServiceImpl;
	
	@MockBean
	PictureRepository mockPictureRepository;
	
	@Test
	public void test_getPictureById() {
		Picture picture = new Picture();
		Mockito.doReturn(picture).when(mockPictureRepository).getById(1);
		Picture servicePicture = pictureServiceImpl.getPictureById(1);
		verify(mockPictureRepository).getById(1);
		Assertions.assertEquals(servicePicture, picture);
		
	}
	
	@Test
	public void test_savePicture() {
		Picture picture = new Picture();
		Mockito.doReturn(null).when(mockPictureRepository).save(picture);
		pictureServiceImpl.savePicture(picture);
		verify(mockPictureRepository).save(picture);
		
	}
	
	
	
}
