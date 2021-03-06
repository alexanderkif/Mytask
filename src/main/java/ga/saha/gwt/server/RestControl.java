package ga.saha.gwt.server;

import ga.saha.gwt.shared.Dogovor;
import ga.saha.gwt.shared.Strahovatel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControl {

    @Autowired
    DogovorRepository dogovorRepository;
    @Autowired
    StrahovatelRepository strahovatelRepository;

    @RequestMapping(value = "/getdogovors", method = RequestMethod.GET)
    public List<Dogovor> getAll(){
        List<Dogovor> list = new ArrayList<>();
        dogovorRepository.findAll().forEach(list::add);
        return list;
    }

    @RequestMapping(value = "/getdogovor/{id}", method = RequestMethod.GET)
    public @ResponseBody Dogovor getDogovorById(@PathVariable Long id) {
        System.out.println("/api/getdogovor/{id}: "+id);
        return dogovorRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/getstrahovatel/{id}", method = RequestMethod.GET)
    public @ResponseBody Strahovatel getStrahovatelById(@PathVariable Long id) {
        System.out.println("/api/getstrahovatel/{id}: "+id);
        return strahovatelRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/getstrahovatels", method = RequestMethod.GET)
    public List<Strahovatel> findStrahovatels(@PathParam("lastname") String lastname,
                                              @PathParam("firstname") String firstname,
                                              @PathParam("firstname2") String firstname2){
        System.out.println("getstrahovatels lastname:"+lastname+" firstname:"+firstname+" firstname2:"+firstname2);
        List<Strahovatel> list = new ArrayList<>();
        if (!lastname.equals("")&&!firstname.equals("")&&!firstname2.equals("")) {
            list.addAll(strahovatelRepository
                    .findByLastnameContainingAndFirstnameContainingAndFirstname2ContainingAllIgnoreCase(lastname, firstname, firstname2));
        }
        if (lastname.equals("")&&!firstname.equals("")&&!firstname2.equals("")) {
            list.addAll(strahovatelRepository
                    .findByFirstnameContainingAndFirstname2ContainingAllIgnoreCase(firstname, firstname2));
        }
        if (!lastname.equals("")&&firstname.equals("")&&!firstname2.equals("")) {
            list.addAll(strahovatelRepository
                    .findByLastnameContainingAndFirstname2ContainingAllIgnoreCase(lastname, firstname2));
        }
        if (!lastname.equals("")&&!firstname.equals("")&&firstname2.equals("")) {
            list.addAll(strahovatelRepository
                    .findByLastnameContainingAndFirstnameContainingAllIgnoreCase(lastname, firstname));
        }
        if (lastname.equals("")&&firstname.equals("")&&!firstname2.equals("")) {
            list.addAll(strahovatelRepository.findByFirstname2ContainingIgnoreCase(firstname2));
        }
        if (!lastname.equals("")&&firstname.equals("")&&firstname2.equals("")) {
            list.addAll(strahovatelRepository.findByLastnameContainingIgnoreCase(lastname));
        }
        if (lastname.equals("")&&!firstname.equals("")&&firstname2.equals("")) {
            list.addAll(strahovatelRepository.findByFirstnameContainingIgnoreCase(firstname));
        }
        if (lastname.equals("")&&firstname.equals("")&&firstname2.equals("")) {
            strahovatelRepository.findAll().forEach(list::add);
        }
        return list;
    }

    @RequestMapping(value = "/savestrahovatel", method = RequestMethod.POST)
    public @ResponseBody Strahovatel save(@RequestBody Strahovatel jsonString) {
        System.out.println("savestrahovatel jsonString:"+jsonString);
        return strahovatelRepository.save(jsonString);
    }

    @RequestMapping(value = "/updatestrahovatel/{id}", method = RequestMethod.POST)
    public @ResponseBody Strahovatel update(@PathVariable Long id, @RequestBody Strahovatel strahovatel) {
        System.out.println("/api/updatestrahovatel/{id}: "+id+", strahovatel:"+strahovatel);
        Strahovatel existing = strahovatelRepository.findById(id).orElse(null);
        if(existing != null) strahovatel.setIdstrah(existing.getIdstrah());
        return strahovatelRepository.save(strahovatel);
    }

    @RequestMapping(value = "/savedogovor", method = RequestMethod.POST)
    public @ResponseBody Dogovor save(@RequestBody Dogovor jsonString) {
        System.out.println("savedogovor jsonString:"+jsonString);
        return dogovorRepository.save(jsonString);
    }

    @RequestMapping(value = "/updatedogovor/{id}", method = RequestMethod.POST)
    public @ResponseBody Dogovor update(@PathVariable Long id, @RequestBody Dogovor dogovor) {
        System.out.println("/api/updatedogovor/{id}: "+id+", dogovor:"+dogovor);
        Dogovor existing = dogovorRepository.findById(id).orElse(null);
        if(existing != null) dogovor.setIddog(existing.getIddog());
        return dogovorRepository.save(dogovor);
    }
}
