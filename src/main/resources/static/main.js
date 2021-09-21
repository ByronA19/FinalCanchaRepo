function deleteUser(route, id) {
    
    Swal.fire({
        title: '¿Está seguro de eliminar el registro?',
        text: "No se podrá recuperar la información",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, borrar'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: route + "/delete/" + id,
                success: function(res) {
                    location.href = route;
                }
            });
        }
    })
}

function cancelBooking(id) {
    
    Swal.fire({
        title: '',
        text: '¿Está seguro que desea cancelar la reservación?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "bookings/cancel/" + id,
                success: function(res) {
                    location.href = "bookings";
                }
            });
        }
    })
}

