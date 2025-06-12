package repository;

import models.TicketModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class that manages the storage and operations of tickets.
 * Implements CRUD operations and basic search functionality.
 * This class serves as the data access layer for the ticket management system.
 */
public class TicketRepository {

    /**
     * In-memory storage for tickets using ArrayList.
     * Provides dynamic sizing and indexed access to ticket objects.
     */
    private List<TicketModel> tickets;
    private int nextId;


    /**
     * Constructs a new TicketRepository.
     * Initializes an empty ticket list and sets the initial ID counter.
     */
    public TicketRepository() {
        this.tickets = new ArrayList<>();
        this.nextId = 1;
    }

    /**
     * Creates a new ticket in the repository.
     * Adds the ticket to the internal list of tickets.
     *
     * @param ticket The TicketModel object to be stored
     */
    public void createTicket(TicketModel ticket) {
        tickets.add(ticket);
    }

    /**
     * Retrieves all tickets from the repository.
     * Returns a new ArrayList to prevent external modification of internal list.
     *
     * @return A new List containing copies of all stored tickets
     */
    public List<TicketModel> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    /**
     * Retrieves a specific ticket by its ID.
     * Performs a sequential search through the tickets list.
     *
     * @param id The ID of the ticket to find
     * @return The matching TicketModel or null if not found
     */
    public TicketModel getTicketById(int id) {
        for (TicketModel ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * Updates an existing ticket in the repository.
     * Replaces the old ticket with the updated version if found.
     *
     * @param updatedTicket The ticket with updated information
     */
    public void updateTicket(TicketModel updatedTicket) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId() == updatedTicket.getId()) {
                tickets.set(i, updatedTicket);
                break;
            }
        }
    }

    /**
     * Deletes a ticket from the repository by its ID.
     * Removes the first ticket found with the matching ID.
     *
     * @param id The ID of the ticket to delete
     * @return true if ticket was found and deleted, false otherwise
     */
    public boolean deleteTicket(int id) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId() == id) {
                tickets.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Performs a multi-criteria search on tickets.
     * Matches tickets against all non-null and non-empty criteria.
     * Uses case-insensitive comparison for all string matches.
     *
     * @param customerName The customer name to match (can be null or empty)
     * @param category The category to match (can be null or empty)
     * @param status The status to match (can be null or empty)
     * @param priority The priority to match (can be null or empty)
     * @return List of tickets matching all specified criteria
     */
    public List<TicketModel> searchByCriteria(String customerName, String category,
                                              String status, String priority) {
        List<TicketModel> results = new ArrayList<>();
        for (TicketModel ticket : tickets) {
            boolean matches = true;

            if (customerName != null && !customerName.isEmpty()) {
                matches = matches && ticket.getCustomerName()
                        .equalsIgnoreCase(customerName);
            }
            if (category != null && !category.isEmpty()) {
                matches = matches && ticket.getCategory()
                        .equalsIgnoreCase(category);
            }
            if (status != null && !status.isEmpty()) {
                matches = matches && ticket.getStatus()
                        .equalsIgnoreCase(status);
            }
            if (priority != null && !priority.isEmpty()) {
                matches = matches && ticket.getPriority()
                        .equalsIgnoreCase(priority);
            }

            if (matches) {
                results.add(ticket);
            }
        }
        return results;
    }

    /**
     * Generates and returns the next available ticket ID.
     * Uses post-increment to ensure unique IDs.
     *
     * @return The next available ticket ID
     */
    public int getNextId() {
        return nextId++;
    }
}