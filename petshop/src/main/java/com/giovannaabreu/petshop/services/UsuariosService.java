package com.giovannaabreu.petshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.giovannaabreu.petshop.entities.Usuarios;
import com.giovannaabreu.petshop.repositories.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuarios> listarTodos(){
        return repository.findAll();
    }
   
    public Usuarios cadastrar(Usuarios usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return repository.save(usuario);
    }
    
    
    public Usuarios atualizar(Long id, Usuarios dados) {
        Usuarios usuario = repository.findById(id).orElse(null);
        if (usuario == null) {
            return null;
        }


        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());
        usuario.setPerfil(dados.getPerfil());
        usuario.setEndereco(dados.getEndereco());
        usuario.setBairro(dados.getBairro());
        usuario.setComplemento(dados.getComplemento());
        usuario.setCep(dados.getCep());
        usuario.setCidade(dados.getCidade());
        usuario.setEstado(dados.getEstado());
        usuario.setFoto(dados.getFoto());

        return repository.save(usuario);
        }

        public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
        return false;
        }

        repository.deleteById(id);
        return true;
        }
        
          public Usuarios buscarPorId(Long id) {
                return repository.findById(id).orElse(null);
            }
        
        public Usuarios login(String email, String senha) {
                       
            Usuarios usuario =  repository.findByEmail(email);
            if (usuario == null) {
                return null;
            }
            if (!passwordEncoder.matches(senha, usuario.getSenha())) {
                return null;
            }
            return usuario;
        }
        
        @Autowired
        private JavaMailSender mailSender; 
        
        public boolean enviarEmailRecuperacao(String email) {
            Usuarios usuario = repository.findByEmail(email);
            
            if (usuario != null) {
                String link = "http://127.0.0.1:5500/redefinir-senha.html?email=" + email;        
                SimpleMailMessage mensagem = new SimpleMailMessage();
                mensagem.setTo(email);
                mensagem.setSubject("Recuperação de Senha - DevSenai");
                mensagem.setText(
                        "Olá " + usuario.getNome() + ",\n\n" + "Clique no link abaixo para criar uma nova senha:\n" +
                link
              );
                    
        mailSender.send(mensagem);
                
                System.out.println("Email enviado para: " +email );
                
                return true;
            }
            
            return false;
            }
        
        public boolean redefinirSenha(String email, String novaSenha) {
            Usuarios usuario = repository.findByEmail(email);
            
            if( usuario != null) {
                usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
                repository.save(usuario);
                return true;
            }
            return false;
        }        
}