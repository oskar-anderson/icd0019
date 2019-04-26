package poly.customer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerRepository {

    ArrayList<AbstractCustomer> customers = new ArrayList<>();
    private ArrayList<String[]> parts;

    public CustomerRepository() {
        try {
            Path path = Paths.get("src/poly/customer/data.txt");
            List<String> lines = Files.readAllLines(path);

            parts = new ArrayList<>();
            for (String line : lines) {
                if (!line.isEmpty()) {
                    parts.add(line.split(";"));
                }
            }

            for (String[] part : parts) {
                String customerType = part[0];
                String customerId = part[1];
                String customerName = part[2];
                Integer bonusPoints = Integer.valueOf(part[3]);
                LocalDate lastOrderDate;
                if (part.length == 5) {
                    lastOrderDate = LocalDate.parse(part[4]);
                } else {
                    lastOrderDate = null;
                }


                if (customerType.equals("REGULAR")) {
                    RegularCustomer customer = new RegularCustomer(customerId, customerName, bonusPoints, lastOrderDate);
                    customers.add(customer);
                } else if (customerType.equals("GOLD")) {
                    GoldCustomer customer = new GoldCustomer(customerId, customerName,  bonusPoints, lastOrderDate);
                    customers.add(customer);
                }

            }   // is everything closed automatically?

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    public AbstractCustomer getCustomerById(String id) {
        for (AbstractCustomer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        throw new IllegalArgumentException("No matching id found!");
    }


    public void save(AbstractCustomer customer) {
        Path path = Paths.get("src/poly/customer/data.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {  // what does "try () {" do?
            List<String> lines = Files.readAllLines(path);
            parts = new ArrayList<>();
            for (String line : lines) {
                if (!line.isEmpty()) {
                    parts.add(line.split(";"));
                }
            }


            for (int i = 0; i < parts.size(); i++) {                // No built in enumerate?
                if (parts.get(i)[1].equals(customer.getId())) {     // why no parts[i][1]?
                    String[] replacement = new String[1];
                    replacement[0] = customer.toString();

                    parts.set(i, replacement);
                }
            }

            for (String[] part : parts) {
                String line = Arrays.toString(part);
                line = line.replace(", ", ";");  // replace > replaceAll
                line = line.replace("[", "");
                line = line.replace("]", "");
                writer.write(line + "\n");
            }

            writer.close();  // why is this reduntant?

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
