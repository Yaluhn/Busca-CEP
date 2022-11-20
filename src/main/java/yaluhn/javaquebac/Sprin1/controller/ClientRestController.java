package yaluhn.javaquebac.Sprin1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yaluhn.javaquebac.Sprin1.model.Cliente;
import yaluhn.javaquebac.Sprin1.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClientRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente) {
        clienteService.insert(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping
    public ResponseEntity<Cliente> update(@RequestBody Long id, Cliente cliente) {
        clienteService.update(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        clienteService.delete(id);
        return ResponseEntity.ok(cliente);
    }


}
