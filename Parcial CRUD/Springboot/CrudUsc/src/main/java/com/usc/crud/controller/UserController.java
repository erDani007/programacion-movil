package com.usc.crud.controller;

import com.usc.crud.model.Empleado;
import com.usc.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private  UserService service;


    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> filtrar(@PathVariable Empleado empleado){

        return ResponseEntity.ok(service.finByUser(empleado));

    }


    // create employee rest api
    @PostMapping("/guardar")
    public Empleado createEmployee(@RequestBody Empleado empleado) {
        return service.guardarUser(empleado);
    }

    @GetMapping("/consultarAll")
    public ResponseEntity<?> consultarByUser(){

        System.out.println(ResponseEntity.ok(service.buscarTdoso()));
        return ResponseEntity.ok(service.buscarTdoso()
        );
    }

    // update employee rest api

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        Optional<Empleado> empleadoActualizadoOptional = service.finByUser(empleado);
        if (empleadoActualizadoOptional.isPresent()) {
            Empleado empleadoActualizado = empleadoActualizadoOptional.get();
            empleadoActualizado.setUser(empleado.getNombre());
            empleadoActualizado.setPassword(empleado.getPassword());
            empleadoActualizado.setEmail(empleado.getEmail());
            service.guardarUser(empleadoActualizado);
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // delete employee rest api
    @DeleteMapping("/nombre/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
       String msj = service.eliminarUser(id);
        return ResponseEntity.ok(msj);
    }

}
