package com.example.hnm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import com.example.hnm.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity,String>{

    @Query(value = "SELECT u.* FROM user_info u WHERE u.username = :username", nativeQuery= true)
    CustomerEntity getCustomerDtobyUsername(@Param("username") String username);

    // Spring Data JPA를 사용한 메소드 수정 위함
    Optional<CustomerEntity> findByUsername(String username);
}
