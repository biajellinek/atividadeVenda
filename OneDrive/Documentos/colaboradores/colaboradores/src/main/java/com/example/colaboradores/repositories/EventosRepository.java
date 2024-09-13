package com.example.colaboradores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.colaboradores.entities.EventosEntity;

@Repository
public interface EventosRepository extends JpaRepository<EventosEntity, Long>{

}
