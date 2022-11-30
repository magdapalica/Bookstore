package com.fdmgroup.bookstoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.bookstoreproject.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

}
