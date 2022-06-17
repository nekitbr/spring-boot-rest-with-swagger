package com.imed.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.imed.api.model.Matricula;
import com.imed.api.repository.FruteiraRepository;

@RestController
@RequestMapping("/api")
public class MatriculaController {

	@Autowired
	private FruteiraRepository matriculaRepository;

	@GetMapping("/matricula")
	public List<Matricula> get() {
		return matriculaRepository.findAll();
	}

	@GetMapping("/matricula/{id}")
    public Matricula getById(@PathVariable Long id) {
		System.out.println(id);
		Optional<Matricula> matriculaOptional = matriculaRepository.findById(id);

        if (!matriculaOptional.isPresent()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return matriculaOptional.get();
    }

	@PostMapping("/matricula")
	@ResponseStatus(HttpStatus.CREATED)
	public Matricula insert(@RequestBody Matricula matricula) {
		return matriculaRepository.save(matricula);
	}

	@DeleteMapping("/matricula/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        Optional<Matricula> matriculaOptional = matriculaRepository.findById(id);

        if (!matriculaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        matriculaRepository.delete(matriculaOptional.get());
    }

	@PutMapping("/matricula/{id}")
	public Matricula replaceMatriculaById(@PathVariable Long id, @RequestBody Matricula newMatricula) {
		Optional<Matricula> matriculaOptional = matriculaRepository.findById(id);

		if (!matriculaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

		newMatricula.setId(id);

		return matriculaRepository.save(newMatricula);
	}

}
