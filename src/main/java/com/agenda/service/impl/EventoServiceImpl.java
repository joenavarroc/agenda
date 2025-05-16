package com.agenda.service.impl;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.agenda.model.Evento;
import com.agenda.model.Usuario;
import com.agenda.repository.EventoRepository;
import com.agenda.repository.UsuarioRepository;
import com.agenda.service.EventoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento guardar(Evento evento) {
        // Si la hora no se ha proporcionado, se asigna la medianoche como valor por defecto
        if (evento.getHora() == null) {
            evento.setHora(LocalTime.MIDNIGHT);
        }
        return eventoRepository.save(evento);
    }

    @Override
    public Evento obtenerPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado con id: " + id));
    }

    @Override
    public boolean eliminar(Long id, String username) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado con id: " + id));

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!evento.getUsuario().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autorizado para eliminar este evento");
        }

        eventoRepository.delete(evento);
        return true;
    }

    @Override
    public Evento actualizar(Long id, Evento eventoEditado, String username) {
        // Obtener el evento a actualizar
        Evento existente = eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado con id: " + id));

        // Buscar al usuario que intenta realizar la actualización
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar si el evento pertenece al usuario correcto
        if (!existente.getUsuario().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autorizado para modificar este evento");
        }

        // Actualizar los campos del evento
        existente.setTitulo(eventoEditado.getTitulo());
        existente.setDescripcion(eventoEditado.getDescripcion());
        existente.setFecha(eventoEditado.getFecha());
        // Si no se ha proporcionado hora, dejamos la existente; si no, usamos la nueva
        existente.setHora(eventoEditado.getHora() != null ? eventoEditado.getHora() : existente.getHora());

        return eventoRepository.save(existente);
    }

    @Override
    public List<Evento> obtenerEventosPorUsuario(String username) {
        // Obtener el usuario por su nombre
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Obtener los eventos de ese usuario
        return eventoRepository.findByUsuario(usuario);
    }
}
