package eva.agenda_contactos.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eva.agenda_contactos.modelo.Contacto;
import eva.agenda_contactos.repositorio.ContactoRepositorio;

@Service
public class ContactoServicio implements IContactoServicio
{
    // Inyectar el repositorio
    @Autowired
    private ContactoRepositorio contactoRepositorio;


    @Override
    public List<Contacto> listarContactos() 
    {
        return contactoRepositorio.findAll();
    }

    @Override
    public Contacto buscarContactoPorId(Integer idContacto) 
    {
        // Si lo encuentra: devuelve el objeto, sino lo encuentra: valor nulo(orElse)
        Contacto contacto = contactoRepositorio.findById(idContacto).orElse(null);
        return contacto;
    }

    // Guarda un contacto si el id=null (porque no existe todavía el contacto en la BD)
    // Actualiza un contacto si el id es diferente de null, no está vacío (porque el contacto ya estaba existe en la BD)
    @Override
    public void guardarContacto(Contacto contacto) 
    {
        contactoRepositorio.save(contacto);
    }

    @Override
    public void eliminarContacto(Contacto contacto) 
    {
        contactoRepositorio.delete(contacto);    
    }

   

}
