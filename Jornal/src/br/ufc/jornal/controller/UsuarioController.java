package br.ufc.jornal.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.jornal.criptografia.Criptografar;
import br.ufc.jornal.dao.RoleDao;
import br.ufc.jornal.dao.SecaoDao;
import br.ufc.jornal.dao.UsuarioDao;
import br.ufc.jornal.model.Role;
import br.ufc.jornal.model.Usuario;

@Transactional
@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;	
	
	@Autowired
	private SecaoDao secaoDao;
	
	@Autowired
	private RoleDao roleDao;
	
	// Controlador Login
	@RequestMapping("formularioLogin")
	public String formularioLogin(Model model){
		List<Role> roles = roleDao.listar();
		model.addAttribute("roles", roles);
		return "/usuario/formulario_login";
	}
	
	@RequestMapping("efetuarLogin")
	public String efetuarLogin(Usuario user, HttpSession session, Model model){
 
		// Criando instância da classe para criptografar a senha do usuario
		Criptografar crip = new Criptografar();
		
		// Pegando senha passada do usuário para criptografar
		String senha = user.getSenha();
		String senha_criptografada = crip.criptografar(senha);		
		String login = user.getLogin();
		long roleId = user.getRoleId();
        
		
        Usuario  userRef = usuarioDao.getUserLogin(login); 
		
        // Lista de Papeis do Usuário
        List<Role> usuario_roles = userRef.getRoles();
        List<Long> id_roles = new ArrayList<Long>();
        
        // Pecorrendo lista de roles para setar seus os ID dos papeis
        for (Role r : usuario_roles) {
			id_roles.add(roleDao.getRoleNome(r.getRole()));
		}
        
        // Quando for inserir os interceptores, fazer o teste entre o valor que vem da seleção e o papel
		if(userRef != null && userRef.getSenha().equals(senha_criptografada) && id_roles.contains(roleId)){
		
            userRef.setRoleId((long) id_roles.indexOf(roleId)); 
			session.setAttribute("usuario", userRef);
			model.addAttribute("usuario", userRef);  // Adicionando meu usuário, para que eu tenha como identifica-lo.
			return "../../index";
		
		}
		
		
        return "redirect:formularioLogin";		
        
	}
	
	
	// Controlador Usuário 	
	 @RequestMapping("formularioUsuario")
	 public String formularioUsuario(){
		 return "/usuario/formulario_usuario";
	 }
	 
	 @RequestMapping("inserirUsuario")
	 public String inserirUsuario(Usuario usuario, HttpSession session){
		
		 // Criando instância da classe para criptografar a senha do usuario
		 Criptografar crip = new Criptografar();
		 // Pegando senha passada do usuário para criptografar
		 String senha = usuario.getSenha();
		 String senha_criptografada = crip.criptografar(senha);
		 
		// Pegando o ID referente ao papel de Jornalista
   	    Long id_ref_leitor = roleDao.getRoleNome("Leitor");
		 
		 // Testa se o Usuário já está cadastrado..
		 if(usuarioDao.getUserLogin(usuario.getLogin()) != null){ 
			 return "/usuario/formulario_usuario";
		 }
	  
		 Role papel = roleDao.getRoleId(id_ref_leitor); 
		 List<Role> roles = roleDao.usuarioRoles(usuario.getId());
		 roles.add(papel);
		 
		 usuario.setRoleId(id_ref_leitor);
		 usuario.setRoles(roles);
		 // Alterando a senha do usuario
		 usuario.setSenha(senha_criptografada);
	  
		 this.usuarioDao.adicionar(usuario); 	 
	
		 return "redirect:formularioLogin";     
	 
	 }
     
	 @RequestMapping("listarUsuario")
	 public String listarUsuario(Model model){
		
		 List<Usuario> users = this.usuarioDao.listar();
		 model.addAttribute("usuarios", users);
		 model.addAttribute("quantidade", users.size());
		 
		 return "/usuario/listar_usuarios";		 
	 }
	 
	 
	 @RequestMapping("alterarUsuario")
	 public String editarUsuario(Usuario user){
		 
		 this.usuarioDao.alterar(user);
		 
		 return "redirect:listarUsuario";
	 }
	 
	 @RequestMapping("excluirUsuario")
	 public String excluirUsuario(Usuario user){
		 
		 this.usuarioDao.remover(user);
		 
		 return "redirect:listarUsuario";
	 }

	 // Controlador de Cadastro de Jornalista
	 
	 @RequestMapping("formularioJornalista")
	 public String formularioJornalista(){
		return "/usuario/formulario_jornalista"; 
	 }
	 
	 @RequestMapping("adicionarJornalista")
	 public String adicionarJornalista(Usuario usuario, HttpSession session){
		 
		 // Criando instância da classe para criptografar a senha do usuario
		 Criptografar crip = new Criptografar();
		 
		 // Pegando senha passada do usuário para criptografar
		 String senha = usuario.getSenha();
		 String senha_criptografada = crip.criptografar(senha);
		 
		 Usuario u = usuarioDao.getUserLogin(usuario.getLogin());
		 
		 // Pegando o ID referente ao papel de Jornalista
		 Long id_ref_jornalista = roleDao.getRoleNome("Jornalista");
		
		 // Caso em que o Usuário está no banco, atualiza e seta um novo papel
		  if(u != null){ 
			  
			     Role userPapel = roleDao.getRoleId(id_ref_jornalista); 
				 List<Role> roles = roleDao.usuarioRoles(u.getId());
				 roles.add(userPapel);
				 
				 usuario.setId(u.getId());
				 usuario.setRoleId(id_ref_jornalista);
			 	 usuario.setRoles(roles);
			 	 
				 // Alterando a senha do usuario
				 usuario.setSenha(senha_criptografada);
			 	 
			 	 this.usuarioDao.alterar(usuario);
			 	 
			 	 return "operacao_realizada";  // Se eu o retorna para para home, ele se passará como um novo usuário.. 

		  }else{	 // Caso em que o Usuário não está no banco, atualiza os papéis do usuário e inseri o mesmo
	
   		         Role papel = roleDao.getRoleId(id_ref_jornalista); 
		         List<Role> roles = roleDao.usuarioRoles(usuario.getId());
		         roles.add(papel);

                 usuario.setRoleId(id_ref_jornalista);
	 	         usuario.setRoles(roles);
		 
	 			 // Alterando a senha do usuario
	 			 usuario.setSenha(senha_criptografada);
	 			 
		         this.usuarioDao.adicionar(usuario); 
		
		         return "operacao_realizada";  
	       }
	 }		  

	 // Caso o usuário queira sair da sua seção atual     
	 @RequestMapping("sair")
	 public String sair(HttpSession session){	 
			
		 session.invalidate();
		 return "../../index";
	 
	 }
	 
	 // Caso o usuário queira voltar diretamente para a página principal
	 
}
