package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
