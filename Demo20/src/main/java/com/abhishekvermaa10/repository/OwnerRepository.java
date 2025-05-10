package com.abhishekvermaa10.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhishekvermaa10.entity.Owner;

/**
 * @author abhishekvermaa10
 */
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	@Query("SELECT o FROM Owner o JOIN FETCH o.pet WHERE o.pet.birthDate BETWEEN :startDate AND :endDate")
	List<Owner> findAllOwnersByPetDateOfBirthRange(LocalDate startDate, LocalDate endDate);

}
