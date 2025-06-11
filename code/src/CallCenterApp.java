import models.TicketModel;
import repository.TicketRepository;
import services.*;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class CallCenterApp {
    private static TicketRepository repository = new TicketRepository();
    private static CreateService createService = new CreateService(repository);
    private static ReadService readService = new ReadService(repository);
    private static UpdateService updateService = new UpdateService(repository);
    private static DeleteService deleteService = new DeleteService(repository);
    private static SearchService searchService = new SearchService(repository);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("XXX Solutions - Call Center Ticket System");
        while (true) {
            printMenu();
            int choice = getIntInput("Select option: ");
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1: createTicket(); break;
                case 2: viewAllTickets(); break;
                case 3: viewTicketDetails(); break;
                case 4: updateTicket(); break;
                case 5: deleteTicket(); break;
                case 6: searchTickets(); break;
                case 7:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Create New Ticket");
        System.out.println("2. View All Tickets");
        System.out.println("3. View Ticket Details");
        System.out.println("4. Update Ticket");
        System.out.println("5. Delete Ticket");
        System.out.println("6. Search Tickets");
        System.out.println("7. Exit System");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();  // Clear invalid input
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    private static void createTicket() {
        System.out.println("\n--- CREATE NEW TICKET ---");
        System.out.print("Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Contact Info: ");
        String contact = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        TicketModel newTicket = new TicketModel(
                repository.getNextId(),
                name,
                contact,
                category,
                description
        );

        createService.create(newTicket);
        System.out.println("\nTicket created successfully! ID: " + newTicket.getId());
    }

    private static void viewAllTickets() {
        List<TicketModel> tickets = readService.readAll();
        if (tickets.isEmpty()) {
            System.out.println("\nNo tickets found.");
            return;
        }

        System.out.println("\n--- ALL TICKETS ---");
        System.out.println("ID\tCustomer\t\tCategory\tStatus");
        System.out.println("------------------------------------------------");
        for (TicketModel ticket : tickets) {
            System.out.printf("%d\t%-20s\t%-15s\t%s%n",
                    ticket.getId(),
                    ticket.getCustomerName(),
                    ticket.getCategory(),
                    ticket.getStatus());
        }
    }

    private static void viewTicketDetails() {
        int id = getIntInput("\nEnter Ticket ID: ");
        scanner.nextLine();  // Consume newline

        TicketModel ticket = readService.read(id);
        if (ticket != null) {
            System.out.println("\n--- TICKET DETAILS ---");
            System.out.println(ticket);
        } else {
            System.out.println("Ticket not found!");
        }
    }

    private static void updateTicket() {
        int id = getIntInput("\nEnter Ticket ID to update: ");
        scanner.nextLine();  // Consume newline

        TicketModel ticket = readService.read(id);
        if (ticket == null) {
            System.out.println("Ticket not found!");
            return;
        }

        System.out.println("\nCurrent Status: " + ticket.getStatus());
        System.out.print("New Status (leave blank to keep current): ");
        String status = scanner.nextLine();

        System.out.println("Current Priority: " + ticket.getPriority());
        System.out.print("New Priority (leave blank to keep current): ");
        String priority = scanner.nextLine();

        System.out.print("Additional Comments: ");
        String comment = scanner.nextLine();

        // Update the ticket
        if (!status.isEmpty()) ticket.setStatus(status);
        if (!priority.isEmpty()) ticket.setPriority(priority);
        if (!comment.isEmpty()) ticket.addComment(comment);

        updateService.update(ticket);
        System.out.println("\nTicket updated successfully!");
    }

    private static void deleteTicket() {
        int id = getIntInput("\nEnter Ticket ID to delete: ");
        scanner.nextLine();  // Consume newline

        if (deleteService.delete(id)) {
            System.out.println("Ticket deleted successfully!");
        } else {
            System.out.println("Ticket not found!");
        }
    }

    private static void searchTickets() {
        System.out.println("\n--- SEARCH TICKETS ---");
        System.out.print("Customer Name (leave blank to skip): ");
        String name = scanner.nextLine();

        System.out.print("Category (leave blank to skip): ");
        String category = scanner.nextLine();

        System.out.print("Status (leave blank to skip): ");
        String status = scanner.nextLine();

        // Build search predicate
        Predicate<TicketModel> predicate = ticket ->
                (name.isEmpty() || ticket.getCustomerName().equalsIgnoreCase(name)) &&
                        (category.isEmpty() || ticket.getCategory().equalsIgnoreCase(category)) &&
                        (status.isEmpty() || ticket.getStatus().equalsIgnoreCase(status));

        List<TicketModel> results = searchService.search(predicate);

        if (results.isEmpty()) {
            System.out.println("\nNo matching tickets found.");
            return;
        }

        System.out.println("\n--- SEARCH RESULTS ---");
        System.out.println("ID\tCustomer\t\tCategory\tStatus");
        System.out.println("------------------------------------------------");
        for (TicketModel ticket : results) {
            System.out.printf("%d\t%-20s\t%-15s\t%s%n",
                    ticket.getId(),
                    ticket.getCustomerName(),
                    ticket.getCategory(),
                    ticket.getStatus());
        }
        System.out.println("Found " + results.size() + " ticket(s)");
    }
}