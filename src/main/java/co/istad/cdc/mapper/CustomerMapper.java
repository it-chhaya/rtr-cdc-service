package co.istad.cdc.mapper;

import co.istad.cdc.client.dto.CustomerSync;
import co.istad.cdc.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerSync toCustomerSync(Customer customer);

}
