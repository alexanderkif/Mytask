package ga.saha.gwt.server;

import ga.saha.gwt.shared.Strahovatel;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface StrahovatelRepository extends Serializable, CrudRepository<Strahovatel,Long>{

    List<Strahovatel> findByLastnameContainingAndFirstnameContainingAndFirstname2ContainingAllIgnoreCase(String lastname, String firstname, String firstname2);

    List<Strahovatel> findByLastnameContainingAndFirstnameContainingAllIgnoreCase(String lastname, String firstname);

    List<Strahovatel> findByLastnameContainingAndFirstname2ContainingAllIgnoreCase(String lastname, String firstname2);

    List<Strahovatel> findByFirstnameContainingAndFirstname2ContainingAllIgnoreCase(String firstname, String firstname2);

    List<Strahovatel> findByFirstname2ContainingIgnoreCase(String firstname2);

    List<Strahovatel> findByLastnameContainingIgnoreCase(String lastname);

    List<Strahovatel> findByFirstnameContainingIgnoreCase(String firstname);
}
