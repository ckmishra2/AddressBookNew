package basics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

class PersonDetails1 {
	String firstName, lastName, city, state, email, dateOfBirth, phnNumber, zip;

	/*
	 * creating a Contacts in Address Book with first and last names, address,city,
	 * state, zip, phone number and email...
	 */
	PersonDetails1(String first, String Last, String dob, String phnnum, String yourcity, String yourState,
			String pincode, String emal) {
		this.firstName = first;
		this.lastName = Last;
		this.dateOfBirth = dob;
		this.phnNumber = phnnum;
		this.city = yourcity;
		this.state = yourState;
		this.zip = pincode;
		this.email = emal;
	}

	public PersonDetails1() {
	}

	void PrintDetails() {
		System.out.println("Printing Details:");
		System.out.println("First Name is: " + this.firstName);
		System.out.println("Last Name is: " + this.lastName);
		System.out.println("Date of birth is: " + this.dateOfBirth);
		System.out.println("Phone number is: " + this.phnNumber);
		System.out.println("City name is: " + this.city);
		System.out.println("Enter State Name " + this.state);
		System.out.println("State Name is: " + this.city);
		System.out.println("Zip is: " + this.zip);
		System.out.println("email is " + this.email);
	}

//ask to user for person details
	void AskDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter First Name ");
		this.firstName = sc.next();
		System.out.println("Enter Last Name ");
		this.lastName = sc.next();
		System.out.println("Entre Date of Birth");
		this.dateOfBirth = sc.next();
		System.out.println("Enter Phone Number ");
		this.phnNumber = sc.next();
		System.out.println("Enter City Name");
		this.city = sc.next();
		System.out.println("Enter State Name ");
		this.state = sc.next();
		System.out.println("Enter Zip");
		this.zip = sc.next();
		System.out.println("Enter email ");
		this.email = sc.next();
	}

}

public class AddressBookwithFileIO {
	List<PersonDetails1> linkedListPersonAddress;
	List<String> firstNameList;
	int sizeOfAB;
	String nameAB;

	void fillDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of addresses to be stored in " + this.nameAB);
		int num = sc.nextInt();
		this.sizeOfAB = num;
		this.linkedListPersonAddress = new LinkedList<PersonDetails1>();
		int i = 0;
		while (i < num) {
			PersonDetails1 person = new PersonDetails1();
			person.AskDetails();
			List<String> NameFound = new ArrayList<>();
			if (!linkedListPersonAddress.isEmpty()) { // using predicate function from java streams for no duplicate
														// entry
				Predicate<String> isSameFirstName = firstname -> firstname.equals(person.firstName);
				linkedListPersonAddress.stream().forEach(LinkedListPersonAddress -> {
					if (isSameFirstName.test(LinkedListPersonAddress.firstName)) {
						System.out.println("This name exists in Address book");
						NameFound.add(LinkedListPersonAddress.firstName);
					}
				});
			}
			if (firstNameList == null) {
				firstNameList = new LinkedList<String>();
			}
			if (NameFound == null || NameFound.isEmpty()) {
				i++;
				linkedListPersonAddress.add(person);
				firstNameList.add(person.firstName);
				NameFound.clear();
			}

		}
	}

	void SearchByState(String state) {// using predicate function from java streams for person belongs to state
		List<String> NameFound = new ArrayList<String>();
		Predicate<String> isSameState = pstate -> pstate.equals(state);
		linkedListPersonAddress.stream().forEach(LinkedListPersonAddress -> {
			if (isSameState.test(LinkedListPersonAddress.state)) {
				NameFound.add(LinkedListPersonAddress.firstName);
			}
		});
		System.out.println("Persons with same state name are" + NameFound);
	}

	void SearchByCity(String city) { // using predicate function from java streams for person belongs to city
		List<String> NameFound = new ArrayList<String>();
		Predicate<String> isSameCity = pCity -> pCity.equals(city);
		linkedListPersonAddress.stream().forEach(LinkedListPersonAddress -> {
			if (isSameCity.test(LinkedListPersonAddress.city)) {
				NameFound.add(LinkedListPersonAddress.firstName);
			}
		});
		System.out.println("Persons with same city are" + NameFound);

	}

//delete the person details 
	void deleteAddress() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the first name of Person to be removed");
		boolean bFound = false;
		while (!bFound) {
			String readName = sc.next();
			for (int i = 0; i < this.linkedListPersonAddress.size(); i++) {
				if (this.linkedListPersonAddress.get(i).firstName.equals(readName)) {
					bFound = true;
					linkedListPersonAddress.remove(i);
					break;
				}
			}
		}

	}// for print details

	void printDetails() {
		System.out.println(this.nameAB + " address book has " + this.linkedListPersonAddress.size() + " addresses");
		for (int i = 0; i < this.linkedListPersonAddress.size(); i++) {
			this.linkedListPersonAddress.get(i).PrintDetails();
		}
	}
/*Write the Address Book with Persons Contact into a
 * File 
 * */
	void writeTextFileContent(String fileURL) throws IOException {

		FileWriter fileWriterObject = new FileWriter(fileURL, true);
		BufferedWriter out = new BufferedWriter(fileWriterObject);
		for (int i = 0; i < this.linkedListPersonAddress.size(); i++) {
			PersonDetails1 temp = linkedListPersonAddress.get(i);
			out.write(temp.firstName + " ");
			out.write(temp.lastName + " ");
			out.write(temp.dateOfBirth + " ");
			out.write(temp.phnNumber + " ");
			out.write(temp.city + " ");
			out.write(temp.state + " ");
			out.write(temp.zip + " ");
			out.write(temp.email + " ");
		}

		out.close();
	}
	/*Read the Address Book with Persons Contact into a
	 * File 
	 * */
	void readTextFileContent(String fileURL) throws IOException {
		int dataPositionPresent;

		FileReader fileReaderObject = new FileReader(fileURL);

		while ((dataPositionPresent = fileReaderObject.read()) != -1) {
			System.out.print((char) dataPositionPresent);
			if ((char) dataPositionPresent == ' ')
				System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		LinkedList<AddressBookwithFileIO> linkedListAB = new LinkedList<AddressBookwithFileIO>();
		AddressBookwithFileIO addressbookobject = new AddressBookwithFileIO();
		addressbookobject.nameAB = "Address book 1";
		addressbookobject.linkedListPersonAddress = new LinkedList<PersonDetails1>();
		PersonDetails1 person1 = new PersonDetails1("Person1FN", "Person1LN", "20122010", "90887", "Mumbai",
				"Maharashtra", "400001", "p1@gmail.com");
		addressbookobject.linkedListPersonAddress.add(person1);
		PersonDetails1 person2 = new PersonDetails1("Person2FN", "Person2LN", "20122010", "90887", "Pune", "Maharashtra",
				"400011", "p2@gmail.com");
		addressbookobject.linkedListPersonAddress.add(person2);
		PersonDetails1 person3 = new PersonDetails1("Person3FN", "Person3LN", "20122010", "90887", "Mumbai",
				"Maharashtra", "400001", "p3@gmail.com");
		addressbookobject.linkedListPersonAddress.add(person3);
		PersonDetails1 person4 = new PersonDetails1("Person4FN", "Person4LN", "20122010", "90887", "Pune", "Maharashtra",
				"400011", "p4@gmail.com");
		addressbookobject.linkedListPersonAddress.add(person4);
		PersonDetails1 person5 = new PersonDetails1("Person5FN", "Person5LN", "20122010", "90887", "Mumbai",
				"Maharashtra", "400001", "p5@gmail.com");
		addressbookobject.linkedListPersonAddress.add(person5);

		// AB1.printDetails();
		String fileURL = "D:\\Chandrakala\\EclipseWorkspace\\address\\src\\main\\java\\basics\\AddressBook.txt";
		try {
			addressbookobject.writeTextFileContent(fileURL);
			addressbookobject.readTextFileContent(fileURL);
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}