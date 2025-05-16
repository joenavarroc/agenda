package com.agenda.controller;

import com.agenda.model.Contacto;
import com.agenda.repository.ContactoRepository;
import com.agenda.repository.UsuarioRepository;
import com.agenda.service.EventoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/contactos/agenda")
public class AgendaController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public String mostrarAgenda(Model model, @AuthenticationPrincipal UserDetails userDetails) throws JsonProcessingException {
        String username = userDetails.getUsername();
        var usuario = usuarioRepository.findByUsername(username).orElse(null);

        String eventosJson = "[]";
        List<Contacto> contactos = new ArrayList<>();

        if (usuario != null) {
            // Cargar eventos y convertir a JSON para FullCalendar
            List<Map<String, Object>> eventosMap = eventoService.obtenerEventosPorUsuario(username)
                .stream().map(evento -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", evento.getId());
                    map.put("title", evento.getTitulo());
                    map.put("start", evento.getFecha() + "T" + evento.getHora());
                    map.put("descripcion", evento.getDescripcion());
                    return map;
                }).collect(Collectors.toList());

            eventosJson = objectMapper.writeValueAsString(eventosMap);

            // Cargar contactos del usuario
            contactos = contactoRepository.findByUsuario_Username(username);
        }

        model.addAttribute("eventosJson", eventosJson);
        model.addAttribute("contactos", contactos);

        return "agenda";
    }
}
