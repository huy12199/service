package com.ngv.api.v1.repository;

import com.ngv.api.v1.entity.ClientRegistryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRegistryEntityRepository extends
    JpaRepository<ClientRegistryEntity, String> {
}