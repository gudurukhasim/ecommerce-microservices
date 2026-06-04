package com.ecommerce.notificationservice.repository;

import com.ecommerce.notificationservice.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserEmail(String userEmail);
    List<Notification> findByOrderNumber(String orderNumber);
}