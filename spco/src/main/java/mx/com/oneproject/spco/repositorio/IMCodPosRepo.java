package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.oneproject.spco.modelo.CodPost;

public interface IMCodPosRepo extends JpaRepository<CodPost, String>{

}
