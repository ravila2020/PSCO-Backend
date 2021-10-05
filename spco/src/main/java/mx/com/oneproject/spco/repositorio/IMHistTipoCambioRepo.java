package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.oneproject.spco.modelo.HistTipoCambio;
import mx.com.oneproject.spco.modelo.HistTipoCambioId;

public interface IMHistTipoCambioRepo extends JpaRepository<HistTipoCambio, HistTipoCambioId>{

}
