package addressbook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

class PersonDetails {
	String firstName, lastName, city, state, email;
	int dateOfBirth, phnNumber, zip;

//creating a Contacts in Address Book with first and last names, address,city, state, zip, phone number and email...
	PersonDetails(String first, String Last, int dob, int phnnum, String yourcity, String yourState, int pincode,
			String emal) {
		this.firstName = first;
		this.lastName = Last;
		this.dateOfBirth = dob;
		this.phnNumber = phnnum;
		this.city = yourcity;
		this.state = yourState;
		this.zip = pincode;
		this.email = emal;
	}

	public PersonDetails() {
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
		this.dateOfBirth = sc.nextInt();
		System.out.println("Enter Phone Number ");
		this.phnNumber = sc.nextInt();
		System.out.println("Enter City Name");
		this.city = sc.next();
		System.out.println("Enter State Name ");
		this.state = sc.next();
		System.out.println("Enter Zip");
		this.zip = sc.nextInt();
		System.out.println("Enter email ");
		this.email = sc.next();
	}

}

public class AddressBook {
	List<PersonDetails> LLPA;
	List<String> firstNameList;
	int sizeOfAB;
	String nameAB;

	void fillDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of addresses to be stored in " + this.nameAB);
		int num = sc.nextInt();
		this.sizeOfAB = num;
		this.LLPA = new LinkedList<PersonDetails>();
		int i = 0;
		while (i < num) {
			PersonDetails p = new PersonDetails();
			p.AskDetails();
			List<String> NameFound = new ArrayList<>();
			if (!LLPA.isEmpty()) { // using predicate function from java streams for no duplicate entry
				Predicate<String> isSameFirstName = firstname -> firstname.equals(p.firstName);
				LLPA.stream().forEach(LLPA -> {
					if (isSameFirstName.test(LLPA.firstName)) {
						System.out.println("This name exists in Address book");
						NameFound.add(LLPA.firstName);
					}
				});
			}
			if (firstNameList == null) {
				firstNameList = new LinkedList<String>();
			}
			if (NameFound == null || NameFound.isEmpty()) {
				i++;
				LLPA.add(p);
				firstNameList.add(p.firstName);
				NameFound.clear();
			}

		}
	}

	void SearchByState(String state) {// using predicate function from java streams for person belongs to state
		List<String> NameFound = new ArrayList<String>();
		Predicate<String> isSameState = pstate -> pstate.equals(state);
		LLPA.stream().forEach(LLPA -> {
			if (isSameState.test(LLPA.state)) {
				NameFound.add(LLPA.firstName);
			}
		});
		System.out.println("Persons with same state name are" + NameFound);
	}

	void SearchByCity(String city) { // using predicate function from java streams for person belongs to city
		List<String> NameFound = new ArrayList<String>();
		Predicate<String> isSameCity = pCity -> pCity.equals(city);
		LLPA.stream().forEach(LLPA -> {
			if (isSameCity.test(LLPA.city)) {
				NameFound.add(LLPA.firstName);
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
			for (int i = 0; i < this.LLPA.size(); i++) {
				if (this.LLPA.get(i).firstName.equals(readName)) {
					bFound = true;
					LLPA.remove(i);
					break;
				}
			}
		}

	}// for print details

	void printDetails() {
		System.out.println(this.nameAB + " address book has " + this.LLPA.size() + " addresses");
		for (int i = 0; i < this.LLPA.size(); i++) {
			this.LLPA.get(i).PrintDetails();
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		LinkedList<AddressBook> LLAB = new LinkedList<AddressBook>();

		System.out.println("Enter number of Address books to be created");
		Scanner sc = new Scanner(System.in);
		int numAB = sc.nextInt();
		int i = 0;
		while (i < numAB) {
			String readName = null;
			Boolean isUnique = false;
			// for each address book has a unique name
			while (!isUnique) {
				System.out.println("Enter name of Address book");
				readName = sc.next();
				isUnique = true;
				for (int j = 0; j < LLAB.size(); j++) {

					if (LLAB.get(j).nameAB.equals(readName)) {

						if (LLAB.get(j).nameAB.equals(readName)) {

							isUnique = false;
							System.out.println(readName + " Address book exists. Enter unique name");
						} else if (j == LLAB.size() - 1)
							break;

					}
				}
				AddressBook AB = new AddressBook();
				AB.nameAB = readName;
				AB.nameAB = readName;
				AB.fillDetails();
				AB.printDetails();
				LLAB.add(AB);
				i++;
			}

			boolean bMod = false;
			while (!bMod) {
				System.out.println("Do you want to modify Address books");
				bMod = !sc.nextBoolean();
				System.out.println("Enter the name of Address book to be modified");
				String AddBookToBeMod = sc.next();
				for (int k = 0; k < LLAB.size(); k++) {
					if (LLAB.get(k).nameAB.equals(AddBookToBeMod)) {
						LLAB.get(k).deleteAddress();
						break;
					}
				}
			}
			for (int k = 0; k < LLAB.size(); k++) {
				System.out.println("Address book========" + LLAB.get(k).nameAB);
				LLAB.get(k).printDetails();
			}
			// Searching by state or city
			String city, state;
			System.out.println("Enter 1 to search by city./n 2 to search by state");
			int choice = sc.nextInt();
			if (choice == 1) {
				System.out.println("Enter city name");
				city = sc.next();
				for (AddressBook addressBook : LLAB) {
					addressBook.SearchByCity(city);
				}
			} else if (choice == 2) {
				System.out.println("Enter state name");
				state = sc.next();
				for (AddressBook addressBook : LLAB) {
					addressBook.SearchByState(state);
				}
			}
		}
	}
}
