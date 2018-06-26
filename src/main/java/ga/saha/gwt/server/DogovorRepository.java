package ga.saha.gwt.server;

import ga.saha.gwt.shared.Dogovor;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface DogovorRepository extends Serializable, CrudRepository<Dogovor,Long> {

}
