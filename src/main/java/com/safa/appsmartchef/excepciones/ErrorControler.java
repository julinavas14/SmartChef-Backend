package com.safa.appsmartchef.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorControler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> controladorErrores(MethodArgumentNotValidException exception) {

        Map<String, String> mapaErrores = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            mapaErrores.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(mapaErrores, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ElementoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarRecursoNoEncontrado(ElementoNoEncontradoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EliminarNoExistenteException.class)
    public ResponseEntity<Map<String, String>> manejarExcepcionEliminarnoExistente(EliminarNoExistenteException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(YaEsFavoritaException.class)
    public ResponseEntity<Map<String, String>> manejarYaFavorita(YaEsFavoritaException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ListaCompraVaciaException.class)
    public ResponseEntity<Map<String, String>> manejarListaCompraVacia(ListaCompraVaciaException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecetaNoEncontradaException.class)
    public ResponseEntity<Map<String, String>> manejarRecetaNoEncontrada(RecetaNoEncontradaException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HistorialCocinaVacioException.class)
    public ResponseEntity<Object> handleHistorialVacio(HistorialCocinaVacioException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(IngredienteNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarIngredienteNoEncontrado(IngredienteNoEncontradoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHayUsuariosConFavoritosException.class)
    public ResponseEntity<Map<String, Object>> handleNoHayUsuariosConFavoritos(NoHayUsuariosConFavoritosException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(RecetaYaExisteException.class)
    public ResponseEntity<Map<String, String>> manejarRecetaYaExiste(RecetaYaExisteException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsuarioYaExisteException.class)
    public ResponseEntity<Map<String, String>> manejarUsuarioYaExiste(UsuarioYaExisteException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}



