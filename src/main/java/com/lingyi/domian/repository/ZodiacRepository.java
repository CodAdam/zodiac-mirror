package com.lingyi.domian.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.lingyi.domian.entity.ZodiacEntity;

@Transactional
public interface ZodiacRepository extends CrudRepository<ZodiacEntity,String>{

}
