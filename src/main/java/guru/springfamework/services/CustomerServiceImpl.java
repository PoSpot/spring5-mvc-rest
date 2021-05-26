package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO dto = customerMapper.customerToCustomerDTO(customer);
                    dto.setCustomerUri("/api/v1/customers/" + dto.getFirstName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findByFirstName(String firstName) {
        return customerMapper.customerToCustomerDTO(customerRepository.findByFirstName(firstName)); // TODO exc handling here
    }
}
