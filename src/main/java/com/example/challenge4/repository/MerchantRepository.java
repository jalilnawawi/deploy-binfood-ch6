package com.example.challenge4.repository;

import com.example.challenge4.model.Merchant;
import com.example.challenge4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    @Query(value = "select m.* from merchant m where open = true",
    nativeQuery = true)
    List<Merchant> findOpenMerchant();

    @Query(value = "select m.* from merchant m where deleted = false",
            nativeQuery = true)
    List<Merchant> findExistingMerchant();

    Optional<Merchant> findByName(String name);
}
