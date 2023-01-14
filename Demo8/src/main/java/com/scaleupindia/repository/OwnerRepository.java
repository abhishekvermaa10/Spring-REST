package com.scaleupindia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scaleupindia.entity.Owner;

/**
 * @author abhishekvermaa10
 *
 */
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
	@Query("SELECT o FROM Owner o WHERE o.pet.birthDate BETWEEN :startDate AND :endDate")
	List<Owner> findAllOwnersByPetDateOfBirthRange(LocalDate startDate, LocalDate endDate);

	@Query("SELECT o FROM Owner o WHERE o.city = :city AND o.state = :state")
	List<Owner> findAllOwnersByCityAndState(String city, String state);
}