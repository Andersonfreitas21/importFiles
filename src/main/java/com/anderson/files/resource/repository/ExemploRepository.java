package com.anderson.files.resource.repository;

import com.anderson.files.domain.data.entity.Exemplo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExemploRepository extends JpaRepository<Exemplo, UUID>, JpaSpecificationExecutor<Exemplo> {
}
