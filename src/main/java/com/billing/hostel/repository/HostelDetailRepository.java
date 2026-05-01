package com.billing.hostel.repository;

import com.billing.hostel.entity.HostelDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostelDetailRepository extends JpaRepository<HostelDetail, Long> {

    List<HostelDetail> findByHostelId(Long hostelId);

    @Query("SELECT hd FROM HostelDetail hd JOIN FETCH hd.hostel WHERE hd.hostel.id = :hostelId")
    List<HostelDetail> findByHostelIdWithHostel(Long hostelId);
}