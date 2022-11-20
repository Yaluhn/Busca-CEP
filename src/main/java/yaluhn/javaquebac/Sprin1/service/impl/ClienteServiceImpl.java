package yaluhn.javaquebac.Sprin1.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yaluhn.javaquebac.Sprin1.model.Cliente;
import yaluhn.javaquebac.Sprin1.model.ClienteRepository;
import yaluhn.javaquebac.Sprin1.model.Endereco;
import yaluhn.javaquebac.Sprin1.model.EnderecoRepository;
import yaluhn.javaquebac.Sprin1.service.ClienteService;
import yaluhn.javaquebac.Sprin1.service.ViaCepService;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Override
    public Iterable<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void insert(Cliente cliente) {
        validandoCliente(cliente);
    }

    @Override
    public void update(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            validandoCliente(cliente);
        }
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    private void validandoCliente(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.buscaEnderecoPor(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
