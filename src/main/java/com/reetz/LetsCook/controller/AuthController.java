package com.reetz.LetsCook.controller;

import com.reetz.LetsCook.dto.AuthRequest;
import com.reetz.LetsCook.dto.AuthResponse;
import com.reetz.LetsCook.entity.Usuario;
import com.reetz.LetsCook.repository.UsuarioRepository;
import com.reetz.LetsCook.security.JwtUtil;
import com.reetz.LetsCook.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UsuarioRepository usuarioRepository,
            AuthenticationManager authenticationManager,
            UsuarioService usuarioService,
            JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            return ResponseEntity.status(400).body("Nome de usuário já existe");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário criado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println("Tentando autenticar: " + authRequest.username());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.username(), authRequest.password()
                    )
            );

            System.out.println("Autenticado com sucesso!");

            UserDetails userDetails = usuarioService.loadUserByUsername(authRequest.username());
            String token = jwtUtil.gerarToken(userDetails.getUsername());

            // Pega o usuário do banco
            Usuario usuario = usuarioRepository.findByUsername(authRequest.username());

            return ResponseEntity.ok(new AuthResponse(token, usuario.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}