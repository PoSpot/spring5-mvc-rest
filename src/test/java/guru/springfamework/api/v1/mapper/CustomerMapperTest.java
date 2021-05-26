package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "Po";
    public static final String LAST_NAME = "Po Po";
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        CustomerDTO dto = customerMapper.customerToCustomerDTO(customer);

        assertEquals(ID, dto.getId());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
    }
}