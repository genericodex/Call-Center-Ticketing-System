package repository;

import models.TicketModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Repository class that manages the storage and operations of tickets.
 * Implements CRUD (Create, Read, Update, Delete) operations and search functionality.
 */

public class TicketRepository {
    /**
     * List to store ticket objects. ArrayList provides dynamic sizing and indexed access.
     * The diamond operator <> indicates type inference for generics
     */

    private List<TicketModel> tickets = new ArrayList<>();
    private int nextId = 1;

    /**
     * Creates a new ticket and adds it to the repository.
     *
     * @param ticket The TicketModel object to be added
     */

    // Create
    public void createTicket(TicketModel ticket) {
        tickets.add(ticket);
    }

    /**
     * Retrieves all tickets from the repository.
     * Returns a new ArrayList to prevent external modification of the internal list.
     *
     * @return A new List containing all tickets
     */

    // Read
    public List<TicketModel> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    /**
     * Finds and returns a ticket by its ID.
     * Uses Stream API for filtering and finding the matching ticket.
     *
     * @param id The ID of the ticket to find
     * @return The matching TicketModel or null if not found
     */

    public TicketModel getTicketById(int id) {
        return tickets.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }


    /**
     * Updates an existing ticket in the repository.
     * Iterates through the list to find and replace the matching ticket.
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
     * Uses removeIf with a lambda expression for conditional removal.
     *
     * @param id The ID of the ticket to delete
     * @return true if a ticket was found and deleted, false otherwise
     */

    public boolean deleteTicket(int id) {
        return tickets.removeIf(t -> t.getId() == id);
    }

    /**
     * Searches for tickets that match the given predicate.
     * Uses Stream API for filtering and collecting results.
     *
     * @param predicate A functional interface defining the search criteria
     * @return List of tickets matching the predicate
     */

    public List<TicketModel> searchTickets(Predicate<TicketModel> predicate) {
        return tickets.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Generates and returns the next available ticket ID.
     * Uses post-increment operator to return the current value and then increment.
     *
     * @return The next available ID
     */

    public int getNextId() {
        return nextId++;
    }
}