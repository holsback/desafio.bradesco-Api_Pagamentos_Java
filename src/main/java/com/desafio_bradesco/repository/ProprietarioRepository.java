package com.desafio_bradesco.repository;

import com.desafio_bradesco.model.Pagamento;
import com.desafio_bradesco.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProprietarioRepository extends JpaRepository<Proprietario, UUID> {

}
