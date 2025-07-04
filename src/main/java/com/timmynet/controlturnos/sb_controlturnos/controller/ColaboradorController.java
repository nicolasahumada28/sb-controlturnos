package com.timmynet.controlturnos.sb_controlturnos.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timmynet.controlturnos.sb_controlturnos.model.entity.Colaborador;
import com.timmynet.controlturnos.sb_controlturnos.service.ColaboradorService;
import com.timmynet.controlturnos.sb_controlturnos.util.LogIcons;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/colaborador")
@Tag(name = "Colaborador", description = "Operaciones relacionadas con colaboradores")
public class ColaboradorController {

    private static final Logger logger = LoggerFactory.getLogger(ColaboradorController.class);

    @Autowired
    public ColaboradorService service;

    @Autowired
    @Qualifier("prettyObjectMapper")
    private ObjectMapper logObjectMapper;

    @GetMapping
    @Operation(summary = "Listar todos los colaboradores con paginación y ordenamiento",
            description = "Permite listar todos los colaboradores con opciones de paginación y ordenamiento.")
    public ResponseEntity<?> listarTodos(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {

        logger.info(LogIcons.INFO+"Ejecución de método listarTodos con parámetros: page={}, size={}, sortBy={}, order={}", page, size,
                sortBy, order);

        Sort sort = Sort.by(Sort.Direction.fromString(order != null ? order : "ASC"), sortBy != null ? sortBy : "id");
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10, sort);
        Page<Colaborador> colaboradores = service.listarColaboradores(pageable);

        logger.info(LogIcons.ONLINE+"Número de colaboradores encontrados: {}", colaboradores.getTotalElements());
        logger.info(LogIcons.PACKAGE+"Página actual: {}, Tamaño de página: {}, Orden: {}", colaboradores.getNumber(),
                colaboradores.getSize(), colaboradores.getSort());

        if (colaboradores.isEmpty()) {
            logger.warn(LogIcons.WARNING+"No se encontraron colaboradores");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron colaboradores");

        }
        try {
            String jsonResponse = logObjectMapper.writeValueAsString(colaboradores);
            logger.info(LogIcons.CHECK+"Respuesta JSON:\n{}", jsonResponse);
        } catch (JsonProcessingException e) {
            logger.error(LogIcons.ERROR+"Error al procesar la respuesta JSON: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la respuesta JSON");
        }
        logger.info(LogIcons.CHECK+"Método listarTodos finalizado con éxito");
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/all")
    @Operation(summary = "Listar todos los colaboradores sin paginación",
            description = "Permite listar todos los colaboradores sin paginación ni ordenamiento.")
    public ResponseEntity<?> listarTodosSinPaginacion() {
        logger.info(LogIcons.INFO + "Ejecución de método listarTodosSinPaginacion");

        try {
            var lista = service.listarColaboradoresSinPag(); // Este método debe devolver List<Colaborador>
            if (lista.isEmpty()) {
                logger.warn(LogIcons.WARNING + "No se encontraron colaboradores");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron colaboradores");
            }

            String jsonResponse = logObjectMapper.writeValueAsString(lista);
            logger.info(LogIcons.CHECK + "Respuesta JSON:\n{}", jsonResponse);
            logger.info(LogIcons.CHECK + "Método listarTodosSinPaginación finalizado con éxito");
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            logger.error(LogIcons.ERROR + "Error al procesar respuesta JSON: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener colaboradores");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener colaborador por ID",
            description = "Permite obtener un colaborador específico por su ID.")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {

        logger.info(LogIcons.INFO+"Ejecución de método obtenerPorId con ID: {}", id);

        Optional<Colaborador> colaborador = service.colaboradorPorId(id);

        if (colaborador.isPresent()) {
            logger.info(LogIcons.CHECK+"Respuesta recibida: "+colaborador.get());
            return ResponseEntity.ok(colaborador.get());
        } else {
            logger.warn(LogIcons.WARNING+"Colaborador no encontrado con ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador no encontrado con el ID: " + id);
        }
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo colaborador",
            description = "Permite crear un nuevo colaborador con los datos proporcionados.")
    public ResponseEntity<?> crearColaborador(@RequestBody Colaborador colaborador) {
        logger.info(LogIcons.INFO+"Ejecución de método crearColaborador con datos: {}", colaborador);
        return ResponseEntity.ok(service.guardarColaborador(colaborador));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar colaborador por ID",
            description = "Permite actualizar los datos de un colaborador específico por su ID.")
    public ResponseEntity<?> actualizaColaborador(@PathVariable Long id, @RequestBody Colaborador nuevColaborador) {
        Optional<Colaborador> colaborador = service.colaboradorPorId(id);
        logger.info(LogIcons.INFO+"Ejecución de método actualizaColaborador con ID: {}", id);
        if (colaborador.isPresent()) {
            Colaborador col = colaborador.get();
            col.setNombre(nuevColaborador.getNombre());
            col.setEmail(nuevColaborador.getEmail());
            col.setRut(nuevColaborador.getRut());
            col.setEdad(nuevColaborador.getEdad());
            col.setTelefono(nuevColaborador.getTelefono());
            col.setInicioContrato(nuevColaborador.getInicioContrato());
            col.setSupervisor(nuevColaborador.isSupervisor());
            col.setActivo(nuevColaborador.isActivo());

            logger.info(LogIcons.CHECK+"Colaborador actualizado: {}", col);
            logger.info(LogIcons.CHECK+"Método actualizaColaborador finalizado con éxito");
            return ResponseEntity.ok(service.guardarColaborador(col));
        } else {
        logger.warn(LogIcons.WARNING+"Colaborador no encontrado con ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Colaborador no encontrado con el ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar colaborador por ID",
            description = "Permite eliminar un colaborador específico por su ID.")
    public ResponseEntity<?> borrarColaborador(@PathVariable Long id) {
        logger.info(LogIcons.INFO+"Ejecución de método borrarColaborador con ID: {}", id);
        if (service.colaboradorPorId(id).isPresent()) {
            service.eliminarColaborador(id);
            logger.info(LogIcons.BLOCKED+"Colaborador con ID: {} eliminado correctamente", id);
            logger.info(LogIcons.CHECK+"Método borrarColaborador finalizado con éxito");
            return ResponseEntity.noContent().build();
        }
        logger.warn(LogIcons.WARNING+"Colaborador no encontrado con ID: {}", id);
        logger.info(LogIcons.CHECK+"Método borrarColaborador finalizado sin éxito");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador no encontrado con el ID: " + id);
    }

}
