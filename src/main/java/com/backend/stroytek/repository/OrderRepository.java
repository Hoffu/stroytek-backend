package com.backend.stroytek.repository;

import com.backend.stroytek.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
