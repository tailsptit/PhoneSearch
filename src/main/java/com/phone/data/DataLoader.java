package com.phone.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.phone.model.Customer;
import com.phone.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {
    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());

    @Value("classpath:data/customers.json")
    private Resource customersJsonFile;

    @Autowired
    private CustomerRepository customerService;

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            try {
                int num = Integer.parseInt(args[0]);
                int numberOfCustomers = (int) Math.pow(10, num);
                String prefixPhone = "84123456789".substring(0, 11 - num);
                String prefixName = "TaiLS_" + prefixPhone;
                genCustomers(num, prefixName, prefixPhone);
//        List<Customer> customers = this.loadCustomersFromFile();
//        customers.forEach(customerService::create);
                logger.info("Completed to insert " + numberOfCustomers + " customers with phone number" +
                        " from " + prefixPhone + String.format("%0" + num + "d", 0) +
                        " to " + prefixPhone + String.format("%0" + num + "d", numberOfCustomers - 1) + " to ElasticSearch database");
            } catch (Exception e){
                logger.info("Parameter should be an integer with range from 1 to 11");
            }
        }
    }

    private List<Customer> loadCustomersFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, JsonCustomer.class);
        List<JsonCustomer> customers = objectMapper.readValue(this.customersJsonFile.getInputStream(), collectionType);
        return customers.stream().map(this::from).collect(Collectors.toList());
    }

    private void genCustomers(int num, String prefixName, String prefixPhone) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, JsonCustomer.class);
        Customer customer = new Customer();
        String suffixPhone;
        for (int i = 0; i < Math.pow(10, num); i++) {
            suffixPhone = String.format("%0" + num + "d", i);
            customer.setName(prefixName + suffixPhone);
            customer.setPhone(prefixPhone + suffixPhone);
            customerService.create(customer);
        }
    }

    private Customer from(JsonCustomer jsonCustomer) {
        return new Customer(jsonCustomer.getName(), jsonCustomer.getPhone());
    }
}