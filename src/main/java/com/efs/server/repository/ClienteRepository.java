package com.efs.server.repository;

import com.efs.server.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("select c.nome from Cliente c")

    List<String> findNomes();

}
