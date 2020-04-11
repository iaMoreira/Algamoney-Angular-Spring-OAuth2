package com.devmobil.algamoney.api.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmobil.algamoney.api.model.BaseEntity;


public interface BaseRepository <T extends BaseEntity> extends JpaRepository<T, Serializable> {

}
