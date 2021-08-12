package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.oneproject.spco.modelo.VigenciaToken;

public interface IMVigTokenRepo extends JpaRepository<VigenciaToken, Integer> {

}
