package mx.com.oneproject.spco.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.oneproject.spco.modelo.SysAduFact;
import mx.com.oneproject.spco.repositorio.IMSysAduFactRepo;

@Service
public class SysAduFactService {
	@Autowired 
	private IMSysAduFactRepo sysAduFactRepo;
	
	@Autowired 
	@PersistenceContext
	private EntityManager em;
	
	public List<SysAduFact> getRemanente(String cli, String part, String ind)
	{
		return em.createNamedStoredProcedureQuery("ParteCliente")
				.setParameter("cli",cli)
				.setParameter("part",part)
				.setParameter("ind", ind)
				.getResultList();
	}

	public List<SysAduFact> getRemanenteInv(String cli, String part, String ind)
	{
		return em.createNamedStoredProcedureQuery("ParteClienteInv")
				.setParameter("cli",cli)
				.setParameter("part",part)
				.setParameter("ind", ind)
				.getResultList();
	}

	public List<SysAduFact> getTraspasoCliente(String empresa, String recinto, String cli, String prod, Integer cantidad, String recintoD, String cliD, String numFactN)
	{
		return em.createNamedStoredProcedureQuery("TraspasoCliente")
		.setParameter("empresa", empresa)
		.setParameter("recinto", recinto)
		.setParameter("cli",cli)
		.setParameter("prod",prod)
		.setParameter("cantidad", cantidad)
		.setParameter("recintoD", recintoD)
		.setParameter("cliD", cliD)
		.setParameter("numFactN", numFactN)
		.getResultList();

	}
	
}
