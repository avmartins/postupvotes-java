package br.com.segware.init;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.segware.entity.User;
import br.com.segware.entity.UserRole;
import br.com.segware.repository.RegistroGeralRepository;
import br.com.segware.service.UserService;

/**
 * @author Anderson Virginio Martins
 */

@SuppressWarnings("ALL")
@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	RegistroGeralRepository registroGeralRepository;

	private final UserService userService;
	
	public Init(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		LocalDateTime localDateTime = LocalDateTime.now();
		int dia = 366;

		User root = new User("Administrador", "000.111.222-33", "root@email.com", "root", UserRole.ADMIN.getRole(),
				localDateTime.toLocalDate().minusDays(dia), true);
		userService.save(root);

		User user = new User("User", "111.555.333-22", "user@email.com", "user", UserRole.USER.getRole(),
				localDateTime.toLocalDate().minusDays(dia), true);
		userService.save(user);	

	}
}
