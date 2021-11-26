package mx.com.oneproject.spco.service;

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

}
