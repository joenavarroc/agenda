document.addEventListener('DOMContentLoaded', function() {
    const calendarEl = document.getElementById('calendar');
    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: '/eventos', // Endpoint que devuelve los eventos en formato JSON
        eventClick: function(info) {
            openModal(info.event);
        },
        dateClick: function(info) {
            openModal(null, info.dateStr);
        },
        eventDrop: function(info) {
            // Lógica para actualizar evento al moverlo
        },
        eventResize: function(info) {
            // Lógica para actualizar evento al cambiar su tamaño
        }
    });
    calendar.render();
});


    let currentEventId = null;

    // Mostrar modal para crear o editar un evento
    function openModal(event = null, selectedDate = null) {
        document.getElementById('eventModal').style.display = 'flex';
        document.getElementById('modalTitle').textContent = event ? 'Editar Evento' : 'Nuevo Evento';
        const deleteButton = document.getElementById('deleteEvent');

        if (event) {
            document.getElementById('eventId').value = event.id;
            document.getElementById('titulo').value = event.title;
            document.getElementById('descripcion').value = event.extendedProps.descripcion;
            document.getElementById('fecha').value = event.startStr.split('T')[0];
            document.getElementById('hora').value = event.startStr.split('T')[1]?.substring(0, 5) || '00:00';
            deleteButton.style.display = 'inline-block'; // Mostrar botón eliminar
        } else if (selectedDate) {
            document.getElementById('eventId').value = '';
            document.getElementById('titulo').value = '';
            document.getElementById('descripcion').value = '';
            document.getElementById('fecha').value = selectedDate;
            document.getElementById('hora').value = '00:00';
            deleteButton.style.display = 'none'; // Ocultar botón eliminar
        }
    }

    document.getElementById('closeModal').addEventListener('click', function () {
        document.getElementById('eventModal').style.display = 'none';
    });

    document.getElementById('eventForm').addEventListener('submit', function (e) {
        e.preventDefault();

        const id = document.getElementById('eventId').value;
        const evento = {
            titulo: document.getElementById('titulo').value,
            descripcion: document.getElementById('descripcion').value,
            fecha: document.getElementById('fecha').value,
            hora: document.getElementById('hora').value
        };

        const method = id ? 'PUT' : 'POST';
        const url = id ? `/eventos/${id}` : `/eventos`;

        console.log("🔁 Enviando evento al backend:", evento);
        console.log("🔗 Método y URL:", method, url);

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(evento)
        })
        .then(response => {
            console.log("📥 Respuesta del servidor:", response.status);
            if (response.ok) {
                console.log("✅ Evento guardado exitosamente.");
                window.location.reload(); // Recarga para ver el nuevo evento
            } else {
                console.error("❌ Error al guardar el evento.");
                alert('Error al guardar el evento');
            }
        })
        .catch(error => {
            console.error("❌ Error de red al guardar evento:", error);
        });

        document.getElementById('eventModal').style.display = 'none';
    });
    document.getElementById('deleteEvent').addEventListener('click', function () {
        const id = document.getElementById('eventId').value;

        if (!id) return;

        if (confirm('¿Estás seguro de que deseas eliminar este evento?')) {
            fetch(`/eventos/${id}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    console.log("🗑️ Evento eliminado");
                    window.location.reload();
                } else {
                    alert("❌ No se pudo eliminar el evento");
                }
            })
            .catch(error => {
                console.error("❌ Error al eliminar evento:", error);
            });
        }
    });