package yaluhn.javaquebac.Sprin1.service;

import yaluhn.javaquebac.Sprin1.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> findAll();
    Cliente findById(Long id);
    void insert(Cliente cliente);
    void update(Long id, Cliente cliente);
    void delete(Long id);
}
