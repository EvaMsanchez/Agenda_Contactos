package eva.agenda_contactos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data // Crea los getters y setters
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contacto 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContacto;

    private String nombre;
    private String telefono;
    private String email;
}
