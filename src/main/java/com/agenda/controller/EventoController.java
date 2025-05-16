package com.agenda.controller;

import com.agenda.model.Evento;
import com.agenda.repository.UsuarioRepository;
import com.agenda.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioRepository usuarioRepository;



    // Obtener eventos del usuario autenticado
    @GetMapping
    @ResponseBody
    public List<Map<String, Object>> listarEventos(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<Evento> eventos = eventoService.obtenerEventosPorUsuario(username);

        return eventos.stream()
                .map(evento -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", evento.getId());
                    map.put("title", evento.getTitulo());
                    map.put("start", evento.getFecha() + "T" + evento.getHora());
                    map.put("descripcion", evento.getDescripcion());
                    return map;
                })
                .collect(Collectors.toList());
    }


    // Crear o actualizar evento
    @PostMapping
    public ResponseEntity<Evento> guardarEvento(@RequestBody Evento evento, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        usuarioRepository.findByUsername(username).ifPresent(evento::setUsuario);
        Evento eventoGuardado = eventoService.guardar(evento);
        return ResponseEntity.ok(eventoGuardado);
    }

    // Eliminar evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        if (eventoService.eliminar(id, username)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(
            @PathVariable Long id,
            @RequestBody Evento eventoEditado,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        String username = userDetails.getUsername();
        Evento actualizado = eventoService.actualizar(id, eventoEditado, username);
        return ResponseEntity.ok(actualizado);
    }

}
