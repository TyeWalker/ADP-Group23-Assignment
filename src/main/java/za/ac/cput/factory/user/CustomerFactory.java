package za.ac.cput.factory.user;

import za.ac.cput.entity.user.Customer;
import za.ac.cput.util.GenericHelper;

/*
 *  Name: Tye Walker
 *  Student Number: 218338562
 *  Group: 23
 *
 *  CustomerService
 */

public class CustomerFactory {

    public static Customer build(String firstName, String lastName, String contactNumber, String email) {
        String customerID = GenericHelper.generateID();

        if (!GenericHelper.validEmail(email)) {
            System.out.println("Email invalid");
            return null;
        }
        if (firstName.isEmpty() || lastName.isEmpty() || contactNumber.isEmpty()) {
            System.out.println("Missing values..");
            return null;
        }

        return new Customer.Builder().customerID(customerID).firstName(firstName).lastName(lastName).contactNumber(contactNumber).email(email).builder();
    }

}
