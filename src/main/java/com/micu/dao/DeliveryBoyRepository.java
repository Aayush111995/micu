package com.micu.dao;

import com.micu.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryBoyRepository extends JpaRepository<Order, String> {
}
