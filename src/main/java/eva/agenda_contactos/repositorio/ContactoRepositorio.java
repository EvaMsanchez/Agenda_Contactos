package eva.agenda_contactos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import eva.agenda_contactos.modelo.Contacto;

// JpaRepository: extiende de dos CrudRepository y PagingAndSortingRepository (paginación y ordenación)
public interface ContactoRepositorio extends JpaRepository<Contacto, Integer>
{

}
