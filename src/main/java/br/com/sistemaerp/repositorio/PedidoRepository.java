package br.com.sistemaerp.repositorio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.sistemaerp.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID>, QuerydslPredicateExecutor<Pedido>{
	@Query(value = "select COALESCE(max(cast(codigo as int)),0) from pedido", nativeQuery = true)
	Integer nextCod();
}
