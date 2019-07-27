package  br.com.segware.service;

import java.io.IOException;
import java.time.LocalDate;

import  br.com.segware.entity.RegistroGeral;
import  br.com.segware.entity.User;

/**
 * @author Anderson Virginio Martins
 */
public interface RegistroGeralService {

	void atualizacaoNoLogIn() throws IOException;

	void primeiroRegistro(User user, LocalDate localDate);

	RegistroGeral ultimoRegistroDoUsuario(User user);

	Iterable<RegistroGeral> findAllByData();

	Iterable<RegistroGeral> findByUser(User user);
	
	Iterable<RegistroGeral> findAllByDataAndUserRoleIsUser();
	
	Iterable<RegistroGeral> findAllByDataAndUserRoleIsAdministrador();
	
	Iterable<RegistroGeral> findAll();

}
