package br.com.sistemaerp.repositorio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.sistemaerp.model.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, UUID>,QuerydslPredicateExecutor<Produto>{
	
	@Query(value = "select COALESCE(max(cast(codigo_interno as int)),0) from produto", nativeQuery = true)
	int nextCod();
}
