package com.example.atividade.lembretes.repository;
import com.example.atividade.lembretes.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete , Long> {

    @Query("FROM Lembrete WHERE ativo = :ativo")
    List<Lembrete> findByAtivo(@Param("ativo") boolean ativo);

}