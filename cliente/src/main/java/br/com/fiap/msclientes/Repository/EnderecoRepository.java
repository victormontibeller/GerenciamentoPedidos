package br.com.fiap.msclientes.Repository;

import br.com.fiap.msclientes.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>  {

    @Query("SELECT e FROM Endereco e WHERE upper(e.cep) = upper(:cep)")
    Endereco findByCep(@Param("cep") String cep);    
    
}
