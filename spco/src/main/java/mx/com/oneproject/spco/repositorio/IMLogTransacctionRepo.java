package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.oneproject.spco.modelo.LogTransacction;

public interface IMLogTransacctionRepo extends JpaRepository<LogTransacction, Integer> {

}
