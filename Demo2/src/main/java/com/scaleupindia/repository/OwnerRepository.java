package com.scaleupindia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scaleupindia.entity.Owner;

/**
 * @author abhishekvermaa10
 *
 */
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}