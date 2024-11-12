package eva.agenda_contactos.controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import eva.agenda_contactos.modelo.Contacto;
import eva.agenda_contactos.servicio.ContactoServicio;

@Controller
public class ContactoControlador 
{
    // Para enviar información a la consola
    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    // Inyectar el servicio
    @Autowired
    private ContactoServicio contactoServicio;

    // Página de inicio
    @GetMapping("/")
    public String iniciar(ModelMap modelo)
    {
        List<Contacto> contactos = contactoServicio.listarContactos();
    
        // Para mostrar en la terminal los contactos
        contactos.forEach((contacto) -> logger.info(contacto.toString()));

        modelo.put("contactos", contactos); // llave y valor
        return "index"; // página html
    }


    // Mostrar SOLO el formulario de agregar (petición GET)
    @GetMapping("/agregar")
    public String mostrarAgregar()
    {
        return "agregar"; // agregar.html
    }


    // Agregar un contacto (petición POST). ModelAttribute: se le pasa el objeto con los datos del formulario (los recibe de modelAttribute del formulario) e indicamos en java que es de la clase Contacto
    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForma") Contacto contacto)
    {
        logger.info("Contacto a agregar: " + contacto); // para mostrar en la terminal el contacto que se va a agregar

        contactoServicio.guardarContacto(contacto);
        return "redirect:/"; // redirigimos al controlador "/" con el método "iniciar", para que se carguen todos los contactos
    }


    // Mostrar SOLO el formulario con los datos a editar (petición GET). PathVariable: "value" el valor del id en el href del botón, luego tipo de dato y nombre de la variable que le asignamos en java
    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") int idContacto, ModelMap modelo)
    {
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto); // recuperamos el contacto a editar
        logger.info("Contacto a editar: " + contacto);

        modelo.put("contacto", contacto); // llave y valor

        return "editar"; // editar.html
    }


    //Editar un contacto (petición POST). ModelAttribute: se le pasa el objeto con los datos del formulario (los recibe de th:object)
    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto") Contacto contacto)
    {
        logger.info("Contacto a guardar (editar): " + contacto);

        contactoServicio.guardarContacto(contacto); // al no ser null el valor del id, lo que hace es actualizar un contacto, no agregar

        return "redirect:/"; // redirigimos al controlador "/" con el método "iniciar", para que se carguen todos los contactos
    }
    

    //Eliminar un contacto (petición GET). ModelAttribute: se le pasa el objeto con los datos del formulario (los recibe de th:object)
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") int idContacto)
    {
        // SOLO necesitamos el id del contacto para eliminarlo, pero le debemos pasar por parámetro un objeto Contacto pero solo con el campo id
        Contacto contacto = new Contacto();
        contacto.setIdContacto(idContacto);

        contactoServicio.eliminarContacto(contacto);

        return "redirect:/";
    }
}
