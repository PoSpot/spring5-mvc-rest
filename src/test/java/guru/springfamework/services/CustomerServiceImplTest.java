package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

class CustomerServiceImplTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "Po";
    public static final String LAST_NAME = "Po Po";

    @Mock
    CustomerRepository customerRepository;

//    @InjectMocks No, cos we want to pass the Mapper, so initMock + constructor
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getCustomers() {
        // given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        Customer customer2 = new Customer();
        customer2.setId(2L);

        given(customerRepository.findAll()).willReturn(List.of(customer, customer2));

        // when
        List<CustomerDTO> list = customerService.getCustomers();

        // then
        assertThat(list, hasSize(2));
    }

    @Test
    void findByFirstName() {
        // given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        given(customerRepository.findByFirstName(anyString())).willReturn(customer);

        // when
        CustomerDTO dto = customerService.findByFirstName(FIRST_NAME);

        // then
        assertEquals(ID, dto.getId());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
    }
}