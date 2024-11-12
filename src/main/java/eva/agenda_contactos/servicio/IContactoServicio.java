package eva.agenda_contactos.servicio;

import java.util.List;

import eva.agenda_contactos.modelo.Contacto;

public interface IContactoServicio 
{
    // Recuperar todos los contactos
    public List<Contacto> listarContactos();

    // Buscar un contacto
    public Contacto buscarContactoPorId(Integer idContacto);

    // Guarda un contacto y actualiza un contacto, para realizar las dos acciones
    // No devuelve nada, solo guarda el contacto
    public void guardarContacto(Contacto contacto);

    // Eliminar un contacto
    // No devuelve nada, solo elimina el contacto
    public void eliminarContacto(Contacto contacto);
}
