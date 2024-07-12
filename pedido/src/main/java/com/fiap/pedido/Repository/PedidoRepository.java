package com.fiap.pedido.Repository;

import com.fiap.pedido.Model.Pedido;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

//        @Query("SELECT p FROM Pedido p JOIN p.produtos prod WHERE prod.id = :produtoId")
//        List<Pedido> findByProdutoId(@Param("produtoId") Long produtoId);

        List<Pedido> findByClienteId(Long clienteId);

}
