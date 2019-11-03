package com.javagda25.securitytemplate.repository;

import com.javagda25.securitytemplate.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByClient_Id(Long id);
}
