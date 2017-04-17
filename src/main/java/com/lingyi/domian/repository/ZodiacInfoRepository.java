package com.lingyi.domian.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.lingyi.domian.entity.ZodiacInfoEntity;

@Transactional
public interface ZodiacInfoRepository extends CrudRepository<ZodiacInfoEntity,String>{

}
