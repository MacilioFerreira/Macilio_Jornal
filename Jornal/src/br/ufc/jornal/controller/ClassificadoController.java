package br.ufc.jornal.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.jornal.dao.ClassificadoDao;
import br.ufc.jornal.dao.RoleDao;
import br.ufc.jornal.dao.UsuarioDao;
import br.ufc.jornal.model.Classificado;
import br.ufc.jornal.model.Role;
import br.ufc.jornal.model.Usuario;

@Transactional
@Controller
public class ClassificadoController {

	@Autowired
	private ClassificadoDao classificadoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private RoleDao roleDao;
	
    @RequestMapping("formularioClassificado")
    public String formularioClassificado(Model model){

    	List<Role> roles = roleDao.listar();
    	model.addAttribute("roles", roles);
		
    	return "/classificado/formulario_classificado";
    }
    
    @RequestMapping("adicionarClassificado")
    public String adicionarClassificado(Classificado c, HttpSession session){
    	
    	Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        List<Classificado> classificados = classificadoDao.listar();        
        
        for (Classificado cass : classificados) {
        	if(cass.equals(c) ){
            	return "redirect:formularioClassificado";
            }
		}
        
        
        Timestamp data = new Timestamp(System.currentTimeMillis());
        c.setData_oferta(data);
        float oferta = 0;
        c.setMelhor_oferta(oferta);
        // Não possuo um autor de oferta ao inserir um Usuário. Porém o cara que cadastrou é responsável por tudo no cadastro
        c.setAutorOferta(usuarioDao.getUserLogin(usuario.getLogin()));
        
        this.classificadoDao.inserir(c);  	
        
    	return "operacao_realizada"; // página de sucesso caso ele seja a maior oferta..
    }
    
    @RequestMapping("listarClassificado")
    public String listarClassificado(Model model, HttpSession session){
    	
    	// Pegando o Usuário que está logado
    	Usuario usuario = (Usuario) session.getAttribute("usuario");
    	
    	List<Classificado> classificados = this.classificadoDao.listar();
    	model.addAttribute("classificados", classificados);
        
    	// Atibuindo o Usuário..
    	model.addAttribute("usuario", usuario);

    	return "/classificado/listar_classificados"; 
    }
    
    @RequestMapping("inserirOferta")
    public String inserirOferta(Classificado cass, Model model){   
    	
    	Classificado cas = classificadoDao.getCass(cass.getClassificadoId());
    	model.addAttribute("classificado", cas);
    	return "/classificado/fazer_oferta";
    
    }
    
    @RequestMapping("realizarCompra")
    public String realizarCompra(float oferta, long id, HttpSession session){
    	
    	Usuario usuario = (Usuario) session.getAttribute("usuario");
    	
    	Classificado cas = classificadoDao.getCass(id);
    	
    	if (usuario != null && usuario.user_role("Leitor")) {

			   if (oferta > cas.getPreco() && oferta > cas.getMelhor_oferta() ) {
				   cas.setMelhor_oferta(oferta);
				   cas.setData_oferta(new Timestamp(System.currentTimeMillis()));
				   cas.setAutorOferta(usuarioDao.getUserLogin(usuario.getLogin()));
				   return "redirect:listarClassificado";
			   }
		}
        
    	
    	return "redirect:listarClassificado";
    }
    
    @RequestMapping("alterarClassificado")
    public String alterarClassificado(Classificado c){
    	this.classificadoDao.alterar(c);
    	return "redirect:listarClassificado";
    }
    
    @RequestMapping("excluirClassificado")
    public String excluirClassificado(Classificado c){
    	this.classificadoDao.remover(c);
    	return "redirect:listarClassificado";
    }

    
}