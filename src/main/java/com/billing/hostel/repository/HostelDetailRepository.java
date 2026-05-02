package com.billing.hostel.repository;

import com.billing.hostel.entity.HostelDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelDetailRepository extends JpaRepository<HostelDetail, Long> {
}