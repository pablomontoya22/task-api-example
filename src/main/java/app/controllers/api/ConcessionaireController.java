package app.controllers.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Concessionaire;
import app.helpers.Messages;
import app.helpers.Response;
import app.helpers.Utilities;
import app.repositories.ConcessionaireRepository;

@RestController
@RequestMapping("/api/concessionaire")
public class ConcessionaireController extends Utilities {

	@Autowired
	private ConcessionaireRepository r;

	@GetMapping(value = "/{id}")
	private Response get(@PathVariable("id") Long id) {
		try {
			return id > 0 ? new Response(wrap(r.findById(id).get()))
					: new Response((List<Concessionaire>) r.findAll());
		} catch (Exception e) {
			return new Response(false, Messages.ERROR.val());
		}
	}

	@PostMapping
	private Response save(@RequestBody Concessionaire entity) {
		try {
			return new Response(wrap(r.save(entity)));
		} catch (Exception e) {
			return new Response(false, Messages.ERROR.val());
		}
	}

	@PutMapping
	private Response update(@RequestBody Concessionaire entity) {
		try {
			r.save(entity);
			return new Response(true, Messages.UPDATED.val());
		} catch (Exception e) {
			return new Response(false, Messages.ERROR.val());
		}
	}

	@DeleteMapping
	private Response delete(@RequestBody Map<String, Long> data) {
		try {
			r.deleteById(data.get("id"));
			return new Response(true, Messages.DELETED.val());
		} catch (Exception e) {
			return new Response(false, Messages.ERROR.val());
		}
	}

}
