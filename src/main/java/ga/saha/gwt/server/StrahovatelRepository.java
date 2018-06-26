package ga.saha.gwt.server;

import ga.saha.gwt.shared.Strahovatel;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface StrahovatelRepository extends Serializable, CrudRepository<Strahovatel,Long>{

    List<Strahovatel> findByLastnameAndFirstnameAndFirstname2(String lastname, String firstname, String firstname2);

    List<Strahovatel> findByLastnameAndFirstname(String lastname, String firstname);

    List<Strahovatel> findByLastnameAndFirstname2(String lastname, String firstname2);

    List<Strahovatel> findByFirstnameAndFirstname2(String firstname, String firstname2);

    List<Strahovatel> findByFirstname2(String firstname2);

    List<Strahovatel> findByLastname(String lastname);

    List<Strahovatel> findByFirstname(String firstname);
}
